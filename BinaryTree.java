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
		Node<E> n = Search(mRoot, data);
		if (n == null) return;

		if (n.mLeft != null && n.mRight != null) {
			Node<E> successor = GetSuccessor(mRoot, data);
			n.mData = successor.mData; // Use successor's data to replace the deletion node's data.
			if (successor.mRight != null) {
				if (successor.mParent.mData.compareTo(successor.mData) > 0) successor.mParent.mLeft = successor.mRight;
				else successor.mParent.mRight = successor.mRight;
			} else if (successor.mLeft != null) {
				if (successor.mParent.mData.compareTo(successor.mData) > 0) successor.mParent.mLeft = successor.mLeft;
				else successor.mParent.mRight = successor.mLeft;
			} else { /* only can be successor.mRight != null && successor.mLeft != null*/
				if (successor.mParent.mData.compareTo(successor.mData) > 0) {
					successor.mParent.mLeft = null;
				}
				else {
					successor.mParent.mRight = null;
				}
			}
		} else if (n.mLeft != null) {
			if (n == mRoot) {
				mRoot = n.mLeft;
				n.mLeft.mParent = null;
			} else {
				if (n.mData.compareTo(n.mParent.mData) <= 0) n.mParent.mLeft = n.mLeft;
				else n.mParent.mRight = n.mLeft;
				n.mLeft.mParent = n.mParent;
			}
		} else if (n.mRight != null) {
			if (n == mRoot) {
				mRoot = n.mRight;
				n.mRight.mParent = null;
			} else {
				if (n.mData.compareTo(n.mParent.mData) <= 0) n.mParent.mLeft = n.mRight;
				else n.mParent.mRight = n.mRight;
				n.mRight.mParent = n.mParent;
			}
		} else {
			if (n == mRoot) {
				mRoot = null;
			} else {
				if (n.mData.compareTo(n.mParent.mData) <= 0) {
					n.mParent.mLeft = null;
				}
				else n.mParent.mRight = null;
			}
		}
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

	private Node<E> GetLeftMost(Node<E> root) {
		if (root.mLeft == null) return root;
		return GetLeftMost(root.mLeft);
	}

	public E GetLeftMost() {
		return GetLeftMost(mRoot).mData;
	}

	private Node<E> GetRightMost(Node<E> root) {
		if (root.mRight == null) return root;
		return GetRightMost(root.mRight);
	}

	public E GetRightMost() {
		return GetRightMost(mRoot).mData;
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

	private Node<E> GetSuccessor(Node<E> root, E data) {
		if (root == null) return null;
		
		Node<E> current = Search(root, data);
		if (current == null) return null;

		if (current.mRight != null)
			return GetLeftMost(current.mRight);

		Node<E> successor = current.mParent;
		while(successor != null && successor.mRight == current) {
			current = successor;
			successor = successor.mParent;
		}

		return successor;
	} 

	public E GetSuccessor(E data) {
		Node<E> n = GetSuccessor(mRoot, data);
		if (n == null) return null;
		else return n.mData;
	} 

	private Node<E> GetPredecessor(Node<E> root, E data) {
		if (root == null) return null;
		
		Node<E> current = Search(root, data);
		if (current == null) return null;

		if (current.mLeft != null)
			return GetRightMost(current.mLeft);

		Node<E> predecessor = current.mParent;
		while(predecessor != null && predecessor.mLeft == current) {
			current = predecessor;
			predecessor = predecessor.mParent;
		}

		return predecessor;
	}

	public E GetPredecessor(E data) {
		Node<E> n = GetPredecessor(mRoot, data);
		if (n == null) return null;
		else return n.mData;
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

		System.out.println(BTAlgo.GetLeftMost());
		System.out.println(BTAlgo.GetRightMost());
		System.out.println(BTAlgo.Search(22));
		System.out.println(BTAlgo.Search(23));
		System.out.println(BTAlgo.Search(0));
		System.out.println(BTAlgo.Search(9));
		System.out.println(BTAlgo.Search(1));
		System.out.println(BTAlgo.Search(44));
		System.out.println(BTAlgo.Search(49));

		System.out.println(BTAlgo.GetPredecessor(5));
		System.out.println(BTAlgo.GetSuccessor(5));
		System.out.println(BTAlgo.GetPredecessor(16));
		System.out.println(BTAlgo.GetSuccessor(16));
		System.out.println(BTAlgo.GetPredecessor(76));
		System.out.println(BTAlgo.GetSuccessor(76));

		BTAlgo.RemoveNode(9);
		BTAlgo.InorderTraverse();
		BTAlgo.PreorderTraverse();
		BTAlgo.RemoveNode(22);
		BTAlgo.InorderTraverse();
		BTAlgo.PreorderTraverse();
		BTAlgo.RemoveNode(16);
		BTAlgo.InorderTraverse();
		BTAlgo.PreorderTraverse();
		BTAlgo.RemoveNode(5);
		BTAlgo.InorderTraverse();
		BTAlgo.PreorderTraverse();
		BTAlgo.RemoveNode(1);
		BTAlgo.InorderTraverse();
		BTAlgo.PreorderTraverse();
		BTAlgo.RemoveNode(27);
		BTAlgo.InorderTraverse();
		BTAlgo.PreorderTraverse();
	}
}



