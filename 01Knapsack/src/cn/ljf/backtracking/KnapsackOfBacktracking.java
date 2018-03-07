package cn.ljf.backtracking;

import java.util.Arrays;

import cn.ljf.element.Element;
import cn.ljf.util.ReadTxt;

public class KnapsackOfBacktracking {
	private static double maxValue;
	private static double cValue;//当前价值
	private static double cWeight;//当前重量
	private static int n;
	private static double c;//背包总容量
	private static Element[] elements;//下标从0开始
	private static int cx[];//保存当前是否装入xi
	private static int x[];//保存最终是否装入xi
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
		printMaxValueAndTrace();//贪心选择算法
	}
	public static void printMaxValueAndTrace() {
		Arrays.sort(elements);//排序,方便计算最优值的上界
		maxValue = 0.00;
		cValue = 0.00;
		cWeight = 0.00;
		backtrack(1);//从根节点开始回溯法
		System.out.println("最大价值："+maxValue);
		for(int m=0; m<n; m++){
			System.out.print("编号："+elements[m].getId());
			System.out.println(" 是否装入："+x[m]);
		}
	}
	public static void backtrack(int i){//回溯法
		if(i>n){//到达叶节点
			if(maxValue<cValue){
				maxValue = cValue;
				for(int m=0; m<n; m++){
					x[m]=cx[m];
				}
			}
			return;
		}
		if(elements[i-1].getWeight()+cWeight<=c){//左子树遍历
			cx[i-1] = 1;
			cWeight += elements[i-1].getWeight();
			cValue += elements[i-1].getValue();
			backtrack(i+1);
			cWeight -= elements[i-1].getWeight();
			cValue -= elements[i-1].getValue();
		}
		if(bound(i+1)>maxValue){//遍历右子树；若最优值的上界还没有当前的最大价值大，则剪枝
			cx[i-1] = 0;
			backtrack(i+1);
		}
	}
	public static double bound(int i){//剪枝函数，计算最优值的上界
		double cleft = c-cWeight;
		double bound = cValue;
		while(i<=n && elements[i-1].getWeight()<=cleft){
			cleft -= elements[i-1].getWeight();
			bound += elements[i-1].getValue();
			i++;
		}
		if(i<=n){//装一部分物品把背包填满
			bound += elements[i-1].getValue()*cleft/elements[i-1].getWeight();
		}
		return bound;
	}
}
