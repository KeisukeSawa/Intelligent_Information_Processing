
import java.util.*;

class Queen {

	final static int SIZE = 8;

	static LinkedList<int[]> l1 = new LinkedList<int[]>();

	/* ”Õ–Êƒf[ƒ^”z—ñq‚ğƒŠƒXƒg‚ÌÅŒã‚É“ü‚ê‚é */
	static void enqueue(int q[]) {
		l1.offer(q.clone());
	}

	/* ƒŠƒXƒg‚Ìæ“ª‚©‚ç”Õ–Êƒf[ƒ^‚ğæ‚èo‚· */
	static int[] dequeue() {
		/* ³í‚Éæ‚èo‚¹‚½‚Æ‚«‚Í”z—ñ‚ª•Ô‚³‚ê‚é */
		return l1.poll();
		/* ƒŠƒXƒg‚ª‹ó‚Ì‚Ínull‚ª•Ô‚³‚ê‚é */
	}

	/* ƒŠƒXƒg‚Ìæ“ª‚©‚ç”Õ–Êƒf[ƒ^‚ğæ‚èo‚· */
	static int[] pop() {
		try {
			/* ³í‚Éæ‚èo‚¹‚½‚Æ‚«‚Í”z—ñ‚ª•Ô‚³‚ê‚é */
			return l1.pop();
		} catch (NoSuchElementException e) {
			/* ƒŠƒXƒg‚ª‹ó‚Ì‚Ínull‚ª•Ô‚³‚ê‚é */
			return null;
		}
	}

	/* ”Õ–Êƒf[ƒ^”z—ñq‚ğƒŠƒXƒg‚Ìæ“ª‚É“ü‚ê‚é */
	static void push(int q[]) {
		l1.push(q.clone());
	}

	/* l1‚ÉŠi”[‚³‚ê‚Ä‚¢‚é—v‘f‚Ì”‚ğ•Ô‚· */
	static int size() {
		return l1.size();
	}

	/* ”Õ–Êƒf[ƒ^q‚Åqueen‚Ì—˜‚«‚ğ’²‚×‚é */
	static int check_solve(int q[]) {
		/* •Ô‚è’l-1‚È‚ç‚Î—˜‚«‹Ø‚É‹î‚ª‚ ‚é */
		int i, j, c = 0;
		/* •Ô‚è’l0ˆÈã‚È‚ç‚Î—˜‚«‹Ø‚É‹î‚ª–³‚­‚Ä”z’u‚Å‚«‚Ä‚¢‚é‹î‚Ì”‚Å‚ ‚é */
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

	/* ”Õ–Êƒf[ƒ^q‚ÌÀÛ‚Ì‹î‚Ì”z’u‚ğ•\¦‚·‚é */
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

	/* ”Õ–Êƒf[ƒ^q‚Ì’l‚ğ•\¦‚³‚¹‚é */
	static void printdata(int q[]) {
		int i;
		for (i = 0; i < SIZE; i++)
			System.out.print(q[i] + " ");
		System.out.println();
	}

	public static void main(String args[]) {
		int q[] = new int[SIZE]; // ƒ‹[ƒgƒm[ƒh
		int n[] = new int[SIZE]; // ¡Œ©‚Ä‚¢‚é“_‚Ì‚±‚Æ
		int check = 0;
<<<<<<< HEAD
		int index = 0;
		
		
		// ãƒ«ãƒ¼ãƒˆãƒãƒ¼ãƒ‰ã‚’åˆæœŸå€¤0ã§ç”Ÿæˆã™ã‚‹ã€‚
		for(int i=0;i<SIZE;i++){
		        q[i] = 0;
=======

		// ƒ‹[ƒgƒm[ƒh‚ğ‰Šú’l0‚Å¶¬‚·‚éB
		for (int i = 0; i < SIZE; i++) {
			q[i] = 0;
>>>>>>> 29a51de7669e8a70f2aa6484983a2642d1660e50
		}

		System.out.println("[‚³—Dæ’Tõ");

		// step1 ƒ‹[ƒgƒm[ƒh‚ğƒŠƒXƒgl1‚É“ü‚ê‚é
		push(q);

		// step2 if L1 = ‹ó then ’Tõ‚Í¸”sAI—¹
		while (!l1.isEmpty()) {

			// step3 L1‚Ìæ“ª‚Ìß“_n‚ğæ‚èœ‚­
			n = pop();

			// step4 if n‚ª–Ú•Wß“_‚Å‚ ‚é then ’Tõ‚Í¬Œ÷AI—¹
			if (check_solve(n) == SIZE) {
				printdata(n);
				printboard(n);
				break;
			}

			/*
			 * step5 if n‚ª“WŠJ‚Å‚«‚éiqß“_‚ğ‚Âj then “WŠJ‚µA“¾‚ç‚ê‚½qß“_‚ğƒŠƒXƒgl1‚Ìæ“ª‚É“ü‚ê‚é qß“_‚©‚çn‚Ö‚Ìƒ|ƒCƒ“ƒ^‚ğ‚Â‚¯‚é
			 * step2‚Ö else step2‚Ö
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