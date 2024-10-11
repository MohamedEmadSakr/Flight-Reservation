
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class User {
       String fname;
    String lname;
    String passportnum;
    String account;
    String pass;
    String email;
    String address;
    String phone;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassportnum() {
        return passportnum;
    }

    public void setPassportnum(String passportnum) {
        this.passportnum = passportnum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public int Signup(){
      int result=0;
      Connection con=null;
      try{
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      con=DriverManager.getConnection("jdbc:derby://localhost:1527/FlightApplication","m","m");
      String str="insert into USERS values"+"(?,?,?,?,?,?,?,?)";
      PreparedStatement stmt=con.prepareStatement(str);
      stmt.setString(1, getFname());
      stmt.setString(2, getLname());
      stmt.setString(3, getPassportnum());
      stmt.setString(4, getAccount());
      stmt.setString(5,getPass());
      stmt.setString(6, getEmail());
      stmt.setString(7, getAddress());
      stmt.setString(8,getPhone());
      result=stmt.executeUpdate();
              }catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        finally{
           
          try {
              if(con !=null)
              {
                  
                 
                  con.close();
              }
              else
              {
                  
              }
          } catch (SQLException ex) {
             ex.printStackTrace();
          }
    }
  return result;
} 
}
