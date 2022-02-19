package Monoalphabetic;

public class  MultiliteralCipher{

	static int k=0;//�ж��ǵڼ�����������

	public char[][] getKeyMatrix(String key){
	String a = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	char[] zimu = a.toCharArray();
	char[][] matrix = new char[5][5];
	
	int k = 0;
	for(int i = 0;i<5;i++) {
		for(int j = 0;j<5;j++) {
			if(k<zimu.length) {
				matrix[i][j] = zimu[k];
				k++;
			}
			else;
//		System.out.print(matrix[i][j]+"   ");
		}
//		System.out.println();
	}
	return matrix;
  }
	
	public String encrypt(String plain, String key) {
        String p = plain.toUpperCase();
        p = p.replaceAll( "[^A-Z]" , "");
        char[] p1 = p.toCharArray();
		for(int i = 0;i<p1.length;i++) {
			if(p1[i] == 'J') {
				p1[i] = 'I';
			} 
		}
		String a1 = key.toUpperCase();
        a1 = a1.replaceAll( "[^A-Z]" , "");
        char[] a11 = a1.toCharArray();
        
		String a2 = key.toUpperCase();
        a2 = a2.replaceAll( "[^A-Z]" , "");
        char[] a22 = a2.toCharArray();
        char[][] KeyMatrix = getKeyMatrix(key);
        String c = "";
		int n = 0;
		for(int i = 0;i<p1.length;i++) {
			for(int j = 0;j<5;j++) {
				for(int r = 0;r<5;r++) {
					if(p1[i] == KeyMatrix[j][r]) {
						c +=  a11[j];
						c +=  a22[r];
					}
				}
			}
		}
		return c;
	}
	
	public String decrypt(String cipher, String key) {
        String c = cipher.toUpperCase();
        c = c.replaceAll( "[^A-Z]" , "");
        char[] c1 = c.toCharArray();
		String a1 = key.toUpperCase();
        a1 = a1.replaceAll( "[^A-Z]" , "");
        char[] a11 = a1.toCharArray();
        
		String a2 = key.toUpperCase();
        a2 = a2.replaceAll( "[^A-Z]" , "");
        char[] a22 = a2.toCharArray();
        char[][] KeyMatrix = getKeyMatrix(key);
        String p = "";
		int n = 0;
		for(int i=0;i<c1.length;i = i + 2) {
			for(int j = 0;j<5;j++) {
				for(int r = 0;r<5;r++) {
					if(c1[i] == a11[j] && c1[i+1] == a22[r]) {
						p += KeyMatrix[j][r];
						n++;
					}
				}

			}
		}
		return p;
		
	}
	
	public static void main(String[] args){
//		Scanner input = new Scanner(System.in);
//		MultiliteralCipher play = new MultiliteralCipher();
//		System.out.println("i��j������ͬһ����ĸ*��XΪ�����ĸ");
//		System.out.println("===========================���ܹ���");
//		System.out.print("��������Կ��");
//		key = input.nextLine();
//		System.out.print("���������ģ�");
//		plain = input.nextLine(); 
//		System.out.print(play.encrypt(plain));
//		System.out.println("===========================���ܹ���");
//		System.out.print("�����������Կ��");
//		key = input.nextLine();
//		System.out.print("���������ģ�");
//		cipher = input.nextLine();
//		System.out.print(play.decrypt(cipher));
	}
}
