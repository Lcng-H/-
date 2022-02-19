package DES;

public class xor {

	static char[] xor(char[] a, char[] b) {
		char[] c = new char[a.length];
		for (int i = 0; i < a.length; i++)
			c[i] = (char) ((a[i] ^ b[i]) + '0');
		return c;
	}

}
