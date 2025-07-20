import jakarta.servlet.http.HttpServlet;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Don’t create if it doesn't exist
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp"); // Go back to login page
    }
}
