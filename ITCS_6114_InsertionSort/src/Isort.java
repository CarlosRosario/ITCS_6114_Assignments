import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Isort {

	public static void main(String[] args) {
		List<Integer> listOfNumbers = new ArrayList<Integer>();
		String inputFile = args[0];
		
		try {
			// Read file
			listOfNumbers = readFile(inputFile);
			
			// Insertion sort
			listOfNumbers = InsertionSort(listOfNumbers);
			
			// Write file
			writeFile(listOfNumbers);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Integer> readFile(String inputFile) throws FileNotFoundException{
		
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
		
		return listOfNumbers;
	}
	
	public static void writeFile(List<Integer> l) throws IOException{
		
		BufferedWriter outputWriter = null;
		outputWriter = new BufferedWriter(new FileWriter("answer.txt"));
		
		for(int n: l){
			outputWriter.write(n + "; " );
		}
		
		outputWriter.flush();
		outputWriter.close();
		
	}
	
	public static List<Integer> InsertionSort(List<Integer> l){
		int key = 0;
		int i = 0; 
		
		for(int j = 1; j < l.size(); j++){
			key = l.get(j);
			
			// Insert A[j] into the sorted sequence A[1..j -1].
			i = j - 1; 
			
			while( i >= 0 && l.get(i) > key){
				l.set(i+1, l.get(i));
				i--;
			}
			l.set(i+1, key);
		}
		
		return l;
	}
}
