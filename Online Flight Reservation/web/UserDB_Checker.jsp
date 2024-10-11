

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      
    </head>
    <body>
        <jsp:useBean id="bean1" class="DataBase.User">
            <jsp:setProperty property="*" name="bean1"/>
        </jsp:useBean>
        <%
            int a=bean1.Signup();
            if(a==1)
            {
                
                out.println("<html>");
                out.println("<head><title>Hello World</title></title>");
                out.println("<body>");
                out.println("<center><pre><b><i>Account Created</i></b></pre></center>");
                out.println("</body></html>");
                request.getRequestDispatcher("index.html").include(request, response);
            }
            else
            {
                out.print("failed");
            }
            %>
        
    </body>
</html>
