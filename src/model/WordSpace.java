package model;

import java.util.ArrayList;
import java.util.List;

public class WordSpace {
	private double spamTreshold;
	private double nonspamTreshold;
	private List<String> spamDocuments;
	private List<String> nonspamDocuments;
	private ArrayList<String> attributes;
	
	public WordSpace() {
		importDataSet();
	}
	
	public WordSpace(double sTresh, double nsTresh) {
		spamTreshold = sTresh;
		nonspamTreshold = nsTresh;
		importDataSet();
	}
	
	public void importDataSet() {
		IOFile io = new IOFile();
		spamDocuments = io.readListText("./data/spam.txt");
		nonspamDocuments = io.readListText("./not_spam.txt");
	}
	
	
}
