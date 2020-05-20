package RegxTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExternalConnection{
	//Static Server connection Data Source
		private static String myDriver = "oracle.jdbc.OracleDriver";
		private static String server = "jdbc:oracle:thin:@localhost:1521:xe";
		private static String username = "system";
		private static String password = "student";
		
		static List<ProductCatolog> values= new ArrayList<>(Arrays.asList());
		static List<ProductCatolog>porduct;
		static List<Categories> type;
		static List<Suppliers> order;
		static List<OrderDetails> orderDe;

		//connect to a remote server
		public static Connection dbConnect() {
			try {
				// create a handshake connection between java and the desired SQL server. 
				Class.forName(myDriver);
				//connect to an SQL  database.
				Connection conn = DriverManager.getConnection(server, username, password);
				return conn;
				
			}catch(Exception e){
				//error handling
				System.err.println("Got an exception! ");
				System.err.println(e.getMessage());
				return null;
			}
		}
		
		
		//populate data layer
		public static List<ProductCatolog> Shopping(int Field1, String Field2) {
			try {
				// create the java statement to connect to a specific database 
				Statement sta = dbConnect().createStatement();
				ResultSet rs;
				porduct = new ArrayList<>(Arrays.asList());
				
				// execute the query just like any normal SQL , and get a java result set
				String inputquery = "SELECT * FROM PRODUCTS Where CATEGORYID = "+Field1; //Shopping Query
				String pricematching = "SELECT * FROM PRODUCTS Where productName = '"+Field2+"' And supplierID = "+ Field1;
				
				if(Field2==null) {
					rs = sta.executeQuery(inputquery);
					//populate the object with the returned results
					while (rs.next()) {
						porduct.add(new ProductCatolog(
								rs.getInt("productID"),
								rs.getString("productName"),
								rs.getInt("supplierID"),
								rs.getInt("categoryID"),
								rs.getString("quantityPerUnit"),
								rs.getInt("unitsInStock"),
								rs.getInt("unitsOnOrder"),
								rs.getDouble("unitprice")
							));
					}
					sta.close();//close off server connection. release use resources 
					return porduct;
				}else {
					rs = sta.executeQuery(pricematching);
					
					//populate the object with the returned results
					while (rs.next()) {
						porduct.add(new ProductCatolog(
								rs.getInt("productID"),
								rs.getString("productName"),
								rs.getInt("supplierID"),
								rs.getInt("categoryID"),
								rs.getString("quantityPerUnit"),
								rs.getInt("unitsInStock"),
								rs.getInt("unitsOnOrder"),
								rs.getDouble("unitprice")
						));
					}
					sta.close();//close off server connection. release use resources 
					return porduct;
				}
			}catch(Exception e){
				//error handling
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
				return null;
			}
		}
		
		//populate data layer
		public static List<Categories> Choice() {
			try {
				// create the java statement to connect to a specific database 
				Statement sta = dbConnect().createStatement();
				ResultSet rs;
				type = new ArrayList<>(Arrays.asList());
						
				// execute the query just like any normal SQL , and get a java result set
				String inputquery = "SELECT * FROM CATEGORIES"; //Shopping Query 
				rs = sta.executeQuery(inputquery);
						
				//populate the object with the returned results
				while (rs.next()) {
					type.add(new Categories(
						rs.getInt("CategoryID"),
						rs.getString("CategoryName"),
						rs.getString("DESCRIPTION")
					));
				}
				sta.close();//close off server connection. release use resources 
				return type;
				}catch(Exception e){
					//error handling
					System.err.println("Got an exception!");
					System.err.println(e.getMessage());
					return null;
				}
			}

		public static  List<Suppliers> PlaceOrder(String Field2){
			try {
				// create the java statement to connect to a specific database 
				Statement sta = dbConnect().createStatement();
				ResultSet rs;
				order = new ArrayList<>(Arrays.asList());
						
				// execute the query just like any normal SQL , and get a java result set
				String inputquery = "SELECT * " + 
						"FROM Suppliers " + 
						"Where COMPANYNAME=( " + 
						"SELECT s.COMPANYNAME " + 
						"FROM PRODUCTS p " + 
						"JOIN SUPPLIERS s ON p.SUPPLIERID = s.SUPPLIERID " + 
						"WHERE  p.PRODUCTNAME='"+Field2+"')"; //Shopping Query 
				rs = sta.executeQuery(inputquery);
						
				//populate the object with the returned results
				while (rs.next()) {
					order.add(new Suppliers(
							rs.getInt("SupplierID"),
							rs.getString("CompanyName"),
							rs.getString("Address"),
							rs.getString("City"),
							rs.getString("Region"),
							rs.getInt("PostalCode"),		
							rs.getString("Country"),
							rs.getString("Phone")
					));
				}
				sta.close();//close off server connection. release use resources 
				return order;
				}catch(Exception e){
					//error handling
					System.err.println("Got an exception!");
					System.err.println(e.getMessage());
					return null;
				}
		}
		
		public static List<OrderDetails> order(int product){
			try {
				Statement sta = dbConnect().createStatement();
				ResultSet rs;
				orderDe = new ArrayList<>(Arrays.asList());
				// execute the query just like any normal SQL , and get a java result set
				String inputquery = "SELECT * FROM ORDERDETAILS Where productID = "+product
						+" And Discount =(SELECT Max(Discount) FROM ORDERDETAILS Where productID = "+product+")";
						 
				rs = sta.executeQuery(inputquery);
				
				while (rs.next()) {
					orderDe.add(new OrderDetails(
							rs.getInt("OrderID"),
							rs.getInt("ProductID"),
							rs.getDouble("UnitPrice"),
							rs.getDouble("Discount")
					));
				}
				sta.close();//close off server connection. release use resources 
				return orderDe;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		public static void update(int itemfeld1, int itemfeld2, String Value1, int Value2 ) {
			Statement sta;
			try {
				sta = dbConnect().createStatement();
				String updatequery = "Update PRODUCTS Set unitsInStock = "+itemfeld1+", unitsOnOrder = "+itemfeld2+
						"Where productName = "+Value1+" And supplierID = "+ Value2;
				sta.executeUpdate(updatequery);
				sta.close();//close off server connection. release use resources 	
			}catch(Exception e){
				//error handling
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
			}
			
		}
		
		//test lab method for ease of debugging
		public static void main(String[] args) {
			System.out.println("Here is the list of product types you can shop from");
			List<Categories> filter = Choice();
			for(Categories i:filter){
				System.out.println(i.getCATEGORYID()+" "+i.getCATEGORYNAME()+": "+i.getDISCRIPTION());
			}
			System.out.println("Chose what type of product you want by product id.");
			Scanner input= new Scanner(System.in);
			int Value;
			while(true) {
			try {
				Value = Integer.parseInt(input.nextLine());
				while(1>Value || Value>8) {
					System.out.println(Value+" is Not a reconised Product Code Please try again");
					Value=input.nextInt();
				}
				System.out.println("Here is the list of Availibule products of type: '"+ filter.get(Value-1).getCATEGORYNAME()+ "' you can Buy");
				break;
			}catch (NumberFormatException e) {
				System.out.println(input.nextLine()+" Input is not a valid integer");
			}
			}
			
			List<ProductCatolog> shipping = Shopping(Value, null);
			for(ProductCatolog i:shipping){
				System.out.println(i.getProductName()+" "+i.getQuantityPerUnit()+" "+i.getUnitsInStock()+" "+i.getSupplierID());
			}
			
			System.out.println("Chose what Supplier of product you want by product name.");
			String BValue;
			
			while(true) {
				BValue = input.nextLine();
				for(ProductCatolog i:shipping){
					if(i.getProductName().equalsIgnoreCase(BValue)) {
						List<Suppliers> pickup = PlaceOrder(BValue);
						for(Suppliers ii:pickup){
							System.out.println(ii.getCompanyName()+" "+ii.getAddress()+" "+ii.getCity()+" "+ii.getCountry()+" "+ii.getPostalCode()+" "+ii.getSupplierID());
							values=Shopping(ii.getSupplierID(), BValue);
							if(values.size()!=0){
							System.out.println(values.get(0).getProductName()+
									" "+values.get(0).getQuantityPerUnit()+
									" "+values.get(0).getUnitsInStock()+
									" "+values.get(0).getUnitprice());
							}		
						}
						break;
					}
				}
				if(values.isEmpty()) {
					System.out.println(BValue+" is not a product we are curently offering. please try again.");
					break;
				}else {break;}
			}
			
			System.out.println("Witch supplyer would you like to buy from?");
			int finalchoice=input.nextInt();
			values=Shopping(finalchoice, BValue);
			System.out.println("There are "+values.get(0).getUnitsInStock()+" of "+values.get(0).getProductName()+" that you can buy for "+values.get(0).getUnitprice()+" per unit");
			System.out.println("How many would you like to buy?");
			
			
			int buy = input.nextInt();
			if(buy>values.get(0).getUnitsInStock()) {
				System.out.println("The Seller dose not have that many items to buy");
			}else {
				double total=buy*values.get(0).getUnitprice();
				update((values.get(0).getUnitsInStock()-buy), buy, values.get(0).getProductName(), finalchoice);
				System.out.println("your total ammout comes to: "+total);
				List<OrderDetails> discount=order(values.get(0).getProductID());
				System.out.println("There is a discout on this item for: "+discount.get(0).getDiscount()+ " Would you like to redeem it?");
				total-=(total*discount.get(0).getDiscount());
				System.out.println("your new total ammout after discount comes to: "+total);
			}
			
			input.close();
			
		}
}
