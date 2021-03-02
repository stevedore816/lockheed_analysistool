package analyzer;

/*
 * This class is the general attackVector object that we will use to store our attack vectors in
 */
public class attackVector {
	String reason; 
	int start; 
	int end; 
	Type type;
	
	/* This is the class method to create the attackVector 
	 *  @param start needs to have a start pos in the string to referenced to
	 *  @param end needs to have a end pos to where the vector statement ends
	 *  @param type needs to have a type of attack is its ex. INPUTVALIDATION, MEMORYLEAK, etc.
	 */
	public attackVector(int start, int end, Type type) {
		this.start = start;
		this.end = end;
		this.type = type;
	}
	
	/* This is the class method to create the attackVector 
	 *  @param start needs to have a start pos in the string to referenced to
	 *  @param end needs to have a end pos to where the vector statement ends
	 *  @param type needs to have a type of attack is its ex. INPUTVALIDATION, MEMORYLEAK, etc.
	 *  @param reason is the reason why this is an attack vector or an important note about it
	 */
	public attackVector(int start, int end, Type type,String reason) {
		this.start = start;
		this.end = end;
		this.type = type;
		this.reason = reason; 
	}
	
	/*This is the class method to also create the attackVector
	 * incase a start and end type cannot be found
	 * @param type takes a type value so its atleast stored based on the type of attackVector it is
	 */
	public attackVector(Type type) {
		this.type = type;
	}
	
	/*
	 * @return the start pos of the attackVector
	 */
	public int getStart() {
		return start;
	}
	
	/*
	 * @param type sets the start pos of attackVector incase it needs to be changed
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/*
	 * @return the end pos of the attackVector
	 */
	public int getEnd() {
		return end;
	}
	/*
	 * @param type sets the end pos of attackVector incase it needs to be changed
	 */
	public void setEnd(int end) {
		this.end = end;
	}
	/*
	 * @return the type of attackVector I.E INPUTVALIDATION, MEMORYLEAK
	 */
	public Type getType() {
		return type;
	}
	/*
	 * @param type sets the type of attackVector incase it needs to be changed
	 */
	public void setType(Type type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	} 
	
	
}
