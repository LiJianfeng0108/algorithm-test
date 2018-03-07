package cn.ljf.util;

import java.io.File;
import java.util.Scanner;

public class ReadTxt {
	public static int n;
	
	public static double cDouble = 0.00;
	public static double[] wDouble;
	public static double[] vDouble;
	
	public static int cInt = 0;
	public static int[] wInt;
	public static int[] vInt;
	
	
	public void ReadDouble(){
		File classes = new File(getClass().getClassLoader().getResource("").getFile());
		File dir = new File(classes.getParentFile(),"data");
		//dir.mkdirs();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(dir.getPath()+File.separator+"input.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sc.hasNextLine()){
			String line1 = sc.nextLine();
			try{
				n = Integer.parseInt(line1.split(" ")[0]);
				cDouble = Double.parseDouble(line1.split(" ")[1]);
			}catch(Exception e){
				System.out.println("读入数据格式错误");
				return;
			}
			
		}
		String line2 = null;
		String line3 = null;
		if(sc.hasNextLine()){
			line2 = sc.nextLine();
		}
		if(sc.hasNextLine()){
			line3 = sc.nextLine();
		}
		wDouble = new double[n+1];
		vDouble = new double[n+1];
		wDouble[0] = 0.00;
		vDouble[0] = 0.00;
		for(int i=1; i<=n; i++){//读取weight和value
			try{
				wDouble[i] = Double.parseDouble(line2.split(" ")[i-1]);
				vDouble[i] = Double.parseDouble(line3.split(" ")[i-1]);
			}catch(Exception e){
				System.out.println("读入数据格式错误");
				return;
			}
			
		}
		
	}
	public void ReadInt(){
		File classes = new File(getClass().getClassLoader().getResource("").getFile());
		File dir = new File(classes.getParentFile(),"data");
		//dir.mkdirs();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(dir.getPath()+File.separator+"input.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sc.hasNextLine()){
			String line1 = sc.nextLine();
			try{
				n = Integer.parseInt(line1.split(" ")[0]);
				cInt = Integer.parseInt(line1.split(" ")[1]);
			}catch(Exception e){
				System.out.println("读入数据格式错误");
				return;
			}
			
		}
		String line2 = null;
		String line3 = null;
		if(sc.hasNextLine()){
			line2 = sc.nextLine();
		}
		if(sc.hasNextLine()){
			line3 = sc.nextLine();
		}
		wInt = new int[n+1];
		vInt = new int[n+1];
		wInt[0] = 0;
		vInt[0] = 0;
		for(int i=1; i<=n; i++){//读取weight和value
			try{
				wInt[i] = Integer.parseInt(line2.split(" ")[i-1]);
				vInt[i] = Integer.parseInt(line3.split(" ")[i-1]);
			}catch(Exception e){
				System.out.println("读入数据格式错误");
				return;
			}
			
		}
		
	}
}
