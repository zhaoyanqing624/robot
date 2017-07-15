package org.xjtusicd3.partner.NLP;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


import org.xjtusicd3.database.model.NLP_Word;

public class TextSimilarity {
	//余弦相似度
	public static double similarScore(List<NLP_Word> words1,List<NLP_Word> words2){
		if (words1==null || words2==null) {
			//只要有一个为空，规定相似度为0，表示完全不相同
			return 0.0;
		}
        if(words1.isEmpty() && words2.isEmpty()){
            //如果两个文本都为空，规定相似度分值为1，表示完全相等
            return 1.0;
        }
        if(words1.isEmpty() || words2.isEmpty()){
            //如果一个文本为空，另一个不为空，规定相似度分值为0，表示完全不相等
            return 0.0;
        }
        	double score = scoreImpl(words1, words2);
		return score;
	}
	public static double scoreImpl(List<NLP_Word> words1,List<NLP_Word> words2){
		//用词频来标注词的权重
		Weight(words1, words2);
        //构造权重快速搜索容器
        Map<String, Float> weights1 = toFastSearchMap(words1);
        Map<String, Float> weights2 = toFastSearchMap(words2);
        //所有的不重复词
        Set<NLP_Word> words = new HashSet<>();
        words.addAll(words1);
        words.addAll(words2);
        //向量的维度为words的大小，每一个维度的权重是词频
        //a.b
        AtomicFloat ab = new AtomicFloat();
        //|a|的平方
        AtomicFloat aa = new AtomicFloat();
        //|b|的平方
        AtomicFloat bb = new AtomicFloat();
        //计算
        words.parallelStream().forEach(word -> {
            Float x1 = weights1.get(word.getText());
            Float x2 = weights2.get(word.getText());
            if (x1 != null && x2 != null) {
                //x1x2
                float oneOfTheDimension = x1 * x2;
                //+
                ab.addAndGet(oneOfTheDimension);
            }
            if (x1 != null) {
                //(x1)^2
                float oneOfTheDimension = x1 * x1;
                //+
                aa.addAndGet(oneOfTheDimension);
            }
            if (x2 != null) {
                //(x2)^2
                float oneOfTheDimension = x2 * x2;
                //+
                bb.addAndGet(oneOfTheDimension);
            }
        });
	    //|a|
	    double aaa = Math.sqrt(aa.doubleValue());
	    //|b|
	    double bbb = Math.sqrt(bb.doubleValue());
        //使用BigDecimal保证精确计算浮点数
        //|a|*|b|
        //double aabb = aaa * bbb;
        BigDecimal aabb = BigDecimal.valueOf(aaa).multiply(BigDecimal.valueOf(bbb));
        //similarity=a.b/|a|*|b|
        //double cos = ab.get() / aabb.doubleValue();
        double cos = BigDecimal.valueOf(ab.get()).divide(aabb, 9, BigDecimal.ROUND_HALF_UP).doubleValue();
        return cos;
	}
	/*
	 * 构造权重快速搜索容器
	 */
    protected static Map<String, Float> toFastSearchMap(List<NLP_Word> words){
        Map<String, Float> weights = new ConcurrentHashMap<>();
        if(words == null){
            return weights;
        }
        words.parallelStream().forEach(word -> {
            if(word.getWeight() != null) {
                weights.put(word.getText(), word.getWeight());
            }else{
                System.out.println("词没有权重信息："+word.getText());
            }
        });
        return weights;
    }
	/*
	 * 计算权重
	 */
	public static void Weight(List<NLP_Word> words1,List<NLP_Word> words2){
		//词频统计标注
		Map<String, AtomicInteger> frequency1 = frequency(words1);
        Map<String, AtomicInteger> frequency2 = frequency(words2);
        //权重标注
        words1.parallelStream().forEach(word->{
            word.setWeight(frequency1.get(word.getText()).floatValue());
        });
        words2.parallelStream().forEach(word->{
            word.setWeight(frequency2.get(word.getText()).floatValue());
        });
	}
	/*
	 * 统计词频
	 */
    public static Map<String, AtomicInteger> frequency(List<NLP_Word> words){
        Map<String, AtomicInteger> frequency =new HashMap<>();
        words.forEach(word->{
            frequency.computeIfAbsent(word.getText(), k -> new AtomicInteger()).incrementAndGet();
        });
        return frequency;
    }
}
