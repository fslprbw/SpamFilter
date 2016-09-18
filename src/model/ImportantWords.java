package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import IndonesianNLP.IndonesianSentenceTokenizer;
import tfidf.TfIdf;

public class ImportantWords {
	private double treshold;
	public ImportantWords() {
		treshold = 5.0;
	}
	
	public ImportantWords(double treshold) {
		this.setTreshold(treshold);
	}
	
	public List<String> getUniqueWords(List<String> words) {
		List<String> result = new ArrayList<String>();
		Set<String> uniqueGas = new HashSet<String>(words);
		result.addAll(uniqueGas);
		return result;
	}
	
	public List<String> getUniqueWordsDoc(List<String> documents) {
		List<String> accWords = new ArrayList<String>();
		IndonesianSentenceTokenizer it = new IndonesianSentenceTokenizer();
		for(String sentence : documents) {
			ArrayList<String> words = it.tokenizeSentence(sentence);
			for(int i = 0; i < words.size(); i++) {
				if (words.get(i) != null && !words.get(i).isEmpty()) {
					accWords.add(words.get(i));
				}
			}
		}
		return getUniqueWords(accWords);
	}
	
	public ArrayList<String> getImportantWord(List<String> documents) {
		ArrayList<String> result = new ArrayList<String>();
		List<String> uniqueWords = getUniqueWordsDoc(documents);
		TfIdf tfIdf = new TfIdf(documents);
	    Map<String, Double> m = tfIdf.tfidf();
		for(String word : uniqueWords) {
			if (m.get(word) >= treshold) {
				result.add(word);
			}
		}
		return result;
	}
	
	
	//Getter and setter
	public double getTreshold() {
		return treshold;
	}

	public void setTreshold(double treshold) {
		this.treshold = treshold;
	}
}
