public class Roshambo
{
	public static void main(String[] args)
	{	
		Prompt in = new Prompt();
		String input = "";
		int rand = 0;
		
		System.out.println("\n--- Welcome to Roshambo ---\n");
		
		while (!input.equals("stop"))
		{
			input = in.getString("Rock (r), paper(p), scissors(s), or stop?");
			
			rand = (int)(Math.random()*3) + 1;
			System.out.println(rand);
			if (input.equals("r"))
			{
				
			}
			else if (input.equals("r"))
			{
				
			}
			else if (input.equals("r"))
			{
				
			}
		}
		
		System.out.println("\nCome again!\n");
	}
}
