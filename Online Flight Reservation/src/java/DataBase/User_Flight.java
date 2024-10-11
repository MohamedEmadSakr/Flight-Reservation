
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;


public class User_Flight {
    String flightN;
    String[] type;
    String names;
    int seatsN;
    String account;

    public String getFlightN() {
        return flightN;
    }

    public void setFlightN(String flightN) {
        this.flightN = flightN;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getSeatsN() {
        return seatsN;
    }

    public void setSeatsN(int seatsN) {
        this.seatsN = seatsN;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int Flight_num()
    {
        
        int result=0;
        String FLIGHTNAME="";
        String TRAVELFROM="";
        String TRAVELTO="";
        String FLIGHTNUMBER="";
        String FLIGHTDATE="";
        
      Connection con=null;
      try{
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      con=DriverManager.getConnection("jdbc:derby://localhost:1527/FlightApplication","m","m");
      String str="select * from flights where flightnumber='"+getFlightN()+"' ";
      Statement stmt=con.createStatement();
      ResultSet rs=stmt.executeQuery(str);
      while(rs.next())
      {
          FLIGHTNAME=rs.getString("FLIGHTNAME"); 
          TRAVELFROM=rs.getString("TRAVELFROM") ;
          TRAVELTO=rs.getString("TRAVELTO") ;
          FLIGHTNUMBER=rs.getString("FLIGHTNUMBER");
          FLIGHTDATE=rs.getString("FLIGHTDATE");
          
      }
      PreparedStatement prestmt=con.prepareStatement("insert into  USERSFLIGHT values(?,?,?,?,?,?,?,?,?)");
      prestmt.setString(1, FLIGHTNAME);
      prestmt.setString(2, TRAVELFROM);
      prestmt.setString(3, TRAVELTO);
      prestmt.setString(4, FLIGHTNUMBER);
      prestmt.setString(5, FLIGHTDATE);
      prestmt.setString(6, getAccount());
      prestmt.setString(7, getType()[0]);
      prestmt.setInt(8, getSeatsN());
      prestmt.setString(9, getNames());
    
      result=prestmt.executeUpdate();
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
