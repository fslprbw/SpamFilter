package tfidf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import IndonesianNLP.IndonesianSentenceTokenizer;

public class TfIdfTest {
	public static void main(String[] args) {
		String document1 = "Halo nama saya cie haha";
	    String document2 = "three four one oneone one";
	    List<String> list = new ArrayList<String>();
	    list.add(document1);
	    list.add(document2);
	    TfIdf tfIdf = new TfIdf(list);
	    Map<String, Double> m = tfIdf.tfidf();
	    System.out.println(m.get("two"));
	    IndonesianSentenceTokenizer it = new IndonesianSentenceTokenizer();
	    ArrayList<String> a = it.tokenizeSentence(document1);
	    for (int i = 0; i < a.size(); i++) {
	    	System.out.println(a.get(i));
	    }
	}
}
