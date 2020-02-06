
import java.util.*;

class Queen{
    
	final static int SIZE=8;

	static LinkedList<int[]> l1=new LinkedList<int[]>();

        /*盤面データ配列qをリストの最後に入れる*/
	static void enqueue(int q[]){
	        l1.offer(q.clone());
	}

        /*リストの先頭から盤面データを取り出す*/
	static int[] dequeue(){
	        /*正常に取り出せたときは配列が返される*/
		return l1.poll();
		/*リストが空の時はnullが返される*/
	}                      

        /*リストの先頭から盤面データを取り出す*/
	static int[] pop(){ 
		try{
		         /*正常に取り出せたときは配列が返される*/
			return l1.pop();   	           
		}catch(NoSuchElementException e){
		        /*リストが空の時はnullが返される*/
			return null;                    
		}
	}

        /*盤面データ配列qをリストの先頭に入れる*/
	static void push(int q[]){
		l1.push(q.clone());
	}

        /*l1に格納されている要素の数を返す*/
	static int size(){
		return l1.size();
	}

        /*盤面データqでqueenの利きを調べる*/
	static int check_solve(int q[]){
	        /*返り値-1ならば利き筋に駒がある*/
  	        int i,j,c=0;
		/*返り値0以上ならば利き筋に駒が無くて配置できている駒の数である*/
		for(i=0;i<SIZE;i++){  
		        if(q[i]!=0){
			        for(j=0;j<SIZE;j++){
					if(j!=i &&  q[j]!=0){
					        if(q[j]==q[i])return -1;
	  				        if((q[i]-i)==(q[j]-j))return -1;
	  				        if((q[i]+i)==(q[j]+j))return -1;
					}
				}
				c++;
			}
		}
		return c;
	}

        /*盤面データqの実際の駒の配置を表示する*/
	static void printboard(int q[]){
	        int i,j;
  	        for(j=1;j<=SIZE;j++){
		        for(i=0;i<SIZE;i++){
			        if(q[i]==j) System.out.print("Q ");
				else System.out.print("_ ");
			}
			System.out.println();
		}
	}

        /*盤面データqの値を表示させる*/
	static void printdata(int q[]){
	        int i;
		for(i=0;i<SIZE;i++) System.out.print(q[i]+" ");
		System.out.println();
	}

	public static void main(String args[]){
	        int q[]=new int[SIZE]; // ルートノード
		int n[]=new int[SIZE]; // 今見ている点のこと
		int check = 0;
		int index = 0;
		
		
		// ルートノードを初期値0で生成する。
		for(int i=0;i<SIZE;i++){
		        q[i] = 0;
		}

		System.out.println("深さ優先探索");
		
		// step1  ルートノードをリストl1に入れる
		push(q);
		
		// step2 if L1 = 空 then 探索は失敗、終了
		while(!l1.isEmpty()){
		    
		        // step3 L1の先頭の節点nを取り除く
		        n=pop();

		        // step4 if nが目標節点である then 探索は成功、終了
		        if(check_solve(n) == SIZE){
			        printdata(n);
				printboard(n);
				break;
			}

			/* step5 if nが展開できる（子節点を持つ）
			 * then 展開し、得られた子節点をリストl1の先頭に入れる
			 *      子節点からnへのポインタをつける step2へ
			 * else step2へ                                     
			 */
			if(check_solve(n) <= SIZE){
			        for(int i=7; i>=0; i--){

				        n[index] = i;
				        
				        if(check_solve(n) >= 0 && check_solve(n) <=SIZE){
					        push(n);
						//System.out.println(n);
				        }
				}

			}
		    
		}
		

	}
}
