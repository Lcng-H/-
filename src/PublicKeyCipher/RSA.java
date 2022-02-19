package PublicKeyCipher;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;

public class RSA {
	
	public long modExp(long b, long n, long m) {		// b^n mod m = (((b^2)^2)...^2) mod m
	    long result = 1;
	    b = b % m;					//���Ƚ�����b����mod m ���� 
	    do {
	        if ((n & 1) == 1)		//ָ��Ϊ����ʱ
	            result=result*b%m;
	        b = b * b % m;			//��b��ƽ������
	        n = n >> 1;				//��ָ������2
	    } while (n != 0);
	    return result;
	}
	

	//ģ������
	static long modInv(long b, long m) {
	    if (b >= m) b %= m;
	    return exGcd(b, m)[1] < 0 ? exGcd(b, m)[1] + m : exGcd(b, m)[1];
	}

	// ��չŷ������㷨
	static long[] exGcd(long a, long b) {
	    if (a < b) {
	        long temp = a;
	        a = b;
	        b = temp;
	    }
	    long[] result = new long[3];
	    if (b == 0) {
	        result[0] = 1;
	        result[1] = 0;
	        result[2] = a;
	        return result;
	    }
	    long[] temp = exGcd(b, a % b);
	    result[0] = temp[1];
	    result[1] = temp[0] - a / b * temp[1];
	    result[2] = temp[2];
	    return result;
	}
	
	public boolean isPrime(int k, Integer n) {		//���Դ���k�������Լ������n
	    ArrayList<Long> a = new ArrayList<Long>();
	    int t = n - 2 > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) (n - 2);		//ѡ������t
	    do {
	        long b = (long) (new Random().nextInt(t - 2) + 2);						//ѡ�������b�� ʹ�� 2<= b <= n-2
	        if (-1 == a.indexOf(b))													//���ɲ��ظ��������b
	        a.add(b);					
	    } while (a.size() < k);														//�������ڲ���k�ε������
	    for (int i = 0; i < k; i++)			
	        if (! Miller(n, a.get(i)))												//���������ޱ��㷨��֤�Ƿ�Ϊ����
	            return false;
	    return true;
	}
	
	public boolean Miller(long n, long b) {		//����֤����n�����������b����ȫ����m
	    long m = n - 1;							//mΪż������ΪnΪ����
	    int t = 0;								//��֤����
	    while (m % 2 == 0) {
	        m /= 2;
	        t++;
	    }
	    long r = modExp(b, m, n);				//r = b^m mod n
	    if (r == 1 || r == n - 1)				//��r = 1�� r = n-1������֤Ϊ�������������  r = r^2
	        return true;
	    for (int j = 1; j < t; j++) {			//����r = r^2�� ��r = n-1��Ϊ����
	        r = r * r % n;
	        if (r == n - 1)
	            return true;
	    }
	    return false;
	}
	
	//���ܲ���
	public BigInteger encrypt(BigInteger M, BigInteger e, BigInteger n) {
		return M.modPow(e, n);
	}
	//���ܲ���
	public BigInteger decrypt(BigInteger C, BigInteger d, BigInteger n) {
		return C.modPow(d, n);
	}
	
//	//���ַ���ת��Ϊ16����
//	public static String StringToHex(String str) {   
//		char[] chars = "0123456789ABCDEF".toCharArray();
//		StringBuilder sb = new StringBuilder("");
//		byte[] bs = str.getBytes();
//		int bit;
//        for (int i = 0; i < bs.length; i++) {
//            bit = (bs[i] & 0x0f0) >> 4;
//            sb.append(chars[bit]);
//            bit = bs[i] & 0x0f;
//            sb.append(chars[bit]);
//        }
//        String C = sb.toString().trim();
//		return C;
//	}
//	
//	public static String HexToString(String C) {
//		 String str = "0123456789ABCDEF";
//			char[] hexs = C.toCharArray();
//			byte[] bytes = new byte[C.length() / 2];
//			int n;
//	        for (int i = 0; i < bytes.length; i++) {
//	            n = str.indexOf(hexs[2 * i]) * 16;
//	            n += str.indexOf(hexs[2 * i + 1]);
//	            bytes[i] = (byte) (n & 0xff);
//	        }
//	        String M = new String(bytes);
//			return M;
//	}
	
	
	public ArrayList<BigInteger> generateKeyPair(ArrayList<BigInteger> al) {
		// ����2����>=100λ�Ĵ�����p,q
		BigInteger p = BigInteger.probablePrime(new Random().nextInt(100) + 2000, new Random());
		BigInteger q = BigInteger.probablePrime(new Random().nextInt(100) + 2000, new Random());
		// ����n,n=p*q
		BigInteger n = p.multiply(q);
		// ����phi_n=(p-1)(q-1)
		BigInteger phi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		// ѡ���������һ����d��ʹ��d��phi_n����
		BigInteger e;
		do {
			e = new BigInteger(new Random().nextInt(phi_n.bitLength() - 1) + 1, new Random());
		} while (e.gcd(phi_n).intValue() != 1 || e.compareTo(phi_n) != -1);
		// ed=1 mod phi_n
		BigInteger d = e.modPow(new BigInteger("-1"), phi_n);
	
		al.add(e);
		al.add(d);
		al.add(n);
		return al;

	}

	public void main(String[] args) {
		//����Կ����Կ��װ
		ArrayList<BigInteger> al = new ArrayList<BigInteger>();
		al = generateKeyPair(al);
//
		BigInteger e = (BigInteger) al.get(0);
		BigInteger d = (BigInteger) al.get(1);
		BigInteger n = (BigInteger) al.get(2);
		/* System.out.print(e); */
		Scanner sc = new Scanner(System.in);
		String M = sc.next();		//MΪ������ַ���
		byte BM[] = Base64.encodeBase64(M.getBytes());				//������ת��Ϊ16����Byte
		BigInteger M_10 = new BigInteger(BM);	//��16����Byteת��Ϊʮ������
		BigInteger C = encrypt(M_10,e,n);		//���ܺ��ʮ��������
		System.out.println("��������(Base64����)��"+ new String(Base64.encodeBase64( C.toByteArray()) ) ); 		
		BigInteger CtoM_10 = decrypt(C,d,n);	//������תΪ10��������
		byte[] BCtoM = CtoM_10.toByteArray();	//��10��������ת��Ϊ16����Byte
		System.out.println("�������ģ�"+ new String (Base64.decodeBase64(new String(BCtoM)))); 
		sc.close();
	}

}
