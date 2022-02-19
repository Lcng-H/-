package PerutationCipher;

import java.util.Arrays;
import java.util.Scanner;

public class Permutation{
	
	public static void main(String[] args) {
//		int choice = -1;
//		int flag = 0;
//		while (flag == 0) {
//			menu();
//			Scanner in2 = new Scanner(System.in);
//			choice = in2.nextInt();
//			switch (choice) {
//			case 1:
//				code(choice);
//				break;
//			case 2:
//				code(choice);
//				break;
//			case 3:
//				flag++;
//				break;
//			default:
//				System.out.println("输入出错！");
//				break;
//			}
//		}
	}
	
	public static void menu() {
		System.out.println();
		System.out.println();
		System.out.println("***********PermutationCipher**********");
		System.out.println("1.明文加密");
		System.out.println("2.密文解密");
		System.out.println("3.退出");
		System.out.println("**************************************");
		System.out.print("请选择菜单功能：");
	}
	
	
	public static String code(int choice, String message, String key){
		int base = 0;
		int group;// 矩阵行数（组数）
		String str = "";
//		if (choice == 1)
//			System.out.print("请输入明文：");
//		else
//			System.out.print("请输入密文：");
//		Scanner in1 = new Scanner(System.in);
//		String message = in1.nextLine();
//		System.out.print("请输入密钥：");
//		Scanner in2 = new Scanner(System.in);
//		String key = in2.nextLine();
		char keyArrayInOrder[] = key.toCharArray();
		Arrays.sort(keyArrayInOrder);
		char keyArray[] = key.toCharArray();
		for (int i = 0; i < keyArray.length; i++) {// 将输入的密钥转化为数字序号
			for (int j = 0; j < keyArrayInOrder.length; j++) {
				if (keyArray[i] == keyArrayInOrder[j]) {
					keyArray[i] = (char) (j + '0');
					break;
				}
			}
		}

		message = message.replaceAll("\\s*", "");// 清除message中的空格
		message = message.toLowerCase();// 转换为小写字母
		group = (int) (Math.ceil(message.length() / (double) key.length()));// group为组数
		char messageArray[] = message.toCharArray();// 将输入的信息转为字符数组保存

		if (choice == 1)
			for (int i = 0; i < group; i++) {
				for (int j = 0; j < keyArray.length; j++) {
					if (base + Integer.parseInt(String.valueOf(keyArray[j])) < messageArray.length)
						str += (messageArray[base + Integer.parseInt(String.valueOf(keyArray[j]))]);
					else
						str += ("#");
				}
				base += key.length();
			}
		if (choice == 2) {
			for (int i = 0; i < group; i++) {
				for (int j = 0; j < keyArray.length; j++) {
					for (int k = 0; k < keyArray.length; k++) {
						if (Integer.parseInt(String.valueOf(keyArray[k])) == j && messageArray[k + base] != '#') {
							str += (messageArray[k + base]);
							break;
						}
					}
				}
				base += key.length();
			}
		}
		return str;
	}
}