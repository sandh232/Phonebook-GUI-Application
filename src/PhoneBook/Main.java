package PhoneBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Main extends javafx.application.Application{
	
	private Label ID,Name,Number;
	private TextField txt1,txt2,txt3,txt4;
	private TextArea txtArea;
	private Button btnadd,btnupdate,btnsearch,btndelete,btnshow,btnclear,btnnext,btnprevious;
	
	private  Connection con;
	private  Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url="jdbc:mysql://localhost:3306/test";
	private String user = "root";
	private String password = "";
	
	public void start(Stage ps) throws Exception{
	
		GridPane gpane = new GridPane();
		BorderPane bpane = new BorderPane();
		
		
		FlowPane pane = new FlowPane();
		
		HBox hbox1 = new HBox(10);
		HBox hbox = new HBox(5);
		HBox hbox2 = new HBox(300);
		VBox vbox = new VBox(10);
		
		
		hbox.setAlignment(Pos.CENTER);

		hbox2.setAlignment(Pos.CENTER);
		hbox2.setPadding(new Insets(20,0,20,0));
		hbox2.setStyle("-fx-background-color: #800080;");
		vbox.setAlignment(Pos.TOP_CENTER);
	    vbox.setPadding(new Insets(80,100,100,100));
		
		String path="/image/contact.png";
	    Image image = new Image(path,200,200,false,false);
		ImageView contact = new ImageView(image);
		
		String path7="/image/contact1.png";
	    Image contact1 = new Image(path7,600,200,false,false);
		ImageView imagecontact = new ImageView(contact1);

		String path2 = "/image/show.png";
		Image show =new Image(path2,30,30,false,false);
		ImageView imageshow= new ImageView(show);

	    String path3="/image/delete.jpg";
		Image delete = new Image(path3, 30, 30, false, false);
		ImageView imagedelete  = new ImageView(delete);
		
		String path4="/image/add.png";
		Image add = new Image(path4, 30, 30, false, false);
		ImageView imageadd  = new ImageView(add);
		
		String path5 = "/image/clear.png";
		Image clear = new Image(path5,30,30,false,false);
		ImageView imageclear = new ImageView(clear);
		
		String path6 = "/image/edit.jpg";
		Image edit =new Image(path6,30,30,false,false);
		ImageView imageedit = new ImageView(edit);
		
		String path8 = "/image/search.jpg";
		Image find =new Image(path8,30,30,false,false);
		ImageView imagesearch = new ImageView(find);
		
		ID = new Label("ID: ");
		Name = new Label("Name: ");
		Number = new Label("Number: ");
		txt1 = new TextField();
		txt1.setMaxWidth(Double.MAX_VALUE);
		txt2 = new TextField();
		txt3 = new TextField();
		txt4 = new TextField();
		txtArea = new TextArea();
		btnadd = new Button("Add",imageadd);
		btnupdate = new Button("Update",imageedit);
		btndelete = new Button("Delete",imagedelete);
		btnsearch  = new Button("Search",imagesearch);
		btnshow  = new Button("ListAll",imageshow);
		btnclear  = new Button("Clear",imageclear);
		btnnext = new Button("Next >");
		btnprevious = new Button("< Previous");
		
		btnnext.setStyle("-fx-font-weight: bold;");
		btnprevious.setStyle("-fx-font-weight: bold;");
		btnadd.setStyle("-fx-font-weight: bold;");
		btnupdate.setStyle("-fx-font-weight: bold;");
		btndelete.setStyle("-fx-font-weight: bold;");
		btnsearch.setStyle("-fx-font-weight: bold;");
		
		ID.setFont(Font.font("Georgia",FontWeight.BOLD,20));
		Name.setFont(Font.font("Georgia",FontWeight.BOLD,20));
		Number.setFont(Font.font("Georgia",FontWeight.BOLD,20));
		
		gpane.setStyle("-fx-background-color: yellow;");
		vbox.setStyle("-fx-background-color: #95D9EB;");
		hbox1.getChildren().addAll(btnadd,btnupdate,btndelete,btnclear);
		hbox.getChildren().addAll(btnprevious,btnnext);
		
        gpane.add(ID, 0, 0);
		gpane.add(txt4,2,0);
		gpane.add(Name, 0, 1);
		gpane.add(txt1, 2, 1);
		gpane.add(Number, 0, 2);
		gpane.add(txt2, 2, 2 );
		gpane.add(hbox, 1, 4);
		gpane.add(hbox1,0,5);

		
		hbox2.getChildren().addAll(imagecontact,contact);
		vbox.getChildren().addAll(txt3,btnsearch,btnshow);
		gpane.setVgap(5);
		gpane.setPadding(new Insets(50,10,10,10));
		GridPane.setColumnSpan(hbox1, 4);
		
		bpane.setTop(hbox2);
		bpane.setLeft(gpane);
		bpane.setRight(vbox);
		bpane.setCenter(txtArea);
		
	   	Showinfo(num); 
		

	btnadd.setOnAction(e-> {
		String sql = "select ID,Name , ContactNumber from phonebook";
	
	
			
		
			 try {
				 if (txt1.getText().equals("")||txt2.getText().equals("")) {
					 JOptionPane.showMessageDialog(null, "Please Fillup all the fields");
				 }else {
			  ResultSet rs = st.executeQuery(sql);
			   rs.moveToInsertRow();
			   rs.updateString(2, txt1.getText());
			   rs.updateDouble(3, Double.parseDouble(txt2.getText()));
			   rs.insertRow(); 
				 
				 }
			 }
			 catch(NumberFormatException e2) {
				 JOptionPane.showMessageDialog(null, "Contact Number must not be Alphabets");
				  
				   
			   }
			 catch(SQLException e1) {
				 e1.printStackTrace();
			 }
			 
		
	
			
		
		
	
	});

	
	 btnupdate.setOnAction(e-> {
		    
		 String sql = "select ID, Name, ContactNumber from phonebook where ID = "+txt4.getText();
	    	System.out.println(sql);
		 try {
  		   
  		   ResultSet rs = st.executeQuery(sql);
  		   if(rs.next()) {
//  		   rs.absolute(Integer.parseInt(txt4.getText()));
  		   rs.updateString(2, txt1.getText());
  		   rs.updateDouble(3, Double.parseDouble(txt2.getText()));
  		   rs.updateRow();
  		   
  		   }else {
  			   System.out.println("not found");
  		   }
  		   
  	   }catch(SQLException e1) {

  		   e1.printStackTrace();
  	   }
    	
	    	
	    });
	 
	 btndelete.setOnAction(e->{
	    	String sql = "delete from phonebook where ID = ?";
	    	try {
	    		pst = con.prepareStatement(sql);
	    		pst.setInt(1, Integer.parseInt(txt4.getText()));
	    		pst.executeUpdate();
	    	}catch(SQLException e1) {
	    		e1.printStackTrace();
	    	}
	    	
	    });
	 
	 btnclear.setOnAction(e-> {
		 
		 txtArea.setText("");
		 txt1.setText("");
		 txt2.setText("");
		 txt4.setText("");
		 
		 
	 });
	
	
	 
	 btnsearch.setOnAction(e-> {
		
		
			
		 String sql = "select ID, Name, ContactNumber from phonebook ";
	    	
		 try {
  	
  		   ResultSet rs = st.executeQuery(sql);
           rsmd = rs.getMetaData();
           
           String table="\n";
           table += String.format("%30s:%32s:%32s", rsmd.getColumnName(1), rsmd.getColumnName(2),rsmd.getColumnName(3));

   		table += "\n";
         while(rs.next()) {
        	 if(txt3.getText().equals(rs.getString(2))) {
         	for(int i=1; i<= rsmd.getColumnCount(); i++) {
         		 
         		table += String.format("%32s",rs.getString(i));
         	}
         	}

         }
         txtArea.setText(table);
         rs.close();
         
		  		   
  	   }catch(SQLException e1) {
  		   e1.printStackTrace();
  	   }
      
});
	 btnshow.setOnAction(e-> {
		 
		 String sql = "select ID, Name , ContactNumber from phonebook";
         try { 
         
          rs = st.executeQuery(sql);
          rsmd = rs.getMetaData();
          String table="\n";
    		table += String.format("%29s:%32s:%35s", rsmd.getColumnName(1), rsmd.getColumnName(2),rsmd.getColumnName(3));
    		table += "\n";

          while(rs.next()) {
          	for(int i=1; i<= rsmd.getColumnCount(); i++) {
          		
          		table += String.format("%32s", rs.getString(i));
          	}
          	table += "\n";
          }
          txtArea.setText(table);
          rs.close();

         }catch(SQLException e1) {
      	   e1.printStackTrace();
         }
      
		 
	 });
	 btnnext.setOnAction(e-> {
			
         num++;
         if(num<BindList().size()) {
        	 Showinfo(num);
         }
         else {
        	 num = BindList().size()-1;
        	 Showinfo(num);
        	 JOptionPane.showMessageDialog(null, "END");
         }
	  

	
		
	});
	
	btnprevious.setOnAction(e-> {
		num--;
		if(num>0) {
			Showinfo(num);
		}else {
			num=0;
			Showinfo(num);
			JOptionPane.showMessageDialog(null, "END");
		}
		
		
		
	});
		 
	 
		
		
	   Class.forName(driver);
       con = DriverManager.getConnection(url,user,password);
   
 	   st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
 	   
		
       Scene scene = new Scene(bpane,1200,500);
       ps.setScene(scene);
       ps.sizeToScene();
       ps.setTitle("PhoneBook");
       ps.show();
      


		
	}	
	public static void main(String[] args) {
		launch(args);
	}
    public static Connection getConnection() {
    	Connection con;
    	
    	try {
			con= DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
			return con;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
    	
    	
    }
	public static  ArrayList<Contact> BindList(){
		try {
			 Connection con = getConnection();
			 Statement st = con.createStatement();
			ResultSet rs;
			rs = st.executeQuery("select * from phonebook");
			ArrayList<Contact> list = new ArrayList<Contact>();
			while(rs.next()) {
				Contact c = new Contact(Integer.parseInt(rs.getString("ID")),rs.getString("Name"),rs.getString("ContactNumber"));
				list.add(c);
			}
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	int num=0;
	public void Showinfo(int index) {
		txt4.setText(Integer.toString(BindList().get(index).getID()));
		txt1.setText(BindList().get(index).getName());
		txt2.setText(BindList().get(index).getNumber());
		
}


}
