/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textpreprocessing;

import IndonesianNLP.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author toshiba
 */
public class TextPreprocessing {
	
	public TextPreprocessing() {
		
	}
	
    public String strip1(String str) {
        String ret = str;
        ret = miscCharIntoSpace(ret);        
        replacePhoneNumber(ret);
        ret = stripShortWords(str, 3);                        
        
        return ret;
    }
    
    public String strip2(String str) {
        IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
        IndonesianStemmer stemmer = new IndonesianStemmer(); 
        
        String ret = str;        
        ret = miscCharIntoSpace(ret);
        ret = replacePhoneNumber(ret);
        ret = formalizer.normalizeSentence(ret);
        ret = stemmer.stemSentence(ret);
                
        return ret;
    }
    
    public String strip3(String str) {
        IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
        IndonesianStemmer stemmer = new IndonesianStemmer(); 
        
        String ret = str;              
        ret = miscCharIntoSpace(ret);
        ret = replacePhoneNumber(ret);
        ret = stripShortWords(ret, 2);
        ret = formalizer.normalizeSentence(ret);
        ret = stemmer.stemSentence(ret);
                
        return ret;
        
    }
    
    public String strip4(String str) {
        IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
        IndonesianStemmer stemmer = new IndonesianStemmer(); 
        
        String ret = str;                
        ret = miscCharIntoSpace(ret);
        ret = replacePhoneNumber(ret);
        ret = stripShortWords(ret, 3);
        ret = formalizer.normalizeSentence(ret);
        ret = stemmer.stemSentence(ret);
                
        return ret;
        
    }
    
    public String replacePhoneNumber(String str) {
        String regex = "[\\+]?[\\d | [\\s]]{8,}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        
        return m.replaceAll("NomorHP");
    }        
    
    public String miscCharIntoSpace(String str) {
        String regex = "[^\\d* | ^\\w*]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        
        return m.replaceAll(" ");
    }
    
    
    public String stripShortWords(String str, int n) {        
        String regex = "\\b\\w{0,"+ n + "}\\b";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        
        return m.replaceAll("");        
    }
    
    public List<String> preprocess(List<String> documents, int option) {
    	List<String> result = new ArrayList<String>();
    	if (option == 1) {
	    	for (String document : documents) {
	    		result.add(strip1(document));
	    	}
    	} else if (option == 2) {
	    	for (String document : documents) {
	    		result.add(strip2(document));
	    	}
    		
    	} else if (option == 3) {
	    	for (String document : documents) {
	    		result.add(strip3(document));
	    	}
    		
    	} else if (option == 4) {
	    	for (String document : documents) {
	    		result.add(strip4(document));
	    	}
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        String sentence = "ayah.isiin pulsa as 25rb di no.625 334006 261.skrg  pnting.";
        //String sentence = "Satu.Dua..Tiga...A";
        TextPreprocessing tp = new TextPreprocessing();
        
        sentence = tp.replacePhoneNumber(sentence);
        //System.out.println("0 : " + sentence.toLowerCase());
        
        System.out.println("0 : " + sentence.toLowerCase());
        System.out.println("1 : " + tp.strip1(sentence).toLowerCase());
        System.out.println("2 : " + tp.strip2(sentence).toLowerCase());
        System.out.println("3 : " + tp.strip3(sentence).toLowerCase());
        System.out.println("4 : " + tp.strip4(sentence).toLowerCase());
        
    }
}
