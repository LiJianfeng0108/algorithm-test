package cn.ljf.element;

public class Element implements Comparable<Element>{//贪心算法和回溯法使用
	private int id;
	private double weight;
	private double value;
	private int isPut;
	public Element(int id, double weight, double value) {
		super();
		this.id = id;
		this.weight = weight;
		this.value = value;
	}
	@Override
	public int compareTo(Element temp) {//按照单位价值比较排序
		double vw = this.value/this.weight;
		double tempvw = temp.value/temp.weight;
		if(vw<tempvw)
			return 1;
		else if(vw==tempvw)
			return 0;
		else
			return -1;
	}
	public String toString(){
		return "编号："+id+" 是否装入："+isPut;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getIsPut() {
		return isPut;
	}
	public void setIsPut(int isPut) {
		this.isPut = isPut;
	}
	
}
