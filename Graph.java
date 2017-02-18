import java.util.*;

class GraphAlgorithm {
	private LinkedList<Integer> mAdjacencyList[];
	private int mAdjacencyMatrix[][];
	private int mVertexNum;

	public GraphAlgorithm(int num) {
		mVertexNum = num;
		mAdjacencyList = new LinkedList[num];
		for (int i = 0; i < mAdjacencyList.length; i++) {
			mAdjacencyList[i] = new LinkedList<Integer>();
		}

		mAdjacencyMatrix = new int[num][];
		for (int i = 0; i < mAdjacencyMatrix.length; i++) {
			mAdjacencyMatrix[i] = new int[num];
		}
	}

	public void addEdge(int v1, int v2, int weight)
	{
		mAdjacencyList[v1].add(v2);
		mAdjacencyMatrix[v1][v2] = weight;
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

	public void MinSPT() {
	}
}


public class Graph {
	public static void main(String[] argv) {
		GraphAlgorithm ga = new GraphAlgorithm(10);
		ga.addEdge(0, 4, 2);
		ga.addEdge(4, 0, 2);
		ga.addEdge(0, 3, 1);
		ga.addEdge(3, 0, 1);
		ga.addEdge(3, 2, 10);
		ga.addEdge(2, 3, 10);
		ga.addEdge(2, 1, 23);
		ga.addEdge(1, 2, 23);
		ga.addEdge(1, 4, 19);
		ga.addEdge(4, 1, 19);
		ga.BFS(4);
		ga.DFS(4);
	}
}
