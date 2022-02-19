package Polygraphic;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPlayFair {

	static char[][] KeyMatrix;//��Կ����
	static String[] formatP;//��ʽ���������
	static String[] formatC;//��ʽ���������
	static String cipherString;
	static int k=0;//�ж��ǵڼ�����������
	
	public char[][] getKeyMatrix(String key){
		char[] array=key.toCharArray();//�������Կ
		char[] arraykey=new char[array.length];//ȥ�ظ������Կ��������ԭ��Կ����
		String keykey="";//ȥ�ظ������Կ
		char[] zimu = new char[26];//�滻��i��j���26����ĸ
		String zimu1="";
		char[] letter = new char[26];//ȥ����Կ�����ĸ
		char[][] matrix = new char[5][5];
		String aaa="";
		
		//��i��j����һ����ĸ*
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
		
		//��Կȥ�أ�������ظ���ĸ��ֻ�������һ�γ��ֵ���ĸ,��Կ�е�i��j����*
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
		
		//ȥ����Կ��ĸ��ʣ�µ���ĸ
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
				//�Ȱ���Կд�����
				if(index<arraykey.length){
					matrix[i][j]=arraykey[index];
					index++;
				}
				//�Ѳ��ظ�����ĸд�����
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
			//���ʣ��һ����ĸʱ
			if(i==arrayplain.length-1 && num ==1) {
				stack.push('X');
				num=2;
			}
			
			while(num == 2) {
				//�����ĸ�Ե�������ĸ����ͬ�����
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
	    String regEx="[^(A-Za-z)]";  //������ʽ
	    Pattern p = Pattern.compile(regEx);   //
	    Matcher getStr = p.matcher(str);
	    str = getStr.replaceAll("").trim();
	    str = str.toUpperCase();
	    return str;
	}
	
	//����
	public String[] encode(String plain,String key) {

		System.out.println("��Կ����Ϊ��");
		KeyMatrix = getKeyMatrix(key);
		System.out.print("��ʽ���������Ϊ��");
		formatP = formatPlain(plain);
		
		cipherString="";
		
		int m=0;
		String[] EnCipher = new String[24];//���ܺ������
		int n=0;

		System.out.print("���ܺ������Ϊ��");
		
		while(formatP[m]!=null) {
			EnCipher[n] = "";
			int[][] position = getPosition(formatP[m],KeyMatrix);
			int rowA = position[0][0];
			int colA = position[0][1];
			int rowB = position[1][0];
			int colB = position[1][1];
			int temp;
			
			//ͬһ��
			if(rowA == rowB) {
				EnCipher[n] += KeyMatrix[rowA][(colA+1)%5];
				EnCipher[n] += KeyMatrix[rowA][(colB+1)%5];
			}
			//ͬһ��
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
			System.out.print("��ʽ���������Ϊ��");
			for(int i=0;i<cipher.length()-1;index++,i+=2) {
				arrayC[index] = cipher.substring(i, i+2);
				System.out.print(arrayC[index]);
			}
			System.out.println();
			System.out.print("���ܺ������Ϊ��");
		}
		else {
			System.out.println("���Ĳ���ȷ��");
			System.out.print("��������ȷ���ģ�");
			Scanner input = new Scanner(System.in);
			cipher = input.nextLine();
			k++;
			decode(cipher,key);
		}

		return arrayC;
	}
	
	public String[] decode(String cipher,String key) {
		if(k == 0) {
			System.out.println("��Կ����Ϊ��");
			KeyMatrix = getKeyMatrix(key);
		}
		
		formatC = formatCipher(cipher, key);
		
		int m=0;
		String[] DeCipher = new String[24];//���ܺ������
		int n=0;
		
		while(formatC[m]!=null) {
			DeCipher[n] = "";
			int[][] position = getPosition(formatC[m],KeyMatrix);
			int rowA = position[0][0];
			int colA = position[0][1];
			int rowB = position[1][0];
			int colB = position[1][1];
			int temp;
			
			//ͬһ��
			if(rowA == rowB) {
				if(colA == 0) 
					colA +=5;
				if(colB == 0) 
					colB +=5;
				DeCipher[n] += KeyMatrix[rowA][(colA-1)%5];
				DeCipher[n] += KeyMatrix[rowA][(colB-1)%5];
			}
			//ͬһ��
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
		//λ�þ����ֵ������ĸ����Կ�����е��к���
		//[0][0]:��ĸA����;[0][1]:��ĸA����;
		//[1][0]:��ĸB����;[1][1]:��ĸB����;
		
		int[][] position = new int[2][2];
		//ȷ����˫��ĸ�����
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
		System.out.println("i��j������ͬһ����ĸ*��XΪ�����ĸ");
		System.out.println("===========================���ܹ���");
		System.out.print("��������Կ��");
		key = input.nextLine();
		System.out.print("���������ģ�");
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
		System.out.println("===========================���ܹ���");
		System.out.print("�����������Կ��");
		key = input.nextLine();
		System.out.print("���������ģ�");
		cipher = input.nextLine();
		key = play.format(key);
	    play.decode(cipher,key);
	}

}
