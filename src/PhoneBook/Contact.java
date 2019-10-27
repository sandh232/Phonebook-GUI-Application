package PhoneBook;
import java.sql.*;

public class Contact {
	
	private int ID;


	private String Name;
	private String Number;
	
	
	
	public Contact(int iD,String name, String number) {
		super();
		ID = iD;
		Name = name;
		Number = number;
	}
	
	public Contact() {
		super();
		
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		
		Name = name;
	}
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public static int size() {
		return 0;
	}
	


}
