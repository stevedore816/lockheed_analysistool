package SQLDatabase;

public class backLog {
	private int CID;
	private String UID;
	private String date;
	private String msg;
	
	public backLog(int CID, String UID, String date, String msg) {
		super();
		this.CID = CID;
		this.UID = UID; 
		this.date = date;
		this.msg = msg;
	}

	public int getCID() {
		return CID;
	}

	public void setCID(int cID) {
		CID = cID;
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	} 
	
	public String toString() {
		return "CID: " + CID + " User: " + UID + " Date & Time: " + date; 
	}
	
}
