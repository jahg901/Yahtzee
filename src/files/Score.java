package files;

import java.io.IOException;
import java.util.Arrays;

public class Score {
	
	public static int Number(int[] n, int a) {
		int counter = 0;
		for (int i : n) {
			if (i == a) counter += a;
		}
		return counter;
	}
	
	public static int ThreeOfKind(int[] n) {
		Arrays.sort(n);
		for (int i = 0; i < 3; i++) {
			if (n[i] == n[i + 1] && n[i] == n[i + 2]) {
				return Chance(n);
			}
		}
		return 0;
	}
	
	public static int FourOfKind(int[] n) {
		Arrays.sort(n);
		for (int i = 0; i < 2; i++) {
			if (n[i] == n[i + 1] && n[i] == n[i + 2] && n[i] == n[i + 3]) {
				return Chance(n);
			}
		}
		return 0;
	}
	
	public static int FullHouse(int[] n) {
		Arrays.sort(n);
		return (n[0] == n[1] && n[3] == n[4] && (n[2] == n[0] || n[2] == n[4])) ? 25 : 0;
	}
	
	public static int SmallStraight(int[] n) {
		Arrays.sort(n);
		int c = 0;
		for (int i = 1; i < 5; i++) {
			if (n[i] - n[i - 1] == 1) {
				c++;
			} else if (n[i] != n[i - 1] && i != 1) {
				break;
			}
		}
		return (c >= 3) ? 30 : 0;
	}
	
	public static int LargeStraight(int[] n) {
		Arrays.sort(n);
		for (int i = 1; i < 5; i++) {
			if (n[i] - n[i - 1] != 1) return 0;
		}
		return 40;
	}
	
	public static int Yahtzee(int[] n) {
		for (int a : n) {
			if (a != n[0]) return 0;
		}
		return 50;
	}
	
	public static int Chance(int[] n) {
		return n[0] + n[1] + n[2] + n[3] + n[4];
	}
	
/*	public static void main(String[] args) throws IOException {
		java.io.BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String[] s = stdin.readLine().split(" ");
		int[] n = new int[5];
		for (int i = 0; i < 5; i++) {
			n[i] = Integer.parseInt(s[i]);
		}
		System.out.println("Ones: " + Number(n, 1));
		System.out.println("Twos: " + Number(n, 2));
		System.out.println("Threes: " + Number(n, 3));
		System.out.println("Fours: " + Number(n, 4));
		System.out.println("Fives: " + Number(n, 5));
		System.out.println("Sixes: " + Number(n, 6));
		System.out.println();
		System.out.println("Three of a Kind: " + ThreeOfKind(n));
		System.out.println("Four of a Kind: " + FourOfKind(n));
		System.out.println("Full House: " + FullHouse(n));
		System.out.println("Small Straight: " + SmallStraight(n));
		System.out.println("Large Straight: " + LargeStraight(n));
		System.out.println("Yahtzee: " + Yahtzee(n));
		System.out.print("Chance: " + Chance(n));
	} */
	
}
