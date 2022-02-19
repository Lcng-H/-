package DES;

public class encryption_loop {
	//A表示经过EBOX扩展的右半部分明文，K为PC-2置换后的密钥
	static char[] f(char[] A, char[] K) {
		BOX c = new BOX();
		xor d = new xor();
		Base_conversion e = new Base_conversion();
		int[] t = new int[48];
		char[] r = new char[32];
		char[] result = new char[32];
		// EBOX和子密钥异或
		for (int i = 0; i < 48; i++) {
			t[i]  = (A[i] ^ K[i]);
		}
		
		// SBOX
		for (int i = 0, a = 0; a < 32; i += 6, a += 4) {
			int j = (t[i]* 2 + t[i + 5]);
			int k = ((t[i + 1]* 8) + t[i + 2]* 4) + (t[i + 3]* 2) + (t[i + 4]);
			String b = Integer.toBinaryString(BOX.S[i / 6][j][k]);
			
			// 把二进制补为4位
			for (int q = b.length(); q < 4; q++) {
				b = "0" + b;
			}
			//把二进制按位存入r[]以便PBOX置换
			for(int n = 0;n < 4;n++) {
				r[a + n] = b.charAt(n);
			}
		    
		}
		// PBOX
		for (int i = 0; i < 32; i++)
			result[i] = r[BOX.P[i] - 1];
		return result;
	}
	

}
