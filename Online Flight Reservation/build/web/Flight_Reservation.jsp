
        <%@page import="java.sql.*"%>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>tables</title>
    </head>
    <body style="background:url(https://cdn.hipwallpaper.com/i/83/50/YreQpy.jpg); "> 
         <jsp:useBean id="acc" class="account.User_Account" scope="session">
            <jsp:setProperty property="*" name="acc"/>
        </jsp:useBean>
       <% String account=acc.getAccount();%> 
       <%       
 
    try
 {

   
     
 Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/FlightApplication", "m", "m");

Statement stmt=con.createStatement();

String str="select  * from FLIGHTS ";

  ResultSet rs = stmt.executeQuery(str);


   
 while(rs.next())
 {  %>
    
    <center><table border="1">  
 
 <tr>    

   <td>  <%= rs.getString("FLIGHTNAME") %>   </td> 
    <td>  <%= rs.getString("TRAVELFROM") %>   </td> 
    <td>  <%= rs.getString("TRAVELTO") %>   </td> 
 <td> <%= rs.getString("FLIGHTNUMBER")%></td>
 <td> <%= rs.getString("FLIGHTDATE")%></td>
 <td> <%= rs.getString("RESERVEDSEATS")%></td>
 </tr>

 <br>
</table></center>
   
 


<%
    }%>
   
    <center>
         <b><i>
        <h1>Please insert this data</h1>
       
          <form action="Flight_Checker" method="POST" >
             <center>
           <pre>

   Your Account<input type="text" name="accounts" value="<%=account%>" readonly="ture"><br><br><br>
  Enter your flight number :    <input type="text" name="flightN"><br><br><br>
  Choose Your Class:            <select name="type">
                                <option name="business">Business</option>
                                <option name="economy">Economy</option>
                                </select><br><br><br>
  enter number of seats:        <input type="text" name="seatsN"><br><br><br>
  enter Users Names:             <input type="text" name="names"><br><br><br>

  
                  <input type="submit" value="reservation">


   
                   </i></b>
  </pre>      
</center>
          </form>
    <form>
        <center>
            <pre>

       
 <% session.setAttribute("account", account);%>

            </pre>
        </center>
    <a href="ViewData.jsp">View Data</a>
</form>
    
    
    
<%
} catch(Exception e)
 {
 out.println("Sorry Try again ");
     // request.getRequestDispatcher("Flight.html").include(request, response);
 }
 %>
        
    </body>
</html>
