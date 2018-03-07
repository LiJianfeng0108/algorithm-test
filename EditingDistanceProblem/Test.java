
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
	public static void main(String[] args){//主方法-----------------------------------------------------------
		char[] a = null;
		char[] b = null;
		String stra = null;
		String strb = null;
		String strAB = null;
		while(flag){
			strAB = getStrFromSelectedMenu();//采用三种测试据返回“stra#strb”的形式
			if(flag){//如果没输入1-3，此时会返回空串，于是在把空串分解两个串之前再做一次判断
				stra = strAB.split("#")[0];
				strb = strAB.split("#")[1];
				System.out.println("A串："+stra+"，B串:"+strb);
				System.out.println("最小编辑距离（上面数组右下角的值）："+
						dis(stra.toCharArray(), strb.toCharArray()));//二维数组
				//System.out.println("最小编辑距离（上面数组右下角的值）："+
				//		dis2(stra.toCharArray(), strb.toCharArray()));//一维数组
				System.out.println();
			}
			
		}
		
	}
	public static int dis(char[] a, char[] b){//核心代码。A、B两字符串的编辑距离---------------------------------------
		int temp = 0;
		int m = a.length;
		int n = b.length;
		int[][] d = new int[m+1][n+1];
		for(int i=0; i<m+1; i++)//第一列置0-》i，代表B为空，A从前往后需要删除字符的次数渐增
			d[i][0] = i;
		for(int j=0; j<n+1; j++)//第一行置0-》j，代表A为空，B从前往后需要添加字符的次数渐增
			d[0][j] = j;
		for(int i=1; i<m+1; i++){
			for(int j=1; j<n+1; j++){
				if(a[i-1]==b[j-1])//a、b的下标从0开始
					temp = d[i-1][j-1];//替换情况下，且a、b相等情况
				else
					temp = d[i-1][j-1]+1;//替换情况下，且a、b不等情况
				//和增、删这两种情况比较选取最小值
				if((d[i-1][j]+1)<temp)
					temp = d[i-1][j]+1;
				if((d[i][j-1]+1)<temp)
					temp = d[i][j-1]+1;
				d[i][j] = temp;
			}
		}
		showDis(d);//打印d[][]数组
		if(x==2){
			printToTxt(d[m][n]);
		}
		return d[m][n];
	}
	public static void showDis(int[][] dis){//输出编辑距离矩阵----------------------------------------------------
		System.out.println("各种情况下编辑距离：");
		for(int i=0; i<dis.length; i++){
			for(int j=0; j<dis[i].length; j++){
				System.out.print(dis[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static String loadFromTxt(){//从input.txt读数据-----------------------------------------------------
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
	public static void printToTxt(int editDis){//输出最终结果到output.txt----------------------------------------
		PrintStream ps = null;
		try {
			ps = new PrintStream(new File(OUTPUTFILE));
			ps.println(editDis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ps.close();
	}
	public static String getRandom(){//获取两个随机字符串---------------------------------------------------------
		int lena = 0;
		int lenb = 0;
		String stra = null;
		String strb = null;
		StringBuffer sb = new StringBuffer();
		Scanner scanner = null;
		System.out.println("输入A串长度：");
		try {
			scanner = new Scanner(System.in);
			lena = scanner.nextInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("输入B串长度：");
		try {
			scanner = new Scanner(System.in);
			lenb = scanner.nextInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		char[] all = {'1','2','3','4','5','6','7','8','9',//不包含大小写o和数字0
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
	public static String getStrFromSelectedMenu(){//菜单界面，根据选择返回相应的测试字符串--------------------------------
		String str = null;
		System.out.println("请选择：\n1：测试示例\n2：测试input.txt并输出到output.txt\n3：测试随机值\n其他：退出");
		Scanner sc = new Scanner(System.in);
		try{
			if(sc.hasNextLine()) 
				x = Integer.parseInt(sc.nextLine());
		}catch(Exception e){//没输入数字也退出
			System.out.println("已退出");
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
			System.out.println("已退出");
			flag = false;
		}
		return str;
	}
	
	public static int dis2(char[] a, char[] b){//一维数组。A、B两字符串的编辑距离---------------------------------------
		int temp = 0;
		int m = a.length;
		int n = b.length;
		int[] d = new int[n+1];//一维数组表示
		for(int j=0; j<n+1; j++)//初始化
			d[j] = j;
		for(int i=1; i<m+1; i++){
			int y = i-1;
			for(int j=1; j<n+1; j++){
				int x = y;//替代[i-1][j-1]，这次循环的x是上个循环的d[j]，当j=1时，x是i-1
				y = d[j];//纵向,表示删除操作替代[i-1][j]
				int z = j>1?d[j-1]:i;//横向，表示插入操作替代[i][j-1]，当j=1时，取原来的[i][0]即第一列
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
