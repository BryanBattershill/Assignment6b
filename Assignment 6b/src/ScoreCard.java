
/****************************************************************************
*
* Created by: Bryan Battershill
* Created on: Nov 2016
* This class is responsible for the scores and the score card
*
****************************************************************************/

import java.util.Arrays;
import java.util.Scanner;

public class ScoreCard {
	private int[] _scores = {0,0,0,0,0,0,0,0,0,0,0,0,0};
	private int _rounds = 1;
	private boolean _bonusYahtzee = true;
	private boolean[] _scoresFilled = {false,false,false,false,false,false,
			false,false,false,false,false,false,false,};
	
	public void score(int boxChosen, int[] dice){
		int total = 0;
		int oneFreq = checkFrequency(dice, 1);
		int twoFreq = checkFrequency(dice, 2);
		int threeFreq = checkFrequency(dice, 3);
		int fourFreq = checkFrequency(dice, 4);
		int fiveFreq = checkFrequency(dice, 5);
		int sixFreq = checkFrequency(dice, 6);
		
		if (boxChosen > 0 && boxChosen<7){
			for (int counter1 = 1; counter1<7; counter1++){
				if (boxChosen == counter1){
					for (int counter2 = 0; counter2<5; counter2++){
						if (dice[counter2] == boxChosen){
							total += boxChosen;
						}
					}
					break;
				}
			}
		}
		else if (boxChosen == 7){
			if (oneFreq > 2||twoFreq > 2||threeFreq > 2||fourFreq > 2||fiveFreq > 2||sixFreq > 2){
				total = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
			}
		}
		else if (boxChosen == 8){
			if (oneFreq > 3||twoFreq > 3||threeFreq > 3||fourFreq > 3||fiveFreq > 3||sixFreq > 3){
				total = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
			}
		}
		else if (boxChosen == 9){
			if ((oneFreq == 2)&&(twoFreq == 3||threeFreq == 3||fourFreq == 3||fiveFreq == 3||sixFreq == 3)){					
				total = 25;
			}
			else if ((twoFreq == 2)&&(oneFreq == 3||threeFreq == 3||fourFreq == 3||fiveFreq == 3||sixFreq == 3)){					
				total = 25;
			}
			else if ((threeFreq == 2)&&(oneFreq == 3||twoFreq == 3||fourFreq == 3||fiveFreq == 3||sixFreq == 3)){					
				total = 25;
			}
			else if ((fourFreq == 2)&&(oneFreq == 3||threeFreq == 3||twoFreq == 3||fiveFreq == 3||sixFreq == 3)){					
				total = 25;
			}
			else if ((fiveFreq == 2)&&(oneFreq == 3||threeFreq == 3||fourFreq == 3||twoFreq == 3||sixFreq == 3)){					
				total = 25;
			}
			else if ((sixFreq == 2)&&(oneFreq == 3||threeFreq == 3||fourFreq == 3||fiveFreq == 3||twoFreq == 3)){					
				total = 25;
			}
		}	
		else if (boxChosen == 10||boxChosen==11){
			int consecNums = 1;
			int[] tempDice = dice.clone();
			Arrays.sort(tempDice);
			for (int counter = 0; counter < 4; counter++){
				if (tempDice[counter] + 1 != tempDice[counter+1]){
					consecNums = 1;
				}
				else{
					consecNums++;
					if (boxChosen == 10){
						if (consecNums == 4){
							total = 30;
							break;
						}
					}
					else if (boxChosen == 11){
						if (consecNums == 5){
							total = 40;
							break;
						}
					}
				}
			}
		}
		else if (boxChosen == 12){
			total = 0;
			this._bonusYahtzee = false;
		}
		else if (boxChosen == 13){
			total = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
		}
			
		this._scores[boxChosen-1] = total;
		this._scoresFilled[boxChosen-1] = true;
		nextRound();
	}
	
	private void tally(){
		int upperTotal = 0;
		int grandTotal = 0;
		
		upperTotal = this._scores[0] + this._scores[1] + this._scores[2] 
				+ this._scores[3] + this._scores[4] + this._scores[5];
		if (upperTotal > 62){
			upperTotal+= 35;
		}
		grandTotal = this._scores[6] + this._scores[7] + this._scores[8] 
				+ this._scores[9] + this._scores[10] + this._scores[11] + this._scores[12] + upperTotal;
		System.out.println("\n\n\n\n\n\n\n\n\n");
		printScores();
		System.out.println("Your final score is " + grandTotal + " points!");
		System.exit(1);
	}
	
	public void printScores(){
		int upperTotal = this._scores[0] + this._scores[1] + this._scores[2] 
				+ this._scores[3] + this._scores[4] + this._scores[5];
		System.out.println("=====================");
		System.out.println("\nUpper section: \n1. Aces: " + this._scores[0] + "\n2. Twos: " + 
				this._scores[1] + "\n3. Threes: " + this._scores[2] + "\n4. Fours: " + 
				this._scores[3] + "\n5. Fives: " + this._scores[4] + "\n6. Sixes: " + this._scores[5]);
		System.out.println("Total: " + upperTotal);
		if (upperTotal > 62){
			System.out.println("Bonus: 35");
			System.out.println("Total + bonus: " + (upperTotal + 35));
		}
		else {
			System.out.println("Bonus: 0");
			System.out.println("Total + bonus: " + upperTotal);
		}
		System.out.println("\n=====================");
		System.out.println("\nLower section: \n7. 3 of a Kind: " + this._scores[6]
				+ "\n8. 4 of a kind: " + this._scores[7] + "\n9. Full House: " + this._scores[8]
				+ "\n10. Short Straight: " + this._scores[9] + "\n11. Long Straight: " + this._scores[10] 
				+ "\n12. Yahtzee: " + this._scores[11] + "\n13. Chance: " + this._scores[12]);
		System.out.println("Total: " + (this._scores[6] + this._scores[7] + this._scores[8] 
				+ this._scores[9] + this._scores[10] + this._scores[11] + this._scores[12]) + '\n');
		System.out.println("=====================");
	}
	
