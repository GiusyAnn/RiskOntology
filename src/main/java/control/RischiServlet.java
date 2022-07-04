package control;

import model.bean.*;
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
@WebServlet("/Rischi")
public class RischiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(RischiServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RiskOntologyDAO dao = new RiskOntologyDAO();
        RiskBean riskBean = dao.RiskDescription();

        ArrayList<RiskBean> list = new ArrayList<>();
        list = dao.getRiskTable();

        //Ritorno i valori nella JSP
        request.setAttribute("descrizione", riskBean);
        request.setAttribute("table", list);

        String url = response.encodeURL("Rischi.jsp");
        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
