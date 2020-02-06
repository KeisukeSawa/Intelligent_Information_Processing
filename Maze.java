import java.awt.Point;
import java.util.*;

class Node {
	Point p;/* ���W*/  
	int fDash;/* �]���֐��l*/ 
	int g;/* ���̍��W�܂ŃR�X�g*/ 
	Node parent;/* �e�ߓ_���w���|�C���^*/ 

	Node(int x, int y, int fd, int gd) {
		p = new Point(x, y);
		fDash = fd;
		g = gd;
	}

	public boolean equals(Object n) {
		return p.equals(((Node) n).p);
	}
}

class Maze {

	static final int WIDTH = 6;
	static final int HEIGHT = 7;

	/* ���H�̃f�[�^*/ 
	static final int maze[][] = { { 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 0, 0, 1 }, { 1, 1, 1, 1, 0, 1, 0, 1 },
			{ 1, 0, 0, 0, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 1, 0, 1 }, { 1, 0, 1, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1 } };

	static Point start = new Point(1, 1);/* �o�����W*/ 
	static Point goal = new Point(6, 7); /* �S�[���̍��W*/ 

	static int hd(int x, int y) {
		/*
		 * �q���[���X�e�B�b�N�֐�, (x,y)���W����S�[���܂ł̃}���n�b�^���������v�Z����*/
		 
		return Math.abs(x - goal.x) + Math.abs(y - goal.y);
	}

	static void sort(ArrayList<Node> l) {
		/* ���X�gl�������Ƀ\�[�g����*/ 
		Collections.sort(l, new NodeComparator());
	}

	static class NodeComparator implements Comparator<Node> {
		/* �e�v�f��fDash�ɂ��ď����ɂ��邽�߂̃R���p���[�^*/ 
		public int compare(Node n1, Node n2) {
			return (n1.fDash - n2.fDash);
		}
	}

	static boolean isEqual(Point p1, Point p2) {
		/*
		 * ���Wp1�ƍ��Wp2������������Ԃ� true�Ȃ�Γ�����,false�Ȃ�Γ������Ȃ�*/
		 
		return p1.equals(p2);
	}

	static void listAdd1(ArrayList<Node> l, int x, int y, int fd, int gd) {
		/*
		 * ���X�gl�ɍ��W(x,y)�ł��]���֐��lfd�� ���ݒn�_�܂ł̃R�X�ggd���������f�[�^��ǉ�����*/
		 
		l.add(new Node(x, y, fd, gd));
	}

	static void listAdd2(ArrayList<Node> l, Node n) {
		/*
		 * ���X�gl�ɗv�fn��ǉ�����
		 */
		l.add(n);
	}

	static Node listPickupTop(ArrayList<Node> l) {
		/* �擪�̗v�f�����X�gl�����菜���C���̐擪�̗v�f��Ԃ��D*/ 
		/* ���o���Ȃ��Ƃ���null��Ԃ�*/ 
		if (l.size() == 0)
			return null;
		Node n = l.get(0);
		l.remove(0);
		return n;
	}

	static Node listPickup(ArrayList<Node> l, Node n) {
		/* ���X�gl����v�fN�̍��W�Ɠ��������W���������f�[�^�����o��*/ 
		/* ���X�gl���ɑ��݂��Ȃ��ꍇ��null��Ԃ�*/ 
		int idx = l.indexOf(n);
		if (idx == -1)
			return null;
		else
			return l.get(idx);
	}

	static void listDel(ArrayList<Node> l, Node n) {
		/* ���X�gl�������̗v�fn����菜��*/ 
		l.remove(l.indexOf(n));
	}

	static void printList(ArrayList<Node> l) {
		/* ���X�gl�̂��ׂĂ̗v�f�̍��W��f_dash��\������*/ 
		for (int i = 0; i < l.size(); i++) {
			Node n = l.get(i);
			System.out.printf("(%d,%d)=%d ", n.p.x, n.p.y, n.fDash);
		}
		System.out.println();
	}

	static void printResult(Node n) {
		/* �v�fn�Ɏ���o�H��\������*/ 
		if (n.parent != null)
			printResult(n.parent);
		System.out.printf("%d,%d\n", n.p.x, n.p.y);
	}

	public static void main(String args[]) {
		ArrayList<Node> l1 = new ArrayList<Node>();
		ArrayList<Node> l2 = new ArrayList<Node>();

	}

}