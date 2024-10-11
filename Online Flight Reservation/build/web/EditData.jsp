
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body  style="background:url(https://images5.alphacoders.com/317/317131.jpg); ">
 
        <%
            Connection con=null;
            String FLIGHTNAME="";
           String TRAVELFROM="";
           String TRAVELTO="";
           String FLIGHTNUMBER="";
           String FLIGHTDATE="";
           String ACCOUNT="";
           String CLASSTYPE="";
           int    RESERVEDSEATS=0;
           String CUSTOMERSNAMES="";
           int id=Integer.parseInt(request.getParameter("id"));
            try {
                 Class.forName("org.apache.derby.jdbc.ClientDriver");
                 con=DriverManager.getConnection("jdbc:derby://localhost:1527/FlightApplication","m","m");
               
               
             String str="Select * from usersflight where id="+id+"";
             
             Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery(str);
             while(rs.next())
             {
                FLIGHTNAME=rs.getString("FLIGHTNAME");
                     TRAVELFROM=rs.getString("TRAVELFROM");
                     TRAVELTO=rs.getString("TRAVELTO");
                     FLIGHTNUMBER=rs.getString("FLIGHTNUMBER");
                     FLIGHTDATE=rs.getString("FLIGHTDATE");
                     ACCOUNT=rs.getString("ACCOUNT");
                     CLASSTYPE=rs.getString("CLASSTYPE");
                     RESERVEDSEATS=rs.getInt("RESERVEDSEATS");
                     CUSTOMERSNAMES=rs.getString("CUSTOMERSNAMES");
   

             }
             
                } catch (Exception e) {
                }
             
            %>
             <center><i> <h2>Please Insert This Data...</h2>
            <form action="FinalEdit" method="POST">
                Your Flight ID: <input type="text" name="id" value="<%=id%>" readOnly="true"><br><br><br>
                Your Flight Number: <input type="text" name="flightN" value="<%=FLIGHTNUMBER%>" readOnly="true"><br><br><br>
            Choose Your Class:            <select name="type">
                                <option name="business">Business</option>
                                <option name="economy">Economy</option>
                                </select><br><br><br>
      Choose Number of ReservedSeats: <input type="text" name="reservedseats" value="<%=RESERVEDSEATS%>"> <br><br><br>   
      Write Your Customers Names:  <input type="text" name="Cusnames" value="<%=CUSTOMERSNAMES%>"><br><br><br>
      <input type="submit" value="Apply">
            </form></i></center>
    </body>
</html>
