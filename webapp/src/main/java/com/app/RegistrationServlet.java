package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String dbUrl = "jdbc:postgresql://postgres-write-primary:5432/app_db";
    private final String dbUser = "app_user";
    private final String dbPassword = "secure_password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sql = "INSERT INTO users (name, age, sex, address) VALUES (?, ?, ?, ?)";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            out.println("<h3>Error: Java JDBC Driver Missing!</h3>");
            return;
        }

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, sex);
            statement.setString(4, address);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                out.println("<html><body>");
                out.println("<h2>🎉 Record Inserted Successfully via Entrypoint Initialized Cluster!</h2>");
                out.println("<p><a href='index.html'>← Register Another Profile</a></p>");
                out.println("</body></html>");
            }
        } catch (Exception e) {
            out.println("<h2>❌ Cluster write execution failure!</h2>");
            out.println("<p>System details: " + e.getMessage() + "</p>");
        }
    }
}
