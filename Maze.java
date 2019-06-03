import java.awt.Point;
import java.util.*;


class Node{
	Point p;/*座標*/ 
	int fDash;/*評価関数値*/
	int g;/*この座標までコスト*/
	Node parent;/*親節点を指すポインタ*/

	Node(int x,int y,int fd,int gd){
		p=new Point(x,y);
		fDash=fd;
		g=gd;
	}

	public boolean equals(Object n){
		return p.equals(((Node)n).p);
	}
}


class Maze{

	static final int WIDTH=6;
	static final int HEIGHT=7;


	/*迷路のデータ*/
	static final int maze[][]={{1,1,1,1,1,1,1,1},
			     {1,0,0,0,0,0,0,1},
			     {1,1,1,1,0,1,0,1},
			     {1,0,0,0,0,1,0,1},
			     {1,0,1,0,1,1,0,1},
			     {1,0,1,0,0,0,0,1},
			     {1,0,1,1,1,1,1,1},
			     {1,0,0,0,0,0,0,1},
			     {1,1,1,1,1,1,1,1}};


	static Point start=new Point(1,1);/*出発座標*/
	static Point goal=new Point(6,7); /*ゴールの座標*/


	static int hd(int x,int y){
  /*ヒューリスティック関数,
    (x,y)座標からゴールまでのマンハッタン距離を計算する*/
    return Math.abs(x-goal.x)+Math.abs(y-goal.y);
	}



	static void sort(ArrayList<Node> l){
  /*リストlを昇順にソートする*/
		Collections.sort(l,new NodeComparator());
	}

	static class NodeComparator implements Comparator<Node>{
				/*各要素のfDashについて昇順にするためのコンパレータ*/
        public int compare(Node n1, Node n2) {
                return (n1.fDash - n2.fDash);
        }
	}

	static boolean isEqual(Point p1,Point p2){
  	/*座標p1と座標p2が等しいかを返す
    trueならば等しい,falseならば等しくない*/
  	return p1.equals(p2);
	}

	static void listAdd1(ArrayList<Node> l,int x,int y,int fd,int gd){
  /*リストlに座標(x,y)でかつ評価関数値fdと
    現在地点までのコストgdを持ったデータを追加する*/
		l.add(new Node(x,y,fd,gd));
	}

	static void listAdd2(ArrayList<Node> l,Node n){
  /*リストlに要素nを追加する
   */
		l.add(n);
	}

	static Node listPickupTop(ArrayList<Node> l){
  /*先頭の要素をリストlから取り除き，その先頭の要素を返す．*/
	/*取り出せないときはnullを返す*/
		if(l.size()==0) return null;
		Node n=l.get(0);
		l.remove(0);
		return n;
	}

	static Node listPickup(ArrayList<Node> l,Node n){
  /*リストlから要素Nの座標と等しい座標を持ったデータを取り出す*/
	/*リストl中に存在しない場合はnullを返す*/
		int idx=l.indexOf(n);
		if(idx==-1) return null;
		else return l.get(idx);
	}

	static void listDel(ArrayList<Node> l,Node n){
		/*リストlから特定の要素nを取り除く*/
		l.remove(l.indexOf(n));
	}


	static void printList(ArrayList<Node> l){
  	/*リストlのすべての要素の座標とf_dashを表示する*/
  	for(int i=0;i<l.size();i++){
			Node n=l.get(i);
    	System.out.printf("(%d,%d)=%d ",n.p.x,n.p.y,n.fDash);
  	}
  	System.out.println();
	}

	static void printResult(Node n){
  	/*要素nに至る経路を表示する*/
  	if(n.parent!=null) printResult(n.parent);
  	System.out.printf("%d,%d\n",n.p.x,n.p.y);
	}


	public static void main(String args[]){
		ArrayList<Node> l1=new ArrayList<Node>();
		ArrayList<Node> l2=new ArrayList<Node>();

	}

}
