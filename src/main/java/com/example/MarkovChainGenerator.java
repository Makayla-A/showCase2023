/*
 * NAME: Makayla Alston
 * COURSE: CRCP 3315 001-1237
 * INSTRUCTOR: Professor Courtney Brown
 * DATE: Oct 19, 2023
 * DESCRIPTION: A generic class called MarkoveChainGenerator, which generates notes for a melody based on the note that comes before it
 * CREDIT: Professor Courtney Brown (author of: protected class SortTTOutput; public SortTTOutput sortTT(ArrayList<E> symbols, ArrayList<ArrayList<Float>> tt); 
 * public void printProbabilityDistribution(boolean round, ArrayList<E> symbols, ArrayList<ArrayList<Float>> tt); 
 * CLASS: MarkovChainGenerator
 * 
 */
package com.example;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MarkovChainGenerator<E> extends ProbabilityGenerator<E>
{
	//empty 2D array of floats called transitionTable
	ArrayList<ArrayList<Float>> transitionTable = new ArrayList<ArrayList<Float>>();
	//keeps track of the number of notes received from input
	double MTokenCounts = 0.0;
	//an instance of my Probability Generator class, which I will use in my generate function
	ProbabilityGenerator<E> ProbGen = new ProbabilityGenerator<>();


  	//nested convenience class to return two arrays from sortTransitionTable() method
	//students do not need to use this class
	protected class SortTTOutput
	{
		public ArrayList<E> symbolsListSorted;
		ArrayList<ArrayList<Float>> ttSorted;
	}

	/*
	 * this function takes in data from the MIDI file. it examines each token and the token that FOLLOWS it and records the amount of times the 
	 * succeeding token follows the proceeding token, storing it in a 2D transitionTable (an ArrayList of Arrays). The function first 
	 * checks to see if the token appears in the alphabet. If it isn't, a new array is added to the transitionTable and filled with zeroes; 
	 * the token is added to the alphabet. if the token already appears in the alphabet, the value that records the intersection of the token and
	 * its succeeding token is incremented by 1
	 * ArrayList<E> data: an ArrayList of tokens. These tokens are taken from the MIDI input file
	 */
	public void train(ArrayList<E> data)
	{
		//the index of the previous token from input
		int lastIndex = -1;
		//iterate through data
		for (int i=0; i < data.size(); i++)
		{
			//tokenIndex is the index of the token in alphabet array
			int tokenIndex = alphabet.indexOf(data.get(i));
			//if it's not in alphabet, -1 is returned and the if statement is entered
			if (tokenIndex == -1)
			{				
				//tokenIndex is set to the size of alphabet
				tokenIndex = alphabet.size();
				//create a new array that is the size of alphabet
				ArrayList<Float> newArray = new ArrayList<Float>();
				//add the new array to my transitionTable
				transitionTable.add(newArray);
				//adding zeroes to the new array
				for (int j=0; j < alphabet.size(); j++)
				{
					newArray.add(0.0f);
				}
				//adds a column to the transitionTable
				for (int j = 0; j < transitionTable.size(); j++)
				{
					transitionTable.get(j).add(0.0f);
				}
				//add token to alphabet array
				alphabet.add(data.get(i));
			}
			if (lastIndex > -1)
			{
				ArrayList<Float> row = transitionTable.get(lastIndex); 
				row.set(tokenIndex, row.get(tokenIndex)  + 1.0f);
			}	
		//setting current to previous for next round
		lastIndex = tokenIndex;
		//incrementing the variable that keeps track of how many notes there are by a value of 1
		MTokenCounts++;
		}
		//calling the train function on the data passed into the function. Train is called on my instance of Probability Generator (called ProbGen)
		ProbGen.train(data);
	}

	/*
	 * this function iterates through an ArrayList and finds the sum of all the values in the list
	 * ArrayList<Float> data: an ArrayList of floats, which records the total amount of times a given token is immediately followed by a specific token
	 */
	public float ArraySum(ArrayList<Float> data)
	{
		//creates and initalizes a variable sum, sets it to 0
		float sum = 0.0f;
		//iterates through the row
		for (int i = 0; i < data.size(); i++)
		{
			//updates sum to include the value at the given index
			sum = data.get(i) + sum;
		}
		//returns the final value of sum
		return sum;
	}

	/*
	 * this function generates a new note based on a previously generated note 
	 * E initToken: a note of data type E that represents the previously generated note; starting token
	 */
	public E generate(E initToken)
	{
		//a local variable which will keep track of the position of the initToken in the alphabet array. Initalized at 0
		int initTokenIndex = 0;
		//a local variable which will hold the value of the sum of a given array. Initalized at 0
		float sum = 0;
		//finds the position of initToken in the alphabet array and updates the local variable initTokenIndex
		initTokenIndex = alphabet.indexOf(initToken);
		//assigns local variable sum with the value returned from my ArraySum function. I pass ArraySum the array in transitionTable that 
		//is located at position initTokenIndex
		sum = ArraySum(transitionTable.get(initTokenIndex));
		//if the sum of the array is 0
		if (sum == 0)
		{
			//returns ProbGen's generate
			return ProbGen.generate(1).get(0);
		}
		else
		{
			//calls Probability Generator's generate function and returns the note that the function produces
			return super.generate(alphabet, transitionTable.get(initTokenIndex),sum);
		}
	}     

	/*
	 * this function creates the Markov Chain, which generates a note based on the note that came before it
	 * E initToken: a note of data type E that represents the previously generated note; starting token
	 * int numberOfTokensToGenerate: the amount of times the for loop will be called
	 */
	ArrayList<E> generate(E initToken, int numberOfTokensToGenerate)
	{
		//an arraylist called notes is initalized with data type E
		ArrayList<E> notes = new ArrayList<E>();
		//for loop; the number of times the for loop will run
		for (int i = 0; i < numberOfTokensToGenerate; i++)
		{
			//calls the generate function that accepts a parameter of type E
			E token = generate(initToken);
			//adds the token to the arrayList called notes
			notes.add(token);
			//sets the initToken (which is passed to the generate function) to the previously generated token
			initToken = token;
		}
		//returns notes arrayList
		return notes;
	}

	/*
	 * this function calls the generate function that accepts 2 parameters with a random initToken
	 * int numberOfTokensToGenerate: the amount of times the function will execute
	 */
	public ArrayList<E> generate(int numberOfTokensToGenerate)
	{
		//generates a random initToken
		E initToken = ProbGen.generate(1).get(0);
		//returns the arrayList created by the generate function; calls said generate function and passes it a random initToken and the amount 
		//of tokens the function will generate
		return  generate(initToken, numberOfTokensToGenerate);
	}

	//sort the symbols list and the counts list, so that we can easily print the probability distribution for testing
	//symbols -- your alphabet or list of symbols (input)
	//tt -- the unsorted transition table (input)
	//symbolsListSorted -- your SORTED alphabet or list of symbols (output)
	//ttSorted -- the transition table that changes reflecting the symbols sorting to remain accurate  (output)
	public SortTTOutput sortTT(ArrayList<E> symbols, ArrayList<ArrayList<Float>> tt)	
	{

		SortTTOutput sortArraysOutput = new SortTTOutput(); 
		
		sortArraysOutput.symbolsListSorted = new ArrayList<E>(symbols);
		sortArraysOutput.ttSorted = new ArrayList<ArrayList<Float>>();
	
		//sort the symbols list
		Collections.sort(sortArraysOutput.symbolsListSorted, new Comparator<E>() {
			@Override
			public int compare(E o1, E o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});

		//use the current sorted list to reference the counts and get the sorted counts
		for(int i=0; i<sortArraysOutput.symbolsListSorted.size(); i++)
		{
			int index = symbols.indexOf(sortArraysOutput.symbolsListSorted.get(i));
			sortArraysOutput.ttSorted.add(new ArrayList<Float>());
			for( int j=0; j<tt.get(index).size(); j++)
			{
				int index2 = symbols.indexOf(sortArraysOutput.symbolsListSorted.get(j));
				sortArraysOutput.ttSorted.get(i).add(tt.get(index).get(index2));
			}
		}

		return sortArraysOutput;
	}

	//this prints the transition table
	//symbols - the alphabet or list of symbols found in the data
	//tt -- the transition table of probabilities (not COUNTS!) for each symbol coming after another
	public void printProbabilityDistribution(boolean round, ArrayList symbols, ArrayList<ArrayList<Float>> tt)
	{
		//sort the transition table
		SortTTOutput sorted = sortTT(symbols, tt);
		symbols = sorted.symbolsListSorted;
		tt = sorted.ttSorted;

		System.out.println("-----Transition Table -----");
		
		System.out.println(symbols);
		
		for (int i=0; i<tt.size(); i++)
		{
			System.out.print("["+symbols.get(i) + "] ");
			for(int j=0; j<tt.get(i).size(); j++)
			{
				//Grabs the data from the transition table
				float data = tt.get(i).get(j);
				//Adds the value of all the values in a single row
				double sum = ArraySum(tt.get(i));
				//Creates a variable called probability and initalizes it to 0
				double  prob = 0.0f;
				//If the sum of the row is 0, enter this if statement
				if (sum == 0)
				{
					//Sets the probability to 0
					prob = 0.0f;
				}
				else
				{
					//Divides the data by the sum 
					prob = (double)data / sum;
				}

				if(round)
				{
					DecimalFormat df = new DecimalFormat("#.##");
					System.out.print( df.format(prob) + " ");
				}
				else
				{
					System.out.print(prob + " ");
				}
			
			}
			System.out.println();


		}
		System.out.println();
		
		System.out.println("------------");
	}


	  /*
     * An overloaded print function that uses class variables to output data
     * boolean round: a parameter used to specify whether the train method or generate method is being run. To run train(), this value should be
     * true. To run generate(), this value should be false.
     */
	public void printProbabilityDistribution(boolean round)
	{
		printProbabilityDistribution(round, alphabet, transitionTable);
	}
}
