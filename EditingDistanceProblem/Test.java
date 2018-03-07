
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;


public class Test {
	private static final String INPUTFILE = "input.txt";
	private static final String OUTPUTFILE = "output.txt";
	private static boolean flag = true;
	private static int x = 0;
	public static void main(String[] args){//������-----------------------------------------------------------
		char[] a = null;
		char[] b = null;
		String stra = null;
		String strb = null;
		String strAB = null;
		while(flag){
			strAB = getStrFromSelectedMenu();//�������ֲ��Ծݷ��ء�stra#strb������ʽ
			if(flag){//���û����1-3����ʱ�᷵�ؿմ��������ڰѿմ��ֽ�������֮ǰ����һ���ж�
				stra = strAB.split("#")[0];
				strb = strAB.split("#")[1];
				System.out.println("A����"+stra+"��B��:"+strb);
				System.out.println("��С�༭���루�����������½ǵ�ֵ����"+
						dis(stra.toCharArray(), strb.toCharArray()));//��ά����
				//System.out.println("��С�༭���루�����������½ǵ�ֵ����"+
				//		dis2(stra.toCharArray(), strb.toCharArray()));//һά����
				System.out.println();
			}
			
		}
		
	}
	public static int dis(char[] a, char[] b){//���Ĵ��롣A��B���ַ����ı༭����---------------------------------------
		int temp = 0;
		int m = a.length;
		int n = b.length;
		int[][] d = new int[m+1][n+1];
		for(int i=0; i<m+1; i++)//��һ����0-��i������BΪ�գ�A��ǰ������Ҫɾ���ַ��Ĵ�������
			d[i][0] = i;
		for(int j=0; j<n+1; j++)//��һ����0-��j������AΪ�գ�B��ǰ������Ҫ����ַ��Ĵ�������
			d[0][j] = j;
		for(int i=1; i<m+1; i++){
			for(int j=1; j<n+1; j++){
				if(a[i-1]==b[j-1])//a��b���±��0��ʼ
					temp = d[i-1][j-1];//�滻����£���a��b������
				else
					temp = d[i-1][j-1]+1;//�滻����£���a��b�������
				//������ɾ����������Ƚ�ѡȡ��Сֵ
				if((d[i-1][j]+1)<temp)
					temp = d[i-1][j]+1;
				if((d[i][j-1]+1)<temp)
					temp = d[i][j-1]+1;
				d[i][j] = temp;
			}
		}
		showDis(d);//��ӡd[][]����
		if(x==2){
			printToTxt(d[m][n]);
		}
		return d[m][n];
	}
	public static void showDis(int[][] dis){//����༭�������----------------------------------------------------
		System.out.println("��������±༭���룺");
		for(int i=0; i<dis.length; i++){
			for(int j=0; j<dis[i].length; j++){
				System.out.print(dis[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static String loadFromTxt(){//��input.txt������-----------------------------------------------------
		Scanner scanner = null;
		StringBuffer sb = new StringBuffer();
		try {
			scanner = new Scanner(new File(INPUTFILE));
			sb.append(scanner.nextLine()).append("#").append(scanner.nextLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	public static void printToTxt(int editDis){//������ս����output.txt----------------------------------------
		PrintStream ps = null;
		try {
			ps = new PrintStream(new File(OUTPUTFILE));
			ps.println(editDis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ps.close();
	}
	public static String getRandom(){//��ȡ��������ַ���---------------------------------------------------------
		int lena = 0;
		int lenb = 0;
		String stra = null;
		String strb = null;
		StringBuffer sb = new StringBuffer();
		Scanner scanner = null;
		System.out.println("����A�����ȣ�");
		try {
			scanner = new Scanner(System.in);
			lena = scanner.nextInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("����B�����ȣ�");
		try {
			scanner = new Scanner(System.in);
			lenb = scanner.nextInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		char[] all = {'1','2','3','4','5','6','7','8','9',//��������Сдo������0
				'A','B','C','D','E','F','G','H','I','J','K','L','M',
				'N','P','Q','R','S','T','U','V','W','X','Y','Z',
				'a','b','c','d','e','f','g','h','i','j','k','l','m',
				'n','p','q','r','s','t','u','v','w','x','y','z',};
		Random rd = new Random();
		for(int i=0; i<lena; i++){
			sb.append(all[rd.nextInt(59)]);
		}
		stra = sb.toString();
		sb = new StringBuffer();
		for(int i=0; i<lenb; i++){
			sb.append(all[rd.nextInt(59)]);
		}
		strb = sb.toString();
		return stra+"#"+strb;
	}
	public static String getStrFromSelectedMenu(){//�˵����棬����ѡ�񷵻���Ӧ�Ĳ����ַ���--------------------------------
		String str = null;
		System.out.println("��ѡ��\n1������ʾ��\n2������input.txt�������output.txt\n3���������ֵ\n�������˳�");
		Scanner sc = new Scanner(System.in);
		try{
			if(sc.hasNextLine()) 
				x = Integer.parseInt(sc.nextLine());
		}catch(Exception e){//û��������Ҳ�˳�
			System.out.println("���˳�");
			flag = false;
			return str;
		}
		if(x==1){
			return "fxpimu#xwrs";
		}else if(x==2){
			return loadFromTxt();
		}else if(x==3){
			return getRandom();
		}else{
			System.out.println("���˳�");
			flag = false;
		}
		return str;
	}
	
	public static int dis2(char[] a, char[] b){//һά���顣A��B���ַ����ı༭����---------------------------------------
		int temp = 0;
		int m = a.length;
		int n = b.length;
		int[] d = new int[n+1];//һά�����ʾ
		for(int j=0; j<n+1; j++)//��ʼ��
			d[j] = j;
		for(int i=1; i<m+1; i++){
			int y = i-1;
			for(int j=1; j<n+1; j++){
				int x = y;//���[i-1][j-1]�����ѭ����x���ϸ�ѭ����d[j]����j=1ʱ��x��i-1
				y = d[j];//����,��ʾɾ���������[i-1][j]
				int z = j>1?d[j-1]:i;//���򣬱�ʾ����������[i][j-1]����j=1ʱ��ȡԭ����[i][0]����һ��
				int p = a[i-1]==b[j-1]?0:1;
				temp = x+p;
				if(temp>(y+1))
					temp = y+1;
				if(temp>(z+1))
					temp = z+1;
				d[j] = temp;
			}
		}
		return d[n];
	}
}
