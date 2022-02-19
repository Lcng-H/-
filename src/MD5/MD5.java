package MD5;
import java.awt.geom.GeneralPath;

import javax.annotation.Generated;

public class MD5 {

    //�洢���
    String resultMessage = "";

    //�ĸ��Ĵ����ĳ�ʼ����IV,����С�˴洢
    static final long A = 0x67452301L;
    static final long B = 0xefcdab89L;
    static final long C = 0x98badcfeL;
    static final long D = 0x10325476L;

    //java��֧���޷��ŵĻ�������(unsigned),����ѡ��long��������
    private long[] caches = {A, B, C, D};

    //���ڴ洢���ֲ�����T[i]
    static final long T[][] = {
            	{0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee,
                    0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501,
                    0x698098d8, 0x8b44f7af, 0xffff5bb1, 0x895cd7be,
                    0x6b901122, 0xfd987193, 0xa679438e, 0x49b40821},

            	{0xf61e2562, 0xc040b340, 0x265e5a51, 0xe9b6c7aa,
                    0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8,
                    0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed,
                    0xa9e3e905, 0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a},

            	{0xfffa3942, 0x8771f681, 0x6d9d6122, 0xfde5380c,
                    0xa4beea44, 0x4bdecfa9, 0xf6bb4b60, 0xbebfbc70,
                    0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x04881d05,
                    0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665},

            	{0xf4292244, 0x432aff97, 0xab9423a7, 0xfc93a039,
                    0x655b59c3, 0x8f0ccc92, 0xffeff47d, 0x85845dd1,
                    0x6fa87e4f, 0xfe2ce6e0, 0xa3014314, 0x4e0811a1,
                    0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391}};
    //��ʾX[k]�еĵ�kȡֵ���������ʹ����Ϣ�����е���
    static final int k[][] = {
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
            {1, 6, 11, 0, 5, 10, 15, 4, 9, 14, 3, 8, 13, 2, 7, 12},
            {5, 8, 11, 14, 1, 4, 7, 10, 13, 0, 3, 6, 9, 12, 15, 2},
            {0, 7, 14, 5, 12, 3, 10, 1, 8, 15, 6, 13, 4, 11, 2, 9}};

    //���ε����в��õ���ѭ����λ��sֵ��ÿ��ֻ���ĸ���ͬ��sֵ
    static final int S[][] = {
            {7, 12, 17, 22},
            {5, 9, 14, 20},
            {4, 11, 16, 23},
            {6, 10, 15, 21}};
    public long dFunc(int i, long x, long y, long z) {
    	switch(i) {
    	case 0:
    		return (x & y) | ((~x) & z);
    	case 1:
    		return (x & z) | (y & (~z));
    	case 2:
    		return x ^ y ^ z;
    	case 3:
    		return y ^ (x | (~z));
    	default:
    		return 0;
    	}
    }
    
    //���ڽ�512λ����
    //��starts��ʼȡ512λ; ��һ��512bits/64bytes���Ϊ16�飬ÿ��32bits/4bytes;
    public long[] divideTo16groups(byte[] inputBytes, int starts) {
    	//long���޷�����������long����ʾ
    	long[] group = new long[16]; 
    	for(int i=0; i<16; i++) {
    		//32λ��4��8λƴ�Ӷ��ɣ��ֱ�����0,8,16,24λ��λ����ƴ�ӳ�32λ��
    		group[i] = byteUnsign(inputBytes[4*i + starts]) |
    				(byteUnsign(inputBytes[4*i + 1 +starts])) <<8 |
    				(byteUnsign(inputBytes[4*i + 2 +starts])) <<16|
    				(byteUnsign(inputBytes[4*i + 3 +starts])) <<24;
    	}
    	return group;
    }
    
    //��16��32λ�ķ�����в���
    //�����groupsָ512bits�ķ��飨64bytes��
    public void roundOperation(long[] groups) {
    	//���Ȼ�ȡ�Ĵ����е�ֵ
    	long a = caches[0];
    	long b = caches[1];
    	long c = caches[2];
    	long d = caches[3];
    	
    	//����ѭ������
    	for(int n=0; n<4; n++) {
    		//ÿһ�ֽ���16�ε���,0xFFFFFFFFL�У�L��ʾ��������������ȷ������Ϊ��������ֹ���
    		for(int i=0; i<16; i++) {
    			caches[0] += (dFunc(n, caches[1], caches[2], caches[3])& 0xFFFFFFFFL) 
    					+ groups[k[n][i]] + T[n][i];
    			//<<S[n][i % 4] �� >>> 32 - S[n][i % 4]����ѭ������
    			caches[0] = caches[1] + 
    					((caches[0]& 0xFFFFFFFFL) << S[n][i % 4] | 
    							((caches[0]& 0xFFFFFFFFL) >>> (32 - S[n][i % 4])));
    			//����ѭ���ֻ�
    			long temp = caches[3];
    			caches[3] = caches[2];
    			caches[2] = caches[1];
    			caches[1] = caches[0];
    			caches[0] = temp;
    		}	
    	}
		//���ּ�������󽫾�ֵ���뵽�Ĵ�����
		caches[0] += a;
		caches[1] += b;
		caches[2] += c;
		caches[3] += d;
		//��ֹ����ع�
		for(int i=0; i<4; i++) {
			caches[i] &= 0xFFFFFFFFL;
		}
    }
    
