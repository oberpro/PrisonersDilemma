package PrisonersDilemma;

import java.util.Random;

public interface PrisonersStrategy {
	public Choice turn(Choice[] myChoices, Choice[] otherChoices, Random rand);
}
