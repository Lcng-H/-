package PerutationCipher;

import java.util.Scanner;

public class column_permutation {

	public static void main(String[] args) {
		
		Encryption();
		Decryption();

	}
	public static void Decryption() {
		System.out.println("Please enter the key");
		Scanner inputkey = new Scanner(System.in);
		String key = inputkey.nextLine();

		System.out.println("Please enter the text");
		Scanner inputplaintext = new Scanner(System.in);
		String text = inputplaintext.nextLine();

		System.out.println(Decryptioncore(key, text));

	}
	public static void Encryption() {
		System.out.println("Please enter the key");
		Scanner inputkey = new Scanner(System.in);
		String key = inputkey.nextLine();

		System.out.println("Please enter the text");
		Scanner inputplaintext = new Scanner(System.in);
		String text = inputplaintext.nextLine();

		System.out.println(Encryptioncore(key, text));

	}
	public static String Decryptioncore(String key, String input) {
		int keylength = key.length();
		int a = input.length();
		if (keylength > input.length()) {//用#补全
			for (int q = 0; q < keylength - a; q++) {
				input = input + '#';
			}
		}
	    if(input.length()%keylength > 0) {
			for(int q = 0; q < keylength - (a%keylength); q++) {
				input = input + '#';
			}
		}
		char text[] = new char[input.length()];
		char interplaintext[] = deal(input).toCharArray();
		int num[] = new int[keylength];
		char key1[] = key.toCharArray();
		
		for (int k = 0; k < keylength; k++) {
			num[k] = 0;
			for (int l = 0; l < keylength; l++) {

				if (key1[l] <= key1[k]) {

					num[k] = num[k] + 1;
				}
				if (key1[l] == key1[k] && l > k)
					num[k] = num[k] - 1;
			}
		}
		for (int i = 0; i < input.length()/keylength; i++) {
			for (int j = 0; j < keylength; j++) {
					text[j + i*keylength] = interplaintext[num[j] + i*keylength - 1];
			}
		}

		String p = new String(text);
		return p;
	}
	
	public static String Encryptioncore(String key, String input) {
		int keylength = key.length();
		int a = input.length();
		if (keylength > input.length()) {//用#补全
			for (int q = 0; q < keylength - a; q++) {
				input = input + '#';
			}
		}
	    if(input.length()%keylength > 0) {
			for(int q = 0; q < keylength - (a%keylength); q++) {
				input = input + '#';
			}
		}
		char text[] = new char[input.length()];
		char interplaintext[] = deal(input).toCharArray();
		int num[] = new int[keylength];
		char key1[] = key.toCharArray();
		
		for (int k = 0; k < keylength; k++) {
			num[k] = 0;
			for (int l = 0; l < keylength; l++) {

				if (key1[l] <= key1[k]) {

					num[k] = num[k] + 1;
				}
				if (key1[l] == key1[k] && l > k)
					num[k] = num[k] - 1;
			}
		}
		for (int i = 0; i < input.length()/keylength; i++) {
			for (int j = 0; j < keylength; j++) {
					text[num[j] + i*keylength - 1] = interplaintext[j + i*keylength];
			}
		}

		String p = new String(text);
		return p;
	}

	
	public static String deal(String input) {
		String input1 = input.toLowerCase();
		String input2 = input1.replaceAll(" ","");
		return input2;
	}
}
