import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "12345";
    private static final String NAME_PATTERN = "^[A-Z][a-zA-Z]{2,}$"; // Starts with a capital letter, min 3 chars

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (!Pattern.matches(NAME_PATTERN, user)) {
            out.println("<font color=red>Invalid name! Name must start with a capital letter and be at least 3 characters long.</font>");
            RequestDispatcher rd = request.getRequestDispatcher("login.html");
            rd.include(request, response);
            return;
        }

        if (USERNAME.equals(user) && PASSWORD.equals(pwd)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        } else {
            out.println("<font color=red>Invalid username or password</font>");
            RequestDispatcher rd = request.getRequestDispatcher("login.html");
            rd.include(request, response);
        }
    }
}
