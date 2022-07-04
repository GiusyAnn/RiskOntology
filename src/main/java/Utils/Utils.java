package Utils;

import model.bean.*;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Property;

import java.util.ArrayList;

public class Utils {

    public static void main(String[] args) {

        String endpoint = "http://localhost:3030/Risk.ttl/query";
        String prefix = "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
                "PREFIX db: <http://dbpedia.org/>\n" +
                "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX foaf:   <http://xmlns.com/foaf/0.1/> \n";

        test4(endpoint, prefix);

    }

    public static void test2(String endpoint, String prefix){

        String q = prefix + "SELECT DISTINCT  ?sub ?abs\n" +
                "WHERE{\n" +
                "     ?sub rdfs:label \"Software_development_process\" .  \n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?sub rdfs:comment ?abs .\n" +
                "  }\n" +
                "}";


        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();

        ModSvilBean modello = new ModSvilBean();
        int i = 0;
        while (results.hasNext()) {
            QuerySolution qSolution = results.nextSolution();
            if (i == 4 ) {
                modello.setNome(qSolution.getResource("sub").getLocalName());
                modello.setDescrizione(qSolution.getLiteral("abs").getString());
                break;
            }
            else i++;
        }
        System.out.println(modello.toString());
    }

    private static void test1(String endpoint, String prefix) {
        String proj = "C03-UniFit";
        ProjectBean projectBean = new ProjectBean();

        String q = prefix + "SELECT DISTINCT ?individual ?association ?associated \n" +
                "WHERE {\n" +
                "{ \n" +
                "    ?individual rdf:type owl:NamedIndividual .\n" +
                "    ?individual rdf:type ?sub .\n" +
                "    ?sub rdfs:label \"Project\" .\n" +
                "    ?individual ?association ?associated . \n" +
                "    ?associated rdf:type owl:NamedIndividual . \n" +
                "    ?individual rdfs:label \"" + proj + "\"\n" +
                "} UNION {\n" +
                "    ?associated rdf:type owl:NamedIndividual .\n" +
                "    ?associated rdf:type ?sub .\n" +
                "    ?sub rdfs:label \"Project_team\" .\n" +
                "    ?associated ?association ?individual .\n" +
                "    ?association rdfs:label \"Work\" . \n" +
                "    ?associated rdf:type owl:NamedIndividual . \n" +
                "    ?individual rdfs:label \"" + proj + "\"\n" +
                "}  UNION {\n" +
                "    ?associated rdf:type owl:NamedIndividual .\n" +
                "    ?associated rdf:type ?sub .\n" +
                "    ?sub rdfs:label \"Project_manager\" .\n" +
                "    ?associated ?association ?individual . \n" +
                "    ?association rdfs:label \"Project_management\" .\n" +
                "    ?associated rdf:type owl:NamedIndividual . \n" +
                "    ?individual rdfs:label \"" + proj + "\"\n" +
                "} \n" +
                "}";

        Query query = QueryFactory.create(q);

        // Esecuzione della querye cattura dei risultati
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qexec.execSelect();

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
        System.out.println(projectBean.toString());
    }

    public static void test3 (String endpoint, String prefix){
        String q = prefix +"SELECT DISTINCT  ?individual ?abs\n" +
                "WHERE{\n" +
                "  ?individual rdf:type owl:NamedIndividual .\n" +
                "  ?individual rdf:type ?sub .\n" +
                "  ?sub rdfs:label \"Software_development_process\" . \n" +
                "   SERVICE <http://dbpedia.org/sparql> {     \n" +
                "     ?individual rdfs:comment ?abs \n" +
                "    FILTER(LANG(?abs) = 'it') \n" +
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
        System.out.println("STAMPIAMO L ARRAY");
        for(ModSvilBean m: models){
            if(m.getNome().equals("")) m.setNome("Scrum");
            System.out.println(m.toString());
        }
    }

    public static void test4(String endpoint, String prefix){
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
                "    FILTER(LANG(?abs) = 'it') \n" +
                "    FILTER(LANG(?absCause) = 'en') \n" +
                "    FILTER(LANG(?absPlan) = 'it') \n" +
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

        System.out.println(rischio.toString());
    }

}
