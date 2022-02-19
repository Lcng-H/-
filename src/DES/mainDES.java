package DES;

import java.util.Scanner;

public class mainDES {

	public static void main(String[] args) {
		
		mainDES m = new mainDES();
		Encryption encry = new Encryption();
		Decryption decry = new Decryption();
		System.out.println("������7λ��Կ");
		Scanner inputkey = new Scanner(System.in);
		String input1 = inputkey.nextLine();
		input1 = m.filter(input1);

		char []key = input1.toCharArray();

		System.out.println("������8λ����");
		Scanner inputplaintext = new Scanner(System.in);
		String input2 = inputplaintext.nextLine();
		input2 = m.filter(input2);

		System.out.print("���� ��" + input2);
		System.out.println("");
		
		char []plaintext = input2.toCharArray();
		System.out.print("���� ��" +(encry.encrypt(plaintext, key)));
		System.out.println("");
		
		char []cipher = (encry.encrypt(plaintext, key)).toCharArray();
		System.out.print("���� ��" +(decry.decrypt(cipher, key)));

	}
	//ȥ���ո�,����ĸ������תΪ������
	public String filter(String input) {
		String input1 = input.replaceAll(" ", "");
		input1 = StrToBinstr(input1);
		return input1;

	}
	public String StrToBinstr(String str) { 
        char[] strChar = str.toCharArray(); 
        String result = ""; 
        String length;
        String a = "0";
        for (int i = 0; i < str.length(); i++) {
        	length = Integer.toBinaryString(strChar[i]);
        	if(length.length() < 8) {
        		length = a + length;
        	}
            result += length; 
        } 
        return result; 
    }
	
	public String BinstrToStr(String str) {
		String output = "";
		char aa[] = new char[str.length()];
		for(int i = 0,j = 0;i < str.length();i += 8,j++) {
			String temp = str.substring(i,i+8);
			int a = Integer.valueOf(temp,2);
			aa[j] = (char)a;
		}
		output = new String(aa);
		return output;
	}

}
