package week2;
import edu.duke.*;
import java.util.*;

public class GladLibMap {
	private HashMap<String, ArrayList<String>> fillerMap = new HashMap<String, ArrayList<String>>();
	private ArrayList<String> usedWords = new ArrayList<String>();
	private ArrayList<String> usedLabels = new ArrayList<String>();

	private Random myRandom;

	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "week2/data/";

	public GladLibMap() {
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}

	public GladLibMap(String source) {
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

	// can update by pulling in filenames instead of defining names explicitly
	private void initializeFromSource(String source) {
		String[] labels = {"adjective", "noun", "color", "country", "name", "animal",
												"timeframe", "fruit", "verb"};
		for(String str : labels) {
			ArrayList<String> list = readIt(source + "/" + str + ".txt");
			fillerMap.put(str, list);
		}
	}

	private String randomFrom(ArrayList<String> source) {
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}

	private String getSubstitute(String label) {
		if(label.equals("number")) {
			return "" + myRandom.nextInt(50) + 5;
		} else if(fillerMap.keySet().contains(label)) {
			if(usedLabels.indexOf(label) == -1) {
				usedLabels.add(label);
			}
			return randomFrom(fillerMap.get(label));
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

	public int totalWordsInMap() {
		int sum = 0;
		for(String key : fillerMap.keySet()) {
			sum  += fillerMap.get(key).size();
		}
		return sum;
	}

	public int totalWordsConsidered() {
		int sum = 0;
		for(String label : usedLabels) {
			System.out.println("Evaluating: " + label);
			sum += fillerMap.get(label).size();
			System.out.println("The sum is: " + sum);
		}
		return sum;
	}

	public static void main(String args[]) {
		GladLibMap gl = new GladLibMap();
		gl.initializeFromSource(dataSourceDirectory);
		gl.makeStory(dataSourceDirectory);

		// checkings
		// gl.printUsed(); // as a check

		int totalSum = gl.totalWordsInMap();
		// System.out.println("There were " + totalSum + " words available");

		int labelSum = gl.totalWordsConsidered();
		// System.out.println("The used labels contained: " + labelSum + " available words");
	}

}
