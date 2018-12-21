package SPacMan;

import java.util.Arrays;
import java.util.Scanner;

public class PMGame {

	public static void main(String[] args) 
	{
		//Create Some variables
		int gameX;       //Game array x size
		int gameY;       //Game array y size
		int cookie = 0;  //Number of cookies
		int nextMove;    //User selected move
		int totCookie;   //Total cookies created
		int tempCookie;  //Cookies before moving
		boolean endGame = false; //User requested end of game
		
		//Create scanner to read user input
		Scanner input = new Scanner(System.in);
		
		//Display the menu and prompt user for input
		System.out.println("Press 1 for control or 2 to play: ");
		
		//If controls screen is chosen
		if(input.nextInt() == 1)
		{
			//Display the controls
			Controls();
		}
		
		//Prompt user for array dimensions
		System.out.println("Enter x and y dimesions for game:");
		
		//Store the user input
		gameX = input.nextInt();
		gameY = input.nextInt();
		
		input.nextLine();
		
		//Create an empty array for game
		String[][] gameScreen = new String[gameX] [gameY];
		
		//Fill the array with cookies and dots
		gameScreen = GameBoard(gameScreen, gameX, gameY);
		
		//Print the array
		PrintArray(gameScreen, gameX, gameY);
		
		//Cycle through each location in array
		for(int i=0; i<gameX; i++)
		{
			for(int j=0; j<gameY; j++)
			{
				//If a cookie is found
				if(gameScreen[i][j] == "O")
				{
					//Increase number of cookies
					cookie++;
				}
			}
		}
		
		//Store the total number of cookies from current cookies
		totCookie = cookie;
		
		//While there are cookies left
		while(cookie > 0 && endGame == false)
		{
			//Prompt user for next move
			System.out.println("Next Move: ");
			
			//Store the user input
			nextMove = input.nextInt();
			
			//If end of game is requested
			if(nextMove == 9)
			{
				//move to the next iteration and end loop
				endGame = true;
				continue;
			}
			
			//Use user input to move PacMan
			gameScreen = PacMove(gameScreen, nextMove, gameX, gameY);
			
			//Store cookies before the move
			tempCookie = cookie;
			
			//Set current cookies to zero
			cookie = 0;
			
			//Cycle through each location in array
			for(int i=0; i<gameX; i++)
			{
				for(int j=0; j<gameY; j++)
				{
					//If a cookie is found
					if(gameScreen[i][j] == "O")
					{
						//Increase number of cookies
						cookie++;
					}
				}
			}
			
			//If there were more cookies before moving
			if((tempCookie - cookie) > 0)
			{
				//Display that a cookie was eaten
				System.out.println("You ate a cookie!!");
			}
			
			//Print the array after moving
			PrintArray(gameScreen, gameX, gameY);
			
			//Display the score
			System.out.println("Score: " + (totCookie - cookie));
		}
		
		//If the game was not requested over
		if(endGame == false)
		{
			//Display the game was won
			System.out.println("You captured all the Pokemon..um, cookies!!");
		}
		
		//Display the game is over
		System.out.println("/*GAME OVER*/");
		
		//Close scanner
		input.close();

	}
	
	//Compare the array location to all PacMan versions
	public static boolean PacManHere(String symbol)
	{
		//If array location matches any PacMan, return true
		switch(symbol)
		{
		case "<": return true;
		case "^": return true;
		case ">": return true;
		case "V": return true;
		default: return false;
		}
	}
	
	//Determine which direction PacMan is facing
	public static int PacManFace(String facing)
	{
		//Create an integer for PacMan direction
		int retInt;
		
		//If the string matches, store the number
		switch(facing)
		{
		case "<": retInt = 0; break;
		case "^": retInt = 1; break;
		case ">": retInt = 2; break;
		case "V": retInt = 3; break;
		default: retInt = 0;
		}
		
		//Return an integer for PacMan direction
		return retInt;
	}
	
