package control;

import model.bean.ModSvilBean;
import model.bean.RiskBean;
import model.dao.RiskOntologyDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Permette di visualizzare il profilo dell'utente.
 * Inoltre gestisce la possibilità, da parte dell'utente, di modificare le informazioni del proprio profilo.
 * Nel caso l'utente fosse un amministratore, recupera anche importanti statistiche relative alla piattaforma web.
 */
@WebServlet("/Management")
public class ManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(ManagementServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RiskOntologyDAO dao = new RiskOntologyDAO();

        ArrayList<ModSvilBean> list = new ArrayList<>();
        list = dao.riskManegement();

        //Ritorno i valori nella JSP
        request.setAttribute("man", list);
        String url = response.encodeURL("Management.jsp");
        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
