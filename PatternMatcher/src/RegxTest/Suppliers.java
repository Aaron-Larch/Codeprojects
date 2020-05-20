package RegxTest;

public class Suppliers {
	private int SupplierID;
	private String CompanyName;
	private String Address;
	private String City;
	private String Region;
	private int PostalCode;
	private String Country;
	private String Phone;
	
	//collect group of object to form a table for better organization
		public Suppliers(int supplierid, String companyname, String address, String city,
				String region, int postalcode, String country, String phone) {
			super();
			this.SupplierID=supplierid;
			this.CompanyName=companyname;
			this.Address=address;
			this.City=city;
			this.Region=region;
			this.PostalCode=postalcode;		
			this.Country=country;
			this.Phone=phone;
		}
	
	public int getSupplierID() {
		return SupplierID;
	}
	public void setSupplierID(int supplierID) {
		SupplierID = supplierID;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getRegion() {
		return Region;
	}
	public void setRegion(String region) {
		Region = region;
	}
	public int getPostalCode() {
		return PostalCode;
	}
	public void setPostalCode(int postalCode) {
		PostalCode = postalCode;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
}
