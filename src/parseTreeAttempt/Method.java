package parseTreeAttempt;

import java.util.ArrayList;

public class Method extends Classes{
	public String string; 
	public Method(String string) {
		super(string);
		this.string = string; 
	}
	public String toString() {
		return string; 
	}
	public boolean hasIfs() {
		return findIfs().size() > 0; 
	}
	
	public boolean hasLoops() {
		return findLoops().size() > 0; 
	}
	
	public ArrayList<ifStatement> findIfs() {
		ArrayList<String> search = super.searchCode(string, "if[ |(].+[) ][{|:]");
		ArrayList<ifStatement> ifs = new ArrayList<ifStatement>();
		if(search.size() == 1) {
			int endPos = string.indexOf(search.get(0)); 
			String news = code.substring(endPos); 
			ifs.add(new ifStatement(news)); 
		}
		for (int i = 1; i < search.size(); i++) {
			int startPos = code.indexOf(search.get(i - 1));
			int endPos = string.indexOf(search.get(i)); 
			String sub; 
			if (i == search.size()-1) {
				 String news = code.substring(endPos); 
				 ifs.add(new ifStatement(news)); 
			} 
			sub = code.substring(startPos, endPos); 
			ifs.add(new ifStatement(sub)); 
		}
		return ifs; 
	}
	
	public ArrayList<Loop> findLoops() {
		ArrayList<String> search = super.searchCode(string, "(for|while|do)[ |(].+[{|:]"); 
		ArrayList<Loop> loops = new ArrayList<Loop>();
		for (int i = 1; i < search.size(); i++) {
			int startPos = code.indexOf(search.get(i - 1));
			int endPos = string.indexOf(search.get(i)); 
			String sub; 
			if (i == search.size()-1) {
				 String news = code.substring(endPos); 
				 loops.add(new Loop(news)); 
			} 
			sub = code.substring(startPos, endPos); 
			loops.add(new Loop(sub)); 
		}
		return loops; 
	}
}

