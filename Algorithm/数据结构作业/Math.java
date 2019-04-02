package service;
import java.math.BigDecimal;
/**
 * 
* @ClassName: Math
* @Description: 加、减、乘、除运算
 * @author xyp
 * @date 2018年3月14日 上午10:47:08
 *
 */
import java.util.ArrayList;
public class Math {
	/**
	 * 
	* @Title: addRow 
	* @Description: 行求和
	* @param @return  row 行号
	* @return String   
	* @throws
	 */
	public static String addRow( ArrayList<ArrayList<String>> arr,int row) {
		BigDecimal count = new BigDecimal("0");
		String i ;
		for(int j = 1;j<arr.get(row).size();j++) {
			i = arr.get(row).get(j);
			if(!i.isEmpty()) {
				count = count.add(new BigDecimal(i));
				}	
		}
		
		return count.toString();
		
	}
	
	
	/**
	 * 
	* @Title: addLine 
	* @Description: 列求和
	* @param @param arr
	* @param @param line 列号
	* @param @return  
	* @return String   
	* @throws
	 */
	public static String addLine( ArrayList<ArrayList<String>> arr,int line) {
		BigDecimal count = new BigDecimal("0");
		for(ArrayList<String> a:arr) {
			if(!a.get(line).isEmpty()) 
				count = count.add(new BigDecimal(a.get(line)));
		}
		return count.toString();	
	}
	/**
	 * 
	* @Title: add 
	* @Description: TODO 两个数求和
	* @param @param arr
	* @param @param row1
	* @param @param line1
	* @param @param row2
	* @param @param line2
	* @param @return  
	* @return String   
	* @throws
	 */
	public static String add( ArrayList<ArrayList<String>> arr,int row1,int line1,int row2,int line2) {
		if(arr.get(row1).get(line1).isEmpty()) return arr.get(row2).get(line2);
		if(arr.get(row2).get(line2).isEmpty()) return arr.get(row1).get(line1);
		BigDecimal a1 = new BigDecimal(arr.get(row1).get(line1));
		BigDecimal a2 = new BigDecimal(arr.get(row2).get(line2));
		return a1.add(a2).toString();	
	}
	
	/**
	 * 
	* @Title: mulRow 
	* @Description: 行求积
	* @param @param arr
	* @param @param row 行号
	* @param @return  
	* @return String   
	* @throws
	 */
	
	public static String mulRow( ArrayList<ArrayList<String>> arr,int row) {
		BigDecimal count = new BigDecimal("1");
		
		for(int j = 1;j<arr.get(row).size();j++) {
			String i = arr.get(row).get(j);
			if(i=="0") return "0";
			if(!i.isEmpty()) {
				count = count.multiply(new BigDecimal(i));
				}	
		}	
		return count.toString();
	}
	
	/**
	 * 
	* @Title: mulLine 
	* @Description: 列求积
	* @param @param arr
	* @param @param line 列号
	* @param @return  
	* @return String   
	* @throws
	 */
	public static String mulLine( ArrayList<ArrayList<String>> arr,int line) {
		BigDecimal count = new BigDecimal("1");
		for(ArrayList<String> a:arr) {
			if(a.get(line)=="0") return "0";
			if(!a.get(line).isEmpty()) 
				count = count.multiply(new BigDecimal(a.get(line)));
		}
		return count.toString();	
	}
	
	
	/**
	 * 
	* @Title: mul 
	* @Description: TODO 两个数求积
	* @param @param arr
	* @param @param row1
	* @param @param line1
	* @param @param row2
	* @param @param line2
	* @param @return  
	* @return String   
	* @throws
	 */
	public static String mul( ArrayList<ArrayList<String>> arr,int row1,int line1,int row2,int line2) {
		if(arr.get(row1).get(line1).isEmpty()) return "选择内容非法";
		if(arr.get(row2).get(line2).isEmpty()) return "选择内容非法";
		BigDecimal a1 = new BigDecimal(arr.get(row1).get(line1));
		BigDecimal a2 = new BigDecimal(arr.get(row2).get(line2));
		return a1.multiply(a2).toString();	
	}
	/**
	 * 
	* @Title: divide 
	* @Description: TODO 除法，保留三位小数，四舍五入
	* @param @param arr
	* @param @param row1
	* @param @param line1
	* @param @param row2
	* @param @param line2
	* @param @return  
	* @return String   
	* @throws
	 */
	public static String divide( ArrayList<ArrayList<String>> arr,int row1,int line1,int row2,int line2) {
		if(arr.get(row1).get(line1).isEmpty()) return "选择内容非法";
		if(arr.get(row2).get(line2).isEmpty()) return "选择内容非法";
		BigDecimal a1 = new BigDecimal(arr.get(row1).get(line1));
		BigDecimal a2 = new BigDecimal(arr.get(row2).get(line2));
		return a1.divide(a2,3,BigDecimal.ROUND_HALF_UP).toString();	
	}
}
