package com.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


public class Test {
	static int s = 0;
	static int a[];
	public static void main(String[] args){//主方法-------------------------------------------------------------
		loadFromTxt();
		double avg = greedy(s);
		System.out.println(avg);
		printToTxt(avg);
	}
	public static double greedy(int s){//贪心算法求最优服务次序，s表示服务点个数--------------------------------------------
		int st[] = new int[s];//每个队列每个顾客的等待时间
		int su[] = new int[s];//每个队列所有顾客的等待时间和
		for(int i=0; i<s; i++){
			st[i] = 0;
			su[i] = 0;
		}
		shellSort();
		/*for(int aa:a){
			System.out.print(aa+" ");
		}
		System.out.println();*/
		int i = 0;//第几个客户
		int j = 0;//第几个服务点
		double t = 0.0;//总等待时间
		while(i<a.length){
			st[j] += a[i];
			su[j] += st[j];
			i++;
			j++;
			if(j==s) j=0;//置为第一个服务点
		}
		for(int x=0; x<s; x++){
			t += su[x];//几个服务点等待时间和
		}
		return t/a.length;
	}
	public static void shellSort(){//希尔排序--------------------------------------------------------------------
		int gap = a.length/2;//步长
		while(gap>0){
			for(int i=gap; i<a.length; i++){//从步长处开始向后依次排序
				int temp = a[i];
				int j = 0;
				for(j=i-gap;j>=0 && temp<a[j]; j-=gap){//步长为gap的直接插入排序
					a[j+gap] = a[j];//大的后移
				}
				a[j+gap] = temp;
			}
			gap/=2;
		}
	}
	public static void loadFromTxt(){//从txt载入数据--------------------------------------------------------------
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("E:\\workspace\\TestBestServiceSort\\src\\com\\main\\input.txt"));
			String line1[] = scanner.nextLine().split(" ");//读第一行
			int len = Integer.parseInt(line1[0]);
			a = new int[len];
			s = Integer.parseInt(line1[1]);
			for(int i=0; i<len; i++){
				a[i] = Integer.parseInt(scanner.nextLine());//读后几行
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public static void printToTxt(double avg){//输出到txt-------------------------------------------------------
		PrintStream ps = null;
		try {
			ps = new PrintStream(new File("E:\\workspace\\TestBestServiceSort\\src\\com\\main\\output.txt"));
			ps.println(avg);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
