package ExampleStrategies;

import java.util.Random;

import PrisonersDilemma.Choice;
import PrisonersDilemma.PrisonersStrategy;

public class TheRandomGuy implements PrisonersStrategy{

	@Override
	public Choice turn(Choice[] myChoices, Choice[] otherChoices, Random rand) {
		if(rand.nextBoolean()){
			return Choice.cooperate;
		}
		else{
			return Choice.betray;
		}
	}
	
}
