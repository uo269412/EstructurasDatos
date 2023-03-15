package graph;

public class GraphNode<T> {

	private T element;
	private boolean visited;

	public GraphNode(T element, boolean visited) {
		this.element = element;
		this.visited = visited;
	}

	public GraphNode(T element) {
		this.element = element;
	}
	public void setElement(T element) {
		this.element = element;
	}

	public T getElement() {
		return element;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isVisited() {
		return visited;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GN(N:").append(getElement()).append("/V:").append(isVisited());
		builder.append(")");
		return builder.toString();
	}

	public void print() {
		System.out.println(toString());
	}

}
