package service;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Search {

	/**
	 * 
	 * @Title: binarySearchRow 
	 * @Description: 二分法查找给定值所在的行号 , 避开空字符
	 * @param @param sorted_array 排好序的ArrayList<ArrayList<Integer>> 列排好序 
	 * @param @param n 第n+1列 
	 * @param @param key k值 
	 * @param @return  
	 * @return int 给定值所在的行号
	 * @throws
	 */
	public static int binarySearchLine(ArrayList<ArrayList<String>> sorted_array, int n, String key) {
		BigDecimal k = new BigDecimal(key);
		int l = 0;
		int r = sorted_array.size() - 1;// 行数
		// 防止起始位置是空字符
		while (sorted_array.get(l).get(n) .isEmpty())
			l++;
		while (sorted_array.get(r).get(n) .isEmpty())
			r--;

		while (l <= r) {
			int m = (r + l) / 2;
			// 遇到空字符
			while (sorted_array.get(m).get(n) .isEmpty())
				m--;
			if (k.compareTo(new BigDecimal(sorted_array.get(m).get(n))) == 0) {
				return m;
			}
			// k小于
			else if (k.compareTo(new BigDecimal(sorted_array.get(m).get(n))) == -1) {
				r = m - 1;
				if(r<0) return -1;
				while (sorted_array.get(r).get(n) .isEmpty()) {
					r--;
					if(r<0) return -1;
				}
			}
			// k大于
			else if (k.compareTo(new BigDecimal(sorted_array.get(m).get(n))) == 1) {
				l = m + 1;
				if(l==sorted_array.size()) return -1;
				while (sorted_array.get(l).get(n) .isEmpty()) {
					l++;
					if(l==sorted_array.size()) return -1;
				}
			}

		}
		return -1;
	}
	
	public static int binarySearchLine2(ArrayList<String> sorted_array, int n, String key) {
		BigDecimal k = new BigDecimal(key);
		int l = 0;
		int r = sorted_array.size() - 1;// 行数
		// 防止起始位置是空字符
		while (sorted_array.get(l) .isEmpty())
			l++;
		while (sorted_array.get(r) .isEmpty())
			r--;

		while (l <= r) {
			int m = (r + l) / 2;
			// 遇到空字符
			while (sorted_array.get(m) .isEmpty())
				m--;
			if (k.compareTo(new BigDecimal(sorted_array.get(m))) == 0) {
				return m;
			}
			// k小于
			else if (k.compareTo(new BigDecimal(sorted_array.get(m))) == -1) {
				r = m - 1;
				if(r<0) return -1;
				while (sorted_array.get(r) .isEmpty()) {
					r--;
					if(r<0) return -1;
				}
			}
			// k大于
			else if (k.compareTo(new BigDecimal(sorted_array.get(m))) == 1) {
				l = m + 1;
				if(l==sorted_array.size()) return -1;
				while (sorted_array.get(l) .isEmpty()) {
					l++;
					if(l==sorted_array.size()) return -1;
				}
			}

		}
		return -1;
	}

	/**
	 * 
	 * @Title: binarySearchLine 
	 * @Description: TODO 二分查找给定值所在列号 
	 * @param @param sorted_array 行已经排好序 
	 * @param @param n 列号 
	 * @param @param key 
	 * @param @return 
	 * @return int 
	 * @throws
	 */
	public static int binarySearchRow(ArrayList<ArrayList<String>> sorted_array, int row, String key) {
		BigDecimal k = new BigDecimal(key);
		int l = 1;
		int r = sorted_array.get(row).size() - 1;// 列数
		// 防止起始位置是空字符，r、l置初值
		while (sorted_array.get(row).get(l) .isEmpty())
			l++;
		while (sorted_array.get(row).get(r) .isEmpty())
			r--;
		// 查找
		while (l <= r) {
			int m = (r + l) / 2;
			while (sorted_array.get(row).get(m) .isEmpty())
				m--;
			if (k.compareTo(new BigDecimal(sorted_array.get(row).get(m))) == 0)
				return m;
			// k小于
			else if (k.compareTo(new BigDecimal(sorted_array.get(row).get(m))) == -1) {
				r = m - 1;
				while (sorted_array.get(row).get(r) .isEmpty()) {
					r--;
					if (r < l)
						break;
				}
			}
			// k大于
			else if (k.compareTo(new BigDecimal(sorted_array.get(row).get(m))) == 1) {
				l = m + 1;
				while (sorted_array.get(row).get(l) .isEmpty()) {
					l++;
					if (r < l)
						break;
				}
			}

		}
		return -1;
	}
}