    //��ʵbyte�൱��һ���ֽڵ��з������������ﲻ��Ҫ����λ�����԰ѷ���λȥ��
    public static long byteUnsign(byte b) {
    	//0x7FΪ0111 1111ȥ�����ײ��ķ���λ���ټ���128�ع�
        return b < 0 ? b & 0x7F + 128 : b;
    }
	
    public String generateMD(String message) {
    	//����Ϣ����ת��Ϊbyte������
    	byte[] bytesInput = message.getBytes();
    	//��ȡ��Ϣbyte�ĳ���
    	int byteLen = bytesInput.length;
    	//��ȡ��Ϣ��byteת��Ϊbits�ĳ���
    	long bitLen = (long) (byteLen << 3);
    	//��ȡ�������飨512bits / 64bytes������
    	int groupCount = byteLen / 64;
    	//�Ƚ������ķ���������ɱ���ժҪ����
    	for(int i=0; i<groupCount; i++) {
    		//ÿ��ѭ������512λ
    		roundOperation(divideTo16groups(bytesInput, i*64));
    	}
    	
/*������*/
    	//���ʣ�಻��512λ���ֵ�λ��
    	int rest = byteLen % 64;
    	//������������
    	byte padding[] =  new byte[64];
    	//��ʣ�಻��512λ�Ĳ��ָ�ֵ��padding
    	for(int i=0; i<rest; i++) {
    		padding[i] = bytesInput[byteLen - rest + i];
    	}
    	
    //���ʣ�ಿ�ֲ�����448λ��56bytes��
    	//��ֻ��Ҫ����һ��
    	if(rest <= 56) {
    		if(rest < 56) {
    			//���10000000������Ҫ�����λ��1
    			padding[rest] = (byte)(1 << 7);
    			//ÿ��ѭ�����һ��byte��0�������00000000
    			for(int i=1; i<56 - rest; i++) {
    				padding[rest + i] = 0;
    			}
    		}
    //���ʣ�ಿ�ֳ���448λ��56bytes��
    	//���������һ�����Ҫ���448λ��56bytes��������������
    	}else {
    		//���10000000������Ҫ�����λ��1
    		padding[rest] = (byte)(1 << 7);
    		//���0����Ҫ�����һ��
    		for(int i=rest+1; i<64; i++) {
    			padding[i] = 0;
    		}
    		roundOperation(divideTo16groups(padding, 0));
    		//�����448bits��0
    		for(int i=0; i<56; i++) {
    			padding[i] = 0;
    		}
    	}
    	//�����64bits��8bytes����ʾ�����λ��
    	for(int i=0; i<8; i++) {
    		//������bits��ת��Ϊ�ֽڣ�����ֹΪ����
    		padding[56 + i] = (byte)(bitLen & 0xFFL);
    		//������һ���ֽ�
    		bitLen >>= 8;
    	}
    	//�����һ�����ɱ���
    	roundOperation(divideTo16groups(padding, 0));
    	
    	//���Ĵ���A,B,C,Dƴ�Ӽ��ɵõ�����ժҪ
    	//Ϊ�˷�ֹƴ��ʱ�����ײ�0��ʧ�����󣬽�cache��������8λ�����ַ�����ʽ�����ٽ���ƴ��
    	for(int i=0; i<4; i++) {
    		resultMessage += String.format("%02x", caches[i]& 0xFF) 
    				+String.format("%02x", (caches[i]& 0xFF00) >> 8)
    				+String.format("%02x", (caches[i]& 0xFF0000) >> 16)
    				+String.format("%02x", (caches[i]& 0xFF000000) >> 24);
    	}
    	return resultMessage;
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MD5 md = new MD5();
		String message = "nihao";
		System.out.println("ԭ���ģ�" + message + "\n");
		System.out.println("����ժҪ��" + md.generateMD(message));
		System.out.println("����ժҪ(UpperCase)��" + md.resultMessage.toUpperCase());
	}

}
