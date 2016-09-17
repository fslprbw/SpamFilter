package model;

import java.util.ArrayList;
import java.util.List;

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
	}
	
	
	public void initImportantAttributes() {
		ImportantWords iw = new ImportantWords(spamTreshold);
		attributes = iw.getImportantWord(spamDocuments);
		//don't forget to set treshold
		iw.setTreshold(nonspamTreshold);
		attributes.addAll(iw.getImportantWord(nonspamDocuments));
	}
	
	public void getVectorSpace() {
		String S = "";
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
