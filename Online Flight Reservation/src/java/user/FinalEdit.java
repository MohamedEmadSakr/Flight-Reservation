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
@WebServlet(name = "FinalEdit", urlPatterns = {"/FinalEdit"})
public class FinalEdit extends HttpServlet {

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
            out.println("<title>Servlet FinalEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FinalEdit at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        String flightN=request.getParameter("flightN");
        String type=request.getParameter("type");
        int EseatsN=Integer.parseInt(request.getParameter("reservedseats"));
        String Cust=request.getParameter("Cusnames");
        PrintWriter out=response.getWriter();
        Connection con=null;
        int seatsN=0;
        int OseatsN=0;
        int max=0;
     
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/FlightApplication","m","m");
            String str="select * from flights where flightnumber='"+flightN+"'";
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(str);
            while(rs.next())
            {
                seatsN=rs.getInt("Reservedseats");
                max=rs.getInt("maxseats");
            }
            String str2="select * from usersflight where id="+id+"";
            Statement stmt2=con.createStatement();
            ResultSet rs2=stmt2.executeQuery(str2);
            while(rs2.next())
            {
                OseatsN=rs2.getInt("Reservedseats");
            }
           
        } catch (Exception e) {
        }
        
        if(id==0 || Cust.equals("") ||EseatsN==0)
        {
            out.print("Fill Your Data");
            request.getRequestDispatcher("EditData.jsp").include(request, response);
        }
        if(EseatsN>OseatsN)
        {
            int num=EseatsN-OseatsN;
            int temp=max;
         
            seatsN=seatsN+num;
           
        }
        
        
        if(EseatsN>OseatsN)
        {
            int num=EseatsN-OseatsN;
            seatsN=seatsN+num;
             if(seatsN>max)
            {
                out.print("Edited reserved seats exceeds max seats");
                response.setContentType("text/html");
                request.getRequestDispatcher("EditData.jsp").include(request, response);
            }
             else{
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                con=DriverManager.getConnection("jdbc:derby://localhost:1527/FlightApplication","m","m");
                String str="update usersflight set reservedseats="+EseatsN+" ,customersnames='"+Cust+"' ,classtype='"+type+"' where id="+id+"";
                
                Statement stmt=con.createStatement();
                stmt.executeUpdate(str);
                String str2="update flights set reservedseats="+seatsN+" where flightnumber='"+flightN+"'";
                Statement stmt2=con.createStatement();
                stmt2.executeUpdate(str2);
                 
                out.print("Data Changed");
                response.setContentType("text/html");
            request.getRequestDispatcher("ViewData.jsp").forward(request, response);
            } catch (Exception e) {
            }
             }
        }
        
        
        if(OseatsN>EseatsN)
        {
            int num=EseatsN-OseatsN;
            seatsN=seatsN-num;
             try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                con=DriverManager.getConnection("jdbc:derby://localhost:1527/FlightApplication","m","m");
                String str="update usersflight set reservedseats="+EseatsN+" ,customersnames='"+Cust+"' ,classtype='"+type+"' where id="+id+"";
                
                Statement stmt=con.createStatement();
                stmt.executeUpdate(str);
                String str2="update flights set reservedseats="+seatsN+" where flightnumber='"+flightN+"'";
                Statement stmt2=con.createStatement();
                stmt2.executeUpdate(str2);
               response.setContentType("text/html");
                out.print("Data Changed");
            request.getRequestDispatcher("ViewData.jsp").include(request, response);
            } catch (Exception e) {
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
