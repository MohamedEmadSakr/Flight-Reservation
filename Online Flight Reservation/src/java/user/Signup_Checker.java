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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Neven Fakhry
 */
@WebServlet(name = "Signup_Checker", urlPatterns = {"/Signup_Checker"})
public class Signup_Checker extends HttpServlet {

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
            out.println("<title>Servlet Signup_Checker</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Signup_Checker at " + request.getContextPath() + "</h1>");
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
          int count = 0;
        response.setContentType("text/html");
       String firstname=request.getParameter("fname");
       String lastname=request.getParameter("lname");
       String passportNum=request.getParameter("passportnum");
       String account=request.getParameter("account");
       String password=request.getParameter("pass");
       String Email=request.getParameter("email");
       String Address=request.getParameter("address");
       String mobile=request.getParameter("phone");
       PrintWriter pw=response.getWriter();
       if("".equals(firstname)||"".equals(lastname)||"".equals(passportNum)||"".equals(account)||"".equals(password)||"".equals(Email)||"".equals(Address)||"".equals(mobile)){
     
      pw.println("<html>");
      pw.println("<head><title>Hello World</title></title>");
      pw.println("<body>");
      pw.println("<center><pre><b><i>Invalid</i></b></pre></center>");
      pw.println("</body></html>");
       
       count=1;
       }
        if(mobile.length()!=11){
           
           pw.println("<html>");
      pw.println("<head><title>Hello World</title></title>");
      pw.println("<body>");
      pw.println("<center><pre><b><i>Invalid Phone Number</i></b></pre></center>");
      pw.println("</body></html>");
       
          count = 1;
       
       }
       if (firstname.length()<3||lastname.length()<3)
       {
            pw.println("<html>");
      pw.println("<head><title>Hello World</title></title>");
      pw.println("<body>");
      pw.println("<center><pre><b><i>Invalid name </i></b></pre></center>");
      pw.println("</body></html>");
       
      
              count = 1;
       }
     
       if(passportNum.length()!=9){
           
      pw.println("<html>");
      pw.println("<head><title>Hello World</title></title>");
      pw.println("<body>");
      pw.println("<center><pre><b><i>Invalid  passport Number </i></b></pre></center>");
      pw.println("</body></html>");
       
    count = 1;
       
       }
           
    
        if (count ==1) 
        {   
           request.getRequestDispatcher("Signup.html").include(request, response);
        }
      if(count==0){
      
      request.getRequestDispatcher("UserDB_Checker.jsp").include(request, response);
     
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
