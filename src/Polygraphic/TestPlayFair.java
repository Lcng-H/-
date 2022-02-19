package Polygraphic;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPlayFair {

	static char[][] KeyMatrix;//密钥矩阵
	static String[] formatP;//格式化后的明文
	static String[] formatC;//格式化后的密文
	static String cipherString;
	static int k=0;//判断是第几次输入密文
	
	public char[][] getKeyMatrix(String key){
		char[] array=key.toCharArray();//输入的密钥
		char[] arraykey=new char[array.length];//去重复后的密钥，不超过原密钥长度
		String keykey="";//去重复后的密钥
		char[] zimu = new char[26];//替换掉i、j后的26个字母
		String zimu1="";
		char[] letter = new char[26];//去掉密钥后的字母
		char[][] matrix = new char[5][5];
		String aaa="";
		
		//把i和j当作一个字母*
		for(int i=0;i<26;i++){
			if((char)(65+i)=='I') {
				zimu1 += '*';
				i++;
			}
			else
				if((char)(65+i)!='J')
					zimu1 += (char)(65+i);
					
		}
		zimu=zimu1.toCharArray();
		
		//密钥去重：如果有重复字母，只输入最后一次出现的字母,密钥中的i、j换成*
		for(int i=array.length - 1;i>= 0;i--) {
			int r=0;
			for(int j=i-1;j>= 0;j--) {
				if(array[i] == array[j]) {
					r=1;
					break;
				}
			}
			if(r==0) {
				if(array[i] =='I' || array[i] =='J') {
					keykey+='*';
				}
				else
					keykey+=array[i];
			}
		}
		arraykey=keykey.toCharArray();
		for(int i = arraykey.length-1;i>=0;i--) {
			aaa += arraykey[i];
		}
		arraykey=aaa.toCharArray();
		
		//去除密钥字母后剩下的字母
		int num=0;
		for(int i=0;i<zimu.length;i++){
			int flag=0;
			for(int j=0;j<arraykey.length;j++){
				if(zimu[i] == arraykey[j]) {
					flag=1;
					break;
				}
			}
			if(flag==0) {
				letter[num]=zimu[i];
				num++;
			}
		}

		int index=0;
		int k=0;
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				//先把密钥写入矩阵
				if(index<arraykey.length){
					matrix[i][j]=arraykey[index];
					index++;
				}
				//把不重复的字母写入矩阵
				else 
					if(k<letter.length){
						matrix[i][j]=letter[k];
						k++;
					}
					else;
				System.out.print(matrix[i][j]+"   ");
				}
			System.out.println();
			}
			return matrix;
		}
	
	public String[] formatPlain(String plain) {
		Stack<Character> stack = new Stack<Character>();
		String[] dual_letter = new String[24];
		Character[] outs = new Character[2];
		char[] arrayplain1 = plain.toCharArray();
		String stringplain = "";
		for(int i=0;i<arrayplain1.length;i++) {
			if(arrayplain1[i] != 'I') {
				if(arrayplain1[i] == 'J') 
					stringplain += '*';
				else
					stringplain += arrayplain1[i];
			}
			else
				stringplain += '*';
		}
		char[] arrayplain = stringplain.toCharArray();
		int num = 0;
		int index=0;
		for(int i=0;i<arrayplain.length;i++) {
			dual_letter[index]="";
			if(num<2) {
				stack.push(arrayplain[i]);
				num++;
			}
			//最后剩下一个字母时
			if(i==arrayplain.length-1 && num ==1) {
				stack.push('X');
				num=2;
			}
			
			while(num == 2) {
				//如果字母对的两个字母是相同的情况
				if(arrayplain[i] == arrayplain[i-1]) {
					stack.pop();
					stack.push('X');
					i--;
				}
				else {
					while(!stack.isEmpty()) {
						for(int k=1;k>=0;k--) {
							outs[k] = (Character)stack.pop();
						}
						for(int q=0;q<outs.length;q++) {
							//System.out.print(outs[q]);
							dual_letter[index] += outs[q];
						}
						System.out.print(dual_letter[index]);
						index++;
					}
					num = 0;
					break;
				}
			}
		}
		System.out.println();
		return dual_letter;
	}
	
	public String format(String str) {
	    String regEx="[^(A-Za-z)]";  //正则表达式
	    Pattern p = Pattern.compile(regEx);   //
	    Matcher getStr = p.matcher(str);
	    str = getStr.replaceAll("").trim();
	    str = str.toUpperCase();
	    return str;
	}
	
	//加密
	public String[] encode(String plain,String key) {

		System.out.println("密钥矩阵为：");
		KeyMatrix = getKeyMatrix(key);
		System.out.print("格式化后的明文为：");
		formatP = formatPlain(plain);
		
		cipherString="";
		
		int m=0;
		String[] EnCipher = new String[24];//加密后的密文
		int n=0;

		System.out.print("加密后的密文为：");
		
		while(formatP[m]!=null) {
			EnCipher[n] = "";
			int[][] position = getPosition(formatP[m],KeyMatrix);
			int rowA = position[0][0];
			int colA = position[0][1];
			int rowB = position[1][0];
			int colB = position[1][1];
			int temp;
			
			//同一行
			if(rowA == rowB) {
				EnCipher[n] += KeyMatrix[rowA][(colA+1)%5];
				EnCipher[n] += KeyMatrix[rowA][(colB+1)%5];
			}
			//同一列
			else if(colA == colB) {
				EnCipher[n] += KeyMatrix[(rowA+1)%5][colA];
				EnCipher[n] += KeyMatrix[(rowB+1)%5][colA];
			}
			else {
				temp = colA;
				colA = colB;
				colB = temp;
				EnCipher[n] += KeyMatrix[rowA][colA];
				EnCipher[n] += KeyMatrix[rowB][colB];
			}
			System.out.print(EnCipher[n]);
			cipherString += EnCipher[n];
			n++;
			m++;
		}
		System.out.println();
		return EnCipher;
	}
	
	public String[] formatCipher(String cipher, String key) {
		String[] arrayC = new String[cipher.length()];
		cipher=cipher.toUpperCase();
		int index = 0;
		if(cipher.length()%2==0) {
			System.out.print("格式化后的密文为：");
			for(int i=0;i<cipher.length()-1;index++,i+=2) {
				arrayC[index] = cipher.substring(i, i+2);
				System.out.print(arrayC[index]);
			}
			System.out.println();
			System.out.print("解密后的明文为：");
		}
		else {
			System.out.println("密文不正确！");
			System.out.print("请输入正确密文：");
			Scanner input = new Scanner(System.in);
			cipher = input.nextLine();
			k++;
			decode(cipher,key);
		}

		return arrayC;
	}
	
	public String[] decode(String cipher,String key) {
		if(k == 0) {
			System.out.println("密钥矩阵为：");
			KeyMatrix = getKeyMatrix(key);
		}
		
		formatC = formatCipher(cipher, key);
		
		int m=0;
		String[] DeCipher = new String[24];//解密后的明文
		int n=0;
		
		while(formatC[m]!=null) {
			DeCipher[n] = "";
			int[][] position = getPosition(formatC[m],KeyMatrix);
			int rowA = position[0][0];
			int colA = position[0][1];
			int rowB = position[1][0];
			int colB = position[1][1];
			int temp;
			
			//同一行
			if(rowA == rowB) {
				if(colA == 0) 
					colA +=5;
				if(colB == 0) 
					colB +=5;
				DeCipher[n] += KeyMatrix[rowA][(colA-1)%5];
				DeCipher[n] += KeyMatrix[rowA][(colB-1)%5];
			}
			//同一列
			else if(colA == colB) {
				if(rowA == 0) 
					rowA +=5;
				if(rowB == 0) 
					rowB +=5;
				DeCipher[n] += KeyMatrix[(rowA-1)%5][colA];
				DeCipher[n] += KeyMatrix[(rowB-1)%5][colA];
			}
			else {
				temp = colA;
				colA = colB;
				colB = temp;
				DeCipher[n] += KeyMatrix[rowA][colA];
				DeCipher[n] += KeyMatrix[rowB][colB];
			}
			System.out.print(DeCipher[n]);
			n++;
			m++;
		}
		System.out.println();
		return DeCipher;
	}

	public int[][] getPosition(String dual,char[][] KeyMatrix) {
		//位置矩阵的值代表字母在密钥矩阵中的行和列
		//[0][0]:字母A的行;[0][1]:字母A的列;
		//[1][0]:字母B的行;[1][1]:字母B的列;
		
		int[][] position = new int[2][2];
		//确保是双字母的情况
		if(dual.length()==2) {
			char a = dual.charAt(0);
			char b = dual.charAt(1);
			for(int i=0;i<KeyMatrix.length;i++) {
				for(int j=0;j<KeyMatrix[0].length;j++) {
					if(a == KeyMatrix[i][j]) {
						position[0][0] = i;
						position[0][1] = j;
					}
					if(b == KeyMatrix[i][j]) {
						position[1][0] = i;
						position[1][1] = j;
					}
				}
			}
		}
		return position;
	}
	public static void main(String[] args){
		String key;
		String plain;
		String cipher;
		Scanner input = new Scanner(System.in);
		TestPlayFair play = new TestPlayFair();
		System.out.println("i和j看成是同一个字母*，X为填充字母");
		System.out.println("===========================加密过程");
		System.out.print("请输入密钥：");
		key = input.nextLine();
		System.out.print("请输入明文：");
		plain = input.nextLine(); 
		key = play.format(key);
		plain = play.format(plain);
		String[] str = play.encode(plain,key);
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < str.length; i++){
			if(str[i] != null)
			sb. append(str[i]);
		}
		System.out.print(sb.toString());
		System.out.println("===========================解密过程");
		System.out.print("请输入解密密钥：");
		key = input.nextLine();
		System.out.print("请输入密文：");
		cipher = input.nextLine();
		key = play.format(key);
	    play.decode(cipher,key);
	}

}
