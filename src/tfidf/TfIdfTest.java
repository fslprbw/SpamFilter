package tfidf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TfIdfTest {
	public static void main(String[] args) {
		String document1 = "one two one one one one one";
	    String document2 = "three four one oneone one";
	    List<String> list = new ArrayList<String>();
	    list.add(document1);
	    //list.add(document2);
	    TfIdf tfIdf = new TfIdf(list);
	    Map<String, Double> m = tfIdf.tfidf();
	    System.out.println(m.get("two"));
	}
}
