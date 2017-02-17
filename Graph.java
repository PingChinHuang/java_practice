import java.util.*;

class GraphAlgorithm {
	private LinkedList<Integer> adj[];
	private int V;

	public GraphAlgorithm(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int v, int w)
	{
		adj[v].add(w);
	}

	private void BFS(int v, boolean visited[])
	{
	}

	public void BFS(int s) {
	}

	private void DFS(int v, boolean visited[])
	{
	}

	public void DFS(int s) {
	}
}


public class Graph {
	public static void main(String[] argv) {
	}
}
