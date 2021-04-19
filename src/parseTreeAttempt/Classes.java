package parseTreeAttempt;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Classes {
	
	public String code;
	
	public Classes(String code) {
		this.code = code; 
	}
	
	
	/*
	 * Attempts to get the class values and the information regarding it
	 * @return the values of what belongs in each class
	 */
	public ArrayList<String> getClassVals() {
		return searchCode(code, ""); 
	}
	
	public ArrayList<Classes> getClasses() {
		ArrayList<String> search = searchCode(code,"(public|private|) class [A-Z,a-z,0-9]+");
		ArrayList<Classes> classes = new ArrayList<Classes>(); 
		for (int i = 1; i < search.size(); i++) {
			int startPos = code.indexOf(search.get(i - 1));
			int endPos = code.indexOf(search.get(i)); 
			String sub = code.substring(startPos, endPos); 
			classes.add(new Classes(sub)); 
		}
		return classes; 
	}
	
	public ArrayList<String> getConstruct() {
		return searchCode(code,"(public|private|def) [A-Z,a-z,+]+ \\((.+|)\\)");
	}
	
	/*
	 * will return a list of all the methods that are in the class
	 */
	public ArrayList<Method> getMethods() {
		ArrayList<Method> methods = new ArrayList<Method>(); 
		ArrayList<String> search = searchCode(code, "((public|private|def) [A-Z,a-z,<>,\\[\\]]+ [A-Z,a-z,( |)+()]+ |int main[A-Z,a-z,( |)+()]+)");
		for (int i = 1; i < search.size(); i++) {
			int startPos = code.indexOf(search.get(i - 1));
			int endPos = code.indexOf(search.get(i)); 
			String sub; 
			if (i == search.size()-1) {
				String news = code.substring(endPos); 
				 methods.add(new Method(news)); 
			} 
			sub = code.substring(startPos, endPos); 
			methods.add(new Method(sub)); 
		}
		return methods; 
	}
	/*
	 * uses regex to search for the code exactly the same as prev methods
	 */
	public ArrayList<String> searchCode (String code, String regex) {
		ArrayList<String> temp = new ArrayList<String> (); 
		Pattern string = Pattern.compile(regex);
		Matcher matcher = string.matcher(code); 
		while (matcher.find()) {
			temp.add(matcher.group()); 
		}
		return temp;  
	}
	
	public int[] getLocation (String regex) {
		int [] startendpos = new int[2]; 
		startendpos[0] = code.indexOf(regex); 
		startendpos[1] = startendpos[0]+regex.length();
		return startendpos;

	}
}