	private void nextRound(){
		this._rounds++;
		if (this._rounds == 14){
			tally();
		}
	}
	
	public boolean getScoresFilled(int boxChosen){
		return this._scoresFilled[boxChosen-1];
	}
	
	public boolean checkYahtzee(int[] dice){
		int oneFreq = checkFrequency(dice, 1);
		int twoFreq = checkFrequency(dice, 2);
		int threeFreq = checkFrequency(dice, 3);
		int fourFreq = checkFrequency(dice, 4);
		int fiveFreq = checkFrequency(dice, 5);
		int sixFreq = checkFrequency(dice, 6);
		
		if (oneFreq == 5||twoFreq == 5||threeFreq == 5||fourFreq == 5||fiveFreq == 5||sixFreq == 5){
			System.out.println("YAHTZEE!!! YAHTZEE!!! YAHTZEE!!! YAHTZEE!!! YAHTZEE!!! YAHTZEE!!!");
			if (this._bonusYahtzee){
				if (this._scores[11] > 0){
					this._scores[11] += 100;
				}
				else{
					this._scores[11] = 50;
				}
				this._scoresFilled[11] = true;
			}
			else{
				if (oneFreq==5&&!this._scoresFilled[0]){
					System.out.println("The aces section has been filled in accordance with the 'Joker Rules'");
					score(1, dice);
				}
				else if (twoFreq==5&&!this._scoresFilled[1]){
					System.out.println("The twos section has been filled in accordance with the 'Joker Rules'");
					score(2, dice);
				}
				else if (threeFreq==5&&!this._scoresFilled[2]){
					System.out.println("The threes section has been filled in accordance with the 'Joker Rules'");
					score(3, dice);
				}
				else if (fourFreq==5&&!this._scoresFilled[3]){
					System.out.println("The fours section has been filled in accordance with the 'Joker Rules'");
					score(4, dice);
				}
				else if (fiveFreq==5&&!this._scoresFilled[4]){
					System.out.println("The fives section has been filled in accordance with the 'Joker Rules'");
					score(5, dice);
				}
				else if (sixFreq==5&&!this._scoresFilled[5]){
					System.out.println("The sixes section has been filled in accordance with the 'Joker Rules'");
					score(6, dice);
				}
				else{
					Scanner userIn = new Scanner(System.in);
					int userInt;
					boolean availableLower = false;
					
					for (int counter = 6; counter <13; counter++){
						if (!_scoresFilled[counter]){
							availableLower = true;
						}
					}
					System.out.println("\n\n\n\n\n\n\n\n\n");
					if (availableLower){
						System.out.println("There corresponding upper section score box is not available, "
								+ "which lower section box would you like to score in?");
						printScores();
						for(;;){
							if (userIn.hasNextInt()){
								userInt = userIn.nextInt();
								if ((userInt > 6 && userInt<14)&&(!_scoresFilled[userInt-1])){
									if (userInt == 7){
										this._scores[userInt-1] = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
									}
									else if (userInt == 8){
										this._scores[userInt-1] = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
									}
									else if (userInt == 9){
										this._scores[userInt-1] = 25;
									}
									else if (userInt == 10){
										this._scores[userInt-1] = 30; 
									}
									else if (userInt == 11){
										this._scores[userInt-1] = 40;
									}
									else if (userInt == 13){
										this._scores[userInt-1] = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
									}
									this._scoresFilled[userInt-1] = true;
									break;
								}
								else {
									System.out.println("That box is not valid.");
								}
							}
						}
					}
					else{
						System.out.println("There are no valid upper or lower section score boxes available,"
								+ " where would you like to score a zero?");
						printScores();
						for (;;){
							if (userIn.hasNextInt()){
								userInt = userIn.nextInt();
								if ((userInt > 0 && userInt<7)&&(!_scoresFilled[userInt-1])){
									if (userInt == 1){
										this._scores[userInt-1] = 0;
									}
									else if (userInt == 2){
										this._scores[userInt-1] = 0;
									}
									else if (userInt == 3){
										this._scores[userInt-1] = 0;
									}
									else if (userInt == 4){
										this._scores[userInt-1] = 0; 
									}
									else if (userInt == 5){
										this._scores[userInt-1] = 0;
									}
									else if (userInt == 6){
										this._scores[userInt-1] = 0;
									}
									this._scoresFilled[userInt-1] = true;
									break;
								}	
								else {
									System.out.println("That box is not valid.");
								}
							}
							else {
								System.out.println("That box is not valid.");
							}
						}
					}
				}
			}
			nextRound();
			return true;
		}
		else {
			return false;
		}
	}
	
	private int checkFrequency(int[]dice, int numEntered){
		int numFreq=0;
		for (int counter = 0; counter < 5; counter++){
			if (dice[counter] == numEntered){
				numFreq++;
			}
		}	
		return numFreq;
	}
	
	public void printRounds(){
		System.out.println("Round " + this._rounds + "/13");
	}
}
