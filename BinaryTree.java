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
		//System.out.print(root.mData + " ");

/*		if (root.mLeft != null) {
			explored.add(root.mLeft);
			System.out.print(root.mLeft.mData + " ");
		}
		if (root.mRight != null) {
			explored.add(root.mRight);
			System.out.print(root.mRight.mData + " ");
		}*/
		explored.add(root);

		while(!explored.isEmpty()) {
			Node<E> n = explored.remove();
			System.out.print(n.mData + " ");

			if (n.mLeft != null) {
				explored.add(n.mLeft);
				System.out.println(n.mLeft.mData + " push ");
			}
			if (n.mRight != null) {
				explored.add(n.mRight);
				System.out.println(n.mRight.mData + " push");
			}
		}
	}

	public void LevelorderTraverse() {
		LevelorderTraverse(mRoot);
		System.out.println(" ");
	}
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
	}
}



