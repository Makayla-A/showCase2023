/*
 * NAME: Makayla Alston
 * COURSE: CRCP 3315 001-1237
 * INSTRUCTOR: Professor Courtney Brown
 * DATE: Oct 19, 2023
 * DESCRIPTION: A generic class called UnitTests that calls the appropriate unit test functions corresponding to the argument passed in launch.json
 * Class: UnitTests
 * 
 */

 package com.example;

 import java.util.ArrayList;

public class UnitTests 

{
	static MidiFileToNotes midiNotes;
	static MelodyPlayer player;

	//constructor for an instance of UnitTests with midi notes and melody player parameters
	UnitTests(MidiFileToNotes m, MelodyPlayer p)
	{
		midiNotes = m;
		player = p;

	}

	/* 
	 * function that runs Project 1 Unit Test 1
	 * int whichTest: value from command line input that trigger switch statement cases; 0 corresponds with this Unit Test
	*/
    public void Proj1UnitTest1 (int whichTest)
    {
        //creation of a pitch generator and a rhythm generator, both ArrayLists. Pitchgen is an ArrayList of integers. RhythmGen is an ArrayList of doubles
		ProbabilityGenerator<Integer> pitchgen = new ProbabilityGenerator<Integer>();
		ProbabilityGenerator<Double> rhythmgen = new ProbabilityGenerator<Double>();

		//ProbabilityGenerator's train() function is called on both PitchGen and RhythmGen
		pitchgen.train(midiNotes.getPitchArray());
		rhythmgen.train(midiNotes.getRhythmArray());

		//The newly created probability generators are printed without rounded values
		pitchgen.printProbabilityDistribution(false);
		rhythmgen.printProbabilityDistribution(false);

    }

	/* 
	 * function that runs Project 1 Unit Test 2
	 * int whichTest: value from command line input that trigger switch statement cases; 1 corresponds with this Unit Test
	*/
    public void Proj1UnitTest2 (int whichTest)
    {
		//creation of a pitch generator and a rhythm generator, both ArrayLists. Pitchgen is an ArrayList of integers. RhythmGen is an ArrayList of doubles
        ProbabilityGenerator<Integer> pitchGen = new ProbabilityGenerator<Integer>();
		ProbabilityGenerator<Double> rhythmGen = new ProbabilityGenerator<Double>();
		//creation of ArrayLists used to generate pitch and rhythm. trainPitch is an ArrayList of integers. trainRhytm is an ArrayList of doubles
		ProbabilityGenerator<Integer> trainPitch = new ProbabilityGenerator<Integer>();
		ProbabilityGenerator<Double> trainRhythm = new ProbabilityGenerator<Double>();

		//ProbabilityGenerator's train() function is called on both PitchGen and RhythmGen
		pitchGen.train(midiNotes.getPitchArray()); 
		rhythmGen.train(midiNotes.getRhythmArray());

		//ProbabilityGenerator's generate() function is called on both trainPitch and trainRhythm to create a melody based on .train's probabilty distribution
		ArrayList<Integer> pitches = pitchGen.generate(20);
		ArrayList<Double> rhythms = rhythmGen.generate(20);

		// sets the player to the melody and rhythm respectively
		player.setMelody(pitches);
		player.setRhythm(rhythms);

		//creates ArrayLists of the notes generated when the .generate() function is called on pitchGen and rhythmGen
		ArrayList<Integer> song = pitchGen.generate(20);
		ArrayList<Double> song2 = rhythmGen.generate(20);

		//generates 10,000 melodies of length 20
		for (int i=0; i < 10000; i++)
		{
			song = pitchGen.generate(20);
			trainPitch.train(song);

			song2 = rhythmGen.generate(20);
			trainRhythm.train(song2);
		}

		//prints the melodies with rounded values
		trainPitch.printProbabilityDistribution(true);
		trainRhythm.printProbabilityDistribution(true);
		// sets the player to the melody and rhythm respectively
		player.setMelody(pitchGen.generate(20));
		player.setRhythm(rhythmGen.generate(20));

    }

	/* 
	 * function that runs the Project 2 Unit Test 1 / cumulative Unit Test 3
	 * int whichTest: value from command line input that trigger switch statement cases; 3 corresponds with this Unit Test
	*/
	public void Proj2UnitTest3(int whichTest)
	{

		//creation of a pitch generator and a rhythm generator, both ArrayLists. Pitchgen is an ArrayList of integers. RhythmGen is an ArrayList of doubles
        MarkovChainGenerator<Integer> pitchGen = new MarkovChainGenerator<Integer>();
		MarkovChainGenerator<Double> rhythmGen = new MarkovChainGenerator<Double>();

		//MarkovChainGenerator's train() function is called on both PitchGen and RhythmGen
		pitchGen.train(midiNotes.getPitchArray());
		rhythmGen.train(midiNotes.getRhythmArray());

		//The newly created probability generators are printed without rounded values
		pitchGen.printProbabilityDistribution(false);
		rhythmGen.printProbabilityDistribution(false);

	}

	/* 
	 * function that runs the Project 2 Unit Test 2 / cumulative Unit Test 4
	 * int whichTest: value from command line input that trigger switch statement cases; 4 corresponds with this Unit Test
	*/
	public void Proj2UnitTest4(int whichTest)
	{
		//creation of a pitch generator and a rhythm generator, both ArrayLists. Pitchgen is an ArrayList of integers. RhythmGen is an ArrayList of doubles
		MarkovChainGenerator<Integer> pitchGen = new MarkovChainGenerator<Integer>();
		MarkovChainGenerator<Double> rhythmGen = new MarkovChainGenerator<Double>();

		//creation of ArrayLists used to generate pitch and rhythm. trainPitch is an ArrayList of integers. trainRhytm is an ArrayList of doubles
		MarkovChainGenerator<Integer> trainPitch = new MarkovChainGenerator<Integer>();
		MarkovChainGenerator<Double> trainRhythm = new MarkovChainGenerator<Double>();

		//MarkovChainGenerator's train() function is called on both PitchGen and RhythmGen
		pitchGen.train(midiNotes.getPitchArray()); 
		rhythmGen.train(midiNotes.getRhythmArray());

		//MarkovChainGenerator's generate() function is called on both trainPitch and trainRhythm to create a melody based on .train's probabilty distribution
		ArrayList<Integer> pitches = pitchGen.generate(20);
		ArrayList<Double> rhythms = rhythmGen.generate(20);

		// sets the player to the melody and rhythm respectively
		player.setMelody(pitches);
		player.setRhythm(rhythms);

		//creates ArrayLists of the notes generated when the .generate() function is called on pitchGen and rhythmGen
		ArrayList<Integer> song = pitchGen.generate(20);
		ArrayList<Double> song2 = rhythmGen.generate(20);

		//generates 100,000 melodies of length 20
		for (int i=0; i < 100000; i++)
		{
			song = pitchGen.generate(20);
			trainPitch.train(song);

			song2 = rhythmGen.generate(20);
			trainRhythm.train(song2);
		}

		//prints the melodies with rounded values
		trainPitch.printProbabilityDistribution(true);
		trainRhythm.printProbabilityDistribution(true);

		// sets the player to the melody and rhythm respectively
		player.setMelody(pitchGen.generate(20));
		player.setRhythm(rhythmGen.generate(20));

	}


}
