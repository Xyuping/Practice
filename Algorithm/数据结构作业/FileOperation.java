package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
* @ClassName: FileOperation
* @Description: TODO 文件保存、打开
 * @author xyp
 * @date 2018年4月8日 下午9:17:00
 *
 */

public class FileOperation {
	/**
	 * 
	* @Title: fileSave 
	* @Description: TODO 保存文件
	* @param @param file
	* @param @param al
	* @param @throws IOException  
	* @return void   
	* @throws
	 */
	public static void fileSave(File file,ArrayList<ArrayList<String>> al) throws IOException {
		BufferedWriter fw = new BufferedWriter(new FileWriter(file));
		for(int i = 0; i<al.size();i++) {
			for(int j = 0;j<al.get(i).size();j++) {
				fw.write(al.get(i).get(j));
				fw.newLine();
				fw.flush();
			}
			fw.write(";");// ；表示换行
			fw.newLine();
		}
		fw.close();
	}
	/**
	 * 
	* @Title: fileSave 
	* @Description: TODO 文件保存
	* @param @param path 文件路径
	* @param @param al
	* @param @throws IOException  
	* @return void   
	* @throws
	 */
	public static void fileSave(String path,ArrayList<ArrayList<String>> al) throws IOException {
		File file = new File(path);
		BufferedWriter fw = new BufferedWriter(new FileWriter(file));
		for(int i = 0; i<al.size();i++) {
			for(int j = 0;j<al.get(i).size();j++) {
					fw.write(al.get(i).get(j));
				fw.newLine();
				fw.flush();
			}
			fw.write(";");// ；表示换行
			fw.newLine();
		}
		fw.close();
	}
	
	/**
	 * 
	* @Title: fileOpen 
	* @Description: TODO 文件打开
	* @param @param file
	* @param @return
	* @param @throws IOException  
	* @return ArrayList<ArrayList<String>>   
	* @throws
	 */
	public static ArrayList<ArrayList<String>> fileOpen(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>> ();		
		String s ;	
		int i = 0;
		al.add(new ArrayList<String>());	
		while((s=br.readLine())!=null) {
			if(s.equals(";")) {
				al.add(new ArrayList<String>());
				i++;
			}
			else al.get(i).add(s);	
		}
		br.close();
		al.remove(i);
		return al;
	}
	
	/**
	 * 
	* @Title: fileOpen 
	* @Description: TODO 文件打开
	* @param @param path 文件路径
	* @param @return
	* @param @throws IOException  
	* @return ArrayList<ArrayList<String>>   
	* @throws
	 */
	public static ArrayList<ArrayList<String>> fileOpen(String path) throws IOException {
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>> ();		
		String s ;	
		int i = 0;
		al.add(new ArrayList<String>());	
		while((s=br.readLine())!=null) {
			if(s.equals(";")) {
				al.add(new ArrayList<String>());
				i++;
			}
			else al.get(i).add(s);	
		}
		br.close();
		al.remove(i);
		return al;
	}
}
