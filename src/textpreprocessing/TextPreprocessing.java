/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textpreprocessing;

import IndonesianNLP.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author toshiba
 */
public class TextPreprocessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String sentence = "ayah.isiin pulsa as 25rb di no.625 334006 261.skrg pnting.";
        //String sentence = "Satu.Dua..Tiga...A";
        sentence = replacePhoneNumber(sentence);
        //System.out.println("0 : " + sentence.toLowerCase());
        
        System.out.println("0 : " + sentence.toLowerCase());
        System.out.println("1 : " + strip1(sentence).toLowerCase());
        System.out.println("2 : " + strip2(sentence).toLowerCase());
        System.out.println("3 : " + strip3(sentence).toLowerCase());
        System.out.println("4 : " + strip4(sentence).toLowerCase());
        
    }
    
    
    public static String strip1(String str) {
        String ret = str;
        ret = miscCharIntoSpace(ret);        
        replacePhoneNumber(ret);
        ret = stripShortWords(str, 3);                        
        
        return ret;
    }
    
    public static String strip2(String str) {
        IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
        IndonesianStemmer stemmer = new IndonesianStemmer(); 
        
        String ret = str;        
        ret = miscCharIntoSpace(ret);
        ret = replacePhoneNumber(ret);
        ret = formalizer.normalizeSentence(ret);
        ret = stemmer.stemSentence(ret);
                
        return ret;
    }
    
    public static String strip3(String str) {
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
    
    public static String strip4(String str) {
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
    
    public static String replacePhoneNumber(String str) {
        String regex = "[\\+]?[\\d | [\\s]]{8,}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        
        return m.replaceAll("NomorHP");
    }        
    
    public static String miscCharIntoSpace(String str) {
        String regex = "[^\\d* | ^\\w*]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        
        return m.replaceAll(" ");
    }
    
    
    public static String stripShortWords(String str, int n) {        
        String regex = "\\b\\w{0,"+ n + "}\\b";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        
        return m.replaceAll("");        
    }
}
