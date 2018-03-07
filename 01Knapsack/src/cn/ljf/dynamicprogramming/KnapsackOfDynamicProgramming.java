package cn.ljf.dynamicprogramming;

import cn.ljf.util.ReadTxt;

public class KnapsackOfDynamicProgramming {
	public static void main(String[] args) {
		new ReadTxt().ReadInt();
		int n = ReadTxt.n;
		int c = ReadTxt.cInt;
		int[] w = ReadTxt.wInt;
		int[] v = ReadTxt.vInt;
		int[][] m = new int[n+1][c+1];//��ǰ����ֵ
		
		printMaxValue(n, c, w, v, m);
		printTrace(n, c, w, m);
	}
	public static void printMaxValue(int n, int c, int[] w, int[] v, int[][] m){//��ӡ����ֵ
		
		int jMax = Math.min(w[n]-1, c);
		for(int j=0; j<=jMax; j++){//����m(n,j)������
			m[n][j] = 0;
		}
		for(int j=w[n]; j<=c; j++){//������
			m[n][j] = v[n];
		}
		for(int i=n-1; i>1; i--){//����m(i,j)
			jMax = Math.min(w[i]-1, c);
			for(int j=0; j<=jMax; j++){//����
				m[i][j] = m[i+1][j];
			}
			for(int j=w[i]; j<=c; j++){//������
				m[i][j] = Math.max(m[i+1][j], m[i+1][j-w[i]]+v[i]);//װ��xi��װ��xi������ȡ����ֵ
			}
		}
		//������������ֵ��m[1][c]
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
		System.out.println("����ֵ��"+m[1][c]);
	}
	public static void printTrace(int n, int c, int[] w, int[][]m){//��ӡ�Ƿ����xi������Ϊ1������Ϊ0
		int[] x = new int[n+1];//�Ƿ����xi������Ϊ1������Ϊ0
		for(int i=1; i<n; i++){//xi��x(n-1)��װ�����
			if(m[i][c]==m[i+1][c])//��ֵ��ȱ�ʾδ���뵱ǰ��xi
				x[i] = 0;
			else{//����ȱ�ʾ����xi
				c -= w[i];//��С��ǰ����
				x[i] = 1;
			}
		}
		x[n] = (m[n][c]>0)?1:0;
		System.out.print("��Ŵ�1��n��װ�������1��װ�룻0����װ�룩��");
		for(int j=1; j<=n; j++){
			System.out.print(x[j]+" ");
		}
	}
	
}
