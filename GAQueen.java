class GAQueen{
	static final int POP_SIZE=20;
	static final int N=16;

	static Indiv old_pop[],new_pop[];
	static Indiv population0[]=new Indiv[POP_SIZE];
	static Indiv population1[]=new Indiv[POP_SIZE];
	
	public static void main(String args[]){
	    // 初期集団設定
	    init();
	    
	    while(true){
		float sumfitness = 0;
		// 各個体の適応度の計算
		for(int i=0; i<POP_SIZE; i++){
		    calcPhenotype(old_pop[i]);
		    calcFitness(old_pop[i]);
		    // 集団の評価
		    if(old_pop[i].fitness >= 1.0){
			print(old_pop[i]);
			break;
		    }
		    sumfitness += old_pop[i].fitness;
		}
		// 選択
	        float[] roulette = new int[POP_SIZE];
		float before = 0;
		for(int i=0; i<POP_SIZE; i++){
		    roulette[i] = before + old_pop[i];
		    before = roulette[i];
		}
		for(int i=0; i<POP_SIZE; i++){
		    float ranbom = ((float)Math.random() * POP_SIZE)
		    for(int j=0; j<POP_SIZE; j++){
			if(ranbom < roulette[j]){
			    new_pop[i] = old_pop[j];
			    break;
			}
		    }
		}
		// 交叉
		
		
	    }
		
	}

	static void init(){/*初期化(初期集団生成)*/
	    int i,j;
	    old_pop=population0;/*集団のポインタに実体の配列を割り当てる*/
	    new_pop=population1;/*集団のポインタに実体の配列を割り当てる*/
	    for(i=0;i<POP_SIZE;i++){
		population0[i]=new Indiv();
		population1[i]=new Indiv();
		for(j=0;j<N;j++){
		    old_pop[i].genotype[j]=(int)(Math.random()*(N-j));/*ランダムに遺伝子を生成する*/
		}
	    }
	}


	static void calcPhenotype(Indiv t){/* tで指した個体に対して表現型を計算する。*/
            /*結果はt.phenotypeへ入る*/
	    int j,k;
	    int w[]=new int[N];
	    for(j=0;j<N;j++) w[j]=j;
	    for(j=0;j<N;j++){
		t.phenotype[j]=w[t.genotype[j]];
		for(k=t.genotype[j];k<N-j-1;k++) w[k]=w[k+1];
	    }
	}

	static void calcFitness(Indiv t){/* tで指した個体に対して適応度を計算する。*/
            /*事前にphenotypeを計算する必要あり*/
            /*結果はt.fitnessへ入る。*/
	    int ng,i,j;
	    ng=0;
	    for(i=0;i<N;i++){
		for(j=i+1;j<N;j++){
		    if(t.phenotype[j]-t.phenotype[i]==j-i) ng++;
		    if(t.phenotype[i]-t.phenotype[j]==j-i) ng++;
		}
	    }
	    t.fitness=(float)(1.0/(1.0+ng));
	}

	static void print(Indiv t){/*tの個体の遺伝子型,表現型,実際のクイーンの配置を表示する*/
	    int j,k;
	    int r[]=new int[N],w[]=new int[N];

	    for(j=0;j<N;j++) System.out.print(t.genotype[j]+" ");
	    System.out.println();
	    for(j=0;j<N;j++) w[j]=j;
	    for(j=0;j<N;j++){
		r[j]=w[t.genotype[j]];
		for(k=t.genotype[j];k<N-j-1;k++) w[k]=w[k+1];
	    }
	    for(j=0;j<N;j++) System.out.print(r[j]+" ");
	    System.out.println();
	    printp(r);
	}

	static void printp(int g[]){
	    int i,j;
	    for(i=0;i<N;i++){
		for(j=0;j<N;j++){
		    if(g[j]==i) System.out.print("＠");
		    else System.out.print("ロ");
		}
		System.out.println();
	    }
	}
}


class Indiv{
	int genotype[]=new int[GAQueen.N];
	int phenotype[]=new int[GAQueen.N];
	float fitness;
}

/*
import java.util.*;
class GAQueen{
    static final int POP_SIZE=20;
    static final int N=16;
    static Indiv old_pop[],new_pop[];
    static Indiv population0[]=new Indiv[POP_SIZE];
    static Indiv population1[]=new Indiv[POP_SIZE];
    public static void main(String args[]){
 init();
 while(true){
     float old_fitsum = 0;//適応度の合計
     for(int i=0;i<POP_SIZE;i++){
  calcPhenotype(old_pop[i]);//表現型計算
  calcFitness(old_pop[i]);//適応度計算
  if(old_pop[i].fitness>=1){//評価
      print(old_pop[i]);
      System.out.println("end");
      return;
  }
  old_fitsum += old_pop[i].fitness;
     }
     //ルーレット選択(ペアになる添字を決める)
     double roul;//適応度内ランダム
     float new_fitsum = 0;//適応度の合計
     int roulIdx[] = new int[POP_SIZE];//遺伝子コード(数字の位置)
     for(int j=0;j<POP_SIZE;j++){
  roul=Math.random()*old_fitsum;
  new_fitsum=0;
  for(int i=0;i<POP_SIZE;i++){
      new_fitsum+=old_pop[i].fitness;
      if(roul<=new_fitsum){
   roulIdx[j]=i;
   break;
      }
  }
     }
     //交叉
     for(int i=0;i<POP_SIZE;i+=2){//ペア
  if(Math.random()<=0.6){
      int crossIdx = (int)(Math.random()*N); //交叉する場所
      for(int j=0;j<N;j++){
   if(crossIdx<=j){//交叉しない部分
       new_pop[i].genotype[j]=old_pop[roulIdx[i]].genotype[j];
       new_pop[i+1].genotype[j]=old_pop[roulIdx[i+1]].genotype[j];
   }else{//交叉する部分
       new_pop[i].genotype[j]=old_pop[roulIdx[i+1]].genotype[j];
       new_pop[i+1].genotype[j]=old_pop[roulIdx[i]].genotype[j];
   }
      }
  }
     }
     //突然変異
     Random mutantoP = new Random();//するかしないかの確率判定用
     for(int i=0;i<POP_SIZE;i++){
  for(int j=0;j<N;j++){
      if(mutantoP.nextDouble() <= 0.0333){
   //変異
   new_pop[i].genotype[j] = (int)(Math.random()*(N-j));
      }
  }
     }
     Indiv[] tmp = new_pop;
     new_pop = old_pop;
     old_pop = tmp;
 }
    }
*/
