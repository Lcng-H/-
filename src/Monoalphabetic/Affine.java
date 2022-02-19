package Monoalphabetic;
import java.util.Scanner;

public class Affine {

//加密算法
    public String encryption(String cleartext, int k1, int k2) {
    	String str = "";
    	String s = cleartext.toLowerCase();
        char[] chars = s.toCharArray();//String转char数组
        for (int i = 0; i < chars.length; i++) {
            int q = (int) chars[i];//转ACSll码 97-122
            if (q != 32) {
                int m = (k1 * (q - 97) + k2 % 26) % 26;
                char c = (char) (m + 65);//转大写字符
                str += c;
            } else {
                System.out.print(" ");
            }
        }
        return str;
    }



//解密算法
    public String decryption(String ciphertext, int k1, int k2) {
    	String str = "";
        //求k1的乘法逆元素
        int M;
        for (M = 0; M < 26; M++) {
            if ((k1 * M) % 26 == 1) {
                break;
            }
        }
    	String s = ciphertext.toUpperCase();
        char[] chars = s.toCharArray();//String转char数组
        for (int i = 0; i < chars.length; i++) {
            int q = (int) chars[i];//转ACSll码 65-90
            if (q != 32) {
                int m = (M * ((q - 65 + 26) - k2)) % 26;
                char c = (char) (m + 97);//转小写字符
                str += (c);
            } else {
                System.out.print(" ");
            }
        }
        return str;
    }
    
    public static void main(String[] args) {
//    	//k1与26互素 0~25
//    	//k2 0~25
//    	int k1,k2;
//    	Affine a = new Affine();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入k1、k2：");
//        k1 = scanner.nextInt();
//        k2 = scanner.nextInt();
//        if((k1 == 1||k1 == 3||k1 == 5||k1 == 7
//        		||k1 == 9||k1 == 11||k1 == 15||
//        		k1 == 17||k1 == 19||k1 == 21||k1 == 23||k1 == 25)&&(0<k2&&k2<=25)) {
////            System.out.println("请输入明文(小写字符)：");
////            a.cleartext = scanner.next();
////            System.out.println("密文为：");
//            a.encryption();
//        }else {
//			System.out.println("Please enter the correct number!");
//        }
//        
//        System.out.println("请输入k1、k2：");
//        a.k1 = scanner.nextInt();
//        a.k2 = scanner.nextInt();
//        if((a.k1 == 1||a.k1 == 3||a.k1 == 5||a.k1 == 7
//        		||a.k1 == 9||a.k1 == 11||a.k1 == 15||
//        		a.k1 == 17||a.k1 == 19||a.k1 == 21||a.k1 == 23||a.k1 == 25)&&(0<a.k2&&a.k2<=25)) {
//        System.out.println("请输入密文(大写字符)：");
//        a.ciphertext = scanner.next();
//        System.out.println("明文为：");
//        a.decryption();
//        }else {
//			System.out.println("Please enter the correct number!");
//        }
    }
}
