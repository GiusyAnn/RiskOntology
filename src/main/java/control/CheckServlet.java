package control;

import model.bean.RiskBean;
import model.dao.RiskOntologyDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Permette di visualizzare il profilo dell'utente.
 * Inoltre gestisce la possibilità, da parte dell'utente, di modificare le informazioni del proprio profilo.
 * Nel caso l'utente fosse un amministratore, recupera anche importanti statistiche relative alla piattaforma web.
 */
@WebServlet("/Check")
public class CheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(CheckServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RiskOntologyDAO dao = new RiskOntologyDAO();
        String pred = request.getParameter("pred");
        String sub = request.getParameter("sub");
        String obj = request.getParameter("obj");

        Boolean check = dao.checkRisk(sub,obj,pred);
        String tmp = check.toString();

        request.setAttribute("sub", sub);
        request.setAttribute("obj", obj);
        request.setAttribute("pred", pred);
        request.setAttribute("result", tmp);

        String url = response.encodeURL("Check.jsp");
        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
