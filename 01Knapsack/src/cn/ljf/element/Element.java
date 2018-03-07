package cn.ljf.element;

public class Element implements Comparable<Element>{//̰���㷨�ͻ��ݷ�ʹ��
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
	public int compareTo(Element temp) {//���յ�λ��ֵ�Ƚ�����
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
		return "��ţ�"+id+" �Ƿ�װ�룺"+isPut;
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