	//Perform all movements of PcMan
	public static String[][] PacMove(String[][] gameScreen, int nextMove, int gameX, int gameY)
	{
		//Crate some variables
		String[] PacManTypes = new String[] {"<", "^", ">", "V"};  //PacMan types
		int PacManDir = 0;  //PacMan direction as an int
		Integer[] PacManLocation = new Integer[2];  //PacMan location array
		Integer[] PacManLocationOld = new Integer[2];  //PacMan's location before move
		
		//Cycle through entire game array
		for(int i=0; i<gameX; i++)
		{
			for(int j=0; j<gameY; j++)
			{
				//If PacMan is found
				if(PacManHere(gameScreen[i][j]) == true)
				{
					//Store his location
					PacManLocation[0] = i;
					PacManLocation[1] = j;
					
					//Copy the location to the old location array
					PacManLocationOld = PacManLocation.clone();
					
					//Find PacMan's direction
					PacManDir = PacManFace(gameScreen[i][j]);
					
				}
			}
		}
		
		//If user selected 1
		if(nextMove == 1)
		{
			//If PacMan is at the end of the direction array
			if(PacManDir == 3)
			{
				//Cycle around the direction array
				PacManDir = 0;
			}
			
			//If not at end of direction array
			else
			{
				//Move clockwise
				PacManDir += 1;
			}
		}
		
		//If user selected 2
		if(nextMove ==2)
		{
			//If PacMan is at the end of the direction array
			if(PacManDir == 0)
			{
				//Cycle around the direction array
				PacManDir = 3;
			}
			
			//If not at end of direction array
			else
			{
				//Move clockwise
				PacManDir -= 1;
			}
		}
		
		//User selected 3
		if(nextMove == 3)
		{
			//Check PacMan direction
			if(PacManDir == 0)
			{
				//If PacMan is not at the edge
				if(PacManLocation[1] < (gameY -1))
				{
					//Move PacMan
					PacManLocation[1] += 1;
				}
			}
			
			//Check PacMan direction
			if(PacManDir == 1)
			{
				//If PacMan is not at the edge
				if(PacManLocation[0] < (gameX -1))
				{
					//Move PacMan
					PacManLocation[0] += 1;
				}
			}
			
			//Check PacMan direction
			if(PacManDir == 2)
			{
				//If PacMan is not at the edge
				if(PacManLocation[1] > 0)
				{
					//Move PacMan
					PacManLocation[1] -= 1;
				}
			}
			
			//Check PacMan direction
			if(PacManDir == 3)
			{
				//If PacMan is not at the edge
				if(PacManLocation[0] > 0)
				{
					//Move PacMan
					PacManLocation[0] -= 1;
				}
			}
		}
		
		//Update the screen after the move
		gameScreen[PacManLocation[0]][PacManLocation[1]] = PacManTypes[PacManDir];
		
		//If PacMan moved
		if(nextMove == 3 && Arrays.equals(PacManLocation, PacManLocationOld) == false)
		{
			//Put a space in PacMan's old location
			gameScreen[PacManLocationOld[0]][PacManLocationOld[1]] = " ";
		}
		
		//Return the game array after the move
		return gameScreen;
	}
	
	//Print the game array to screen
	public static void PrintArray(String[][] gameScreen, int gameX, int gameY)
	{
		//Create a 1d array
		String[] gameLine = new String[gameX];
		
		//Cycle through the game array
		for(int i=0; i< gameX; i++)
		{
			for(int j=0; j<gameY; j++)
			{
				//Store and print row of 2d array to 1d array
				gameLine[j] = gameScreen[i][j];
				System.out.print(gameLine[j]);
			}
			
			//Move to next line
			System.out.println("");
		}
	}
	
	//Populate the game array with cookies and dots
	public static String[][] GameBoard(String[][] gameScreen, int gameX, int gameY)
	{
		//Create a variable for a random number
		double randomNum;
		
		//Cycle through the array
		for(int i=0; i<gameX; i++)
		{
			for(int j=0; j<gameY; j++)
			{
				//Fill position 0,0
				if(i==0 && j==0)
				{
					//Add PacMan
					gameScreen[i][j] = "<";
				}
				
				//All other locations
				else
				{
					//Create a random number
					randomNum = Math.random();
					
					//For 8% of the entire population
					if(randomNum < 0.08)
					{
						//Fill the location with a cookies
						gameScreen[i][j] = "O";
					}
					
					//All other locations
					else
					{
						//Fill the location with a dot
						gameScreen[i][j] = ".";
					}
				}
			}
		}
		
		//Return the filled game array
		return gameScreen;
	}
	
	//Display the controls
	public static void Controls()
	{
		//Display the control buttons and actions
		System.out.println("Press 1 to turn clockwise");
		System.out.println("");
		
		System.out.println("Press 2 to turn counter-clockwise");
		System.out.println("");
		
		System.out.println("Press 3 to move");
		System.out.println("");
		
		System.out.println("Press 9 to exit");
		System.out.println("");
	}

}
