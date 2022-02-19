package Polyalphabetic;

import java.util.Arrays;
import java.util.Scanner;

public class AutokeyPlaintext {

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

	 public String Encryptioncore(String key, String plaintext) { 
			char key0[] = new char[key.length()];//储存key方便接下来进行字母与数字的对应；
			char plaintext0[] = new char[plaintext.length()];//同上
			char ciphertext0[] = new char[plaintext.length()];//方便加密循环内明文与密文的相互对应;
			int a;
			key0 = key.toCharArray();//key0仅在此处使用，在进入加密循环以后明文将与密文相互对应；
			plaintext0 = plaintext.toCharArray();
				for(int i = 0;i <= plaintext.length()-1;i++) {//正式进入加密循环前先处理key，得到第一份密文；		
					a = (plaintext0[i] - 97) - (key0[i%key.length()] - 97 );
					
					if(a >= 0) {
						a = a % 26 + 97;}
					else {
						a = (a+26) % 26 + 97;
					}
						if(i - key.length() < 0) {
							key0[i%key.length()] = plaintext0[i];
						}else {
					        key0[i%key.length()] = plaintext0[i - key.length()];//更新密钥，每进行一轮加密就将密文装入密钥
					     }
					ciphertext0[i] = (char)a;//通过ASC||码对字母和数字进行转换；
				}
			String cipher = new String(ciphertext0);
			return cipher;
 }
	
	public String Decryptioncore(String key, String plaintext) {
			char key0[] = new char[key.length()];//储存key方便接下来进行字母与数字的对应；
			char plaintext0[] = new char[plaintext.length()];//同上
			char ciphertext0[] = new char[plaintext.length()];//方便加密循环内明文与密文的相互对应;
			key0 = key.toCharArray();//key0仅在此处使用，在进入加密循环以后明文将与密文相互对应；
			plaintext0 = plaintext.toCharArray();
				for(int i = 0;i <= plaintext.length()-1;i++) {//正式进入加密循环前先处理key，得到第一份密文；
					int a = (plaintext0[i] - 97) + (key0[i%key.length()] - 97);
						a = a % 26 + 97;
					key0[i%key.length()] = (char)a;//更新密钥，每进行一轮加密就将密文装入密钥
					ciphertext0[i] = (char)a;//通过ASC||码对字母和数字进行转换；
				}
			String cipher = new String(ciphertext0);
			return cipher;
	}
	public String deal(String input) {
		String input1 = input.toLowerCase();
		String input2 = input1.replaceAll(" ", "");
		return input2;

	}
}
