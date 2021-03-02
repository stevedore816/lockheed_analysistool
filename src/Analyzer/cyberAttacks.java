package analyzer;

import java.util.ArrayList;
/*
 * This class is where the general list of attackVectors will be stored 
 */
public class cyberAttacks {
	//vector value that will be referenced on a global reference using getList()
	private static ArrayList<attackVector> vector = new ArrayList<attackVector>();
	
	/*adds a value to the global reference vector
	 * @param attack is the attackvector to be added from the list
	 */
	public static void add (attackVector attack) {
		vector.add(attack); 
	}
	/*adds a value to the global reference vector
	 * @param attack is the attackvector to be removed from the list
	 */
	public static void remove (attackVector attack) {
		vector.remove(attack); 
	}
	
	/*
	 * @return returns the list of attacks that the code has
	 */
	public static ArrayList<attackVector> getList() {
		return vector; 
	}
	/*
	 * @param type the type that is going to be found in this list
	 * @return returns an array of vectors that are all the same cyber attack
	 */
	public static ArrayList<attackVector> sortByType(Type type) {
		ArrayList<attackVector> newVector = new ArrayList<attackVector>(); 
		for(attackVector attack : vector) {
			if(attack.getType() == type) {
				newVector.add(attack); 
			}
		}
		
		return newVector;
	}
}
