package DES;

public class key_loop {
	static char[][] generateKeys(char[] sKey) {
		PC_1 a = new PC_1();
		PC_2 c = new PC_2();
		LSC b = new LSC();
		char[] C = new char[28];
		char[] D = new char[28];
		char[] keys = new char[56];
		char[][] K = new char[16][56];
		
		for (int j = 0; j < 28; j++) {
			C[j] = sKey[j];//��28λ
			D[j] = sKey[j + 28];//��28λ
		}
		//ѭ������������Կ
		for(int i = 0;i < 16;i++) {
		// ѭ������
		C = LSC.RSHR(C, PC_1.moveNum[i]);
		D = LSC.RSHR(D, PC_1.moveNum[i]);
		for (int j = 0; j < 28; j++) {
			K[i][j] = C[j];
			K[i][j + 28] = D[j];
		}

	}
		return K;
}

}
