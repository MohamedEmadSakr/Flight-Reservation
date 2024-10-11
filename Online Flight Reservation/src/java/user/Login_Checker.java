/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
@WebServlet(name = "Login_Checker", urlPatterns = {"/Login_Checker"})
public class Login_Checker extends HttpServlet {

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
            out.println("<title>Servlet Login_Checker</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login_Checker at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
         String account = request.getParameter("account");
        String password = request.getParameter("pass");
        Cookie c=new Cookie("account",account);
        response.addCookie(c);
     Connection con=null;
      try
 {

    
    
     
     
 Class.forName("org.apache.derby.jdbc.ClientDriver");
             con=DriverManager.getConnection("jdbc:derby://localhost:1527/FlightApplication", "m", "m");

Statement stmt=con.createStatement();

String sn="select * from USERS where account ='"+account+" ' and password='"+password+"'";

  ResultSet rs = stmt.executeQuery(sn);
 if(rs.next())
 {
     
     request.getRequestDispatcher("Flight_Reservation.jsp").include(request, response);
 }
 else
 {
     out.println("<html>");
                out.println("<head><title>Hello World</title></title>");
                out.println("<body>");
                out.println("<center><pre><b><i>Data Not Found</i></b></pre></center>");
                out.println("</body></html>");
      
     request.getRequestDispatcher("index.html").include(request, response);
 }


} catch(ClassNotFoundException | SQLException ex) {
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
                 out.print("Failed to Connect to table");
              }
          } catch (SQLException ex) {
             ex.printStackTrace();
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
