package org.xjtusicd3.partner.lucene;


import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.QuestionPersistence;

import java.nio.file.Paths;
import java.util.List;

public class Indexer {
    private Directory dir;

    /**
     * 获取IndexWriter实例
     * @return
     * @throws Exception
     */
    private IndexWriter getWriter()throws Exception{
        //Analyzer analyzer=new StandardAnalyzer(); // 标准分词器
        SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
        IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
        IndexWriter writer=new IndexWriter(dir, iwc);
        return writer;
    }

    /**
     * 生成索引
     * @param indexDir
     * @throws Exception
     */
    public void index(String indexDir)throws Exception{
        dir=FSDirectory.open(Paths.get(indexDir));
        IndexWriter writer=getWriter();
        List<QuestionPersistence> qList = QuestionHelper.getFaqTotal();
        for(int i=0;i<qList.size();i++){
            Document doc=new Document();
            doc.add(new StringField("questionId", qList.get(i).getFAQQUESTIONID()+"", Field.Store.YES));
            doc.add(new TextField("questionTitle",qList.get(i).getFAQTITLE(),Field.Store.YES));
            doc.add(new TextField("questionDescription", qList.get(i).getFAQDESCRIPTION(), Field.Store.YES));
            writer.addDocument(doc); // 添加文档
        }
        writer.close();
    }


    public static void main(String[] args) throws Exception {
        new Indexer().index("E:\\Lucene");
    }
}
