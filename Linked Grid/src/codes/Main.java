package codes;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) 
	{
		LinkedGrid LG = new LinkedGrid(8);
		Scanner input = new Scanner(System.in);
		
		int lives = 20;
		
		LG.display();
		
		while (lives > 0)
		{
			System.out.println();
			System.out.println("You have " + lives + " lives.");
			System.out.println("Please change the top left corner to any number: ");
			int number = input.nextInt();
			
			System.out.println();
			LG.changeData(number, LG.getFirst());
			LG.display();
			
			if (LG.winCondition() == false)
				lives--;
			else if (LG.winCondition() == true)
			{
				System.out.println("You win!");
				break;
			}
		}
	}
}