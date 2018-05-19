package week2;
import edu.duke.*;
import java.util.*;

public class GladLib {
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	private ArrayList<String> fruitList;
	private ArrayList<String> verbList;
	ArrayList<String> usedWords = new ArrayList<String>();

	private Random myRandom;

	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "week2/data";

	public GladLib() {
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}

	public GladLib(String source) {
		initializeFromSource(source);
		myRandom = new Random();
	}

	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()) {
				list.add(line);
			}
		}	else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()) {
				list.add(line);
			}
		}
		return list;
	}

	private void initializeFromSource(String source) {
		adjectiveList = readIt(source + "/adjective.txt");
		nounList = readIt(source + "/noun.txt");
		colorList = readIt(source + "/color.txt");
		countryList = readIt(source + "/country.txt");
		nameList = readIt(source + "/name.txt");
		animalList = readIt(source + "/animal.txt");
		timeList = readIt(source + "/timeframe.txt");
		fruitList = readIt(source + "/fruit.txt");
		verbList = readIt(source + "/verb.txt");
	}

	private String randomFrom(ArrayList<String> source) {
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}

	// update this structure so that all conditionals aren't necessary
	private String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(countryList);
		} else if (label.equals("color")) {
			return randomFrom(colorList);
		} else if (label.equals("noun")) {
			return randomFrom(nounList);
		} else if (label.equals("name")) {
			return randomFrom(nameList);
		} else if (label.equals("adjective")) {
			return randomFrom(adjectiveList);
		} else if (label.equals("animal")) {
			return randomFrom(animalList);
		} else if (label.equals("timeframe")) {
			return randomFrom(timeList);
		} else if (label.equals("number")) {
			return "" + myRandom.nextInt(50) + 5;
		} else if (label.equals("fruit")) {
			return randomFrom(fruitList);
		} else if (label.equals("verb")) {
			return randomFrom(verbList);
		} else {
			return "**Unknown**";
		}
	}

	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0, first);
		String suffix = w.substring(last + 1);
		String sub = getSubstitute(w.substring(first + 1, last));
		int isUsed = usedWords.indexOf(sub);
		if(isUsed != -1) {
			while (isUsed != -1) {
				sub = getSubstitute(w.substring(first + 1, last));
				isUsed = usedWords.indexOf(sub);
			}
		}
		usedWords.add(sub);
		return prefix + sub + suffix;
	}

	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w + " ");
			charsWritten += w.length() + 1;
		}
	}

	private String fromTemplate(String source) {
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()) {
				story = story + processWord(word) + " ";
			}
		} else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()) {
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}

	public void makeStory(String source){
	  System.out.println("\n");
		String story = fromTemplate(source + "/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\n");
	}

	public void printUsed() {
		for(int i = 0; i < usedWords.size(); i++) {
			System.out.println(usedWords.get(i));
		}
	}

	public static void main(String args[]) {
		GladLib gl = new GladLib();
		gl.initializeFromSource(dataSourceDirectory);
		gl.makeStory(dataSourceDirectory);
		gl.printUsed(); // as a check
	}

}
