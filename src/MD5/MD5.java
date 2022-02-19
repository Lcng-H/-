package MD5;
import java.awt.geom.GeneralPath;

import javax.annotation.Generated;

public class MD5 {

    //存储结果
    String resultMessage = "";

    //四个寄存器的初始向量IV,采用小端存储
    static final long A = 0x67452301L;
    static final long B = 0xefcdab89L;
    static final long C = 0x98badcfeL;
    static final long D = 0x10325476L;

    //java不支持无符号的基本数据(unsigned),所以选用long数据类型
    private long[] caches = {A, B, C, D};

    //用于存储四轮操作的T[i]
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
    //表示X[k]中的的k取值，决定如何使用消息分组中的字
    static final int k[][] = {
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
            {1, 6, 11, 0, 5, 10, 15, 4, 9, 14, 3, 8, 13, 2, 7, 12},
            {5, 8, 11, 14, 1, 4, 7, 10, 13, 0, 3, 6, 9, 12, 15, 2},
            {0, 7, 14, 5, 12, 3, 10, 1, 8, 15, 6, 13, 4, 11, 2, 9}};

    //各次迭代中采用的做循环移位的s值，每轮只有四个不同的s值
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
    
    //用于将512位分组
    //从starts开始取512位; 将一个512bits/64bytes块分为16组，每组32bits/4bytes;
    public long[] divideTo16groups(byte[] inputBytes, int starts) {
    	//long是无符号数，故用long来表示
    	long[] group = new long[16]; 
    	for(int i=0; i<16; i++) {
    		//32位是4个8位拼接而成，分别左移0,8,16,24位按位与来拼接成32位数
    		group[i] = byteUnsign(inputBytes[4*i + starts]) |
    				(byteUnsign(inputBytes[4*i + 1 +starts])) <<8 |
    				(byteUnsign(inputBytes[4*i + 2 +starts])) <<16|
    				(byteUnsign(inputBytes[4*i + 3 +starts])) <<24;
    	}
    	return group;
    }
    
    //对16组32位的分组进行操作
    //这里的groups指512bits的分组（64bytes）
    public void roundOperation(long[] groups) {
    	//首先获取寄存器中的值
    	long a = caches[0];
    	long b = caches[1];
    	long c = caches[2];
    	long d = caches[3];
    	
    	//四轮循环操作
    	for(int n=0; n<4; n++) {
    		//每一轮进行16次迭代,0xFFFFFFFFL中：L表示长整数，整体是确保所得为正数，防止溢出
    		for(int i=0; i<16; i++) {
    			caches[0] += (dFunc(n, caches[1], caches[2], caches[3])& 0xFFFFFFFFL) 
    					+ groups[k[n][i]] + T[n][i];
    			//<<S[n][i % 4] 和 >>> 32 - S[n][i % 4]用于循环左移
    			caches[0] = caches[1] + 
    					((caches[0]& 0xFFFFFFFFL) << S[n][i % 4] | 
    							((caches[0]& 0xFFFFFFFFL) >>> (32 - S[n][i % 4])));
    			//进行循环轮换
    			long temp = caches[3];
    			caches[3] = caches[2];
    			caches[2] = caches[1];
    			caches[1] = caches[0];
    			caches[0] = temp;
    		}	
    	}
		//四轮计算结束后将旧值加入到寄存器中
		caches[0] += a;
		caches[1] += b;
		caches[2] += c;
		caches[3] += d;
		//防止溢出回滚
		for(int i=0; i<4; i++) {
			caches[i] &= 0xFFFFFFFFL;
		}
    }
    
    //其实byte相当于一个字节的有符号整数，这里不需要符号位，所以把符号位去掉
    public static long byteUnsign(byte b) {
    	//0x7F为0111 1111去除了首部的符号位，再加上128回滚
        return b < 0 ? b & 0x7F + 128 : b;
    }
	
    public String generateMD(String message) {
    	//将消息明文转化为byte型数组
    	byte[] bytesInput = message.getBytes();
    	//获取消息byte的长度
    	int byteLen = bytesInput.length;
    	//获取消息由byte转化为bits的长度
    	long bitLen = (long) (byteLen << 3);
    	//获取完整分组（512bits / 64bytes）个数
    	int groupCount = byteLen / 64;
    	//先将完整的分组进行生成报文摘要操作
    	for(int i=0; i<groupCount; i++) {
    		//每次循环处理512位
    		roundOperation(divideTo16groups(bytesInput, i*64));
    	}
    	
/*填充操作*/
    	//获得剩余不满512位部分的位数
    	int rest = byteLen % 64;
    	//用于填充的数组
    	byte padding[] =  new byte[64];
    	//将剩余不满512位的部分赋值给padding
    	for(int i=0; i<rest; i++) {
    		padding[i] = bytesInput[byteLen - rest + i];
    	}
    	
    //如果剩余部分不超过448位（56bytes）
    	//则只需要新增一组
    	if(rest <= 56) {
    		if(rest < 56) {
    			//填充10000000，即主要填充首位的1
    			padding[rest] = (byte)(1 << 7);
    			//每次循环填充一个byte的0，即填充00000000
    			for(int i=1; i<56 - rest; i++) {
    				padding[rest + i] = 0;
    			}
    		}
    //如果剩余部分超过448位（56bytes）
    	//则在填充满一组后还需要填充448位（56bytes）；即新增两组
    	}else {
    		//填充10000000，即主要填充首位的1
    		padding[rest] = (byte)(1 << 7);
    		//填充0，但要填充满一组
    		for(int i=rest+1; i<64; i++) {
    			padding[i] = 0;
    		}
    		roundOperation(divideTo16groups(padding, 0));
    		//再填充448bits个0
    		for(int i=0; i<56; i++) {
    			padding[i] = 0;
    		}
    	}
    	//再填充64bits（8bytes）表示输入的位数
    	for(int i=0; i<8; i++) {
    		//将长度bits数转化为字节，并防止为负数
    		padding[56 + i] = (byte)(bitLen & 0xFFL);
    		//进入下一个字节
    		bitLen >>= 8;
    	}
    	//对最后一组生成报文
    	roundOperation(divideTo16groups(padding, 0));
    	
    	//将寄存器A,B,C,D拼接即可得到报文摘要
    	//为了防止拼接时产生首部0丢失的现象，将cache中数据逐8位进行字符串格式化，再进行拼接
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
		System.out.println("原报文：" + message + "\n");
		System.out.println("报文摘要：" + md.generateMD(message));
		System.out.println("报文摘要(UpperCase)：" + md.resultMessage.toUpperCase());
	}

}
