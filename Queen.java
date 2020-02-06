
import java.util.*;

class Queen {

	final static int SIZE = 8;

	static LinkedList<int[]> l1 = new LinkedList<int[]>();

	/* �Ֆʃf�[�^�z��q�����X�g�̍Ō�ɓ���� */
	static void enqueue(int q[]) {
		l1.offer(q.clone());
	}

	/* ���X�g�̐擪����Ֆʃf�[�^�����o�� */
	static int[] dequeue() {
		/* ����Ɏ��o�����Ƃ��͔z�񂪕Ԃ���� */
		return l1.poll();
		/* ���X�g����̎���null���Ԃ���� */
	}

	/* ���X�g�̐擪����Ֆʃf�[�^�����o�� */
	static int[] pop() {
		try {
			/* ����Ɏ��o�����Ƃ��͔z�񂪕Ԃ���� */
			return l1.pop();
		} catch (NoSuchElementException e) {
			/* ���X�g����̎���null���Ԃ���� */
			return null;
		}
	}

	/* �Ֆʃf�[�^�z��q�����X�g�̐擪�ɓ���� */
	static void push(int q[]) {
		l1.push(q.clone());
	}

	/* l1�Ɋi�[����Ă���v�f�̐���Ԃ� */
	static int size() {
		return l1.size();
	}

	/* �Ֆʃf�[�^q��queen�̗����𒲂ׂ� */
	static int check_solve(int q[]) {
		/* �Ԃ�l-1�Ȃ�Η����؂ɋ���� */
		int i, j, c = 0;
		/* �Ԃ�l0�ȏ�Ȃ�Η����؂ɋ�����Ĕz�u�ł��Ă����̐��ł��� */
		for (i = 0; i < SIZE; i++) {
			if (q[i] != 0) {
				for (j = 0; j < SIZE; j++) {
					if (j != i && q[j] != 0) {
						if (q[j] == q[i])
							return -1;
						if ((q[i] - i) == (q[j] - j))
							return -1;
						if ((q[i] + i) == (q[j] + j))
							return -1;
					}
				}
				c++;
			}
		}
		return c;
	}

	/* �Ֆʃf�[�^q�̎��ۂ̋�̔z�u��\������ */
	static void printboard(int q[]) {
		int i, j;
		for (j = 1; j <= SIZE; j++) {
			for (i = 0; i < SIZE; i++) {
				if (q[i] == j)
					System.out.print("Q ");
				else
					System.out.print("_ ");
			}
			System.out.println();
		}
	}

	/* �Ֆʃf�[�^q�̒l��\�������� */
	static void printdata(int q[]) {
		int i;
		for (i = 0; i < SIZE; i++)
			System.out.print(q[i] + " ");
		System.out.println();
	}

	public static void main(String args[]) {
		int q[] = new int[SIZE]; // ���[�g�m�[�h
		int n[] = new int[SIZE]; // �����Ă���_�̂���
		int check = 0;
<<<<<<< HEAD
		int index = 0;
		
		
		// ルートノードを初期値0で生成する。
		for(int i=0;i<SIZE;i++){
		        q[i] = 0;
=======

		// ���[�g�m�[�h�������l0�Ő�������B
		for (int i = 0; i < SIZE; i++) {
			q[i] = 0;
>>>>>>> 29a51de7669e8a70f2aa6484983a2642d1660e50
		}

		System.out.println("�[���D��T��");

		// step1 ���[�g�m�[�h�����X�gl1�ɓ����
		push(q);

		// step2 if L1 = �� then �T���͎��s�A�I��
		while (!l1.isEmpty()) {

			// step3 L1�̐擪�̐ߓ_n����菜��
			n = pop();

			// step4 if n���ڕW�ߓ_�ł��� then �T���͐����A�I��
			if (check_solve(n) == SIZE) {
				printdata(n);
				printboard(n);
				break;
			}

			/*
			 * step5 if n���W�J�ł���i�q�ߓ_�����j then �W�J���A����ꂽ�q�ߓ_�����X�gl1�̐擪�ɓ���� �q�ߓ_����n�ւ̃|�C���^������
			 * step2�� else step2��
			 */
<<<<<<< HEAD
			if(check_solve(n) <= SIZE){
			        for(int i=7; i>=0; i--){

				        n[index] = i;
				        
				        if(check_solve(n) >= 0 && check_solve(n) <=SIZE){
					        push(n);
						//System.out.println(n);
				        }
				}

			}
		    
=======
			if (check_solve(n) <= SIZE) {
				for (int i = 1; i <= SIZE; i++) {
					if (check_solve(n) >= 0 && check_solve(n) <= SIZE) {
						push(n);
						System.out.println(n);
					}
				}

			}

>>>>>>> 29a51de7669e8a70f2aa6484983a2642d1660e50
		}

	}
}