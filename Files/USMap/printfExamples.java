/**
 * 	Examples of printf formatting
 * 
 *	@author Petros Mzikyan
 *	@since September 6, 2024
 */
public class PrintfExamples
{
	public static void main(String[] args) {
		int i = 987;
		double d = 32.678912;
		String str = "hello world";
		
		// output strings
		System.out.printf("%s after\n", str);		//open format
		System.out.printf("%20s after\n", str);		//left justified
		System.out.printf("%-20s after\n", str);	//right justified
		
		// output ints
		System.out.printf("%d after\n", i);			//open format
		System.out.printf("%20d after\n", i);		//left justified
		System.out.printf("%-20d after\n", i);		//right justified
		
		// output doubles
		System.out.printf("%.2f after\n", d);		//open format
		System.out.printf("%20.2f after\n", d);		//left justified
		System.out.printf("%-20.2f after\n", d);	//right justified
		System.out.printf("%20.3e after\n", d);
		
		// formatting text
		String myStr = String.format("%15s %15d %15.2e", str, i, d);
	}
}
