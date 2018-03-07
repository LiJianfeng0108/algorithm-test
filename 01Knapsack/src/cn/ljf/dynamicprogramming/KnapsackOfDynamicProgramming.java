package cn.ljf.dynamicprogramming;

import cn.ljf.util.ReadTxt;

public class KnapsackOfDynamicProgramming {
	public static void main(String[] args) {
		new ReadTxt().ReadInt();
		int n = ReadTxt.n;
		int c = ReadTxt.cInt;
		int[] w = ReadTxt.wInt;
		int[] v = ReadTxt.vInt;
		int[][] m = new int[n+1][c+1];//当前最大价值
		
		printMaxValue(n, c, w, v, m);
		printTrace(n, c, w, m);
	}
	public static void printMaxValue(int n, int c, int[] w, int[] v, int[][] m){//打印最大价值
		
		int jMax = Math.min(w[n]-1, c);
		for(int j=0; j<=jMax; j++){//计算m(n,j)：超重
			m[n][j] = 0;
		}
		for(int j=w[n]; j<=c; j++){//不超重
			m[n][j] = v[n];
		}
		for(int i=n-1; i>1; i--){//计算m(i,j)
			jMax = Math.min(w[i]-1, c);
			for(int j=0; j<=jMax; j++){//超重
				m[i][j] = m[i+1][j];
			}
			for(int j=w[i]; j<=c; j++){//不超重
				m[i][j] = Math.max(m[i+1][j], m[i+1][j-w[i]]+v[i]);//装入xi或不装入xi，二者取最大价值
			}
		}
		//计算整体最大价值即m[1][c]
		m[1][c] = m[2][c];
		if(w[1]<=c){
			m[1][c] = Math.max(m[1][c], m[2][c-w[1]]+v[1]);
		}
		for(int i=1; i<=n; i++){
			for(int j=0; j<=c; j++){
				System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("最大价值："+m[1][c]);
	}
	public static void printTrace(int n, int c, int[] w, int[][]m){//打印是否加入xi，加入为1，不加为0
		int[] x = new int[n+1];//是否加入xi，加入为1，不加为0
		for(int i=1; i<n; i++){//xi到x(n-1)的装入情况
			if(m[i][c]==m[i+1][c])//价值相等表示未加入当前的xi
				x[i] = 0;
			else{//不相等表示加入xi
				c -= w[i];//减小当前容量
				x[i] = 1;
			}
		}
		x[n] = (m[n][c]>0)?1:0;
		System.out.print("编号从1到n的装入情况（1：装入；0：不装入）：");
		for(int j=1; j<=n; j++){
			System.out.print(x[j]+" ");
		}
	}
	
}
