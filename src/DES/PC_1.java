package DES;

public class PC_1 {
	//�û�ѡ��1����
	static int[] replace1C = {
	        57, 49, 41, 33, 25, 17,  9,
	         1, 58, 50, 42, 34, 26, 18,
	        10,  2, 59, 51, 43, 35, 27,
	        19, 11,  3, 60, 52, 44, 36
	};//ǰ28λ
	static int[] replace1D = {
	        63, 55, 47, 39, 31, 23, 15,
	         7, 62, 54, 46, 38, 30, 22,
	        14,  6, 61, 53, 45, 37, 29,
	        21, 13,  5, 28, 20, 12,  4
	};//��28λ

	//ѭ������λ����
	static int[] moveNum = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

}