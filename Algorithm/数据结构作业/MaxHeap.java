package service;

import java.math.BigDecimal;
import java.util.ArrayList;

 /**
  * 
 * @ClassName: MaxHeap
 * @Description: TODO 大顶堆
  * @author xyp
  * @date 2018年3月15日 下午11:34:16
  *
  */
public class MaxHeap {
    
	/**
	 * 
	* @Title: fixDown 
	* @Description: TODO 向下调整，顶端的小值往下调，主要用于删除和建堆
	* @param @param data
	* @param @param i 表示要调整的节点索引,删除时候，i是0，建堆时候i从最后一个节点的父节点依次往前调整
	* @param @param n 表示堆的最后一个元素索引
	* @return void   
	* @throws
	 */
    public static void fixDown(ArrayList<BigDecimal> data, int i, int n) {
        BigDecimal num = data.get(i);
        int son = i * 2 + 1;
        while (son <= n) {
            if (son + 1 <= n && data.get(son+1) .compareTo(data.get(son))==1)
                son++;
            if (num .compareTo(data.get(son))==1)
                break;
            data.set(i, data.get(son));
            i = son;
            son = i * 2 + 1;
        }
        data.set(i,num) ;
    }
 
   
    /**
     * 
    * @Title: fixUp 
    * @Description: TODO 向上调整，大值往上走,用于添加
    * @param @param data
    * @param @param n  表示堆的最后一个元素的索引
    * @return void   
    * @throws
     */
    public static void fixUp(ArrayList<BigDecimal> data, int n) {
        BigDecimal num = data.get(n);
        int father = (n - 1) / 2;
        // 当n等于0时，father=0；进入死循环，所以当n==0时，需要跳出循环
        while (data.get(father).compareTo(num)==-1 && n != 0) {
            data.set(n, data.get(father));
            n = father;
            father = (n - 1) / 2;
        }
        data.set(n, num);//data[n] = num;
    }
 
    // 删除最顶端,n表示堆的最后一个元素的索引
    public static void delete(ArrayList<BigDecimal> data, int n) {
    		data.set(0, data.get(n));
    		data.remove(n);
        fixDown(data, 0, n - 1);
    }
 
    // 增加,num表示要增加的数字，n表示增加位置的索引，是堆的最后一个元素
    public static void insert(ArrayList<BigDecimal> data, String num, int n) {
        data.add(n,new BigDecimal(num));
        fixUp(data, n);
    }
 
    // 建堆,n表示要建堆的最后一个元素的索引
    public static void creat(ArrayList<BigDecimal> data, int n) {
        for (int i = (n - 1) / 2; i >= 0; i--)
            fixDown(data, i, n);
    }
 
    public static void main(String[] args) {
        //data = { 15.1, 13.5, 1.3, 5, 20, 12, 8, 9, 11 };
    		ArrayList<BigDecimal> arr = new ArrayList<BigDecimal>();
    		arr.add(new BigDecimal("15.1"));
    		arr.add(new BigDecimal("13.5"));
    		arr.add(new BigDecimal("1.3"));
    		arr.add(new BigDecimal("5"));
    		arr.add(new BigDecimal("20"));
    		arr.add(new BigDecimal("12"));
    		arr.add(new BigDecimal("8"));
    		arr.add(new BigDecimal("9"));
    		arr.add(new BigDecimal("11"));
        // 测试建堆
        creat(arr, arr.size() - 1);
        System.out.println(arr);
        // 测试删除
        delete(arr, arr.size() - 1);
        System.out.println(arr);
       // 测试插入
        insert(arr, "3", arr.size() );
        System.out.println(arr);
    }
}