package service;
import java.math.BigDecimal;
import java.util.ArrayList;
public class Sort {
	/**
	 * 
	* @Title: quickRow 
	* @Description: TODO 快速排序，一行
	* @param   row 行号
	* @return void   
	* @throws
	 */
	public static void quickRow(ArrayList<ArrayList<String>> arr,int row,int n) {
		ArrayList<BigDecimal> al = MyArrayList.fullArrayList(arr.get(row));
		//升序
		if(n==0) {
			quickSort(al);
		}
		//降序
		if(n==1) {
			quickSortDown(al);
		}
		for(int i = 1,j=0;i<arr.get(row).size();i++) {
			if(!arr.get(row).get(i).isEmpty()) arr.get(row).set(i,al.get(j++).toString());
		}
	}
	/**
	 * 
	* @Title: quickLine 
	* @Description: TODO 快速排序，列
	* @param   
	* @return void   
	* @throws
	 */
	public static void quickLine(ArrayList<ArrayList<String>> arr,int line,int n) {
		ArrayList<String> a = new ArrayList<String>();
		for(int i= 0;i<arr.size();i++){
			a.add(arr.get(i).get(line));
		}
		ArrayList<BigDecimal> al = MyArrayList.fullArrayListL(a);
		
		//升序
		if(n==0) {
			quickSort(al);
		}
		//降序
		if(n==1) {
			quickSortDown(al);
		}
		for(int i = 0,j=0;i<arr.size();i++) {
			if(!arr.get(i).get(line).isEmpty()) arr.get(i).set(line,al.get(j++).toString());
		}
		
	}
	
	/**
	 * 
	* @Title:  bubbletRow 
	* @Description: TODO 冒泡排序，n=0升序，n=1降序，行
	* @param   
	* @return void   
	* @throws
	 */
	public static void bubbleRow(ArrayList<ArrayList<String>> arr,int row, int n) {
		int flag = 0;
			for(int i = 0;i<arr.get(row).size();i++) {
				for(int j = 1;j<arr.get(row).size()-i-1;j++) {
					while(arr.get(row).get(j).isEmpty()) {
						j++;
						if(j>=arr.get(row).size()-i) break;
					}
					if(j>=arr.get(row).size()-i) break;
					int k = j+1;
					while(arr.get(row).get(k).isEmpty()) {
						k++;
						if(k>=arr.get(row).size()-i) break;
					}
					if(k>=arr.get(row).size()-i) break;
					if(new BigDecimal(arr.get(row).get(j)).compareTo(new BigDecimal(arr.get(row).get(k)))==1) {
						if(n==0) {
							swap(arr,row,j,row,k);
							flag=1;
						}
						j=k-1;
					}
					else if(new BigDecimal(arr.get(row).get(j)).compareTo(new BigDecimal(arr.get(row).get(k)))==-1) {
						if(n==1) {
							swap(arr,row,j,row,k);
							flag=1;
						}
						j=k-1;
					}
				}
				if(flag==0) break;//这一趟没有发生交换
				flag = 0;
			}
	}
	/**
	 * 
	* @Title: bubbleLine 
	* @Description: TODO 冒泡排序，列
	* @param   
	* @return void   
	* @throws
	 */
	public static void bubbleLine(ArrayList<ArrayList<String>> arr,int line,int n) {
		int flag = 0;
		for(int i = 0;i<arr.size();i++) {
			for(int j = 0;j<arr.size()-i-1;j++) {
				while(arr.get(j).get(line).isEmpty()) {
					j++;
					if(j>=arr.size()-i) break;
				}
				if(j>=arr.size()-i) break;
				int k = j+1;
				while(arr.get(k).get(line).isEmpty()) {
					k++;
					if(k>=arr.size()-i) break;
				}
				if(k>=arr.size()-i) break;
				if(new BigDecimal(arr.get(j).get(line)).compareTo(new BigDecimal(arr.get(k).get(line)))==1) {
					if(n==0) {
						swap(arr,j,line,k,line);
						flag=1;
					}
					j=k-1;
				}
				else if(new BigDecimal(arr.get(j).get(line)).compareTo(new BigDecimal(arr.get(k).get(line)))==-1) {
					if(n==1) {
						swap(arr,j,line,k,line);
						flag=1;
					}
					j=k-1;
				}
			}
			if(flag==0) break;//这一趟没有发生交换
			flag = 0;
		}
	}
	/**
	 * 
	* @Title: swap 
	* @Description: TODO 交换
	* @param @param arr
	* @param @param row1
	* @param @param line1
	* @param @param row2
	* @param @param line2  
	* @return void   
	* @throws
	 */
	public static void swap(ArrayList<ArrayList<String>> arr,int row1,int line1,int row2,int line2) {
		String s = arr.get(row1).get(line1);
		arr.get(row1).set(line1, arr.get(row2).get(line2));
		arr.get(row2).set(line2, s);
	}
	
	/**
	 * 
	* @Title: quickSort 
	* @Description: TODO 快速排序，升序
	* @param @param arr  
	* @return void   
	* @throws
	 */
	public static void quickSort(ArrayList<BigDecimal> arr) {  
        if(arr.size()>0) {  
            quickSort(arr, 0 , arr.size()-1);  
        }  
    }  
    private static void quickSort(ArrayList<BigDecimal> arr, int low, int high) {  
        if( low > high) {  
            return;  
        }  
        int i = low;  
        int j = high;  
        BigDecimal key = arr.get(low);  
        //一趟排序  
        while( i< j) {  
            //从右往左找到第一个小于key的数  
            while(i<j && arr.get(j).compareTo(key)==1 ){  
                j--;  
            }  
            // 从左往右找到第一个大于key的数  <= key
            while( i<j &&( arr.get(i).compareTo(key)==0||arr.get(i).compareTo(key)==-1)) {  
                i++;  
            }  
            //交换  
            if(i<j) {  
                BigDecimal p = arr.get(i);  
                arr.set(i, arr.get(j)) ;  
                arr.set(j,p);  
            }  
        }  
        // 调整key的位置  
        BigDecimal p = arr.get(i);  
        arr.set(i,arr.get(low)); 
        arr.set(low,p);  
        // 对key左边进行快速排序  
        quickSort(arr, low, i-1 );  
        //对key右边进行快速排序  
        quickSort(arr, i+1, high);  
    }  
    /**
     * 
    * @Title: quickSortDown 
    * @Description: TODO 快速排序，降序
    * @param @param arr  
    * @return void   
    * @throws
     */
	public static void quickSortDown(ArrayList<BigDecimal> arr) {  
        if(arr.size()>0) {  
            quickSortDown(arr, 0 , arr.size()-1);  
        }  
    }  
    private static void quickSortDown(ArrayList<BigDecimal> arr, int low, int high) {  
        if( low > high) {  
            return;  
        }  
        int i = low;  
        int j = high;  
        BigDecimal key = arr.get(low);  
        while( i< j) {  
            //从右往左找到第一个大于key的数  
            while(i<j && arr.get(j).compareTo(key)==-1 ){  
                j--;  
            }  
            //从左往右找到第一个小于key的数 
            while( i<j &&( arr.get(i).compareTo(key)==0||arr.get(i).compareTo(key)==1)) {  
                i++;  
            }  
            if(i<j) {  
                BigDecimal p = arr.get(i);  
                arr.set(i, arr.get(j)) ;  
                arr.set(j,p);  
            }  
        }  
        BigDecimal p = arr.get(i);  
        arr.set(i,arr.get(low)); 
        arr.set(low,p);  
        quickSortDown(arr, low, i-1 );  
        quickSortDown(arr, i+1, high);  
    }  
}