import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testzhao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date nowdate = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String t = sdf.format(nowdate);
		System.out.println(t);
	}

}
