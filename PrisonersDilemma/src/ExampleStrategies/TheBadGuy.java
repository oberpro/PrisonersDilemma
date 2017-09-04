package ExampleStrategies;

import java.util.Random;

import PrisonersDilemma.Choice;
import PrisonersDilemma.PrisonersStrategy;

public class TheBadGuy implements PrisonersStrategy{

	@Override
	public Choice turn(Choice[] myChoices, Choice[] otherChoices, Random rand) {
		return Choice.betray;
	}
	
}
