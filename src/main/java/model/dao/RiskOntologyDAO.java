package model.dao;

import control.DescriptionServlet;
import model.bean.*;
import org.apache.jena.query.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RiskOntologyDAO {
    private final String endpoint = "http://localhost:3030/Risk.ttl/query";
    private String prefix = "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
            "PREFIX db: <http://dbpedia.org/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>";

    public ProjectBean  returnProjects( String proj) {
        String q = prefix + "SELECT DISTINCT ?individual ?association ?associated \n" +
                "WHERE {\n" +
                "{ \n" +
                "    ?individual rdf:type owl:NamedIndividual .\n" +
                "    ?individual rdf:type ?sub .\n" +
                "    ?sub rdfs:label \"Project\" .\n" +
                "    ?individual ?association ?associated . \n" +
                "    ?associated rdf:type owl:NamedIndividual . \n" +
                "    ?individual rdfs:label \""+proj+"\"\n" +
                "} UNION {\n" +
                "    ?associated rdf:type owl:NamedIndividual .\n" +
                "    ?associated rdf:type ?sub .\n" +
                "    ?sub rdfs:label \"Project_team\" .\n" +
                "    ?associated ?association ?individual .\n" +
                "    ?association rdfs:label \"Work\" . \n" +
                "    ?associated rdf:type owl:NamedIndividual . \n" +
                "    ?individual rdfs:label \""+proj+"\"\n" +
                "}  UNION {\n" +
                "    ?associated rdf:type owl:NamedIndividual .\n" +
                "    ?associated rdf:type ?sub .\n" +
                "    ?sub rdfs:label \"Project_manager\" .\n" +
                "    ?associated ?association ?individual . \n" +
                "    ?association rdfs:label \"Project_management\" .\n" +
                "    ?associated rdf:type owl:NamedIndividual . \n" +
                "    ?individual rdfs:label \" "+proj+" \"\n" +
                "} \n" +
                "}";

        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();

        ProjectBean projectBean = new ProjectBean();
        while (results.hasNext()) {
            QuerySolution qSolution = results.nextSolution();

            if (ProjectBean.getNome() == null) {
                projectBean.setNome(qSolution.getResource("individual").getLocalName());
            }

            if (qSolution.getResource("association").getLocalName().equals("produce_documentation")) {
                if (projectBean.getDocument() == null) {
                    ArrayList<String> tmp = new ArrayList<>();
                    tmp.add(qSolution.getResource("associated").getLocalName());
                    projectBean.setDocument(tmp);
                } else {
                    projectBean.getDocument().add(qSolution.getResource("associated").getLocalName());
                }
            }

            if (qSolution.getResource("association").getLocalName().equals("use_to_be_deployed")) {
                projectBean.setModSviluppo(qSolution.getResource("associated").getLocalName());
            }

            if (qSolution.getResource("association").getLocalName().equals("produce_software")) {
                projectBean.setSoftware(qSolution.getResource("associated").getLocalName());
            }

            if (qSolution.getResource("association").getLocalName().equals("Work")) {
                projectBean.setTm(qSolution.getResource("associated").getLocalName());
            }

            if (qSolution.getResource("association").getLocalName().equals("Project_management")) {
                projectBean.setPm(qSolution.getResource("associated").getLocalName());
            }

        }

        qexec.close();
        return projectBean;
    }

    public ArrayList<ModSvilBean> getSoftwareDevelopment(){
        String q = prefix +"SELECT DISTINCT  ?sub ?abs\n" +
                "WHERE{\n" +
                "  {\n" +
                "  ?sub rdfs:label \"Software_development_process\" . \n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?sub rdfs:comment ?abs \n" +
                "    FILTER(LANG(?abs) = 'en') \n" +
                "    }} UNION {\n" +
                "  ?sub rdfs:label \"Software_development\" . \n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?sub rdfs:comment ?abs \n" +
                "    FILTER(LANG(?abs) = 'en') \n" +
                "    }\n" +
                "  }\n" +
                "}";


        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();

        ArrayList<ModSvilBean> mod = new ArrayList<>();

        while (results.hasNext()) {
            ModSvilBean modello = new ModSvilBean();
            QuerySolution qSolution = results.nextSolution();
            modello.setNome(qSolution.getResource("sub").getLocalName());
            modello.setDescrizione(qSolution.getLiteral("abs").getString());
            mod.add(modello);
        }
        return mod;
    }

    public ArrayList<ModSvilBean> getAllModelliSviluppo(){
        String q = prefix +"SELECT DISTINCT  ?individual ?abs\n" +
                "WHERE{\n" +
                "  ?individual rdf:type owl:NamedIndividual .\n" +
                "  ?individual rdf:type ?sub .\n" +
                "  ?sub rdfs:label \"Software_development_process\" . \n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?individual rdfs:comment ?abs \n" +
                "    FILTER(LANG(?abs) = 'en') \n" +
                "  }\n" +
                "}";


        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();


        ArrayList<ModSvilBean> models = new ArrayList<>();

        while (results.hasNext()) {
            ModSvilBean modello = new ModSvilBean();
            QuerySolution qSolution = results.nextSolution();
            modello.setNome(qSolution.getResource("individual").getLocalName());
            modello.setDescrizione(qSolution.getLiteral("abs").getString());
            models.add(modello);
        }
        return models;
    }

    public RiskBean RiskDescription(){
        String q = prefix + "SELECT DISTINCT ?abs ?absCause ?absPlan ?predes ?condes\n" +
                "WHERE {\n" +
                "  ?sub rdfs:label \"Risk\" . \n" +
                "  ?cause rdfs:label \"Root_cause\" .\n" +
                "  ?plan rdfs:label \"Plan\" .\n" +
                "  ?pre rdfs:label \"Prevention_Plan\" .\n" +
                "  ?con rdfs:label \"Contingency_Plan\" . \n" +
                "  ?pre dc:description ?predes .\n" +
                "  ?con dc:description ?condes .\n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "    ?sub rdfs:comment ?abs .  \n" +
                "    ?cause rdfs:comment ?absCause .\n" +
                "    ?plan rdfs:comment ?absPlan \n" +
                "    FILTER(LANG(?abs) = 'en') \n" +
                "    FILTER(LANG(?absCause) = 'en') \n" +
                "    FILTER(LANG(?absPlan) = 'en') \n" +
                "  }\n" +
                "}\n";


        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();

        RiskBean rischio = new RiskBean();

        while (results.hasNext()) {
            QuerySolution qSolution = results.nextSolution();
            rischio.setNome(qSolution.getLiteral("abs").getString());
            rischio.setCausa(qSolution.getLiteral("absCause").getString());
            rischio.setTrigger(qSolution.getLiteral("absPlan").getString());
            rischio.setPrevenzione(qSolution.getLiteral("predes").getString());
            rischio.setMitigazione(qSolution.getLiteral("condes").getString());
        }
        return rischio;
    }

    public ArrayList<RiskBean> getRiskTable(){
        String q = prefix +"SELECT DISTINCT ?individual ?cause ?des ?prevention ?con\n" +
                "WHERE { \n" +
                "    ?individual rdf:type owl:NamedIndividual .\n" +
                "    ?individual rdf:type ?sub .\n" +
                "    ?sub rdfs:label \"Risk\" .\n" +
                "    ?individual ?asscause ?cause .\n" +
                "    ?asscause rdfs:label \"cause\" .\n" +
                "    ?individual ?assdes ?des .\n" +
                "    ?assdes rdfs:label \"discovered\" . \n" +
                "    ?individual ?assprev ?prev .\n" +
                "    ?assprev rdfs:label \"prevention\" .\n" +
                "    ?prev dc:description ?prevention . \n" +
                "    ?individual ?assmit ?mit .\n" +
                "    ?assmit rdfs:label \"mitigazione\" .\n" +
                "    ?prev dc:description ?con . \n" +
                "}";


        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();


        ArrayList<RiskBean> models = new ArrayList<>();

        while (results.hasNext()) {
            RiskBean rischio = new RiskBean();
            QuerySolution qSolution = results.nextSolution();
            rischio.setNome(qSolution.getResource("individual").getLocalName());
            rischio.setCausa(qSolution.getResource("cause").getLocalName());
            rischio.setTrigger(qSolution.getResource("des").getLocalName());
            rischio.setPrevenzione(qSolution.getLiteral("prevention").getString());
            rischio.setMitigazione(qSolution.getLiteral("con").getString());
            models.add(rischio);
        }
        return models;
    }

    public ArrayList<ModSvilBean> riskManegement(){
        String q = prefix +"SELECT DISTINCT  ?sub ?abs\n" +
                "WHERE{\n" +
                "  {\n" +
                "  ?sub rdfs:label \"Risk_management\" . \n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?sub rdfs:comment ?abs \n" +
                "    FILTER(LANG(?abs) = 'en') \n" +
                "    }} UNION {\n" +
                "  ?sub rdfs:label \"Project_management\" . \n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?sub rdfs:comment ?abs \n" +
                "    FILTER(LANG(?abs) = 'en') \n" +
                "    }\n" +
                "  }\n" +
                "  UNION {\n" +
                "  ?sub rdfs:label \"Stakeholder_management\" . \n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?sub rdfs:comment ?abs \n" +
                "    FILTER(LANG(?abs) = 'en') \n" +
                "    }\n" +
                "  }\n" +
                "}";


        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();


        ArrayList<ModSvilBean> management = new ArrayList<>();
        String s=null;
        while (results.hasNext()) {
            ModSvilBean mod = new ModSvilBean();
            QuerySolution qSolution = results.nextSolution();
            mod.setNome(qSolution.getResource("sub").getLocalName());
            mod.setDescrizione(qSolution.getLiteral("abs").getString());
            management.add(mod);
        }
        return management;
    }

    public ModSvilBean getDescription(String value){
        String q = prefix + "SELECT DISTINCT  ?sub ?abs ?des\n" +
                "WHERE{\n" +
                "   ?sub rdfs:label \""+value+"\" . \n" +
                "  OPTIONAL{\n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?sub rdfs:comment ?abs \n" +
                "   FILTER(LANG(?abs) = 'en') \n" +
                "}} \n" +
                "  OPTIONAL{\n" +
                "    ?sub rdfs:comment ?abs\n" +
                "  }  \n" +
                "}";

        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();

        ModSvilBean des = new ModSvilBean();

        while (results.hasNext()) {
            QuerySolution qSolution = results.nextSolution();
            des.setNome(qSolution.getResource("sub").getLocalName());
            des.setDescrizione(qSolution.getLiteral("abs").getString());
        }
        return des;
    }

    public ArrayList<ModSvilBean> getArtifacts(){
        String q = prefix +"SELECT DISTINCT ?sub ?abs \n" +
                "WHERE \n" +
                "{\n" +
                "{   ?sub rdfs:label \"Artifact_(software_development)\" .\n" +
                "    SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?sub rdfs:comment ?abs \n" +
                "    FILTER(LANG(?abs) = 'en') \n" +
                "  }\n" +
                "\n" +
                "} UNION {\n" +
                "   ?sub rdfs:label \"Software_documentation\" .\n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?sub rdfs:comment ?abs \n" +
                "    FILTER(LANG(?abs) = 'en') \n" +
                "  } \n" +
                "}  UNION {\n" +
                "   ?sub rdfs:label \"Risk_management_plan\" .\n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?sub rdfs:comment ?abs \n" +
                "    FILTER(LANG(?abs) = 'en') \n" +
                "  } \n" +
                "}   UNION {\n" +
                "   ?sub rdfs:label \"Risk_register\" .\n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?sub rdfs:comment ?abs \n" +
                "    FILTER(LANG(?abs) = 'en') \n" +
                "  } \n" +
                "}    UNION {\n" +
                "   ?sub rdfs:label \"Software\" .\n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?sub rdfs:comment ?abs \n" +
                "    FILTER(LANG(?abs) = 'en') \n" +
                "  } \n" +
                "}\n" +
                "}";


        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();


        ArrayList<ModSvilBean> management = new ArrayList<>();
        String s=null;
        while (results.hasNext()) {
            ModSvilBean mod = new ModSvilBean();
            QuerySolution qSolution = results.nextSolution();
            mod.setNome(qSolution.getResource("sub").getLocalName());
            mod.setDescrizione(qSolution.getLiteral("abs").getString());
            management.add(mod);
        }
        return management;
    }

    public ArrayList<String> getProjectArtifacts(String project){
        String q = prefix +"SELECT DISTINCT ?ass \n" +
                "WHERE {\n" +
                "    ?individual rdf:type owl:NamedIndividual .\n" +
                "    ?individual rdf:type ?sub .\n" +
                "    ?sub rdfs:label \"Project\" .\n" +
                "    ?individual ?association ?ass . \n" +
                "    ?associated rdf:type owl:NamedIndividual . \n" +
                "    ?individual rdfs:label \""+project+"\" .\n" +
                "    ?association rdfs:label \"produce_documentation\"\n" +
                "}\n" +
                "\n";


        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();

        ArrayList<String> management = new ArrayList<>();
        String s=null;
        while (results.hasNext()) {
            String tmp;
            QuerySolution qSolution = results.nextSolution();
            tmp = (qSolution.getResource("ass").getLocalName());
            management.add(tmp);
        }
        return management;
    }

    public ProjectBean getProject(String value){
        String q = prefix + "SELECT DISTINCT ?soft ?softdesc ?tm ?dep\n" +
                "WHERE {\n" +
                "    ?individual ?association ?soft . \n" +
                "    ?associated rdf:type owl:NamedIndividual . \n" +
                "    ?individual rdfs:label \""+value+"\" .\n" +
                "    ?association rdfs:label \"produce_software\" .\n" +
                "    ?soft rdfs:comment ?softdesc .\n" +
                "    ?tm ?ass ?individual . \n" +
                "    ?ass rdfs:label \"Work\" . \n" +
                "    ?individual ?use ?dep .\n" +
                "    ?use rdfs:label \"use_to_be_development\" \n" +
                "}";

        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();

        ProjectBean des = new ProjectBean();

        while (results.hasNext()) {
            QuerySolution qSolution = results.nextSolution();
            des.setNome(value);
            des.setSoftware(qSolution.getResource("soft").getLocalName());
            des.setDescriprion(qSolution.getLiteral("softdesc").getString());
            des.setTm(qSolution.getResource("tm").getLocalName());
            des.setModSviluppo(qSolution.getResource("dep").getLocalName());
        }
        return des;
    }

    public ArrayList<ModSvilBean> getTm(String value, String team){
        String q = prefix +"SELECT DISTINCT ?tm ?abs\n" +
                "WHERE {\n" +
                "  {\n" +
                "    ?tm ?association ?individual .\n" +
                "    ?individual rdfs:label \""+value+"\" .\n" +
                "    ?association rdfs:label \"Project_management\" . \n" +
                "    ?class rdfs:label \"Project_manager\" . \n" +
                " SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?class rdfs:comment ?abs \n" +
                "    FILTER(LANG(?abs) = 'en') \n" +
                "  }\n" +
                "  } UNION { \n" +
                "    ?tm ?association ?individual .\n" +
                "    ?individual rdfs:label \""+team+"\" .\n" +
                "    ?association rdfs:label \"PlayerInTeam\" . \n" +
                "    ?class rdfs:label \"TeamMember\" . \n" +
                "    ?class rdfs:comment ?abs \n" +
                "  }\n" +
                "}";


        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();


        ArrayList<ModSvilBean> models = new ArrayList<>();

        while (results.hasNext()) {
            ModSvilBean modello = new ModSvilBean();
            QuerySolution qSolution = results.nextSolution();
            modello.setNome(qSolution.getResource("tm").getLocalName());
            modello.setDescrizione(qSolution.getLiteral("abs").getString());
            models.add(modello);
        }
        return models;
    }

    public ArrayList<RiskBean> getRiskRegister(String value){
        String q = prefix +"SELECT DISTINCT ?individual ?cause ?des ?prevention ?con\n" +
                "WHERE { \n" +
                "    ?associated ?association ?individual . \n" +
                "    ?associated rdfs:label \""+value+"\" .\n" +
                "    ?association rdfs:label \"Risk_Container\" .\n" +
                "    ?individual ?asscause ?cause .\n" +
                "    ?asscause rdfs:label \"cause\" .\n" +
                "    ?individual ?assdes ?des .\n" +
                "    ?assdes rdfs:label \"discovered\" . \n" +
                "    ?individual ?assprev ?prev .\n" +
                "    ?assprev rdfs:label \"prevention\" .\n" +
                "    ?prev dc:description ?prevention . \n" +
                "    ?individual ?assmit ?mit .\n" +
                "    ?assmit rdfs:label \"mitigazione\" .\n" +
                "    ?prev dc:description ?con . \n" +
                "}";


        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();


        ArrayList<RiskBean> models = new ArrayList<>();

        while (results.hasNext()) {
            RiskBean rischio = new RiskBean();
            QuerySolution qSolution = results.nextSolution();
            rischio.setNome(qSolution.getResource("individual").getLocalName());
            rischio.setCausa(qSolution.getResource("cause").getLocalName());
            rischio.setTrigger(qSolution.getResource("des").getLocalName());
            rischio.setPrevenzione(qSolution.getLiteral("prevention").getString());
            rischio.setMitigazione(qSolution.getLiteral("con").getString());
            models.add(rischio);
        }
        return models;
    }

    public boolean checkRisk(String sub, String obj, String pred) {

        String q = prefix + "ASK {\n" +
                "  ?individual ?ass ?object .\n" +
                "  ?individual rdfs:label \""+sub+"\" . \n" +
                "  ?ass rdfs:label \""+pred+"\" . \n" +
                "  ?object rdfs:label \""+obj+"\"\n" +
                "}  ";

        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        boolean result = qexec.execAsk();
        qexec.close();
        return result;
    }

}
