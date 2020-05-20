package RegxTest;

public class Categories {

	private int CATEGORYID;//Primary key foreign key link to products table
	
	private String CATEGORYNAME;
	private String DISCRIPTION;
	
	//collect group of object to form a table for better organization
	public Categories(int CatigoryID, String CatigoryName, String Discription) {
			super();
			this.CATEGORYID=CatigoryID;
			this.CATEGORYNAME=CatigoryName;
			this.DISCRIPTION=Discription;
		}
	
	public int getCATEGORYID() {
		return CATEGORYID;
	}
	public void setCATEGORYID(int cATEGORYID) {
		CATEGORYID = cATEGORYID;
	}
	public String getCATEGORYNAME() {
		return CATEGORYNAME;
	}
	public void setCATEGORYNAME(String cATEGORYNAME) {
		CATEGORYNAME = cATEGORYNAME;
	}
	public String getDISCRIPTION() {
		return DISCRIPTION;
	}

	public void setDISCRIPTION(String dISCRIPTION) {
		DISCRIPTION = dISCRIPTION;
	}
}
