
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marlo
 */
public class Account {
    int id;
    int balance;
    Date dateCreated;
    int initialBalance;
    int withdraw;
    int deposit;
    String username;
    
    public Account(){
        
    }
    public Account(int ID){
        id = ID;
        initialBalance = Integer.parseInt(JOptionPane.showInputDialog("Enter deposit amount"));
        username = JOptionPane.showInputDialog("Enter username");
     
        try{
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://LAPTOP-FQI3EQ9U;databaseName=Account;user=JD522;password=JD522; encrypt=true; trustServerCertificate=true");

        String query = " insert into AccountHolder (ID, USERNAME, INITIALBALANCE, CURRENTBALANCE)"
        + " values (?, ?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt (1, id);
        preparedStmt.setString (2, username);
        preparedStmt.setInt    (3, initialBalance);
        preparedStmt.setInt    (4, initialBalance);
        
        preparedStmt.execute();
        
        conn.close();
        
           
        
        
    }
    catch(Exception e){
        System.out.println(e);
    
    }                                
   
    }
    
    public int getID(){
        return id;
    }
    
    public void setID(int id){
        this.id = id;
    }
    
    public void setBalance(){
         try{
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://LAPTOP-FQI3EQ9U;databaseName=Account;user=JD522;password=JD522; encrypt=true; trustServerCertificate=true");
        String query = "Select CURRENTBALANCE from AccountHolder Where ID = '"+ getID() +"'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        if(rs.next()){
             balance = rs.getInt("CURRENTBALANCE");
            
        }
        
        
    }
    catch(Exception e){
        System.out.println(e);
    
    }
    }
    
    public int getBalance(){
        return balance;
    }
    
    public Date getDate(){
        return dateCreated;
    }
    
    public void setDate(Date newdate){
        this.dateCreated = newdate;
    }
    
    
    public double getInitialBalance(){
        return initialBalance;
    }
    
    
    public String getUsername(){
        return username;
    }
    
    
    public void withdraw(int withdrawAmt){
      
        
        if(withdrawAmt > getBalance()){
            JOptionPane.showMessageDialog(null, "You have exceeded your limit!");
        }
        else{
            balance = getBalance() - withdrawAmt;
        try{
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://LAPTOP-FQI3EQ9U;databaseName=Account;user=JD522;password=JD522; encrypt=true; trustServerCertificate=true");
        String query = "UPDATE AccountHolder set CURRENTBALANCE = '"+ balance +"' Where ID = '"+ getID() +"'";
        Statement st = con.createStatement();
        st.executeUpdate(query);
        
        }
    catch(Exception e){
        System.out.println(e);
    
                }

            }
                            }
    public void deposit(int depositAmt){
        if(depositAmt > 10000){
            JOptionPane.showMessageDialog(null, "“No amount greater than 10.000 will be deposited. Please contact the bank");
        }
        else{
            balance = getBalance() + depositAmt;
        try{
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://LAPTOP-FQI3EQ9U;databaseName=Account;user=JD522;password=JD522; encrypt=true; trustServerCertificate=true");
        String query = "UPDATE AccountHolder set CURRENTBALANCE = '"+ balance +"' Where ID = '"+ getID() +"'";
        Statement st = con.createStatement();
        st.executeUpdate(query);
        
        }
    catch(Exception e){
        System.out.println(e);
    
                }

            }
    }
    
    public void printStatement(){
        try{
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://LAPTOP-FQI3EQ9U;databaseName=Account;user=JD522;password=JD522; encrypt=true; trustServerCertificate=true");
        String query = "Select CURRENTBALANCE, USERNAME from AccountHolder Where ID = '"+ getID() +"'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        if(rs.next()){
             balance = rs.getInt("CURRENTBALANCE");
             username = rs.getString("USERNAME");
            JOptionPane.showMessageDialog(null, "Username: " + username + "\nBalance: " + "R" + balance + "\nDate: " + getDate());
        }
        
        
    }
    catch(Exception e){
        System.out.println(e);
    
    }
    
    }
}
