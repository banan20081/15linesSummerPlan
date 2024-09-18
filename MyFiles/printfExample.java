/**
  * Examples of printfformatting
  *
  *@author Banan
  *@since September 6, 2024
  **/




public class printfExample{
	public static void main(String[] args){
		int i =987;
		double d = 32.45678;
		String str = "Hello world";
		
		// output strings
		System.out.printf("%s after\n" , str);		// open format
		System.out.printf("%20s after\n",str);		//right Justified
		System.out.printf("%-20s after\n",str);		//left Justified
		
		//output integers
		System.out.printf("%d after \n", i);		//open format
		System.out.printf("%20d after \n", i);		//right justified
		System.out.printf("%-20d after \n", i);		//right justified
		
		//output doubles
		System.out.printf("%f after\n", d);		//open format
		System.out.printf("%20.2f after\n", d);		//right justified
		System.out.printf("%-20.2f after\n", d);		//left justified
		System.out.printf("%20.2e after \n", d);
		
		//formatting text
		String myStr = String.format("%15s %10d %15f\n", str, i, d);
		System.out.println(myStr);

	}
}
