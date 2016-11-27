import java.util.Random;

public class Cup {
	private int[] _diceVals = new int[5];
	private boolean[] _restricted = new boolean[5];
	private int _timesRolled = 0;
	
	public void rollDice(){
		Random rand = new Random();
		for (int counter = 0; counter < 5; counter++){
			if (!this._restricted[counter]){
				this._diceVals[counter] = rand.nextInt(6) + 1;
			}
		}
		this._timesRolled++;
	}
	
	public void restrictDie(int die){
		this._restricted[die-1] = true;
	}
	
	public void unrestrictDie(int die){
		this._restricted[die-1] = false;
	}
	
	public int[] getDice(){
		return this._diceVals;
	}
	
	public void printDice(){
		System.out.println("Die A. " + this._diceVals[0] + " Die B. " + this._diceVals[1] 
				+ " Die C. " + this._diceVals[2] + " Die D. " + this._diceVals[3] 
				+ " Die E. " + this._diceVals[4]);
		System.out.println("Restricted: \n" + this._restricted[0] + " " + this._restricted[1] 
				+ " " + this._restricted[2] + " " + this._restricted[3] + " " + this._restricted[4]);
	}
	
	public int getTimesRolled(){
		return this._timesRolled;
	}
	
	public void resetRolls(){
		this._timesRolled = 0;
		
		for (int counter = 0; counter < 5; counter++){
			this._restricted[counter] = false;
		}
	}
}
