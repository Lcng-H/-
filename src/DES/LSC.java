package DES;

public class LSC {
	
	static char[] RSHR(char[] b, int n) {
	char temp[] = new char[b.length];
	for(int i = 0;i < b.length;i++) {
		if(i - n >= 0) {
		temp[i - n] = b[i];
		}else {
		temp[i - n + b.length] = b[i];
		}
	}
		return temp;
	}

}
