package PrisonersDilemma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import ExampleStrategies.TheBadGuy;
import ExampleStrategies.TheConsequentGuy;
import ExampleStrategies.TheGoodGuy;
import ExampleStrategies.TheRandomGuy;

public class Main {
	public static Random rand = new Random();
	public static PrisonersStrategy[] strats;
	
	public static void recreateStrats(){
		PrisonersStrategy[] strats = {
				new TheBadGuy(),
				new TheGoodGuy(),
				new TheRandomGuy(),
				new TheConsequentGuy(),
				new TheConsequentGuy(),
				new TheConsequentGuy(),
				new TheConsequentGuy(),
			};
		
		Main.strats = strats;
	}
	
	public static void main(String[] args) {
		recreateStrats();
		
		
		
		PrisonerWithPoints[] candidates = new PrisonerWithPoints[strats.length];
		for(int i = 0; i<candidates.length; i++){
			candidates[i] = new PrisonerWithPoints(strats[i]);
		}
		
		tournament(candidates);
		
		Arrays.sort(candidates, new Comparator<PrisonerWithPoints>(){
			@Override
			public int compare(PrisonerWithPoints p1, PrisonerWithPoints p2) {
				return (p2.score - p1.score);
			}
		});
		
		for(int i = 0; i<candidates.length; i++){
			System.out.println(padRight("#" + (i + 1), 5) + " " + padRight(candidates[i].prisoner.getClass().getSimpleName(), 20) + " " + candidates[i].score);
		}
	}
	
	public static String padRight(String string, int spaces) {
		return String.format("%1$-" + spaces + "s", string);  
	}
	
	public static void tournament(PrisonerWithPoints[] candidates){
		for(int i = 0; i<candidates.length; i++){
			for(int k = i + 1; k<candidates.length; k++){
				fight(candidates[i], candidates[k]);
				recreateStrats();						//Perhaps a little wasteful?
				candidates[i].prisoner = strats[i];
				candidates[k].prisoner = strats[k];
			}
		}
	}
	
	public static void fight(PrisonerWithPoints p1, PrisonerWithPoints p2){
		/*System.out.println("Fight start of...");
		System.out.println(p1.prisoner.getClass().getSimpleName());
		System.out.println(p2.prisoner.getClass().getSimpleName());
		System.out.println();*/
		
		List<Choice> p1Choices = new ArrayList<>();
		List<Choice> p2Choices = new ArrayList<>();
		
		for(int i = 0; i<32; i++){
			Choice p1Choice = Choice.cooperate;
			Choice p2Choice = Choice.cooperate;
			
			{
				Choice[] arrP1Choices = new Choice[i];
				Choice[] arrP2Choices = new Choice[i];

				p1Choices.toArray(arrP1Choices);
				p2Choices.toArray(arrP2Choices);
				
				p1Choice = p1.prisoner.turn(arrP1Choices, arrP2Choices, rand);
			}
			
			{
				Choice[] arrP1Choices = new Choice[i];
				Choice[] arrP2Choices = new Choice[i];

				p1Choices.toArray(arrP1Choices);
				p2Choices.toArray(arrP2Choices);
				
				p2Choice = p2.prisoner.turn(arrP2Choices, arrP1Choices, rand);
			}
			
			if      (p1Choice == Choice.cooperate && p2Choice == Choice.cooperate)
			{
				p1.score += 50;
				p2.score += 50;
			}else if(p1Choice == Choice.betray    && p2Choice == Choice.cooperate)
			{
				p1.score += 75;
				p2.score += 0;
			}else if(p1Choice == Choice.cooperate && p2Choice == Choice.betray)
			{
				p1.score += 0;
				p2.score += 75;
			}else if(p1Choice == Choice.betray    && p2Choice == Choice.betray)
			{
				p1.score += 10;
				p2.score += 10;
			}
			
			p1Choices.add(p1Choice);
			p2Choices.add(p2Choice);
		}
	}
}
