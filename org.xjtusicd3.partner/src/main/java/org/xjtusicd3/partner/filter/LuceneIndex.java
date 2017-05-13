//package org.xjtusicd3.partner.filter;
//
//import org.apache.lucene.analysis.TokenStream;
//import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.*;
//import org.apache.lucene.queryparser.classic.QueryParser;
//import org.apache.lucene.search.*;
//import org.apache.lucene.search.highlight.*;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.xjtusicd3.database.model.UserPersistence;
//
//import java.io.StringReader;
//import java.nio.file.Paths;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * 博客索引类
// * @author Administrator
// *
// */
//public class LuceneIndex {
//
//    private Directory dir=null;
//
//    /**
//     * 获取IndexWriter实例
//     * @return
//     * @throws Exception
//     */
//    private IndexWriter getWriter()throws Exception{
//        /**
//         * 生成的索引我放在了C盘，可以根据自己的需要放在具体位置
//         */
//        dir= FSDirectory.open(Paths.get("C://lucene"));
//        SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
//        IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
//        IndexWriter writer=new IndexWriter(dir, iwc);
//        return writer;
//    }
//
//    /**
//     * 添加博客索引
//     * @param user
//     */
//    public void addIndex(UserPersistence userPersistence)throws Exception{
//        IndexWriter writer=getWriter();
//        Document doc=new Document();
//        doc.add(new StringField("id",String.valueOf(userPersistence.getUSERID()), Field.Store.YES));
//        /**
//         * yes是会将数据存进索引，如果查询结果中需要将记录显示出来就要存进去，如果查询结果
//         * 只是显示标题之类的就可以不用存，而且内容过长不建议存进去
//         * 使用TextField类是可以用于查询的。
//         */
//        doc.add(new TextField("username", userPersistence.getUSERNAME(), Field.Store.YES));
//        doc.add(new TextField("description",userPersistence.getUSERSIGNATURE(), Field.Store.YES));
//        writer.addDocument(doc);
//        writer.close();
//    }
//
//    /**
//     * 更新博客索引
//     * @param user
//     * @throws Exception
//     */
//    public void updateIndex(UserPersistence userPersistence)throws Exception{
//        IndexWriter writer=getWriter();
//        Document doc=new Document();
//        doc.add(new StringField("id",String.valueOf(userPersistence.getUSERID()), Field.Store.YES));
//        doc.add(new TextField("username", userPersistence.getUSERNAME(), Field.Store.YES));
//        doc.add(new TextField("description",userPersistence.getUSERSIGNATURE(), Field.Store.YES));
//        writer.updateDocument(new Term("id", String.valueOf(userPersistence.getUSERID())), doc);
//        writer.close();
//    }
//
//    /**
//     * 删除指定博客的索引
//     * @param userId
//     * @throws Exception
//     */
//    public void deleteIndex(String userId)throws Exception{
//        IndexWriter writer=getWriter();
//        writer.deleteDocuments(new Term("id", userId));
//        writer.forceMergeDeletes(); // 强制删除
//        writer.commit();
//        writer.close();
//    }
//
//    /**
//     * 查询用户
//     * @param q 查询关键字
//     * @return
//     * @throws Exception
//     */
//    public List<UserPersistence> searchBlog(String q)throws Exception{
//        /**
//         * 注意的是查询索引的位置得是存放索引的位置，不然会找不到。
//         */
//        dir= FSDirectory.open(Paths.get("C://lucene"));
//        IndexReader reader = DirectoryReader.open(dir);
//        IndexSearcher is=new IndexSearcher(reader);
//        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
//        SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
//        /**
//         * username和description就是我们需要进行查找的两个字段
//         * 同时在存放索引的时候要使用TextField类进行存放。
//         */
//        QueryParser parser=new QueryParser("username",analyzer);
//        Query query=parser.parse(q);
//        QueryParser parser2=new QueryParser("description",analyzer);
//        Query query2=parser2.parse(q);
//        booleanQuery.add(query, BooleanClause.Occur.SHOULD);
//        booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
//        TopDocs hits=is.search(booleanQuery.build(), 100);
//        QueryScorer scorer=new QueryScorer(query);
//        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
//        /**
//         * 这里可以根据自己的需要来自定义查找关键字高亮时的样式。
//         */
//        SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
//        Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);
//        highlighter.setTextFragmenter(fragmenter);
//        List<UserPersistence> userList=new LinkedList<UserPersistence>();
//        for(ScoreDoc scoreDoc:hits.scoreDocs){
//            Document doc=is.doc(scoreDoc.doc);
//            UserPersistence user=new UserPersistence();
//            user.setUSERID(doc.get(("id")));
//            user.setUSERSIGNATURE(doc.get(("description")));
//            String username=doc.get("username");
//            String description=doc.get("description");
//            if(username!=null){
//                TokenStream tokenStream = analyzer.tokenStream("username", new StringReader(username));
//                String husername=highlighter.getBestFragment(tokenStream, username);
//                if(StringUtil.isEmpty(husername)){
//                    user.setUsername(username);
//                }else{
//                    user.setUsername(husername);
//                }
//            }
//            if(description!=null){
//                TokenStream tokenStream = analyzer.tokenStream("description", new StringReader(description));
//                String hContent=highlighter.getBestFragment(tokenStream, description);
//                if(StringUtil.isEmpty(hContent)){
//                    if(description.length()<=200){
//                        user.setDescription(description);
//                    }else{
//                        user.setDescription(description.substring(0, 200));
//                    }
//                }else{
//                    user.setDescription(hContent);
//                }
//            }
//            userList.add(user);
//        }
//        return userList;
//    }
//}
