import java.util.Scanner;

public class GameEngine {
	
	public void newGame(){
		Scanner userIn = new Scanner(System.in);
		ScoreCard scoreCard = new ScoreCard();
		Cup cup = new Cup();
		String userString;
		int userInt;
		
		System.out.println("Type '1' to make the first roll.");
		if (userIn.next().equals("1")){
			cup.rollDice();
			//Double infinite loop because there is no goto method
			for (;;){
			for (;;){
				System.out.println("\n\n\n\n\n\n\n\n\n");
				cup.printDice();
				System.out.println("Rolls left: " + (3-cup.getTimesRolled()));
				scoreCard.printRounds();
				scoreCard.printScores();
				System.out.println("Enter the number of the action you would like to take:"
						+ "\n1.Roll dice\n2.Restrict die\n3.Unrestrict die\n4.Score a box");
				
				if (scoreCard.checkYahtzee(cup.getDice())){
					System.out.println("Type anything to perform the next roll.");
					userString = userIn.next();
					cup.resetRolls();
					cup.rollDice();
					break;
				}
				userString = userIn.next(); 	
				
				if (userString.equals("1")){
					if (cup.getTimesRolled() != 3){
						cup.rollDice();
					}
					else {
						System.out.println("You have already rolled 3 times");
					}
				}
				else if (userString.equals("2")){
					System.out.println("Which die would you like to restrict (A = 1...)?");
					if (userIn.hasNextInt()){
						cup.restrictDie(userIn.nextInt());
					}
					else {
						System.out.println("That is not a valid input.");
					}
				}
				else if (userString.equals("3")){
					System.out.println("Which die would you like to unrestrict (A = 1...)?");
					if (userIn.hasNextInt()){
						cup.unrestrictDie(userIn.nextInt());
					}
					else {
						System.out.println("That is not a valid input.");
					}
				}
				else if (userString.equals("4")){
					System.out.println("Which box would you like to score?");
					if (userIn.hasNextInt()){
						userInt = userIn.nextInt();
						if ((userInt > 0 && userInt<14)&&(!scoreCard.getScoresFilled(userInt))){
							scoreCard.score(userInt, cup.getDice());
							System.out.println("Type anything to perform the next roll.");
							userString = userIn.next();
							cup.resetRolls();
							cup.rollDice();
						}
						else{
							System.out.println("Not a valid box");
						}
					}
					else {
						System.out.println("That is not a valid input.");
					}
				}
				else {
					System.out.println("Not a valid action");
				}
			}
			}
		}
	}
}
