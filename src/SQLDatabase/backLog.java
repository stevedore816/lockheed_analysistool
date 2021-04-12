package SQLDatabase;

public class backLog {
	private String CID;
	private String UID;
	private String date;
	private String msg;
	
	public backLog(String CID, String UID, String date, String msg) {
		super();
		this.CID = "";
		this.UID = UID; 
		this.date = date;
		this.msg = msg;
	}

	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
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
