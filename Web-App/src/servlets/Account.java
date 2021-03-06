package servlets;

import Utils.Constants;
import Utils.EngineManager;
import Utils.Gsonparser;
import com.Engine.EngineInterface;
import com.Engine.Myexception;
import com.UserAccountDTO;
import com.UserDTO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "account", urlPatterns = {"/api/account"})
public class Account extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        EngineInterface engineInterface= EngineManager.getEngine(req.getServletContext());
        try {
            UserAccountDTO userAccountDTO=engineInterface.getUserAccount(req.getParameter(Constants.URL_USER_PARAM));
            Gson gson= Gsonparser.getParser(req.getServletContext());
            resp.getWriter().print(gson.toJson(userAccountDTO));
        } catch (Myexception myexception) {
            resp.setStatus(500);
            resp.getWriter().print(myexception.toString());
        }

    }
}
