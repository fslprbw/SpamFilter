package model;

import java.util.ArrayList;
import java.util.List;

import IndonesianNLP.IndonesianSentenceTokenizer;
import textpreprocessing.TextPreprocessing;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class WordSpace {
	private double spamTreshold;
	private double nonspamTreshold;
	private List<String> spamDocuments;
	private List<String> nonspamDocuments;
	private ArrayList<String> attributes;
	private ArrayList<VectorSpace> vspaces;
	
	private String spamfile;
	private String nonspamfile;
	
	public static final String spamclass = "spam";
	public static final String nonspamclass = "bukan_spam";
	
	public WordSpace() {
		spamfile = "./data/spam.txt";
		nonspamfile = "./data/spam.txt";
		//default value
		
		vspaces = new ArrayList<VectorSpace>();
		setSpamTreshold(0.5);
		setNonspamTreshold(0.5);
		importDataSet();
	}
	
	public WordSpace(double sTresh, double nsTresh) {
		vspaces = new ArrayList<VectorSpace>();
		setSpamTreshold(sTresh);
		setNonspamTreshold(nsTresh);
		spamfile = "./data/spam.txt";
		nonspamfile = "./data/spam.txt";
		importDataSet();
	}
	
	public WordSpace(double sTresh, double nsTresh, String spamfile, String nonspamfile) {
		this.spamfile = spamfile;
		this.nonspamfile = nonspamfile;
		vspaces = new ArrayList<VectorSpace>();
		setSpamTreshold(sTresh);
		setNonspamTreshold(nsTresh);
		this.spamfile = spamfile;
		this.nonspamfile = nonspamfile;
		importDataSet();
	}
	
	public void importDataSet() {
		IOFile io = new IOFile();
		spamDocuments = io.readListTextSp(spamfile);
		nonspamDocuments = io.readListTextSp(nonspamfile);		
		//Preprocess
		TextPreprocessing tp = new TextPreprocessing();
		spamDocuments = tp.preprocess(spamDocuments, 4);
		nonspamDocuments = tp.preprocess(nonspamDocuments, 4);
	}
	
	public void loadTest(ArrayList<String> attrs) {
		attributes = attrs;
		getAllVectorSpace();
	}
	
	public void load() {
		loadAttributes();
		getAllVectorSpace();
	}
	
	public void loadAttributes() {
		ImportantWords iw = new ImportantWords(spamTreshold);
		attributes = iw.getImportantWord(spamDocuments);
		//don't forget to set treshold
		iw.setTreshold(nonspamTreshold);
		attributes.addAll(iw.getImportantWord(nonspamDocuments));
		attributes = (ArrayList<String>) iw.getUniqueWords(attributes);
		
	}
	
	// Call load before calling this method
	public Instances getTrainingSetInstances() {
		ArrayList<Attribute> fvWekaAttributes = new ArrayList<Attribute>();
		for(int i = 0; i < attributes.size(); i++) {
			fvWekaAttributes.add(new Attribute(attributes.get(i)));
		}
		// Declare the class attribute along with its values
		 ArrayList<String> fvClassVal = new ArrayList<String>();
		 fvClassVal.add(WordSpace.spamclass);
		 fvClassVal.add(WordSpace.nonspamclass);
		 Attribute ClassAttribute = new Attribute("theClass", fvClassVal);
		 int classIdx = fvWekaAttributes.size();
		 fvWekaAttributes.add(ClassAttribute);
		 	 
		 //create empty training set
		 Instances trainingSet = new Instances("spam_filtering", fvWekaAttributes, vspaces.size() + 10);
		 trainingSet.setClassIndex(classIdx);
		 
		 //add vector space into training set
		 for(VectorSpace vs : vspaces) {
			Instance ins = new DenseInstance(fvWekaAttributes.size());
			ArrayList<Integer> vector = vs.getValue();
			int i;
			for(i = 0; i < vector.size(); i++) {
				ins.setValue(fvWekaAttributes.get(i), vector.get(i));
			}
			ins.setValue(fvWekaAttributes.get(i), vs.getClassName());
			
			trainingSet.add(ins);
		 }
		 //saveModel(trainingSet);
		 return trainingSet;
	}
	
	public void saveModel(Instances instances) {
		IOFile io = new IOFile();
		io.writeText("vs_model.arff", instances.toString());
	}
	
	public void getAllVectorSpace() {
		//spam data
		for(String document : spamDocuments) {
			vspaces.add(getVectorSpace(document, WordSpace.spamclass));
		}
		
		//nonspamdata
		for(String document : nonspamDocuments) {
			vspaces.add(getVectorSpace(document, WordSpace.nonspamclass));
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
	
	@Override
	public String toString() {
		String res = "";
		for(String attr : attributes) {
			res += attr + ", ";
		}
		res = res.substring(0, res.length()-2) + "\n";
		
		for(VectorSpace vsp : vspaces) {
			String acc = "";
			for(Integer idx : vsp.getValue()) {
				acc += idx.toString() + ", ";
			}
			acc += vsp.getClassName() + "\n";
			res += acc;
		}
		return res;
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
