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
		Element[] elements = new Element[n];//下标从0开始
		for(int i=0; i<n; i++){
			elements[i] = new Element(i+1, w[i+1], v[i+1]);
		}
		printMaxValueAndTrace(elements, n, c);//贪心选择算法
		
	}
	public static void printMaxValueAndTrace(Element[] elements, int n, double c){//单位价值的贪心选择算法
		Arrays.sort(elements);//排序
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
		System.out.println("最大价值："+maxValue);
		System.out.println("装入情况（1：装入；0：不装入）：");
		for(int i=0; i<n; i++){
			System.out.println(elements[i]);
		}
	}

}
