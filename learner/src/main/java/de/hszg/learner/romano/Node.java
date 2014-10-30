package de.hszg.learner.romano;

import java.util.ArrayList;

import de.hszg.learner.Concept;

public class Node{
	
	private Node mother;
	private int edge;
	private ArrayList<Node> children;
	private Concept  concept;
	
	public Node(Node mother, int edge, Concept concept){
		
		this.mother = mother;
		this.edge = edge;
		this.children = new ArrayList<Node>();
		this.concept = concept;
	}
	
	public Node getMother() {
		return mother;
	}

	public void setMother(Node mother) {
		this.mother = mother;
	}

	public int getEdge() {
		return edge;
	}

	public void setEdge(int edge) {
		this.edge = edge;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	
	public void setChildren(Node node){
		
		this.children.add(node);
	}
}
