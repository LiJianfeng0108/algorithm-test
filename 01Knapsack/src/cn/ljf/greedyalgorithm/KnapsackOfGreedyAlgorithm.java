package cn.ljf.greedyalgorithm;

import java.util.Arrays;

import cn.ljf.element.Element;
import cn.ljf.util.ReadTxt;

public class KnapsackOfGreedyAlgorithm {

	public static void main(String[] args) {
		new ReadTxt().ReadDouble();
		int n = ReadTxt.n;
		double c = ReadTxt.cDouble;
		double[] w = ReadTxt.wDouble;
		double[] v = ReadTxt.vDouble;
		Element[] elements = new Element[n];//�±��0��ʼ
		for(int i=0; i<n; i++){
			elements[i] = new Element(i+1, w[i+1], v[i+1]);
		}
		printMaxValueAndTrace(elements, n, c);//̰��ѡ���㷨
		
	}
	public static void printMaxValueAndTrace(Element[] elements, int n, double c){//��λ��ֵ��̰��ѡ���㷨
		Arrays.sort(elements);//����
		double maxValue = 0.00;
		for(int i=0; i<n; i++){
			if(elements[i].getWeight()<=c){
				elements[i].setIsPut(1);
				maxValue += elements[i].getValue();
				c -= elements[i].getWeight();
			}else{
				elements[i].setIsPut(0);
			}
		}
		System.out.println("����ֵ��"+maxValue);
		System.out.println("װ�������1��װ�룻0����װ�룩��");
		for(int i=0; i<n; i++){
			System.out.println(elements[i]);
		}
	}

}
