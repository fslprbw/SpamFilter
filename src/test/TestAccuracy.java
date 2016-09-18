package test;

import classifier.J48Filter;
import model.WordSpace;
import weka.core.Instance;
import weka.core.Instances;

public class TestAccuracy {
	private Instances dataTraining;
	private Instances dataTest;
	private int nTrue;
	private int nFalse;
	private J48Filter j48;
	public TestAccuracy() {
		double tresholdSpam = 0.4;
		double tresholdNonSpam = 0.4;
		WordSpace ws = new WordSpace(tresholdSpam, tresholdNonSpam);
		ws.load();
		dataTraining = ws.getTrainingSetInstances();
		
		WordSpace wsTest = new WordSpace(tresholdSpam, tresholdNonSpam, "./data/spam_test.txt", "./data/not_spam_test.txt");
		wsTest.loadTest(ws.getAttributes());
		dataTest = wsTest.getTrainingSetInstances();
		j48 = new J48Filter(dataTraining);
		nTrue = 0;
		nFalse = 0;
	}
	
	public void test() {
		System.out.println("Total data = " + dataTest.size());
		for(int i = 0; i < dataTest.size(); i++) {
			Instance ins = dataTest.get(i);
			int classVal = (int) dataTest.get(i).classValue();
			int expectedClassVal = j48.classifyIdx(ins);
			if (classVal == expectedClassVal) {
				nTrue++;
			}else{
				nFalse++;
			}
		}
		System.out.println("Benar = " + nTrue);
		System.out.println("Salah = " + nFalse);
		double accuracy = (double)nTrue / (nTrue + nFalse) * 100;
		System.out.println("Akurasi = " + accuracy + " persen");
	}
	
	public static void main(String[] args) {
		TestAccuracy ta = new TestAccuracy();
		ta.test();
	}
}
