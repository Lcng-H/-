package DES;

public class Encryption {

	public String encrypt(char[] plaintext, char[] sKey) {
		key_loop c = new key_loop();
		encryption_loop d = new encryption_loop();
		PC_2 e = new PC_2();
		xor f = new xor();
		FT g = new FT();
		IT h = new IT();
		char[][] L = new char[17][32];
		char[][] R = new char[17][32];
		char[][] RR = new char[16][48];
		char[]flag = new char[56];
		char[][]temp = new char[16][48];
		char[] ciphertext = new char[64];
		char[] sKey1 = new char[64];
		for(int i = 1;i <= 8;i++) {
			for(int j = 0;j < 7;j++) {
				sKey1[j + (i-1)*8] = sKey[j + 7*(i - 1)];
			}
			sKey1[8*i - 1] = 0;
		}
//---------------------------------------------------		
		//PC-1
		char[] C = new char[28];
		char[] D = new char[28];
		for (int j = 0; j < 28; j++) {
			C[j] = sKey1[PC_1.replace1C[j]-1];
			D[j] = sKey1[PC_1.replace1D[j]-1];
			flag[j] = C[j];
			flag[j+28] = D[j];
		}
//----------------------------------------------------	
	    // 初始置换IP
	      plaintext = IT.IP(plaintext);
//----------------------------------------------------	      
	    // 将明文分成左半部分L0和右半部分R0
	      for (int j = 0; j < 32; j++) {
		       L[0][j] = plaintext[j];
		       R[0][j] = plaintext[j + 32];
	          }
//-----------------------------------------------------	
		// 子密钥的产生(flag)
		char K[][] = key_loop.generateKeys(flag);//K[16][56]

		//i表示轮次
		for (int i = 0; i <= 15; i++) {
				//PC-2
				for(int q = 0;q < 48;q++) {
				temp[i][q] = K[i][PC_2.replace2[q] - 1];
				}
				// EBOX
				for (int m = 0; m < 48; m++) {
					RR[i][m] = R[i][BOX.E[m] - 1];
				}
		       // 加密迭代  
				R[i+1] = xor.xor(L[i], encryption_loop.f(RR[i], temp[i]));
				L[i+1] = R[i];
			}
//-----------------------------------------------------------------
	    // 以R16为左半部分，L16为右半部分合并
		    for (int k = 0; k < 32; k++) {
			     ciphertext[k] = R[16][k];
			     ciphertext[k + 32] = L[16][k];
		        }
//------------------------------------------------------------------		
		// 逆初始置换IP^-1
		ciphertext = FT.rIP(ciphertext);
		return String.valueOf(ciphertext);
	}

}
