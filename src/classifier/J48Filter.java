package classifier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;

public class J48Filter {
	private J48 j48;
	private Instances data;
	
	public J48Filter() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("vs_model.arff"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			data = new Instances(reader);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.setClassIndex(data.numAttributes() - 1); 
		initJ48();
	}
	
	public J48Filter(Instances instances){
		data = instances;
		initJ48();
	}
	
	public int classifyIdx(Instance instance) {
		int res = -1;
		try {
			res = (int) j48.classifyInstance(instance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public String classify(Instance instance) {
		return data.classAttribute().value(classifyIdx(instance));
	}
	
	private void initJ48() {
		j48 = new J48();
		j48.setUnpruned(false);
		try {
			j48.buildClassifier(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
