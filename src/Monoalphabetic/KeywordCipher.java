package Monoalphabetic;

public class KeywordCipher {
//	static String plain;//����
//	static String cipher;//����
	static String cipherString;
	static int k=0;//�ж��ǵڼ�����������
	
	public char[] getkey(String key) {
        String ke = key.toUpperCase();
        ke = ke.replaceAll( "[^A-Z]" , "");
        char[] array = ke.toCharArray();
		char[] arraykey=new char[array.length];//ȥ�ظ������Կ��������ԭ��Կ����
		String keykey="";//ȥ�ظ������Կ
		char[] letter = new char[26];//ȥ����Կ�����ĸ
		String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] zimu = a.toCharArray();
		char[] keystring = new char[26];
		String aaa="";
		
		for(int i=array.length - 1;i>= 0;i--) {
			int r=0;
			for(int j=i-1;j>= 0;j--) {
				if(j<0)break;
				if(array[i] == array[j]) {
					r=1;
				}
			}
			if(r==0) {
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
		for(int i=0;i<keystring.length;i++){
				//�Ȱ���Կд��
				if(index<arraykey.length){
					keystring[i]=arraykey[index];
					index++;
				}
				//�Ѳ��ظ�����ĸд��
				else 
					if(k<letter.length){
						keystring[i]=letter[k];
						k++;
					}
					else;
			}
			return keystring;
		}
	
	public String encrypt(String plain, String key) {
		String p = plain.toUpperCase();
        p = p.replaceAll( "[^A-Z]" , "");
        char[] p1 = p.toCharArray();
		char[] aa = getkey(key);
//		System.out.print("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
//		System.out.println();
//		System.out.print(aa);
//		System.out.println();
		for(int i = 0;i <p1.length;i++) {

					p1[i] = aa[(p1[i]-'A')];
		}		
		return new String(p1);
	}
	
	public String decrypt(String cipher, String key) {
		String c = cipher.toUpperCase();
        c = c.replaceAll( "[^A-Z]" , "");
        char[] c1 = c.toCharArray();
		char[] aa = getkey(key);
//		System.out.print(aa);
//		System.out.println();
//		System.out.print("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
//		System.out.println();
		for(int i = 0;i < c1.length;i++) {
			 for(int j=0;j<26;j++)
			 {
				 if(aa[j]==c1[i])
				 {
					 c1[i]=(char)('A'+j);
					 break;
				}
			}
		}
		return new String(c1);
	}
	
	public static void main(String[] args){
//		Scanner input = new Scanner(System.in);
//		KeywordCipher play = new KeywordCipher();
//		
//
//		System.out.print("�����������Կ��");
//		key = input.nextLine();
//		System.out.print("���������ģ�");
//		cipher = input.nextLine();
//		System.out.print(play.decrypt(cipher));
	}
	
}
