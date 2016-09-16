package tfidf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import IndonesianNLP.IndonesianSentenceTokenizer;

public class TfIdfTest {
	public static void main(String[] args) {
		String document1 = "Halo nama saya cie haha";
	    String document2 = "three four one one one one";
	    List<String> list = new ArrayList<String>();
	    list.add(document1);
	    list.add(document2);
	    TfIdf tfIdf = new TfIdf(list);
	    Map<String, Double> m = tfIdf.tfidf();
	    IndonesianSentenceTokenizer it = new IndonesianSentenceTokenizer();
	    ArrayList<String> a = it.tokenizeSentence(document2);
	    List<String> list1 = a;
	    Set<String> uniqueGas = new HashSet<String>(list1);
	    System.out.println("Unique gas count: " + uniqueGas.size());
	    Object[] c = uniqueGas.toArray();
	    for(int i = 0; i < c.length; i++) {
	    	System.out.println(c[i]);
	    }
	}
}
