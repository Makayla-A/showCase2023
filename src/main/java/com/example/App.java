/*
 * NAME: Makayla Alston
 * COURSE: CRCP 3315 001-1237
 * INSTRUCTOR: Professor Courtney Brown
 * DATE: Nov 2023
 * Description: This is a template for the project 2 code, which is an implementation of a Markov chain of order 1
 * CREDIT: Professor Courtney Brown (author of: public static void main(String[] args); public static void setup(); static public void playMelody(); 
 * static void midiSetup(String filePath); static void resetMelody(); static void playMidiFileDebugTest(String filename))
 * CLASS: App.java 
 */

package com.example;

//importing the JMusic stuff
import jm.music.data.*;
import jm.util.*;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import processing.core.*;

//make sure this class name matches your file name, if not fix.
public class App extends PApplet
{
	//initalizes an arrayList that holds the type icon. This is a global variable
	ArrayList<icon> myIcons;

	static MelodyPlayer player; // play a midi sequence
	static MidiFileToNotes midiNotes; // read a midi file
	static int noteCount = 0;

	//make cross-platform
	static FileSystem sys = FileSystems.getDefault();

	//the getSeperator() creates the appropriate back or forward slash based on the OS in which it is running -- OS X & Windows use same code :) 
	static String filePath = "mid"  + sys.getSeparator() +  "ShakeItOff.mid"; // path to the midi file -- you can change this to your file
															// location/name


	//creates an instance of my icon class, which represents the notes on the screen
	icon myIcon1 = new icon(this);
	public static void main(String[] args) 
	{

		PApplet.main("com.example.App");


		// TODO Auto-generated method stub

		// run the unit tests. gets command-line input and triggers switch cases, which calls the appropriate functions 
		int whichTest = Integer.parseInt(args[0]);

		//setup();
		UnitTests test = new UnitTests(midiNotes,player);


		switch(whichTest)
		{
			case 0:
				test.Proj1UnitTest1(whichTest);
				break;
			case 1:
				test.Proj1UnitTest2(whichTest);
				break;
			case 2:
				test.Proj1UnitTest1(whichTest);
				test.Proj1UnitTest2(whichTest);
				break;
			case 3:
				test.Proj2UnitTest3(whichTest);
				break;
			case 4:
				test.Proj2UnitTest4(whichTest);
				break;
			case 5:
				test.Proj2UnitTest3(whichTest);
				test.Proj2UnitTest4(whichTest);
				break;
				
		}

		// setup the melody player
		// uncomment below when you are ready to test or present sound output
		// make sure that it is commented out for your final submit to github (eg. when
		// pushing)
		// setup();
		// playMelody();
		// player.listDevices(); 

		// uncomment to debug your midi file
		// this code MUST be commited when submitting unit tests or any code to github
		// playMidiFileDebugTest(filePath);
	}

	// doing all the setup stuff
	public void setup() 
	{

		// playMidiFile(filePath); //use to debug -- this will play the ENTIRE file --
		// use ONLY to check if you have a valid path & file & it plays
		// it will NOT let you know whether you have opened file to get the data in the
		// form you need for the assignment

		midiSetup(filePath);

		//creation of a pitch generator and a rhythm generator, both ArrayLists. Pitchgen is an ArrayList of integers. RhythmGen is an ArrayList of doubles
		MarkovChainGenerator<Integer> pitchGen = new MarkovChainGenerator<Integer>();
		MarkovChainGenerator<Double> rhythmGen = new MarkovChainGenerator<Double>();

		//MarkovChainGenerator's train() function is called on both PitchGen and RhythmGen
		pitchGen.train(midiNotes.getPitchArray()); 
		rhythmGen.train(midiNotes.getRhythmArray());

		//MarkovChainGenerator's generate() function is called on both trainPitch and trainRhythm to create a melody based on .train's probabilty distribution
		ArrayList<Integer> pitches = pitchGen.generate(200);
		ArrayList<Double> rhythms = rhythmGen.generate(200);

		// sets the player to the melody and rhythm respectively
		player.setMelody(pitches);
		player.setRhythm(rhythms);


	}

	public void settings()
	{
		//sets the size of the processing sketch screen
		size(1000,1000);
		//fullScreen();

		//creates an new arrayList called myIcons
		myIcons = new ArrayList<>();
		
	

	} 

	public void draw()
	{
		background(0);

		//holds the value of the note returned from the function playMelody()
		int note = playMelody();
		//if a note is playing, this if statement is entered
		if (note != -1)	
		{
			println(note);

			//creates a new instance of an icon
			icon newIcon = new icon(this);
			//calls the function setNote() in the icon class and passes the value of the integer note
			newIcon.setNote(note);
			//adds the icon to the arrayList myIcons
			 myIcons.add(newIcon);
		}
		
		//range based for loop that iterates through the arrayList of icons called myIcons
		for (icon currentIcon : myIcons)
		{
			//draws the current icon
			currentIcon.draw();
			//calls the icon class's move function and passes the current icon as a parameter
			currentIcon.move(currentIcon);
		}

	

	}

	// plays the midi file using the player -- so sends the midi to an external
	// synth such as Kontakt or a DAW like Ableton or Logic
	static public Integer playMelody() 
	{

		assert (player != null); // this will throw an error if player is null -- eg. if you haven't called
									// setup() first

		if (!player.atEndOfMelody()) 
		{
			return player.play(); // play each note in the sequence -- the player will determine whether is time
							// for a note onset
		}
		return -1;

	}

	// opens the midi file, extracts a voice, then initializes a melody player with
	// that midi voice (e.g. the melody)
	// filePath -- the name of the midi file to play
	static void midiSetup(String filePath) 
	{

		// Change the bus to the relevant port -- if you have named it something
		// different OR you are using Windows
		player = new MelodyPlayer(100, "Microsoft GS Wavetable Synth"); // sets up the player with your bus.

		midiNotes = new MidiFileToNotes(filePath); // creates a new MidiFileToNotes -- reminder -- ALL objects in Java
													// must
													// be created with "new". Note how every object is a pointer or
													// reference. Every. single. one.

		// // which line to read in --> this object only reads one line (or ie, voice or
		// ie, one instrument)'s worth of data from the file
		midiNotes.setWhichLine(0); // this assumes the melody is midi channel 0 -- this is usually but not ALWAYS
									// the case, so you can try other channels as well, if 0 is not working out for
									// you.

		noteCount = midiNotes.getPitchArray().size(); // get the number of notes in the midi file

		assert (noteCount > 0); // make sure it got some notes (throw an error to alert you, the coder, if not)

		// sets the player to the melody to play the voice grabbed from the midi file
		// above
		player.setMelody(midiNotes.getPitchArray());
		player.setRhythm(midiNotes.getRhythmArray());
	}

	static void resetMelody() 
	{
		player.reset();
	}

	// this function is not currently called. you may call this from setup() if you
	// want to test
	// this just plays the midi file -- all of it via your software synth. You will
	// not use this function in upcoming projects
	// but it could be a good debug tool.
	// filename -- the name of the midi file to play
	static void playMidiFileDebugTest(String filename) 
	{
		Score theScore = new Score("Temporary score");
		Read.midi(theScore, filename);
		Play.midi(theScore);
	}

}
