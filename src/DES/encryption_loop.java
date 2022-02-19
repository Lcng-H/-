package DES;

public class encryption_loop {
	//A��ʾ����EBOX��չ���Ұ벿�����ģ�KΪPC-2�û������Կ
	static char[] f(char[] A, char[] K) {
		BOX c = new BOX();
		xor d = new xor();
		Base_conversion e = new Base_conversion();
		int[] t = new int[48];
		char[] r = new char[32];
		char[] result = new char[32];
		// EBOX������Կ���
		for (int i = 0; i < 48; i++) {
			t[i]  = (A[i] ^ K[i]);
		}
		
		// SBOX
		for (int i = 0, a = 0; a < 32; i += 6, a += 4) {
			int j = (t[i]* 2 + t[i + 5]);
			int k = ((t[i + 1]* 8) + t[i + 2]* 4) + (t[i + 3]* 2) + (t[i + 4]);
			String b = Integer.toBinaryString(BOX.S[i / 6][j][k]);
			
			// �Ѷ����Ʋ�Ϊ4λ
			for (int q = b.length(); q < 4; q++) {
				b = "0" + b;
			}
			//�Ѷ����ư�λ����r[]�Ա�PBOX�û�
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
