package ExampleStrategies;

import java.util.Random;

import PrisonersDilemma.Choice;
import PrisonersDilemma.PrisonersStrategy;

public class TheSmartGuy implements PrisonersStrategy {

	@Override
	public Choice turn(Choice[] myChoices, Choice[] otherChoices, Random rand) {
		Choice myLastChoice = getLast(myChoices);
		Choice otherLastChoice = getLast(otherChoices);
		if(amountMoreThan(3,Choice.betray,otherChoices)){
			return Choice.betray;
		}
		if(myLastChoice != null && otherLastChoice != null){
			if(myLastChoice.equals(otherLastChoice) && myLastChoice.equals(Choice.betray)){
				return Choice.betray;
			}
		}
		return Choice.cooperate;
	}
	
	private boolean amountMoreThan(int number,Choice c,Choice[] choices){
		int cnt = countChoice(c,choices);
		if(cnt >= number ){
			return true;
		}
		return false;
	}
	
	private int countChoice(Choice c,Choice[] choices){
		int cnt = 0;
		for (Choice choice : choices) {
			if(choice.equals(c)){
				cnt++;
			}
		}
		return cnt;
	}
	
	private Choice getLast(Choice[] choices){
		if(choices.length == 0)return null;
		return choices[choices.length-1];
	}

}
