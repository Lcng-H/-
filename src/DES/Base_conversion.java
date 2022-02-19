package DES;

public class Base_conversion {

	public static int Base_conversion(char a[]) {
		int num = 0;
		int c = a.length;
		char b[] = new char[c];
		for(int n = 0,m = c - 1;n < c;n++,m--){
			num = num +a[m]*(2^n);
			n++;
		}
		
		return num;
		
	}
}
