
/****************************************************************************
*
* Created by: Bryan Battershill
* Created on: Nov 2016
* This class is responsible for the dice properties and methods related to 
* them
*
****************************************************************************/

import java.util.Random;

public class Cup {
	//Dive values, dice to not roll, and times rolled in a round
	private int[] _diceVals = new int[5];
	private boolean[] _restricted = new boolean[5];
	private int _timesRolled = 0;
	
	//Roll the unrestricted dice (rand 1-6)
	public void rollDice(){
		
		Random rand = new Random();
		
		for (int counter = 0; counter < 5; counter++){
			
			if (!this._restricted[counter]){
				
				this._diceVals[counter] = rand.nextInt(6) + 1;
				
			}
		}
		this._timesRolled++;
	}
	
	//Restrict a die
	public void restrictDie(int die){
		this._restricted[die-1] = true;
	}
	
	//Unrestrict a die
	public void unrestrictDie(int die){
		this._restricted[die-1] = false;
	}
	
	//Return the array of dice for the score card
	public int[] getDice(){
		return this._diceVals;
	}
	
	//Print out the dice and which are restricted in a specific format
	public void printDice(){
		
		System.out.println("Die A. " + this._diceVals[0] + " Die B. " + this._diceVals[1] 
				+ " Die C. " + this._diceVals[2] + " Die D. " + this._diceVals[3] 
				+ " Die E. " + this._diceVals[4]);
		
		System.out.println("=====================");
		
		System.out.println("Restricted: \n" + this._restricted[0] + " " + this._restricted[1] 
				+ " " + this._restricted[2] + " " + this._restricted[3] + " " + this._restricted[4]);
		
		System.out.println("=====================");
	}
	
	//Find how many rolls have happened this round
	public int getTimesRolled(){
		return this._timesRolled;
	}
	
	//Reset the number of rolls and set all dice to unrestricted
	public void resetRolls(){
		this._timesRolled = 0;
		
		for (int counter = 0; counter < 5; counter++){
			
			this._restricted[counter] = false;
		}
	}
}
