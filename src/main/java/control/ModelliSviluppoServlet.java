package control;

import model.*;
import model.bean.ModSvilBean;
import model.dao.RiskOntologyDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Permette di visualizzare il profilo dell'utente.
 * Inoltre gestisce la possibilità, da parte dell'utente, di modificare le informazioni del proprio profilo.
 * Nel caso l'utente fosse un amministratore, recupera anche importanti statistiche relative alla piattaforma web.
 */
@WebServlet("/ModelliSviluppo")
public class ModelliSviluppoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(ModelliSviluppoServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RiskOntologyDAO dao = new RiskOntologyDAO();
        ArrayList<ModSvilBean> modelli = new ArrayList<>();
        ArrayList<ModSvilBean> dev = new ArrayList<>();


        dev=dao.getSoftwareDevelopment();
        modelli=dao.getAllModelliSviluppo();

        //Ritorno i valori nella JSP
        request.setAttribute("modello", dev);
        request.setAttribute("modelli", modelli);
        String url = response.encodeURL("ModSviluppo.jsp");
        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
