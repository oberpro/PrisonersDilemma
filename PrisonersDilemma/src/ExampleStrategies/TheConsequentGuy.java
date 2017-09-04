package ExampleStrategies;

import java.util.Random;

import PrisonersDilemma.Choice;
import PrisonersDilemma.PrisonersStrategy;

public class TheConsequentGuy implements PrisonersStrategy{

	@Override
	public Choice turn(Choice[] myChoices, Choice[] otherChoices, Random rand) {
		if(myChoices.length == 0)
		{
			return Choice.cooperate;
		}
		else
		{
			return otherChoices[otherChoices.length - 1];
		}
	}

}
