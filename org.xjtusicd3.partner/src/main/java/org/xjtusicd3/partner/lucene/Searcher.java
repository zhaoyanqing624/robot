package org.xjtusicd3.partner.lucene;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.StringReader;
import java.nio.file.Paths;

public class Searcher {

    public static void search(String indexDir,String q)throws Exception{
        Directory dir=FSDirectory.open(Paths.get(indexDir));
        IndexReader reader=DirectoryReader.open(dir);
        IndexSearcher is=new IndexSearcher(reader);
        // Analyzer analyzer=new StandardAnalyzer(); // 标准分词器
        SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
        QueryParser parser=new QueryParser("questionDescription", analyzer);
        Query query=parser.parse(q);
        long start=System.currentTimeMillis();
        TopDocs hits=is.search(query, 10);
        long end=System.currentTimeMillis();
        System.out.println("匹配 "+q+" ，总共花费"+(end-start)+"毫秒"+"查询到"+hits.totalHits+"个记录");

        QueryScorer scorer=new QueryScorer(query);
        Fragmenter fragmenter=new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
        Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);
        for(ScoreDoc scoreDoc:hits.scoreDocs){
            Document doc=is.doc(scoreDoc.doc);
            System.out.println(doc.get("questionTitle"));
            System.out.println(doc.get("questionDescription"));
            String desc=doc.get("questionDescription");
            if(desc!=null){
                TokenStream tokenStream=analyzer.tokenStream("questionDescription", new StringReader(desc));
                System.out.println(highlighter.getBestFragment(tokenStream, desc));
            }
        }
        reader.close();
    }

    public static void main(String[] args) {
        String indexDir="E:\\Lucene";
        String q="开始屏幕";
        try {
            search(indexDir,q);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

