package Monoalphabetic;

public class TestCaesar {
	private long key;
	public String encrypt(String pt, long temp)
	{
		String result ="";
		if(temp > 0 && temp < 26) key = temp;
		else
		{
			result = "Invalid key!";
			return result;
		}
		
		char[] c = new char[pt.length()];
		for(int i = 0; i < pt.length(); i++)
		{
			c[i] = pt.charAt(i);
			if(c[i] >= 97 && c[i] <= 122) 
			{
				c[i] += key;
				if(c[i] > 122 || c[i] < 97) c[i] -= 26;	
				result += c[i];
			} else
			if(c[i] >= 65 && c[i] <= 90) 
			{
				c[i] += key;
				if(c[i] < 65 || c[i] > 90) c[i] -= 26;
				result += c[i];
			} else {
				result = "Invalid!";
				return result;
			}		
		}
		return result;
	}
	

	public String decrypt(String pt,int temp)
	{
		String result = "";
		if(temp > 0 && temp < 26) key = temp;
		else
		{
			result = "Invalid key!";
			return result;
		}
		
		char[] c = new char[pt.length()];
			for(int i = 0; i < pt.length(); i++)
			{
				c[i] = pt.charAt(i);
				if(c[i] >= 97 && c[i] <= 122) 
				{
					c[i] -= key;
					if(c[i] > 122 || c[i] < 97) c[i] += 26;	
					result += c[i];
				} else
				if(c[i] >= 65 && c[i] <= 90) 
				{
					c[i] -= key;
					if(c[i] < 65 || c[i] > 90) c[i] += 26;
					result += c[i];
				} else 
				{
					result = "Invalid!";
					return result;
				}
			}
			return result;
	}
	
	
//	public static void main(String[] args)
//	{
//		System.out.print("Please enter number 1 or 2 to choose which operation you want to do (1.Encrypt 2.Decrypt): ");
//		Scanner s = new Scanner(System.in);
//		int flag = s.nextInt();
//		
//		if(flag != 1 && flag != 2) 
//		{
//			System.out.println("Please enter the correct number!");
//			System.exit(0);
//		}
//		  
//		TestCaesar c = new TestCaesar();
//		if(flag == 1) c.encrypt();
//		else if(flag == 2) c.decrypt();
//		
//		s.close();
//	}
}
