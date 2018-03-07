package cn.ljf.element;

public class ElementsNode implements Comparable<ElementsNode>{
	private double weight;//�ýڵ�Ŀǰ�����е�����  
	private double value;//�ýڵ�Ŀǰ�����е��ܼ�ֵ  
	private double upprofit;//�ýڵ��ܹ��ﵽ�ļ�ֵ�Ͻ�  
	private int level;  //�ýڵ��ǵڼ�����Ʒ��ѡ��  
	private Node liveNode; 
	@Override
	public int compareTo(ElementsNode temp) {
		double tempUpprofit = temp.getUpprofit();
		if(this.upprofit<tempUpprofit)
			return 1;
		else if(this.upprofit==tempUpprofit)
			return 0;
		return -1;
	}
	public ElementsNode(double weight, double value, double upprofit, int level, Node liveNode) {
		super();
		this.weight = weight;
		this.value = value;
		this.upprofit = upprofit;
		this.level = level;
		this.liveNode = liveNode;
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
	public double getUpprofit() {
		return upprofit;
	}
	public void setUpprofit(double upprofit) {
		this.upprofit = upprofit;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Node getLiveNode() {
		return liveNode;
	}
	public void setLiveNode(Node liveNode) {
		this.liveNode = liveNode;
	}
	
}
