package Monoalphabetic;

public class KeywordCipher {
//	static String plain;//明文
//	static String cipher;//密文
	static String cipherString;
	static int k=0;//判断是第几次输入密文
	
	public char[] getkey(String key) {
        String ke = key.toUpperCase();
        ke = ke.replaceAll( "[^A-Z]" , "");
        char[] array = ke.toCharArray();
		char[] arraykey=new char[array.length];//去重复后的密钥，不超过原密钥长度
		String keykey="";//去重复后的密钥
		char[] letter = new char[26];//去掉密钥后的字母
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
		for(int i=0;i<keystring.length;i++){
				//先把密钥写入
				if(index<arraykey.length){
					keystring[i]=arraykey[index];
					index++;
				}
				//把不重复的字母写入
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
//		System.out.print("请输入解密密钥：");
//		key = input.nextLine();
//		System.out.print("请输入密文：");
//		cipher = input.nextLine();
//		System.out.print(play.decrypt(cipher));
	}
	
}
