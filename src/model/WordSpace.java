package model;

import java.util.ArrayList;
import java.util.List;

import IndonesianNLP.IndonesianSentenceTokenizer;
import textpreprocessing.TextPreprocessing;

public class WordSpace {
	private double spamTreshold;
	private double nonspamTreshold;
	private List<String> spamDocuments;
	private List<String> nonspamDocuments;
	private ArrayList<String> attributes;
	private ArrayList<VectorSpace> vspaces;

	public WordSpace() {
		importDataSet();
	}
	
	public WordSpace(double sTresh, double nsTresh) {
		setSpamTreshold(sTresh);
		setNonspamTreshold(nsTresh);
		importDataSet();
	}
	
	
	public void importDataSet() {
		IOFile io = new IOFile();
		spamDocuments = io.readListText("./data/spam.txt");
		nonspamDocuments = io.readListText("./not_spam.txt");		
		//Preprocess
		TextPreprocessing tp = new TextPreprocessing();
		spamDocuments = tp.preprocess(spamDocuments, 4);
		nonspamDocuments = tp.preprocess(nonspamDocuments, 4);
	}
	
	
	public void loadAttributes() {
		ImportantWords iw = new ImportantWords(spamTreshold);
		attributes = iw.getImportantWord(spamDocuments);
		//don't forget to set treshold
		iw.setTreshold(nonspamTreshold);
		attributes.addAll(iw.getImportantWord(nonspamDocuments));
	}
	
	public void getAllVectorSpace() {
		//spam data
		for(String document : spamDocuments) {
			vspaces.add(getVectorSpace(document, "spam"));
		}
		
		//nonspamdata
		for(String document : nonspamDocuments) {
			vspaces.add(getVectorSpace(document, "bukan_spam"));
		}
	}
	
	public VectorSpace getVectorSpace(String sentence, String label) {
		IndonesianSentenceTokenizer it = new IndonesianSentenceTokenizer();
		ArrayList<String> words = it.tokenizeSentence(sentence);
		ArrayList<Integer> vector = new ArrayList<Integer>();
		for (String attr : attributes) {
			int numSame = 0;
			ArrayList<Integer> toRemove = new ArrayList<Integer>();
			for (int i = 0; i < words.size(); i++) {
				if (attr.equals(words.get(i))) {
					numSame++;
					toRemove.add(i);
				}
			}
			
			for(Integer idx : toRemove) {
				words.remove(idx);
			}
			toRemove.clear();
			
			vector.add(numSame);
		}
		
		return new VectorSpace(vector, label);
	}
	
	//Getter and setter
	public double getSpamTreshold() {
		return spamTreshold;
	}

	public void setSpamTreshold(double spamTreshold) {
		this.spamTreshold = spamTreshold;
	}

	public double getNonspamTreshold() {
		return nonspamTreshold;
	}

	public void setNonspamTreshold(double nonspamTreshold) {
		this.nonspamTreshold = nonspamTreshold;
	}

	public ArrayList<String> getAttributes() {
		return attributes;
	}

	public ArrayList<VectorSpace> getVspaces() {
		return vspaces;
	}
	
}
