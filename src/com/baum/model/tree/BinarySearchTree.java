package com.baum.model.tree;

public class BinarySearchTree<Elem> {
	private int key;
	private Elem x = null;
	private BinarySearchTree<Elem> left = null;
	private BinarySearchTree<Elem> right = null;
	private boolean isRoot;

	// //////////////////////////////////////////////////////////////////////
	// ///// Baum erzeugen
	// //////////////////////////////////////////////////////////////////////

	private BinarySearchTree() {
		isRoot = true;
	}

	private BinarySearchTree(int key, Elem value) {
		x = value;
		this.key = key;
		isRoot = false;
	}

	public static <Elem> BinarySearchTree<Elem> empty() {
		return new BinarySearchTree<Elem>();
	}

	// //////////////////////////////////////////////////////////////////////
	// ///// Baum befüllen und Elemente löschen
	// //////////////////////////////////////////////////////////////////////

	public void insert(int key, Elem value) {
		if (isEmpty()) {
			this.key = key;
			this.x = value;
		} else {
			BinarySearchTree<Elem> bst = searchKey(key);
			if (bst.key == key) {
				bst.x = value;
				return;
			}
			if (key > bst.key) {
				bst.right = new BinarySearchTree<Elem>(key, value);
				return;
			}
			if (key < bst.key) {
				bst.left = new BinarySearchTree<Elem>(key, value);
				return;
			}
		}
	}

	public void delete(int key) throws TreeAccessException {
		if (isEmpty())
			throw new TreeAccessException("Leerer Baum!");
		BinarySearchTree<Elem> del = searchKey(key);
		if (del.key != key)
			throw new TreeAccessException("Der Key wurde nicht gefunden.");
		if (del.isRoot) {
			if (isLeaf()) {
				x = null;
				return;
			}
			if (hasLeftChild() && !hasRightChild()) {
				copyAllAndDelete(left);
				return;
			}
			if (!hasLeftChild() && hasRightChild()) {
				copyAllAndDelete(right);
				return;
			}
			if (hasLeftChild() && hasRightChild()) {
				BinarySearchTree<Elem> min = right.min();
				if (min == right) {
					right = min.right;
					copyContentAndDelete(min);
					return;
				}
				if (min != right) {
					BinarySearchTree<Elem> pmin = getParent(min);
					pmin.left = min.right;
					copyContentAndDelete(min);
					return;
				}
			}
		} else {
			if (del.isLeaf()) {
				BinarySearchTree<Elem> pmin = getParent(del);
				if (pmin.left == del) {
					pmin.left = null;
					return;
				} else {
					pmin.right = null;
					return;
				}
			}
			if (!del.isLeaf()) {
				if (del.hasLeftChild() && !del.hasRightChild()) {
					del.copyAllAndDelete(del.left);
					return;
				}
				if (del.hasRightChild() && !del.hasLeftChild()) {
					del.copyAllAndDelete(del.right);
					return;
				}
				if (del.hasRightChild() && del.hasLeftChild()) {
					BinarySearchTree<Elem> min = del.right.min();
					if (min == del.right) {
						del.right = min.right;
						del.copyContentAndDelete(min);
						return;
					}
					if (min != del.right) {
						BinarySearchTree<Elem> pmin = getParent(min);
						pmin.left = min.right;
						del.copyContentAndDelete(min);
						return;
					}
				}
			}

		}

	}

	private void copyAllAndDelete(BinarySearchTree<Elem> child) {
		x = child.x;
		left = child.left;
		right = child.right;
		key = child.key;
		child.left = null;
		child.right = null;
	}

	private void copyContentAndDelete(BinarySearchTree<Elem> child) {
		key = child.key;
		x = child.x;
		child.left = null;
		child.right = null;
	}

	// //////////////////////////////////////////////////////////////////////
	// ///// Baum durchsuchen
	// //////////////////////////////////////////////////////////////////////

	public Elem value(int key) throws TreeAccessException {
		if (isEmpty())
			throw new TreeAccessException();
		if (this.key == key) {
			return x;
		} else {
			BinarySearchTree<Elem> suchbaum = searchKey(key);
			return suchbaum.value(key);
		}
	}

	private BinarySearchTree<Elem> searchKey(int key) {
		if (this.key == key)
			return this;

		if (isLeaf())
			return this;

		if (key < this.key) {
			if (hasLeftChild()) {
				return left.searchKey(key);
			}
			return this;
		}

		if (key > this.key) {
			if (hasRightChild()) {
				return right.searchKey(key);
			}
			return this;
		}

		return null;
	}

	private BinarySearchTree<Elem> min() {
		if (isEmpty())
			return null;
		if (!hasLeftChild()) {
			return this;
		} else {
			return left.min();
		}
	}

	private BinarySearchTree<Elem> getParent(BinarySearchTree<Elem> child) {
		if (child.isRoot())
			return null;
		if (hasLeftChild()) {
			if (this.left == child) {
				return this;
			} else {
				BinarySearchTree<Elem> p = left.getParent(child);
				if (p != null)
					return p;
			}
		}
		if (hasRightChild()) {
			if (this.right == child) {
				return this;
			} else {
				BinarySearchTree<Elem> p = right.getParent(child);
				if (p != null)
					return p;
			}
		}
		return null;
	}

	// //////////////////////////////////////////////////////////////////////
	// ///// Ausgabe
	// //////////////////////////////////////////////////////////////////////

	public String getPreorderString(int n) {
		if (isEmpty()) {
			return space(3 * n) + "Der Baum ist leer!" + cr();
		}
		String s = "";
		s = this.toString();
		if (isRoot()) {
			s = space(3 * n) + "Key=" + key + " Value=" + x + cr();
		} else {
			s = "Key=" + key + " Value=" + x + cr();
		}
		if (isLeaf()) {
			return s;
		}

		if (hasLeftChild()) {
			s = s + space(3 * (n + 1)) + "Links: "
					+ left.getPreorderString(n + 1);
		} else {
			s = s + space(3 * (n + 1)) + "Links: --> leer" + cr();
		}

		if (hasRightChild()) {
			s = s + space(3 * (n + 1)) + "Rechts: "
					+ right.getPreorderString(n + 1);
		} else {
			s = s + space(3 * (n + 1)) + "Rechts: --> leer" + cr();
		}
		return s;

	}

	private String space(int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			s = s + " ";
		}
		return s;
	}

	private String cr() {
		return "\n";
	}

	public String toString() {
		return "<Key=" + key + "; Value=" + x + ">";
	}

	// //////////////////////////////////////////////////////////////////////
	// ///// Prüfungen
	// //////////////////////////////////////////////////////////////////////

	private boolean isEmpty() {
		if (x == null && left == null && right == null) {
			return true;
		}
		return false;
	}

	private boolean isLeaf() {
		return x != null && left == null && right == null;
	}

	private boolean isRoot() {
		return isRoot;
	}

	private boolean hasLeftChild() {
		return left != null;
	}

	private boolean hasRightChild() {
		return right != null;
	}
}
