/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import com.sun.xml.ws.runtime.dev.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Neven Fakhry
 */
@WebServlet(name = "Flight_Checker", urlPatterns = {"/Flight_Checker"})
public class Flight_Checker extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Flight_Checker</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Flight_Checker at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String flightN=request.getParameter("flightN");
        String names=request.getParameter("names");
        String[] type=request.getParameterValues("type");
        int maxN=0;
        int temp=0;
        int seatsN=Integer.parseInt(request.getParameter("seatsN"));
        PrintWriter out=response.getWriter();
        Connection con=null;
        if(  seatsN==0 || names.equals(""))
        {
             out.println("<html>");
                out.println("<head><title>Hello World</title></title>");
                out.println("<body>");
                out.println("<center><pre><b><i>Fill All Data Correctly</i></b></pre></center>");
                out.println("</body></html>");
                request.getRequestDispatcher("Flight_Reservation.jsp").include(request, response);
        }
        if(seatsN>1 && names.equals("") )
        {
             out.println("<html>");
                out.println("<head><title>Hello World</title></title>");
                out.println("<body>");
                out.println("<center><pre><b><i>Enter Names of Other Customers</i></b></pre></center>");
                out.println("</body></html>");
                request.getRequestDispatcher("Flight_Reservation.jsp").include(request, response);
        }
         
      try{
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      con=DriverManager.getConnection("jdbc:derby://localhost:1527/FlightApplication","m","m");
      String str="select * from flights where flightnumber='"+flightN+"'";
      Statement stmt=con.createStatement();
      ResultSet rs=stmt.executeQuery(str);
      while(rs.next())
      {
        maxN=rs.getInt("maxseats");
        temp=rs.getInt("reservedseats");
        
      }
      }catch(ClassNotFoundException | SQLException ex) {
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
      temp=seatsN+temp;
      if(temp<maxN)
      {
          
          //enter data here
          
          /*request.getRequestDispatcher("UserDB_FlighrChecker.jsp").forward(request, response);*/
        String FLIGHTNAME="";
        String TRAVELFROM="";
        String TRAVELTO="";
        String FLIGHTNUMBER="";
        String FLIGHTDATE="";
        int MAXSEATS=0;
        
      
      try{
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      con=DriverManager.getConnection("jdbc:derby://localhost:1527/FlightApplication","m","m");
      String str1="select * from flights where flightnumber='"+flightN+"' ";
      Statement stmt1=con.createStatement();
      ResultSet rs1=stmt1.executeQuery(str1);
      while(rs1.next())
      {
          FLIGHTNAME=rs1.getString("FLIGHTNAME"); 
          TRAVELFROM=rs1.getString("TRAVELFROM") ;
          TRAVELTO=rs1.getString("TRAVELTO") ;
          FLIGHTNUMBER=rs1.getString("FLIGHTNUMBER");
          FLIGHTDATE=rs1.getString("FLIGHTDATE");
          MAXSEATS=rs1.getInt("MAXSEATS");
          
      }
     
       String str3="DELETE FROM flights WHERE flightnumber='"+flightN+"'";
       Statement stmt3=con.createStatement();
       stmt3.executeUpdate(str3);
      /*String str2="update flights set reservedseats='"+temp+"' where flightnumber='"+flightN+"' ";*/
      String str2="insert into flights values(?,?,?,?,?,?,?)";
     PreparedStatement prestmt2=con.prepareStatement(str2);
     prestmt2.setString(1,FLIGHTNAME);
     prestmt2.setString(2,TRAVELFROM);
     prestmt2.setString(3,TRAVELTO);
     prestmt2.setString(4,FLIGHTNUMBER);
     prestmt2.setString(5,FLIGHTDATE);
     prestmt2.setInt(6,temp);
     prestmt2.setInt(7,MAXSEATS);
     prestmt2.executeUpdate();
     
     temp=0;
      
      PreparedStatement prestmt=con.prepareStatement("insert into  USERSFLIGHT values(?,?,?,?,?,?,?,?,?,?)");
      prestmt.setString(1, FLIGHTNAME);
      prestmt.setString(2, TRAVELFROM);
      prestmt.setString(3, TRAVELTO);
      prestmt.setString(4, FLIGHTNUMBER);
      prestmt.setString(5, FLIGHTDATE);
       String account=request.getParameter("accounts");
      prestmt.setString(6, account);
        
      
      prestmt.setString(7, type[0]);
      prestmt.setInt(8, seatsN);
      prestmt.setString(9, names);
          Random rand=new Random();
          int rand_int=rand.nextInt(10000);
          prestmt.setInt(10, rand_int);
    
    prestmt.executeUpdate();
  
   
      request.getRequestDispatcher("Flight_Reservation.jsp").forward(request, response);
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
     
      }
      else
      {
          out.println("<html>");
                out.println("<head><title>Hello World</title></title>");
                out.println("<body>");
                out.println("<center><pre><b><i>Number of Reserved Seats Exceeds Max Seats That Can Be Reserved</i></b></pre></center>");
                out.println("</body></html>");
                 
                
      }
        
      }
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
