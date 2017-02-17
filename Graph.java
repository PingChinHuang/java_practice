import java.util.*;

class GraphAlgorithm {
	private LinkedList<Integer> mAdjacencyList[];
	private int mVertexNum;

	public GraphAlgorithm(int num) {
		mVertexNum = num;
		mAdjacencyList = new LinkedList[num];
		for (int i = 0; i < mAdjacencyList.length; i++) {
			mAdjacencyList[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int v, int w)
	{
		mAdjacencyList[v].add(w);
	}

	public void BFS(int s) {
		boolean visited[] = new boolean[mVertexNum];
		ArrayDeque<Integer> explored = new ArrayDeque<Integer>();
		for (int i = 0; i < visited.length; i++) visited[i] = false;

		explored.add(s);
		visited[s] = true;
		System.out.println(s);

		while (!explored.isEmpty()) {
			int v = explored.remove();
			ListIterator<Integer> iter = mAdjacencyList[v].listIterator();
			while(iter.hasNext()) {
				int w = iter.next();
				if (!visited[w]) {
					visited[w] = true;
					System.out.println(w);
					explored.add(w);
				}
			}
		}
	}

	public void DFS(int s) {
		boolean visited[] = new boolean[mVertexNum];
		Stack<Integer> explored = new Stack<Integer>();
		for (int i = 0; i < visited.length; i++) visited[i] = false;

		explored.push(s);
		visited[s] = true;
		System.out.println(s);

		while (!explored.isEmpty()) {
			int v = explored.pop();
			ListIterator<Integer> iter = mAdjacencyList[v].listIterator();
			while(iter.hasNext()) {
				int w = iter.next();
				if (!visited[w]) {
					visited[w] = true;
					System.out.println(w);
					explored.push(w);
				}
			}
		}
	}
}


public class Graph {
	public static void main(String[] argv) {
		GraphAlgorithm ga = new GraphAlgorithm(10);
		ga.addEdge(0, 4);
		ga.addEdge(4, 0);
		ga.addEdge(0, 3);
		ga.addEdge(3, 0);
		ga.addEdge(3, 2);
		ga.addEdge(2, 3);
		ga.addEdge(2, 1);
		ga.addEdge(1, 2);
		ga.addEdge(1, 4);
		ga.addEdge(4, 1);
		ga.BFS(4);
		ga.DFS(4);
	}
}
