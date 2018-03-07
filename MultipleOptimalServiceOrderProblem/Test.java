package com.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


public class Test {
	static int s = 0;
	static int a[];
	public static void main(String[] args){//������-------------------------------------------------------------
		loadFromTxt();
		double avg = greedy(s);
		System.out.println(avg);
		printToTxt(avg);
	}
	public static double greedy(int s){//̰���㷨�����ŷ������s��ʾ��������--------------------------------------------
		int st[] = new int[s];//ÿ������ÿ���˿͵ĵȴ�ʱ��
		int su[] = new int[s];//ÿ���������й˿͵ĵȴ�ʱ���
		for(int i=0; i<s; i++){
			st[i] = 0;
			su[i] = 0;
		}
		shellSort();
		/*for(int aa:a){
			System.out.print(aa+" ");
		}
		System.out.println();*/
		int i = 0;//�ڼ����ͻ�
		int j = 0;//�ڼ��������
		double t = 0.0;//�ܵȴ�ʱ��
		while(i<a.length){
			st[j] += a[i];
			su[j] += st[j];
			i++;
			j++;
			if(j==s) j=0;//��Ϊ��һ�������
		}
		for(int x=0; x<s; x++){
			t += su[x];//���������ȴ�ʱ���
		}
		return t/a.length;
	}
	public static void shellSort(){//ϣ������--------------------------------------------------------------------
		int gap = a.length/2;//����
		while(gap>0){
			for(int i=gap; i<a.length; i++){//�Ӳ�������ʼ�����������
				int temp = a[i];
				int j = 0;
				for(j=i-gap;j>=0 && temp<a[j]; j-=gap){//����Ϊgap��ֱ�Ӳ�������
					a[j+gap] = a[j];//��ĺ���
				}
				a[j+gap] = temp;
			}
			gap/=2;
		}
	}
	public static void loadFromTxt(){//��txt��������--------------------------------------------------------------
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("E:\\workspace\\TestBestServiceSort\\src\\com\\main\\input.txt"));
			String line1[] = scanner.nextLine().split(" ");//����һ��
			int len = Integer.parseInt(line1[0]);
			a = new int[len];
			s = Integer.parseInt(line1[1]);
			for(int i=0; i<len; i++){
				a[i] = Integer.parseInt(scanner.nextLine());//������
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public static void printToTxt(double avg){//�����txt-------------------------------------------------------
		PrintStream ps = null;
		try {
			ps = new PrintStream(new File("E:\\workspace\\TestBestServiceSort\\src\\com\\main\\output.txt"));
			ps.println(avg);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
