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
    private static final String PASSWORD = "Admin@123";  // Must follow validation rules

    private static final String NAME_PATTERN = "^[A-Z][a-zA-Z]{2,}$"; // Name starts with capital & min 3 chars
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=|<>?{}\\[\\]-]).{8,}$";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Validate Name
        if (!Pattern.matches(NAME_PATTERN, user)) {
            out.println("<font color=red>Invalid name! Name must start with a capital letter and be at least 3 characters long.</font>");
            RequestDispatcher rd = request.getRequestDispatcher("login.html");
            rd.include(request, response);
            return;
        }

        // Validate Password
        if (!Pattern.matches(PASSWORD_PATTERN, pwd)) {
            out.println("<font color=red>Invalid password! Password must have:</font><br>");
            out.println("<font color=red>- Minimum 8 characters</font><br>");
            out.println("<font color=red>- At least 1 uppercase letter</font><br>");
            out.println("<font color=red>- At least 1 numeric digit</font><br>");
            out.println("<font color=red>- Exactly 1 special character</font><br>");
            RequestDispatcher rd = request.getRequestDispatcher("login.html");
            rd.include(request, response);
            return;
        }

        // Check credentials
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
