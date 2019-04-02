package service;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MyArrayList {
	public static ArrayList<BigDecimal> fullArrayList(ArrayList<String> al){
		ArrayList<BigDecimal> new_al = new ArrayList<BigDecimal>();
		String s;
		for(int i = 1;i<al.size();i++) {
			s = al.get(i);
			if(!s.isEmpty()) new_al.add(new BigDecimal(s));
		}
		return new_al;
	}
	
	public static ArrayList<BigDecimal> fullArrayListL(ArrayList<String> al){
		ArrayList<BigDecimal> new_al = new ArrayList<BigDecimal>();
		String s;
		for(int i = 0;i<al.size();i++) {
			s = al.get(i);
			if(!s.isEmpty()) new_al.add(new BigDecimal(s));
		}
		return new_al;
	}
	
	public static ArrayList<String> stringArrayList(ArrayList<String> al){
		ArrayList<String> new_al = new ArrayList<String>();
		String s;
		for(int i = 1;i<al.size();i++) {
			s = al.get(i);
			if(!s.isEmpty()) new_al.add(s);
		}
		return new_al;
	}
	public static ArrayList<String> stringArrayListL(ArrayList<String> al){
		ArrayList<String> new_al = new ArrayList<String>();
		String s;
		for(int i = 0;i<al.size();i++) {
			s = al.get(i);
			if(!s.isEmpty()) new_al.add(s);
		}
		return new_al;
	}
}
