/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Neven Fakhry
 */
@WebServlet(name = "DeleteData", urlPatterns = {"/DeleteData"})
public class DeleteData extends HttpServlet {

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
            out.println("<title>Servlet DeleteData</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteData at " + request.getContextPath() + "</h1>");
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
      PrintWriter out=response.getWriter();
      Connection con=null;
      int id=Integer.parseInt(request.getParameter("id"));
      int OseatsN=0;
      int seatsN=0;
      String flightN="";
       if(id==0)
        {
            out.print("Fill Your Data");
            request.getRequestDispatcher("EditData.jsp").include(request, response);
        }
      try {
                 Class.forName("org.apache.derby.jdbc.ClientDriver");
                 con=DriverManager.getConnection("jdbc:derby://localhost:1527/FlightApplication","m","m");
               
               
             String str="Select * from usersflight where id="+id+"";
             
             Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery(str);
             while(rs.next())
             {
               
                     flightN=rs.getString("FLIGHTNUMBER");
                     OseatsN=rs.getInt("reservedseats");
   

             }
             
             
             String str2="Select * from flights where flightnumber='"+flightN+"'";
             
             Statement stmt2=con.createStatement();
             ResultSet rs2=stmt2.executeQuery(str2);
             while(rs2.next())
             {
               
                    seatsN=rs2.getInt("reservedseats");
   

             }
              seatsN=seatsN-OseatsN;
             
              
              
              String str3="Delete from usersflight where id="+id+"";
             Statement stmt3=con.createStatement();
             stmt3.execute(str3);
             
             
             String str4="update flights set reservedseats="+seatsN+" where flightnumber='"+flightN+"'";
                Statement stmt4=con.createStatement();
                stmt4.executeUpdate(str4);
                
                out.print("Data Deleted");
                response.setContentType("text/html");
               request.getRequestDispatcher("ViewData.jsp").include(request, response); 
                } catch (Exception e) {
                }
           
      
     
      
        try {
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
                 con=DriverManager.getConnection("jdbc:derby://localhost:1527/FlightApplication","m","m");
            
                 
             
            
        } catch (Exception e) {
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
