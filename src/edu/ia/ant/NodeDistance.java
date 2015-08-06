package edu.ia.ant;

public class NodeDistance {

	private int source, destination;
	private double distance;
	
	public NodeDistance(int source, int destination, double distance){
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}
	
	public NodeDistance(int source, int destination){
		this.source = source;
		this.destination = destination;
	}
	
	public int getSource(){
		return this.source;
	}
	
	public int getDestination(){
		return this.destination;
	}
	
	public double getDistance(){
		return this.distance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + source;
		result = prime * result + destination;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeDistance other = (NodeDistance) obj;
		if (source != other.source && destination != other.source)
			return false;
		if (destination != other.destination && source != other.destination)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NodeDistance [source=" + source + ", destination="
				+ destination + ", distance=" + distance + "]";
	}
	
	
}
