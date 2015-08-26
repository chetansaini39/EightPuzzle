package com.chetan.nextPuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.example.algo.A_Star_H_Two;
import com.example.algo.GlobalData;
import com.example.algo.HelperMethods;
import com.example.algo.PuzzleMoves;
import com.example.eightpuzzle.StaticVariableHolder;

public class NextPuzzleGenerator
{

	public int puzzle[] = GlobalData.goalState.clone();
	int noSteps = 6;// working fine with 20
	int zeroPos = 0;
	HelperMethods helper = new HelperMethods();
	int puzzleLength = puzzle.length;
	List<Integer> list = new ArrayList<Integer>();
	PuzzleMoves moves = new PuzzleMoves();
	int inversion = 0;
	A_Star_H_Two aStar;
	int randomNumber=0;
	public NextPuzzleGenerator()
	{
	
		shuffle2();
	}

	int lastMOVE = 0;

	public void Shuffle()
	{
		int randomNumber;

		for (int i = 0; i < noSteps; i++) {
			zeroPos = findZero(puzzle);
			switch (zeroPos)
			{
			case 0:
				// DOWN & RIGHT
				randomNumber = randomSelect(2);
				System.out.println("Random Number : " + randomNumber);
				if (randomNumber == 1 && lastMOVE != StaticVariableHolder.MOVE_UP) {
					moves.moveDOWN(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_DOWN;
				} else if (randomNumber == 2 && lastMOVE != StaticVariableHolder.MOVE_LEFT) {
					moves.moveRIGHT(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_RIGHT;
				}

				break;
			case 1:// DOWN & LEFT & RIGHT
				randomNumber = randomSelect(3);
				System.out.println("Random Number : " + randomNumber);
				if (randomNumber == 1 && lastMOVE != StaticVariableHolder.MOVE_UP) {
					moves.moveDOWN(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_DOWN;
				} else if (randomNumber == 2 && lastMOVE != StaticVariableHolder.MOVE_LEFT) {
					moves.moveRIGHT(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_RIGHT;
				} else if (randomNumber == 3 && lastMOVE != StaticVariableHolder.MOVE_RIGHT) {
					moves.moveLEFT(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_LEFT;
				}
				break;
			case 2:// DOWN & LEFT
				randomNumber = randomSelect(2);
				System.out.println("Random Number : " + randomNumber);
				if (randomNumber == 1 && lastMOVE != StaticVariableHolder.MOVE_UP) {
					moves.moveDOWN(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_DOWN;
				} else if (randomNumber == 2 && lastMOVE != StaticVariableHolder.MOVE_RIGHT) {
					moves.moveLEFT(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_LEFT;
				}
				break;
			case 3:// UP, DOWN & RIGHT
				randomNumber = randomSelect(3);
				System.out.println("Random Number : " + randomNumber);
				if (randomNumber == 1 && lastMOVE != StaticVariableHolder.MOVE_UP) {
					moves.moveDOWN(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_DOWN;
				} else if (randomNumber == 2 && lastMOVE != StaticVariableHolder.MOVE_LEFT) {
					moves.moveRIGHT(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_RIGHT;
				} else if (randomNumber == 3 && lastMOVE != StaticVariableHolder.MOVE_DOWN) {
					moves.moveUP(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_UP;
				}
				break;
			case 4:// UP, DOWN, LEFT & RIGHT
				randomNumber = randomSelect(4);
				System.out.println("Random Number : " + randomNumber);
				if (randomNumber == 1 && lastMOVE != StaticVariableHolder.MOVE_RIGHT) {
					moves.moveLEFT(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_LEFT;
				} else if (randomNumber == 2 && lastMOVE != StaticVariableHolder.MOVE_LEFT) {
					moves.moveRIGHT(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_RIGHT;
				} else if (randomNumber == 3 && lastMOVE != StaticVariableHolder.MOVE_DOWN) {
					moves.moveUP(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_UP;
				} else if (randomNumber == 4 && lastMOVE != StaticVariableHolder.MOVE_UP) {
					moves.moveDOWN(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_DOWN;
				}
				break;
			case 5:// UP, DOWN & LEFT
				randomNumber = randomSelect(3);
				System.out.println("Random Number : " + randomNumber);
				if (randomNumber == 1 && lastMOVE != StaticVariableHolder.MOVE_UP) {
					moves.moveDOWN(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_DOWN;
				} else if (randomNumber == 2 && lastMOVE != StaticVariableHolder.MOVE_DOWN) {
					moves.moveUP(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_UP;
				} else if (randomNumber == 3 && lastMOVE != StaticVariableHolder.MOVE_RIGHT) {
					moves.moveLEFT(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_LEFT;
				}
				break;
			case 6:// UP & RIGHT
				randomNumber = randomSelect(2);
				System.out.println("Random Number : " + randomNumber);
				if (randomNumber == 1 && lastMOVE != StaticVariableHolder.MOVE_LEFT) {
					moves.moveRIGHT(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_RIGHT;
				} else if (randomNumber == 2 && lastMOVE != StaticVariableHolder.MOVE_DOWN) {
					moves.moveUP(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_UP;
				}
				break;
			case 7:// UP, LEFT & RIGHT
				randomNumber = randomSelect(3);
				System.out.println("Random Number : " + randomNumber);
				if (randomNumber == 1 && lastMOVE != StaticVariableHolder.MOVE_LEFT) {
					moves.moveRIGHT(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_RIGHT;
				} else if (randomNumber == 2 && lastMOVE != StaticVariableHolder.MOVE_DOWN) {
					moves.moveUP(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_UP;
				} else if (randomNumber == 3 && lastMOVE != StaticVariableHolder.MOVE_RIGHT) {
					moves.moveLEFT(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_LEFT;
				}
				break;
			case 8:// UP, LEFT
				randomNumber = randomSelect(2);
				System.out.println("Random Number : " + randomNumber);
				if (randomNumber == 2 && lastMOVE != StaticVariableHolder.MOVE_RIGHT) {
					moves.moveLEFT(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_LEFT;
				} else if (randomNumber == 3 && lastMOVE != StaticVariableHolder.MOVE_DOWN) {
					moves.moveUP(puzzle);
					lastMOVE = StaticVariableHolder.MOVE_UP;
				}
				break;
			}
		}
	}
/**
 * Shuffles the input array puzzle but it can also produce insolvable puzzle
 * @param array
 */
	private void ShuffleArray(int[] array)
	{
		int index, temp;
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}


	/**
	 * Method to to find the position of Zero in the array
	 * 
	 * @param puzzle
	 * @return
	 */
	public int findZero(int[] puzzle)
	{
		int zeroPosition = 10;
		for (int i = 0; i < puzzle.length; i++) {
			if (puzzle[i] == 0) {
				zeroPosition = i;
			}
		}
		return zeroPosition;
	}

	/**
	 * Method to check if a puzzle is solvable
	 * 
	 * @param puzzle
	 * @return
	 */
	public boolean isSolvable(int[] puzzle)
	{
		System.out.println(Arrays.toString(puzzle));
		if (puzzle[0] > 1)
			inversion++;
		if (puzzle[1] > 2)
			inversion++;
		if (puzzle[2] > 3)
			inversion++;
		// if (puzzle[3] > 8)
		// inversion++;
		// if (puzzle[4] > 0)
		// inversion++;
		if (puzzle[5] > 4)
			inversion++;
		if (puzzle[6] > 7)
			inversion++;
		if (puzzle[7] > 6)
			inversion++;
		if (puzzle[8] > 5)
			inversion++;

		// for(int i=0;i<puzzleLength;i++)
		// {
		// if(puzzle[i]==0 && i%2==1)inversion++;
		// }
		System.out.println("INversion : " + inversion);
		return (inversion % 2 == 0);
	}

	/**
	 * Method to return a random int
	 * 
	 * @param i
	 * @return
	 */
	public int randomSelect(int i)
	{
		Random random = new Random();
		return random.nextInt(i)+1;
	}
	
	/**
	 * Selects a random choice from given array
	 * @param list
	 * @return
	 */
	public int getRandomSelectionFromList(int[] list)
	{
		Random random=new Random();
		return list[random.nextInt(list.length)];
	}

	/**
	 * Method to shuffle the puzzle. The methods first finds the zero postion in the puzzle and move the puzzle to LEFT/RIGHT/UP/DOWN based on random number generator
	 */
	public void shuffle2()
	{
		int i = 0;
		int list[]= {1,2};
		
		while (i < noSteps) {
			zeroPos = findZero(puzzle);
			switch (zeroPos)
			{
			case 0:
				//move RIGHT & DOWN
				list=new int[] {StaticVariableHolder.MOVE_RIGHT,StaticVariableHolder.MOVE_DOWN};
				randomNumber=getRandomSelectionFromList(list);
				i = selectRandomMove(i);
				
				break;
			case 1://move LEFT, RIGHT & DOWN
				list=new int[] {StaticVariableHolder.MOVE_LEFT,StaticVariableHolder.MOVE_RIGHT,StaticVariableHolder.MOVE_DOWN};
				randomNumber=getRandomSelectionFromList(list);
				i = selectRandomMove(i);
				break;
			case 2://move LEFT & DOWN
				list=new int[] {StaticVariableHolder.MOVE_LEFT,StaticVariableHolder.MOVE_DOWN};
				randomNumber=getRandomSelectionFromList(list);
				i = selectRandomMove(i);
				break;
			case 3://move RIGHT, UP & DOWN
				list=new int[] {StaticVariableHolder.MOVE_RIGHT,StaticVariableHolder.MOVE_UP,StaticVariableHolder.MOVE_DOWN};
				randomNumber=getRandomSelectionFromList(list);
				i = selectRandomMove(i);
				break;
			case 4://move LEFT,RIGHT , UP & DOWN
				list=new int[] {StaticVariableHolder.MOVE_LEFT,StaticVariableHolder.MOVE_RIGHT,StaticVariableHolder.MOVE_UP,StaticVariableHolder.MOVE_DOWN};
				randomNumber=getRandomSelectionFromList(list);
				i = selectRandomMove(i);
				break;
			case 5://move LEFT, UP & DOWN
				list=new int[] {StaticVariableHolder.MOVE_LEFT,StaticVariableHolder.MOVE_UP,StaticVariableHolder.MOVE_DOWN};
				randomNumber=getRandomSelectionFromList(list);
				i = selectRandomMove(i);
				break;

			case 6://move RIGHT , UP 
				list=new int[] {StaticVariableHolder.MOVE_RIGHT,StaticVariableHolder.MOVE_UP};
				randomNumber=getRandomSelectionFromList(list);
				i = selectRandomMove(i);
				break;
			case 7://move LEFT,RIGHT , UP 
				list=new int[] {StaticVariableHolder.MOVE_LEFT,StaticVariableHolder.MOVE_RIGHT,StaticVariableHolder.MOVE_UP};
				randomNumber=getRandomSelectionFromList(list);
				i = selectRandomMove(i);
				break;
			case 8://move LEFT, UP 
				list=new int[] {StaticVariableHolder.MOVE_LEFT,StaticVariableHolder.MOVE_UP};
				randomNumber=getRandomSelectionFromList(list);
				i = selectRandomMove(i);			
				break;
			}
		}
	}

	/**
	 * @param randNum
	 * @return
	 */
	private int selectRandomMove(int randNum)
	{
		if(randomNumber==1 && lastMOVE!=StaticVariableHolder.MOVE_RIGHT)
		{
			moves.moveLEFT(puzzle);
			lastMOVE=StaticVariableHolder.MOVE_LEFT;
			randNum++;
		}
		else if(randomNumber==2 && lastMOVE!=StaticVariableHolder.MOVE_LEFT )
		{
			moves.moveRIGHT(puzzle);
			lastMOVE=StaticVariableHolder.MOVE_RIGHT;
			randNum++;
		}
		
		else if(randomNumber==3 && lastMOVE!=StaticVariableHolder.MOVE_DOWN)
		{
			moves.moveUP(puzzle);
			lastMOVE=StaticVariableHolder.MOVE_UP;
			randNum++;
		}
		else if(randomNumber==4 && lastMOVE!=StaticVariableHolder.MOVE_UP)
		{
			moves.moveDOWN(puzzle);
			lastMOVE=StaticVariableHolder.MOVE_DOWN;
			randNum++;
		}
		return randNum;
	}
}