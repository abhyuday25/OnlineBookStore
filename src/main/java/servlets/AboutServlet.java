package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.model.UserRole;
import com.bittercode.util.StoreUtil;

public class AboutServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        // If the store is logged in as customer or seller, show About info
        if (StoreUtil.isLoggedIn(UserRole.CUSTOMER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("CustomerHome.html");
            rd.include(req, res);
            StoreUtil.setActiveTab(pw, "about");

            printTeamInfo(pw);

        } else if (StoreUtil.isLoggedIn(UserRole.SELLER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("SellerHome.html");
            rd.include(req, res);
            StoreUtil.setActiveTab(pw, "about");

            printTeamInfo(pw);

        } else {
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.include(req, res);
            pw.println("<table class='tab'><tr><td>Please Login First to Continue!!</td></tr></table>");
        }
    }

    // A method to print the team section with modern CSS
    private void printTeamInfo(PrintWriter pw) {
        pw.println("<style>");
        pw.println("body { font-family: 'Poppins', sans-serif; background-image: linear-gradient(to top, #09203f 0%, #537895 100%); margin: 0; padding: 0; }");
        pw.println(".team-container { display: flex; flex-direction: column; align-items: center; justify-content: center; height: 80vh; }");
        pw.println(".team-card { background: rgba(255,255,255,0.25); backdrop-filter: blur(10px); border-radius: 16px; padding: 30px 50px; "
                + "box-shadow: 0 8px 32px rgba(0,0,0,0.15); text-align: center; width: 60%; max-width: 500px; transition: transform 0.3s ease, box-shadow 0.3s ease; }");
        pw.println(".team-card:hover { transform: scale(1.03); box-shadow: 0 12px 40px rgba(0,0,0,0.2); }");
        pw.println(".team-title { font-size: 28px; color: #2c3e50; margin-bottom: 20px; font-weight: 600; }");
        pw.println(".member { font-size: 18px; color: #1a1a1a; margin: 10px 0; font-weight: 500; }");
        pw.println(".member span { color: #7b1fa2; font-weight: 600; }");
        pw.println("</style>");

        pw.println("<div class='team-container'>");
        pw.println("<div class='team-card'>");
        pw.println("<h2 class='team-title'>Team Members</h2>");
        pw.println("<p class='member'><span>Abhyuday Sinha</span> (23MIC0036)</p>");
        pw.println("<p class='member'><span>Sammridhi Sharma</span> (23MIC0097)</p>");
        pw.println("</div>");
        pw.println("</div>");
    }
}