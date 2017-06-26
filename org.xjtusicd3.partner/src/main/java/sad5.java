import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class sad5 {
	final static Logger logger = LoggerFactory.getLogger(sad5.class);
	
	public static void main(String[] args) {

        
        //打印当前的日志信息有效级别
        //测试日志级别的作用，仅仅输出大于等于目前有效级别的日志信息
        logger.trace("my level is TRACE");
        logger.debug("my level is DEBUG");
        logger.info(" my level is INFO");
        logger.warn(" my level is WARN");
        logger.error("my level is ERROR");
	}
	
	public static void testzhao(int x){
		logger.info("猪朵的测试：{}", x);
		System.out.println("wowowo");
	}
}
