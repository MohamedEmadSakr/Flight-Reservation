

<%@page import="javax.annotation.Resource"%>
<%@page import="javax.security.auth.login.AccountException"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background:url(https://cdn.hipwallpaper.com/i/83/50/YreQpy.jpg); "> 
        <%
            Connection con=null;
            try {
                 Class.forName("org.apache.derby.jdbc.ClientDriver");
                 con=DriverManager.getConnection("jdbc:derby://localhost:1527/FlightApplication","m","m");
              
                  String account=(String)session.getAttribute("account");
              
             String str="Select * from usersflight where account='"+account+"'";
             Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery(str);
             while(rs.next())
             {%>
                 <table border="1">    
 <tr>    
     
   <td>  <%= rs.getString("FLIGHTNAME") %>   </td> 
    <td>  <%= rs.getString("TRAVELFROM") %>   </td> 
    <td>  <%= rs.getString("TRAVELTO") %>   </td> 
 <td> <%= rs.getString("FLIGHTNUMBER")%></td>
 <td> <%= rs.getString("FLIGHTDATE")%></td>
 <td> <%= rs.getString("account")%></td>
 
  <td> <%= rs.getInt("reservedseats")%></td>
   <td> <%= rs.getString("customersnames")%></td>
    <td> <%= rs.getInt("id")%></td>
 </tr>

 <br>
</table>
             <%}%>
             <%
                } catch (Exception e) {
                }
             
            %>
              <%  
                  String ac=(String)session.getAttribute("account");
              %>
            
             Your Account<input type="text" name="account2" value="<%=ac%>" readonly="ture">
    <center><i> <h2>Please Insert This Data...</h2>
            <form action="EditData.jsp">
           
                <input type="number" name="id" placeholder="Enter Your ID">
            <input  type="submit" value="EditData"><br>
             </form>
           
            <form action="DeleteData" method="POST">
               
                 <input type="number" name="id"placeholder="Enter Your ID">
                <input  type="submit" value="Delete">
            </form>
        </i> </center>
            
            
    </body>
</html>
