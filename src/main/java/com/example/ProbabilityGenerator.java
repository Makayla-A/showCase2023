/*
 * NAME: Makayla Alston
 * COURSE: CRCP 3315 001-1237
 * INSTRUCTOR: Professor Courtney Brown
 * DATE: Oct 19, 2023
 * DESCRIPTION: A generic class called ProbabilityGenerator, which utilizes ArrayLists to accept input in the form of MIDI music files, records the unique tokens 
 * found in the input file and documents the number of instances each note has occured. Creates a probability distribution from this data -- that is, divides the
 * instances of each note by the total number of notes to estimate the probability of a given note being the next to occur in a given song. Prints the output
 * of this data to the terminal.
 * CREDIT: Professor Courtney Brown (author of: SortArraysOutput (symbols, counts); printProbabilityDistribution(round, symbols, counts, sumSymbols)) 
 * Class: ProbabliityGenerator
 * 
 */

 package com.example;

 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Comparator;
 
 public class ProbabilityGenerator <E>
 {
     // An ArrayList of tokens. This ArrayList is a counterpart to tokenCounts. It will contain the full list of every unique instance of a musical note 
     // (A-G) taken from a MIDI song file
     ArrayList<E> alphabet = new ArrayList<E>();
     // An ArrayList of floats. This ArrayList is a counterpart to alphabet. This ArrayList records the number of instances each note has occured 
     // as a float value
     ArrayList<Float> tokenCounts = new ArrayList<Float>(); 
     // A value that records the total amount of tokens in a given MIDI song file
     double tokenCount = 0; 
 
     /* 
     * this function adds values to alphabet and tokenCounts, and uses these to generate a probability distribution
     * ArrayList<E> data: an ArrayList of tokens. These tokens are taken from the MIDI input file
     */
     void train(ArrayList<E>data) 
     {
         // This for loop iterates through the length of data, which contains the tokens taken from the MIDI file
         for (int index = 0; index < data.size(); index++)
         {
             // check to see if the token (the variable alphaIndex, which denotes the given index in the arrayList alphabet) already exists in alphabet
             int alphaIndex = alphabet.indexOf(data.get(index));
             // if this token is not in alphabet, -1 is returned. As a result, this if statement is entered
             if (alphaIndex == -1)
             {
                 // the index within alphabet is set to alphabet's size
                 alphaIndex = alphabet.size();
 
                 // the token is added to alphabet at the corresponding index
                 alphabet.add(data.get(index));
                 //tokenCounts is updated at the corresponding index with a value of 0.0
                 tokenCounts.add(0.0f);
             }
             // add 1 to tokenCounts at the given index
             tokenCounts.set(alphaIndex, tokenCounts.get(alphaIndex)+1.0f);
             // increment tokenCount, which tracks the total amount of tokens in data
             tokenCount++;
         }
     }
 
     /* 
     * this function use the probability distribution created in train() and returns a note based on the statistical likelihood that, given the total number
     * of tokens and the frequency of a given token's occurence, a note from A to G will be generated next
     * ArrayList<E> alphabet: An ArrayList of tokens. This ArrayList is a counterpart to tokenCounts. It will contain the full list of every unique instance 
     of a musical note (A-G) taken from a MIDI song file
     * ArrayList<Float> tokenCounts: An ArrayList of floats. This ArrayList is a counterpart to alphabet. This ArrayList records the number of instances 
     each note has occured as a float value
     * double tokenCount: A value that records the total amount of tokens in a given MIDI song file
     */
 
      public E generate(ArrayList<E> alphabet, ArrayList<Float> tokenCounts, double tokenCount)
     {
         //this function returns data type E; this line of code creates an instance of E and initalizes it to null
         E note = null;
         //create a new array of doubles and set its size equal to the size of tokenCounts; this will hold my percentages
         ArrayList<Double> percentages = new ArrayList<Double>(tokenCounts.size());
         //iterate through the arrayList
         for (int i = 0; i <tokenCounts.size(); i++)
         {
             //creating a variable called newValue, which is the number present at each index of the arrayList divided by the total amount of tokens
             double newValue = tokenCounts.get(i)/tokenCount;
             //updating percentage arrayList with the value calculated above
             percentages.add(i, newValue);
         }
 
         //generate a random number in which all probabilities for each number are equal, but needs to be changed 
         float rIndex = (float) Math.random();
 
 
         //iterate through the arrayList created in the last for loop, which contains the percentage value of each note's appearance within alphabet
         for (int i = 0; i < percentages.size(); i++)
         {
             //if my random number is larger than the value indexed in the percentages arrayList, subtract the value of percentages[i] from random number
             if (rIndex > percentages.get(i))
             {
                 rIndex -= percentages.get(i);
             }
             //if my random number is NOT larger than the value indexed in the percentages arrayList, then this is the note that will be generated next. 
             //Stop the function and assign the previously null E variable to the corresponding index in the alphabet arrayList
             else
             {
                 note = alphabet.get(i);
                 break;
             }
         }
         //the generated note will be returned, and the function terminates
         return note;
     }
 
         /*
         * this function is an overloaded call to the function() generate. Since my class variables cannot be used in App.java, I pass an integer
         * as a parameter
         * int count: the amount of calls made to the function generate()
         */
         public ArrayList<E> generate(int count)
         {
             ArrayList<E> result = new ArrayList<E>();
             for (int i=0; i < count; i++)
             {
                 result.add(generate(alphabet, tokenCounts,tokenCount));
             }
             return result;
 
         }
 
     // nested convenience class to return two arrays from sortArrays() method
     // students do not need to use this class
     protected class SortArraysOutput
     {
         public ArrayList<E> symbolsListSorted;
         public ArrayList<Float> symbolsCountSorted;
     }
 
     // sort the symbols list and the counts list, so that we can easily print the probability distribution for testing
     // symbols -- your alphabet or list of symbols (input)
     // counts -- the number of times each symbol occurs (input)
     // symbolsListSorted -- your SORTED alphabet or list of symbols (output)
     // symbolsCountSorted -- list of the number of times each symbol occurs inorder of symbolsListSorted  (output)
     public SortArraysOutput sortArrays(ArrayList<E> symbols, ArrayList<Float> counts)	
     {
 
         SortArraysOutput sortArraysOutput = new SortArraysOutput(); 
         
         sortArraysOutput.symbolsListSorted = new ArrayList<E>(symbols);
         sortArraysOutput.symbolsCountSorted = new ArrayList<Float>();
     
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
             sortArraysOutput.symbolsCountSorted.add(counts.get(index));
         }
 
         return sortArraysOutput;
 
     }
     
     //Students should USE this method in your unit tests to print the probability distribution
     //HINT: you can overload this function so that it uses your class variables instead of taking in parameters
     //boolean is FALSE to test train() method & TRUE to test generate() method
     //symbols -- your alphabet or list of symbols (input)
     //counts -- the number of times each symbol occurs (input)
     //sumSymbols -- the count of how many tokens we have encountered (input)
     public void printProbabilityDistribution(boolean round, ArrayList<E> symbols, ArrayList<Float> counts, double sumSymbols)
     {
         //sort the arrays so that elements appear in the same order every time and it is easy to test.
         SortArraysOutput sortResult = sortArrays(symbols, counts);
         ArrayList<E> symbolsListSorted = sortResult.symbolsListSorted;
         ArrayList<Float> symbolsCountSorted = sortResult.symbolsCountSorted;
 
         System.out.println("-----Probability Distribution-----");
         
         for (int i = 0; i < symbols.size(); i++)
         {
             if (round){
                 DecimalFormat df = new DecimalFormat("#.##");
                 System.out.println("Data: " + symbolsListSorted.get(i) + " | Probability: " + df.format((double)symbolsCountSorted.get(i) / sumSymbols));
             }
             else
             {
                 System.out.println("Data: " + symbolsListSorted.get(i) + " | Probability: " + (double)symbolsCountSorted.get(i) / sumSymbols);
             }
         }
         
         System.out.println("------------");
     }
 
     /*
     * An overloaded print function that uses class variables to output data
     * boolean round: a parameter used to specify whether the train method or generate method is being run. To run train(), this value should be
     * true. To run generate(), this value should be false.
     */
     public void printProbabilityDistribution(boolean round)
     {
         printProbabilityDistribution(round, alphabet, tokenCounts, tokenCount);
 
     }
 
 }
