package cn.ljf.element;

public class Node {
	private Node parent;
	private int isLeft;
	
	public Node() {
		super();
	}
	public Node(Node parent, int isLeft) {
		super();
		this.parent = parent;
		this.isLeft = isLeft;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public int getIsLeft() {
		return isLeft;
	}
	public void setIsLeft(int isLeft) {
		this.isLeft = isLeft;
	}
	
}
