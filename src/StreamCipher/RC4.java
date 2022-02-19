package StreamCipher;

public class RC4 {

	//���ڽ���������i,jԪ�ص�λ��
	public void swap(int[] S,int i,int j) {
		int temp = S[i];
		S[i] = S[j];
		S[j] = temp;
	}
	
	public String MyRC4(String aInput,String aKey) {
		int[] S = new int[256]; 
        byte[] K = new byte[256]; 
        
//KSA���ܣ�Key Scheduling Algorithm��Կ�����㷨 ����������
        //��ʼ��S[i]���飬��0~256
        for (int i=0;i<256;i++) 
        	S[i]=i; 
        
        for (short i= 0;i<256;i++) 
        { 
            K[i]=(byte)aKey.charAt(i % aKey.length()); 
        } 

        int j=0;
        
        for (int i=0;i<255;i++) 
        { 
            j=(j + S[i] + K[i]) % 256; 
            swap(S,i,j);
        } 
        int i=0; 
        j=0; 
        char[] aInputChar = aInput.toCharArray(); 
        char[] aOutputChar = new char[aInputChar.length];
        
//PRGA���ܣ�����α�����
        for(short x = 0;x<aInputChar.length;x++) 
        { 
            i = (i+1) % 256; 
            j = (j+S[i]) % 256; 
            swap(S,i,j);
            int t = (S[i]+(S[j] % 256)) % 256; 
            char k = (char)(S[t]);           //����ת��ΪUnicode����
            aOutputChar[x] =(char)( aInputChar[x] ^ k) ;    
        } 
        return new String(aOutputChar); 
	}
	
	public static void main(String[] args) {
//		String input = "12123";
//		String key = "122";
//		String str = MyRC4(input,key);
//		System.out.println("���ģ�" + input);
//		System.out.println("���ģ�" + str);
//		System.out.println("�õ������ģ�" + MyRC4(str,key));
	}

}
