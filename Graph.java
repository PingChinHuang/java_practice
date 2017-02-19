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
			for (int j = 0; j < mAdjacencyMatrix[i].length; j++) {
				mAdjacencyMatrix[i][j] = Integer.MAX_VALUE / 2;
			}
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

	private int[] DijkstraShortestPath(int s) {
		boolean visited[] = new boolean[mVertexNum];
		int distance[] = new int[mVertexNum];
		int parent[] = new int[mVertexNum];

		for (int i = 0; i < mVertexNum; i ++) {
			visited[i] = false;
			distance[i] = Integer.MAX_VALUE / 2;
			parent[i] = s;
		}

		distance[s] = 0;
		parent[s] = s;

		for (int i = 0; i < mVertexNum; i++) {
			// To prevent from overflow..... inital value set to Integer.MAX_VALUE/2
			// The edge weight no more than it.
			int a = -1, min = Integer.MAX_VALUE / 2;

			// Find a vertex a with minimal distance to check its edges to next vertex.
			for (int j = 0; j < mVertexNum; j++) {
				if (!visited[j] && distance[j] < min) {
					min = distance[j];
					a = j;
				}
			}

			if (a == -1) break;
			visited[a] = true;

			// Calculate the minimal distance from a to all nodes.
			for (int j = 0; j < mVertexNum; j++) {
				if (!visited[j] && distance[a] + mAdjacencyMatrix[a][j] < distance[j]) {
					distance[j] = distance[a] + mAdjacencyMatrix[a][j];
					parent[j] = a;
				}
			}
		}

		/*for (int i = 0; i < distance.length; i++) {
			System.out.print(i + ":" + distance[i] + " " );
		}
		System.out.println(" ");*/
		return parent;
	}

	public void FindShortestPath(int s, int e){
		int parent[] = DijkstraShortestPath(s);

		int f = e;

		// parenet[f] = f means the starting point.
		while (parent[f] != f) {
			System.out.print(f + " ");
			f = parent[f];
		}

		System.out.println(f);
		/*for (int i = 0; i < parent.length; i++) {
			System.out.print(i + ":" + parent[i] +" ");
		}
		System.out.println(" ");*/
	}

	public void MinSPT() {
	}

	private boolean HasLoop(int s, boolean visited[], boolean recStack[]) {
		visited[s] = true;
		recStack[s] = true;

		ListIterator<Integer> iter = mAdjacencyList[s].listIterator();
		while(iter.hasNext()) {
			int w = iter.next();
			if (!visited[w] && HasLoop(w, visited, recStack))
				return true;
			else if (recStack[w]) {
				for (int i = 0; i < recStack.length; i++) {
					if (recStack[i]) System.out.print(i + " ");
				}
				System.out.println("");
				return true;
			}	   
		}
		recStack[s] = false;
		return false;
	}

	public boolean HasLoop(int s) {
		boolean visited[] = new boolean[mVertexNum];
		boolean recStack[] = new boolean[mVertexNum];
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
			recStack[i] = false;
		}

		return HasLoop(s, visited, recStack);
	}
}


public class Graph {
	public static void main(String[] argv) {
		GraphAlgorithm ga = new GraphAlgorithm(10);
		/*ga.addEdge(0, 4, 2);
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
		ga.DFS(4);*/

		/*ga.addEdge(0, 1, 0);
		ga.addEdge(1, 2, 0);
		ga.addEdge(2, 3, 0);
		ga.addEdge(3, 1, 0);
		ga.addEdge(3, 4, 0);
		ga.HasLoop(0);*/

		/*ga.addEdge(0, 1, 0);
		ga.addEdge(1, 2, 0);
		ga.addEdge(2, 3, 0);
		ga.addEdge(3, 1, 0);
		System.out.println(ga.HasLoop(0));*/
		
		ga.addEdge(0, 1, 2);
		ga.addEdge(0, 3, 1);
		ga.addEdge(1, 4, 1);
		ga.addEdge(3, 4 ,3);
		ga.FindShortestPath(0, 4);	

	}
}

