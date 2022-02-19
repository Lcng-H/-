package Polyalphabetic;

import java.util.Arrays;
import java.util.Scanner;
public class vigenere {

	public static void main(String[] args) {
//		Encryption();
//		Decryption();
	}
		
		
	public void Encryption() {
		System.out.println("Please enter the key");
		Scanner inputkey = new Scanner(System.in);
		String key = inputkey.nextLine();
		char key0[] = new char[key.length()];
		key0 = key.toCharArray();
		int count1 = 0;
		for(int i = 0;i < key.length();i++) {
			if(key0[i] - 97 < 0 || key0[i] - 97 > 26) {
				System.out.println("输入密钥格式错误，请检查输入!");
				count1++;
				break;
			}
		}

		System.out.println("Please enter the plaintext");
		Scanner inputplaintext = new Scanner(System.in);
		String plaintext = inputplaintext.nextLine();
		char plaintext0[] = new char[plaintext.length()];
		plaintext0 = plaintext.toCharArray();
		int count2 = 0;
		for(int i = 0;i < plaintext0.length;i++) {
			if(plaintext0[i] - 97 < 0 || plaintext0[i] - 97 > 26) {
				System.out.println("输入明文格式错误，请检查输入!");
				count2++;
				break;
			}
		}
		if(count1 == 0 && count2 == 0) {
		System.out.println(Encryptioncore(key, plaintext));
		}
	}

	public void Decryption() {
		System.out.println("Please enter the key");
		Scanner inputkey = new Scanner(System.in);
		String key = inputkey.nextLine();
		char key0[] = new char[key.length()];
		key0 = key.toCharArray();
		int count1 = 0;
		for(int i = 0;i < key.length();i++) {
			if(key0[i] - 97 < 0 || key0[i] - 97 > 26) {
				System.out.println("输入密钥格式错误，请检查输入!");
				count1++;
				break;
			}
		}

		System.out.println("Please enter the ciphertext");
		Scanner inputciphertext = new Scanner(System.in);
		String ciphertext = inputciphertext.nextLine();
		char plaintext0[] = new char[ciphertext.length()];
		plaintext0 = ciphertext.toCharArray();
		int count2 = 0;
		for(int i = 0;i < plaintext0.length;i++) {
			if(plaintext0[i] - 97 < 0 || plaintext0[i] - 97 > 26) {
				System.out.println("输入密文格式错误，请检查输入!");
				count2++;
				break;
			}
		}
		if(count1 == 0 && count2 == 0) {
		System.out.println(Decryptioncore(key, ciphertext));
		}
	}

	public String Encryptioncore(String input1, String input2) {
		int keylength = deal(input1).length();//deal()处理输入，全部降为小写，除去空格
		int plaintextlength = deal(input2).length();
		char plaintext[] = deal(input2).toCharArray();
		char key[] = deal(input1).toCharArray();
		char key0[] = new char[plaintextlength];
		char cipher[] = new char[plaintextlength];
		for (int i = 0; i <= plaintextlength - 1; i++) {
			int asike = 0;
			int number = i % keylength;
			key0[i] = key[number];
			int a = key0[i];
			int b = plaintext[i];
			int algorithm = (a - 97) + (b - 97);
			if (algorithm < 0) {
				asike = (algorithm + 26) % 26 + 97;
			} else {
				asike = algorithm % 26 + 97;
			}
			char c = (char) asike;
			cipher[i] = c;
		}
		String p = String.valueOf(cipher);
		return p;
	}

	public String Decryptioncore(String input1, String input2) {
		int keylength = deal(input1).length();
		int ciphertextlength = deal(input2).length();
		char ciphertext[] = deal(input2).toCharArray();
		char key[] = deal(input1).toCharArray();
		char key0[] = new char[ciphertextlength];
		char cipher[] = new char[ciphertextlength];
		for (int i = 0; i <= ciphertextlength - 1; i++) {
			int asike = 0;
			int number = i % keylength;
			key0[i] = key[number];
			int a = key0[i];
			int b = ciphertext[i];
			int algorithm = (b - 97) - (a - 97);
			if (algorithm < 0) {
				asike = (algorithm + 26) % 26 + 97;
			} else {
				asike = algorithm % 26 + 97;
			}
			char c = (char) asike;
			cipher[i] = c;
		}
		String p = String.valueOf(cipher);
		return p;
	}

	public String deal(String input) {
		String input1 = input.toLowerCase();
		String input2 = input1.replaceAll(" ", "");
		return input2;

	}
}

