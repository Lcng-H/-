package PublicKeyCipher;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;

public class RSA {
	
	public long modExp(long b, long n, long m) {		// b^n mod m = (((b^2)^2)...^2) mod m
	    long result = 1;
	    b = b % m;					//首先将底数b进行mod m 操作 
	    do {
	        if ((n & 1) == 1)		//指数为奇数时
	            result=result*b%m;
	        b = b * b % m;			//对b做平方操作
	        n = n >> 1;				//将指数除以2
	    } while (n != 0);
	    return result;
	}
	

	//模逆运算
	static long modInv(long b, long m) {
	    if (b >= m) b %= m;
	    return exGcd(b, m)[1] < 0 ? exGcd(b, m)[1] + m : exGcd(b, m)[1];
	}

	// 扩展欧几里德算法
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
	
	public boolean isPrime(int k, Integer n) {		//测试次数k，待素性检验的数n
	    ArrayList<Long> a = new ArrayList<Long>();
	    int t = n - 2 > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) (n - 2);		//选择整数t
	    do {
	        long b = (long) (new Random().nextInt(t - 2) + 2);						//选择随机数b， 使得 2<= b <= n-2
	        if (-1 == a.indexOf(b))													//生成不重复的随机数b
	        a.add(b);					
	    } while (a.size() < k);														//产生用于测试k次的随机数
	    for (int i = 0; i < k; i++)			
	        if (! Miller(n, a.get(i)))												//调用米勒罗宾算法验证是否为素数
	            return false;
	    return true;
	}
	
	public boolean Miller(long n, long b) {		//待验证整数n，和随机整数b，安全参数m
	    long m = n - 1;							//m为偶数，因为n为奇数
	    int t = 0;								//验证次数
	    while (m % 2 == 0) {
	        m /= 2;
	        t++;
	    }
	    long r = modExp(b, m, n);				//r = b^m mod n
	    if (r == 1 || r == n - 1)				//若r = 1或 r = n-1，则验证为素数；否则计算  r = r^2
	        return true;
	    for (int j = 1; j < t; j++) {			//计算r = r^2， 若r = n-1则为素数
	        r = r * r % n;
	        if (r == n - 1)
	            return true;
	    }
	    return false;
	}
	
	//加密操作
	public BigInteger encrypt(BigInteger M, BigInteger e, BigInteger n) {
		return M.modPow(e, n);
	}
	//解密操作
	public BigInteger decrypt(BigInteger C, BigInteger d, BigInteger n) {
		return C.modPow(d, n);
	}
	
//	//将字符串转化为16进制
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
		// 生成2进制>=100位的大素数p,q
		BigInteger p = BigInteger.probablePrime(new Random().nextInt(100) + 2000, new Random());
		BigInteger q = BigInteger.probablePrime(new Random().nextInt(100) + 2000, new Random());
		// 生成n,n=p*q
		BigInteger n = p.multiply(q);
		// 生成phi_n=(p-1)(q-1)
		BigInteger phi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		// 选择随机生成一个数d，使得d与phi_n互素
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
		//将公钥、密钥封装
		ArrayList<BigInteger> al = new ArrayList<BigInteger>();
		al = generateKeyPair(al);
//
		BigInteger e = (BigInteger) al.get(0);
		BigInteger d = (BigInteger) al.get(1);
		BigInteger n = (BigInteger) al.get(2);
		/* System.out.print(e); */
		Scanner sc = new Scanner(System.in);
		String M = sc.next();		//M为输入的字符串
		byte BM[] = Base64.encodeBase64(M.getBytes());				//将明文转化为16进制Byte
		BigInteger M_10 = new BigInteger(BM);	//将16进制Byte转化为十进制数
		BigInteger C = encrypt(M_10,e,n);		//加密后的十进制密文
		System.out.println("加密密文(Base64编码)："+ new String(Base64.encodeBase64( C.toByteArray()) ) ); 		
		BigInteger CtoM_10 = decrypt(C,d,n);	//将密文转为10进制明文
		byte[] BCtoM = CtoM_10.toByteArray();	//将10进制密文转化为16进制Byte
		System.out.println("解密明文："+ new String (Base64.decodeBase64(new String(BCtoM)))); 
		sc.close();
	}

}
