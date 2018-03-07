package cn.ljf.branchandboundmethod;

import java.util.Arrays;
import java.util.PriorityQueue;

import cn.ljf.element.Element;
import cn.ljf.element.ElementsNode;
import cn.ljf.element.Node;
import cn.ljf.util.ReadTxt;

public class KnapsackOfBranchAndBoundMethod {
	private static double maxValue;
	private static double cValue;//当前价值
	private static double cWeight;//当前重量
	private static int n;
	private static double c;//背包总容量
	private static Element[] elements;//下标从0开始
	private static int x[];//保存最终是否装入xi

	private static PriorityQueue<ElementsNode> pq = new PriorityQueue<ElementsNode>();
	
	public static void main(String[] args) {
		new ReadTxt().ReadDouble();
		n = ReadTxt.n;
		c = ReadTxt.cDouble;
		double[] w = ReadTxt.wDouble;
		double[] v = ReadTxt.vDouble;
		elements = new Element[n];
		x = new int[n];
		for(int i=0; i<n; i++){
			elements[i] = new Element(i+1, w[i+1], v[i+1]);
		}
		printMaxValueAndTrace();
	}
	
	public static void printMaxValueAndTrace() {
		Arrays.sort(elements);//单位价值排序
		maxValue = 0.00;
		cValue = 0.00;
		cWeight = 0.00;
		bbMethod();
		System.out.println("最大价值："+maxValue);
		/*System.out.println("装入情况（1：装入；0：不装入）：");
		for(int m=0; m<n; m++){
			System.out.println(elements[m]);
		}*/
		
	}

	public static void bbMethod() {//主要算法
		Node node = null;
		int i=1;
		//double maxValue = 0.00;
		double up = bound(1);
		while(i!=n+1){//搜索子集空间树
			double tWeight = cWeight+elements[i-1].getWeight();
			if(tWeight<=c){//左子树有可行结点
				if(cValue+elements[i-1].getValue()>maxValue)
					maxValue = cValue+elements[i-1].getValue();
				ElementsNode en = new ElementsNode(cWeight+elements[i-1].getWeight(),
						cValue+elements[i-1].getValue(),
						up,i+1,new Node(node, 1));
				pq.add(en);//加入优先级队列
			}
			up = bound(i+1);//计算不装入此物品时的上界
			if(up>maxValue){//右子树
				ElementsNode en = new ElementsNode(cWeight,cValue,
						up,i+1,new Node(node, 0));	
				pq.add(en);//加入优先级队列
			}
			ElementsNode enode = pq.poll();
			if(enode!=null){//取出头结点的系列属性
				node = enode.getLiveNode();
				cWeight = enode.getWeight();
				cValue = enode.getValue();
				up = enode.getUpprofit();
				i = enode.getLevel();
			}else
				break;
			
		}
		/*for(int m=0; m<n&&node!=null; m++){//装入情况
			x[m] = (node.getIsLeft()==1)?1:0;
			elements[m].setIsPut(x[m]);
			node = node.getParent();
		}*/
	}

	public static double bound(int i){//计算最优值的上界
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
