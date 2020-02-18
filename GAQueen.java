import java.util.*;

class GAQueen {
	static final int POP_SIZE = 20;
	static final int N = 16;

	static Indiv old_pop[], new_pop[];
	static Indiv population0[] = new Indiv[POP_SIZE];
	static Indiv population1[] = new Indiv[POP_SIZE];

	public static void main(String args[]) {
		// �����W�c�ݒ�
		init();

		// ������ڂ��J�E���g����
		int cnt = 0;

		while (true) {
			float sumfitness = 0;
			// �e�̂̓K���x�̌v�Z
			for (int i = 0; i < POP_SIZE; i++) {
				// �\���^�̌v�Z
				calcPhenotype(old_pop[i]);
				// �K�p�x�̌v�Z
				calcFitness(old_pop[i]);
				// �W�c�̕]��
				if (old_pop[i].fitness >= 1.0) {
					print(old_pop[i]);
					System.out.println("���������߂邽�߂�"+cnt+"���ォ����܂����B");
					return;
				}
				sumfitness = sumfitness + old_pop[i].fitness;
			}
			// �I��
			// ���[���b�g�\
			int[] roulette = new int[POP_SIZE];
			// ���[���b�g���g�p�����K�p�x�̍��v��������ϐ�
			float new_sumfitness;
			for (int i = 0; i < POP_SIZE; i++) {
				// �����_���Ȓl���쐬
				float ranbom = ((float) Math.random() * sumfitness);
				new_sumfitness = 0;
				for (int j = 0; j < POP_SIZE; j++) {
					// �K�p�x�̍��v���X�V
					new_sumfitness = new_sumfitness + old_pop[j].fitness;
					if (ranbom <= new_sumfitness) {
						roulette[i] = j;
						break;
					}
				}
			}
			// ����
			// �����̓y�A�ōs�����߁A2�����₷
			for (int i = 0; i < POP_SIZE; i=i+2) {
				// �����̊m����0.6
				if(Math.random()<=0.6){
					// �����ꏊ���w��
					int index = (int)Math.random()*N;
					// ����������
					for(int j = 0; j < N; j++){
						// ��������ꍇ
						if(index > j){
							//new_pop[i].genotype[j]=old_pop[roulette[i+1]].genotype[j];
							//new_pop[i+1].genotype[j]=old_pop[roulette[i]].genotype[j];
							
							new_pop[i].genotype[j]=old_pop[i+1].genotype[j];
							new_pop[i+1].genotype[j]=old_pop[i].genotype[j];
						}
						// �������Ȃ��ꍇ
						else{
							//new_pop[i].genotype[j]=old_pop[roulette[i]].genotype[j];
							//new_pop[i+1].genotype[j]=old_pop[roulette[i+1]].genotype[j];
							
							new_pop[i].genotype[j]=old_pop[roulette[i+1]].genotype[j];
							new_pop[i+1].genotype[j]=old_pop[roulette[i]].genotype[j];
						}
					}
				}

			}

			// �ˑR�ψ�
			for(int i=0; i < POP_SIZE; i++ ){
				for(int j = 0; j < N; j++){
					// �ˑR�ψق���m����0.0333
					if(Math.random()<=0.0333){
						new_pop[i].genotype[j] = (int)(Math.random()*(N-j));
					}
				}
			}

			// old_pop��new_pop�̓���ւ�
			Indiv[] tmp = new_pop;
			new_pop = old_pop;
			old_pop = tmp;

			// ����̃J�E���g
			cnt++;

		}

	}

	static void init() {/* ������(�����W�c����)*/  
		int i, j;
		old_pop = population0;/* �W�c�̃|�C���^�Ɏ��̂̔z������蓖�Ă�*/  
		new_pop = population1;/* �W�c�̃|�C���^�Ɏ��̂̔z������蓖�Ă�*/  
		for (i = 0; i < POP_SIZE; i++) {
			population0[i] = new Indiv();
			population1[i] = new Indiv();
			for (j = 0; j < N; j++) {
				old_pop[i].genotype[j] = (int) (Math.random() * (N - j));/* �����_���Ɉ�`�q�𐶐�����*/  
			}
		}
	}

	static void calcPhenotype(Indiv t) {/* t�Ŏw�����̂ɑ΂��ĕ\���^���v�Z����B*/  
		/* ���ʂ�t.phenotype�֓���*/  
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

	static void calcFitness(Indiv t) {/* t�Ŏw�����̂ɑ΂��ēK���x���v�Z����B*/  
		/* ���O��phenotype���v�Z����K�v����*/  
		/* ���ʂ�t.fitness�֓���B*/  
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

	static void print(Indiv t) {/* t�̌̂̈�`�q�^,�\���^,���ۂ̃N�C�[���̔z�u��\������*/  
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
					System.out.print("��");
				else
					System.out.print("��");
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