package test;

import java.util.ArrayList;
import java.util.List;

import model.*;

public class ImpWordTest {
	public static void main (String[] args) {
		/*
		IOFile io = new IOFile();
		ImportantWords iw = new ImportantWords(1);
		List<String> list = new ArrayList<String>();
		String[] S = {"BNI SMS BANKING ke an  Sdr TUSIRI telah berhasil", 
				"Sisa kuota", 
				"SYUKIS ISO SODEKAT", 
				"satu satu satu aku mau makan"};
		for(int i = 0; i < S.length; i++) {
			list.add(S[i]);
		}
		
		//list.clear();
		list = io.readListTextSp("./data/spam.txt");
		
		List<String> res = iw.getImportantWord(list);
		String st = "ab    abcA ssa";
		st = st.replaceAll("\\s+", " ");
		System.out.println(st);
		printList(res);
		*/
		WordSpace ws = new WordSpace();
		ws.load();
		ws.getTrainingSetInstances();
		
		
		
	}
	
	public static void printList(List L) {
		for(int i = 0; i < L.size(); i++) {
			System.out.println(i + " - " + L.get(i));
		}
	}
}
