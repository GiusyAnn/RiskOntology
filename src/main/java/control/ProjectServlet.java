package control;

import model.bean.ModSvilBean;
import model.bean.ProjectBean;
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

@WebServlet("/Project")
public class ProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(ProjectServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = Logger.getLogger(ProjectServlet.class.getName());


        RiskOntologyDAO dao = new RiskOntologyDAO();
        RiskBean riskBean = dao.RiskDescription();
        String val = request.getParameter("value");


        ProjectBean project = new ProjectBean();
        project = dao.getProject(val);
        ArrayList<String> doc = dao.getProjectArtifacts(val);
        project.setDocument(doc);
        ArrayList<ModSvilBean> tmlist = new ArrayList<>();
        tmlist=dao.getTm(val, project.getTm());
        project.setPm(tmlist.get(0).getNome());

        ArrayList<RiskBean> rr = new ArrayList<>();
        rr = dao.getRiskRegister("RR_"+project.getSoftware());



        //Ritorno i valori nella JSP
        request.setAttribute("project", project);
        request.setAttribute("tm", tmlist);
        request.setAttribute("table", rr);
        String url = response.encodeURL("Project.jsp");
        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
