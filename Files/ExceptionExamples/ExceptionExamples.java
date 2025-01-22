import java.util.ArrayList;
import java.util.List;
import java.util.ConcurrentModificationException;

/**
 * The APCS exam tests for the following exceptions.
 *	- ArithmeticException
 *	- NullPointerException
 *	- IndexOutOfBoundsException
 *	- ArrayIndexOutOfBoundsException
 *	- ConcurrentModificationException
 */

public class ExceptionExamples {
	public static void main(String[] args) {
		ExceptionExamples ee = new ExceptionExamples();
		ee.run();
	}
	
	public void run() {
		arithmetic();
		nullPointer();
		indexBounds();
		arrayIndexBounds();
		concurrentModification();
	}
	
	public void arithmetic() {
		int x = 1;
		int y = 0;
		try {
			int z = x / y;
		}
		catch (ArithmeticException E) {
			System.err.println("ERROR: ArithmeticException");
		}
	}
	public void nullPointer() {
		Integer [] arr = new Integer [10];
		try {
			int z = arr[0].intValue();
		}
		catch (NullPointerException E) {
			System.err.println("ERROR: NullPointerException");
		}
	}
	
	public void indexBounds() {
		String hello = "Hello";
		try {
			char c = hello.charAt(hello.length());
		}
		catch (IndexOutOfBoundsException E) {
			System.err.println("ERROR: IndexOutOfBoundsException");
		}
	}
	
	public void arrayIndexBounds() {
		int [] arr = new int [10];
		try {
			int k = arr[10];
		}
		catch (ArrayIndexOutOfBoundsException E) {
			System.err.println("ERROR: ArrayIndexOutOfBoundsException");
		} 
	}
	
	public void concurrentModification() {
		List<Integer> arr = new ArrayList<Integer>();
		
		for (int a = 0; a < 5; a++)
			arr.add(a + 1);
		
		try {
			for (Integer x : arr)
				arr.remove(0);
		}
		catch (ConcurrentModificationException E) {
			System.err.println("ERROR: ConcurrentModificationException");
		}
	}
	
}
