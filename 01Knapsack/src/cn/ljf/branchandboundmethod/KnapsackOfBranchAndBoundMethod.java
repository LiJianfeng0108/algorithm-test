package cn.ljf.branchandboundmethod;

import java.util.Arrays;
import java.util.PriorityQueue;

import cn.ljf.element.Element;
import cn.ljf.element.ElementsNode;
import cn.ljf.element.Node;
import cn.ljf.util.ReadTxt;

public class KnapsackOfBranchAndBoundMethod {
	private static double maxValue;
	private static double cValue;//��ǰ��ֵ
	private static double cWeight;//��ǰ����
	private static int n;
	private static double c;//����������
	private static Element[] elements;//�±��0��ʼ
	private static int x[];//���������Ƿ�װ��xi

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
		Arrays.sort(elements);//��λ��ֵ����
		maxValue = 0.00;
		cValue = 0.00;
		cWeight = 0.00;
		bbMethod();
		System.out.println("����ֵ��"+maxValue);
		/*System.out.println("װ�������1��װ�룻0����װ�룩��");
		for(int m=0; m<n; m++){
			System.out.println(elements[m]);
		}*/
		
	}

	public static void bbMethod() {//��Ҫ�㷨
		Node node = null;
		int i=1;
		//double maxValue = 0.00;
		double up = bound(1);
		while(i!=n+1){//�����Ӽ��ռ���
			double tWeight = cWeight+elements[i-1].getWeight();
			if(tWeight<=c){//�������п��н��
				if(cValue+elements[i-1].getValue()>maxValue)
					maxValue = cValue+elements[i-1].getValue();
				ElementsNode en = new ElementsNode(cWeight+elements[i-1].getWeight(),
						cValue+elements[i-1].getValue(),
						up,i+1,new Node(node, 1));
				pq.add(en);//�������ȼ�����
			}
			up = bound(i+1);//���㲻װ�����Ʒʱ���Ͻ�
			if(up>maxValue){//������
				ElementsNode en = new ElementsNode(cWeight,cValue,
						up,i+1,new Node(node, 0));	
				pq.add(en);//�������ȼ�����
			}
			ElementsNode enode = pq.poll();
			if(enode!=null){//ȡ��ͷ����ϵ������
				node = enode.getLiveNode();
				cWeight = enode.getWeight();
				cValue = enode.getValue();
				up = enode.getUpprofit();
				i = enode.getLevel();
			}else
				break;
			
		}
		/*for(int m=0; m<n&&node!=null; m++){//װ�����
			x[m] = (node.getIsLeft()==1)?1:0;
			elements[m].setIsPut(x[m]);
			node = node.getParent();
		}*/
	}

	public static double bound(int i){//��������ֵ���Ͻ�
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
