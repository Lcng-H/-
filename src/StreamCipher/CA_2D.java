package StreamCipher;
import java.util.Random;

public class CA_2D {

	public String CA(int key,int sourceX,int sourceY, long bits) {
		Random r = new Random(1);
		int Cells[][] = new int [3][3];
		int length = Cells.length;
		//对细胞进行初始化
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				Cells[i][j] = r.nextInt(2);
			}
		}
		int X,C,N,W,S,E = 0;
		int temp = key;
		E = temp%2;
		temp/=2;
		S = temp%2;
		temp/=2;
		W = temp%2;
		temp/=2;
		N = temp%2;
		temp/=2;
		C = temp%2;
		temp/=2;
		X = temp%2;
		temp/=2;
		
		String stream = "";

		for(int t=0; t<bits; t++) {
			for(int i=0; i<length; i++) {
				for(int j=0; j<length; j++) {
					Cells[i][j] = X ^ (C * Cells[i][j]) ^ (N * Cells[(i-1+length)%length][j])
							^ (W * Cells[i][(j-1+length)%length]) ^ (S * Cells[(i+1)%length][j])
							^ (E * Cells[i][(j+1)%length]);
					
				}
			}
			stream += (char)(Cells[sourceX][sourceY]+'0');
		}
		return stream;
	}
	
	public static void main(String[] args) {
		CA_2D ca = new CA_2D();
		System.out.print(ca.CA(14,1,1,999));

	}

}
