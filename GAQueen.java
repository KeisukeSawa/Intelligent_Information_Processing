import java.util.*;

class GAQueen {
	static final int POP_SIZE = 20;
	static final int N = 16;

	static Indiv old_pop[], new_pop[];
	static Indiv population0[] = new Indiv[POP_SIZE];
	static Indiv population1[] = new Indiv[POP_SIZE];

	public static void main(String args[]) {
		// 初期集団設定
		init();

		// 何世代目かカウントする
		int cnt = 0;

		while (true) {
			float sumfitness = 0;
			// 各個体の適応度の計算
			for (int i = 0; i < POP_SIZE; i++) {
				// 表現型の計算
				calcPhenotype(old_pop[i]);
				// 適用度の計算
				calcFitness(old_pop[i]);
				// 集団の評価
				if (old_pop[i].fitness >= 1.0) {
					print(old_pop[i]);
					System.out.println("答えを求めるために"+cnt+"世代かかりました。");
					return;
				}
				sumfitness = sumfitness + old_pop[i].fitness;
			}
			// 選択
			// ルーレット表
			int[] roulette = new int[POP_SIZE];
			// ルーレットを使用した適用度の合計を代入する変数
			float new_sumfitness;
			for (int i = 0; i < POP_SIZE; i++) {
				// ランダムな値を作成
				float ranbom = ((float) Math.random() * sumfitness);
				new_sumfitness = 0;
				for (int j = 0; j < POP_SIZE; j++) {
					// 適用度の合計を更新
					new_sumfitness = new_sumfitness + old_pop[j].fitness;
					if (ranbom <= new_sumfitness) {
						roulette[i] = j;
						break;
					}
				}
			}
			// 交叉
			// 交叉はペアで行うため、2ずつ増やす
			for (int i = 0; i < POP_SIZE; i=i+2) {
				// 交叉の確率は0.6
				if(Math.random()<=0.6){
					// 交叉場所を指定
					int index = (int)Math.random()*N;
					// 交叉が発生
					for(int j = 0; j < N; j++){
						// 交叉する場合
						if(index > j){
							//new_pop[i].genotype[j]=old_pop[roulette[i+1]].genotype[j];
							//new_pop[i+1].genotype[j]=old_pop[roulette[i]].genotype[j];
							
							new_pop[i].genotype[j]=old_pop[i+1].genotype[j];
							new_pop[i+1].genotype[j]=old_pop[i].genotype[j];
						}
						// 交叉しない場合
						else{
							//new_pop[i].genotype[j]=old_pop[roulette[i]].genotype[j];
							//new_pop[i+1].genotype[j]=old_pop[roulette[i+1]].genotype[j];
							
							new_pop[i].genotype[j]=old_pop[roulette[i+1]].genotype[j];
							new_pop[i+1].genotype[j]=old_pop[roulette[i]].genotype[j];
						}
					}
				}

			}

			// 突然変異
			for(int i=0; i < POP_SIZE; i++ ){
				for(int j = 0; j < N; j++){
					// 突然変異する確率は0.0333
					if(Math.random()<=0.0333){
						new_pop[i].genotype[j] = (int)(Math.random()*(N-j));
					}
				}
			}

			// old_popとnew_popの入れ替え
			Indiv[] tmp = new_pop;
			new_pop = old_pop;
			old_pop = tmp;

			// 世代のカウント
			cnt++;

		}

	}

	static void init() {/* 初期化(初期集団生成)*/  
		int i, j;
		old_pop = population0;/* 集団のポインタに実体の配列を割り当てる*/  
		new_pop = population1;/* 集団のポインタに実体の配列を割り当てる*/  
		for (i = 0; i < POP_SIZE; i++) {
			population0[i] = new Indiv();
			population1[i] = new Indiv();
			for (j = 0; j < N; j++) {
				old_pop[i].genotype[j] = (int) (Math.random() * (N - j));/* ランダムに遺伝子を生成する*/  
			}
		}
	}

	static void calcPhenotype(Indiv t) {/* tで指した個体に対して表現型を計算する。*/  
		/* 結果はt.phenotypeへ入る*/  
		int j, k;
		int w[] = new int[N];
		for (j = 0; j < N; j++)
			w[j] = j;
		for (j = 0; j < N; j++) {
			t.phenotype[j] = w[t.genotype[j]];
			for (k = t.genotype[j]; k < N - j - 1; k++)
				w[k] = w[k + 1];
		}
	}

	static void calcFitness(Indiv t) {/* tで指した個体に対して適応度を計算する。*/  
		/* 事前にphenotypeを計算する必要あり*/  
		/* 結果はt.fitnessへ入る。*/  
		int ng, i, j;
		ng = 0;
		for (i = 0; i < N; i++) {
			for (j = i + 1; j < N; j++) {
				if (t.phenotype[j] - t.phenotype[i] == j - i)
					ng++;
				if (t.phenotype[i] - t.phenotype[j] == j - i)
					ng++;
			}
		}
		t.fitness = (float) (1.0 / (1.0 + ng));
	}

	static void print(Indiv t) {/* tの個体の遺伝子型,表現型,実際のクイーンの配置を表示する*/  
		int j, k;
		int r[] = new int[N], w[] = new int[N];

		for (j = 0; j < N; j++)
			System.out.print(t.genotype[j] + " ");
		System.out.println();
		for (j = 0; j < N; j++)
			w[j] = j;
		for (j = 0; j < N; j++) {
			r[j] = w[t.genotype[j]];
			for (k = t.genotype[j]; k < N - j - 1; k++)
				w[k] = w[k + 1];
		}
		for (j = 0; j < N; j++)
			System.out.print(r[j] + " ");
		System.out.println();
		printp(r);
	}

	static void printp(int g[]) {
		int i, j;
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				if (g[j] == i)
					System.out.print("＠");
				else
					System.out.print("ロ");
			}
			System.out.println();
		}
	}
}

class Indiv {
	int genotype[] = new int[GAQueen.N];
	int phenotype[] = new int[GAQueen.N];
	float fitness;
}