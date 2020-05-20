package RegxTest;

public class ProductCatolog {

	private int productID;
	private String productName;
	private String quantityPerUnit;
	private int unitsInStock;
	private int unitsOnOrder;
	private double unitprice;
	
	private int supplierID;
	private int categoryID;

	//collect group of object to form a table for better organization
	public ProductCatolog(int productID, String productName, int supplierID, int categoryID, String quantityPerUnit,
			int unitsInStock, int unitsOnOrder, double unitprice) {
		super();
		this.productID=productID;
		this.productName=productName;
		this.supplierID=supplierID;
		this.categoryID=categoryID;
		this.quantityPerUnit=quantityPerUnit;
		this.unitsInStock=unitsInStock;
		this.unitsOnOrder=unitsOnOrder;
		this.unitprice=unitprice;
	}
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}
	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}
	public int getUnitsInStock() {
		return unitsInStock;
	}
	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	public int getUnitsOnOrder() {
		return unitsOnOrder;
	}
	public void setUnitsOnOrder(int unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}
	
	public double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
}
