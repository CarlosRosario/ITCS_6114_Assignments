// Author: Carlos Rosario

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Qsort {

	public static void main(String[] args) {
		int[] numbers;
		String inputFile = args[0];
		
        generateTestFile(1000);
		
		try {
			// Read file
			numbers = readFile(inputFile);
			
			// Quick sort
			double start = System.nanoTime();
			quickSort(numbers, 0, numbers.length - 1);
			double end = System.nanoTime() ;
			
			// Write output
			writeFile(numbers, (end-start)/1000000);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Implements the actual quickSort algorithm from our textbook
	public static void quickSort(int A[], int left, int right){
		int pivot = 0;
		if(left < right){
			pivot = partition(A, left, right);
			quickSort(A, left, pivot - 1);
			quickSort(A, pivot + 1, right);
		}
	}
	
	// Implements the partition algorithm from our textbook - and is used in the quickSort function above.
	public static int partition(int[] A, int left, int right){
		int lastElement = A[right];
		int i = left - 1; 
		
		for(int j = left; j < right; j++){
			if(A[j] <= lastElement){
				i++;
				// swap A[i] with A[j]
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;
				
			}
		}
		// exchange A[i+1] with A[right]
		int temp = A[i+1];
		A[i+1] = A[right];
		A[right] = temp;
		
		return i+1;
	}
	
	// Reads in a file of numbers separated by a semi-colon and returns an array with those numbers. The array that this function returns is the array that we will sort via quicksort.
	public static int[] readFile(String inputFile) throws FileNotFoundException{
		
		List<Integer> listOfNumbers = new ArrayList<Integer>();
		String[] numbers;
		Scanner scan = new Scanner(new File(inputFile));
		String fileContents = "";
		
		
		while (scan.hasNextLine()){
			fileContents = scan.nextLine();
		}
		scan.close();
		
		numbers = fileContents.split(";");
		
		for(String n: numbers){
			listOfNumbers.add(Integer.parseInt(n.trim()));
		}
		
		int[] returnArray = new int[listOfNumbers.size()];
		for(int i = 0; i < listOfNumbers.size(); i++){
			returnArray[i] = listOfNumbers.get(i);
		}
		
		return returnArray;
	}
	
	// Write the "answer.txt" file as described in the requirements for this assignment.
	public static void writeFile(int[] numbers, double sortingTime) throws IOException{
		
		BufferedWriter outputWriter = null;
		outputWriter = new BufferedWriter(new FileWriter("answer.txt"));
		
		// Write sorted numbers
		for(int i = 0; i < numbers.length; i++){
			if(i == numbers.length - 1){
				outputWriter.write(numbers[i] + "");
			}
			else {
				outputWriter.write(numbers[i] + "; " );	
			}
		}
		
		// Write performance analysis 
		outputWriter.write("\r\n");
		outputWriter.write("Performance analysis:\r\n");
		outputWriter.write("Size \t\t Sorting Time(in milliseconds)\r\n");
		outputWriter.write(numbers.length + "\t\t\t\t " + sortingTime + " \r\n");
		
		outputWriter.flush();
		outputWriter.close();
		
	}
	
	// Overloaded method that lets me generate large text files for the purposes of my testing. 
    public static void writeFile(int[] numbers) throws IOException{
		
		BufferedWriter outputWriter = null;
		outputWriter = new BufferedWriter(new FileWriter("samplenumbers.txt"));
		
		// Write sorted numbers
		for(int i = 0; i < numbers.length; i++){
			if(i == numbers.length - 1){
				outputWriter.write(numbers[i] + "");
			}
			else {
				outputWriter.write(numbers[i] + "; " );	
			}
		}
		
		outputWriter.flush();
		outputWriter.close();
		
	}
    
    // Generate a test input file of whatever size is passed to it.
    public static void generateTestFile(int size){
		int[] testNumbers = new int[size];
		for(int i = 0; i < size; i++){
			Random rand = new Random();
			
			//System.out.println(rand.nextInt((size - 1)+1)+1 + ";");
			testNumbers[i] = rand.nextInt((size - 1)+1)+1;
		}
		try {
			writeFile(testNumbers);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
	
    

}