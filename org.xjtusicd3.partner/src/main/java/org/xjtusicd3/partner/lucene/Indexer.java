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
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.QuestionPersistence;

import java.io.File;
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
		String localurl = System.getProperty("user.dir");
		File file = new File(localurl+"/luence");
		if (file.exists()) {
			deleteDir(file);
		}else{
			file.mkdir();
			new Indexer().index(localurl+"/luence");
		}
    }
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
