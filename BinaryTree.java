import java.util.*;

class Node<E> {
	public E mData;
	public Node<E> mParent;
	public Node<E> mLeft;
	public Node<E> mRight;

	public Node(E data) {
		mData = data;
		mParent = null;
		mLeft = null;
		mRight = null;
	}
}

class BinaryTAlgorithm<E extends Comparable<? super E>> {
	private Node<E> mRoot = null;

	public BinaryTAlgorithm() {
	}

	public BinaryTAlgorithm(E data) {
		mRoot = new Node<E>(data);
	}

	public void SetRoot(E data) {
		mRoot = new Node<E>(data);
	}

	private void AddNode(Node<E> root, E data) {
		System.out.println(root.mData);
		if (root.mData.compareTo(data) > 0) {
			if (root.mLeft != null) {
				AddNode(root.mLeft, data);	
			} else {
				root.mLeft = new Node<E>(data);
				root.mLeft.mParent = root;
			}
		} else {
			if (root.mRight != null) {
				AddNode(root.mRight, data);	
			} else {
				root.mRight = new Node<E>(data);
				root.mRight.mParent = root;
			}
		}
	}

	public void AddNode(E data) {
		if (mRoot == null) {
			SetRoot(data);
			return;
		}
		AddNode(mRoot, data);
	}

	public void RemoveNode(E data) {
	}

	private void InorderTraverse(Node<E> root){
		if (root == null) return;

		InorderTraverse(root.mLeft);
		System.out.print(root.mData + " ");
		InorderTraverse(root.mRight);
	}

	public void InorderTraverse() {
		InorderTraverse(mRoot);
		System.out.println(" ");
	}

	private void PreorderTraverse(Node<E> root) {
		if (root == null) return;

		System.out.print(root.mData + " ");
		PreorderTraverse(root.mLeft);
		PreorderTraverse(root.mRight);
	}

	public void PreorderTraverse() {
		PreorderTraverse(mRoot);
		System.out.println(" ");
	}

	private void PostorderTraverse(Node<E> root) {
		if (root == null) return;

		PostorderTraverse(root.mLeft);
		PostorderTraverse(root.mRight);
		System.out.print(root.mData + " ");
	}

	public void PostorderTraverse() {
		PostorderTraverse(mRoot);
		System.out.println(" ");
	}

	private void LevelorderTraverse(Node<E> root) {
		if (root == null) return;

		ArrayDeque<Node<E>> explored = new ArrayDeque<Node<E>>();
		explored.add(root);

		while(!explored.isEmpty()) {
			Node<E> n = explored.remove();
			System.out.print(n.mData + " ");

			if (n.mLeft != null) {
				explored.add(n.mLeft);
			}
			if (n.mRight != null) {
				explored.add(n.mRight);
			}
		}
	}

	public void LevelorderTraverse() {
		LevelorderTraverse(mRoot);
		System.out.println(" ");
	}

	private E GetLeftMost(Node<E> root) {
		if (root.mLeft == null) return root.mData;
		return GetLeftMost(root.mLeft);
	}

	public E GetLeftMost() {
		return GetLeftMost(mRoot);
	}

	private E GetRightMost(Node<E> root) {
		if (root.mRight == null) return root.mData;
		return GetRightMost(root.mRight);
	}

	public E GetRightMost() {
		return GetRightMost(mRoot);
	}

	private Node<E> Search(Node<E> root, E data) {
		if (root == null) return null;
		if (root.mData.compareTo(data) == 0) return root;

		Node<E> n;
		if (root.mData.compareTo(data) > 0) {
			n = Search(root.mLeft, data);
		} else {
			n = Search(root.mRight, data);
		}

		return n;
	}

	public boolean Search(E data) {
		if (mRoot == null) return false;

		Node<E> n = Search(mRoot, data);
		if (n == null) return false;
		else return true;	
	}

	/*public E GetSuccessor(E data) {
		
	}*/

	/*public E GetPredecessor(E data) {
	}*/
}

public class BinaryTree {
	public static void main(String[] argv) {
		BinaryTAlgorithm<Integer> BTAlgo = new BinaryTAlgorithm<Integer>(new Integer(16));

		BTAlgo.AddNode(new Integer(22));
		BTAlgo.AddNode(new Integer(44));
		BTAlgo.AddNode(new Integer(9));
		BTAlgo.AddNode(new Integer(27));
		BTAlgo.AddNode(new Integer(76));
		BTAlgo.AddNode(new Integer(1));
		BTAlgo.AddNode(new Integer(5));

		BTAlgo.InorderTraverse();
		BTAlgo.PreorderTraverse();
		BTAlgo.PostorderTraverse();
		BTAlgo.LevelorderTraverse();

		System.out.println(BTAlgo.GetLeftMost());
		System.out.println(BTAlgo.GetRightMost());
		System.out.println(BTAlgo.Search(22));
		System.out.println(BTAlgo.Search(23));
		System.out.println(BTAlgo.Search(0));
		System.out.println(BTAlgo.Search(9));
		System.out.println(BTAlgo.Search(1));
		System.out.println(BTAlgo.Search(44));
		System.out.println(BTAlgo.Search(49));
	}
}



