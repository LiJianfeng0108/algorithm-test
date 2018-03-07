package cn.ljf.backtracking;

import java.util.Arrays;

import cn.ljf.element.Element;
import cn.ljf.util.ReadTxt;

public class KnapsackOfBacktracking {
	private static double maxValue;
	private static double cValue;//��ǰ��ֵ
	private static double cWeight;//��ǰ����
	private static int n;
	private static double c;//����������
	private static Element[] elements;//�±��0��ʼ
	private static int cx[];//���浱ǰ�Ƿ�װ��xi
	private static int x[];//���������Ƿ�װ��xi
	public static void main(String[] args) {
		new ReadTxt().ReadDouble();
		n = ReadTxt.n;
		c = ReadTxt.cDouble;
		double[] w = ReadTxt.wDouble;
		double[] v = ReadTxt.vDouble;
		elements = new Element[n];
		cx = new int[n];
		x = new int[n];
		for(int i=0; i<n; i++){
			elements[i] = new Element(i+1, w[i+1], v[i+1]);
		}
		printMaxValueAndTrace();//̰��ѡ���㷨
	}
	public static void printMaxValueAndTrace() {
		Arrays.sort(elements);//����,�����������ֵ���Ͻ�
		maxValue = 0.00;
		cValue = 0.00;
		cWeight = 0.00;
		backtrack(1);//�Ӹ��ڵ㿪ʼ���ݷ�
		System.out.println("����ֵ��"+maxValue);
		for(int m=0; m<n; m++){
			System.out.print("��ţ�"+elements[m].getId());
			System.out.println(" �Ƿ�װ�룺"+x[m]);
		}
	}
	public static void backtrack(int i){//���ݷ�
		if(i>n){//����Ҷ�ڵ�
			if(maxValue<cValue){
				maxValue = cValue;
				for(int m=0; m<n; m++){
					x[m]=cx[m];
				}
			}
			return;
		}
		if(elements[i-1].getWeight()+cWeight<=c){//����������
			cx[i-1] = 1;
			cWeight += elements[i-1].getWeight();
			cValue += elements[i-1].getValue();
			backtrack(i+1);
			cWeight -= elements[i-1].getWeight();
			cValue -= elements[i-1].getValue();
		}
		if(bound(i+1)>maxValue){//������������������ֵ���Ͻ绹û�е�ǰ������ֵ�����֦
			cx[i-1] = 0;
			backtrack(i+1);
		}
	}
	public static double bound(int i){//��֦��������������ֵ���Ͻ�
		double cleft = c-cWeight;
		double bound = cValue;
		while(i<=n && elements[i-1].getWeight()<=cleft){
			cleft -= elements[i-1].getWeight();
			bound += elements[i-1].getValue();
			i++;
		}
		if(i<=n){//װһ������Ʒ�ѱ�������
			bound += elements[i-1].getValue()*cleft/elements[i-1].getWeight();
		}
		return bound;
	}
}
