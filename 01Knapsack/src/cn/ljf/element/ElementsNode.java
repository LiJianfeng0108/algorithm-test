package cn.ljf.element;

public class ElementsNode implements Comparable<ElementsNode>{
	private double weight;//该节点目前背包中的重量  
	private double value;//该节点目前背包中的总价值  
	private double upprofit;//该节点能够达到的价值上界  
	private int level;  //该节点是第几个物品的选择  
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
