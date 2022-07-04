package model.bean;

import java.util.ArrayList;

public class ProjectBean {
    private static String nome;
    private static String modSviluppo;
    private static String software;
    private static String pm;
    private static String tm;
    private static String description;
    private static ArrayList<String> document;

    @Override
    public String toString() {
        return "Progetto{" +
                "Nome ='" + nome + '\'' +
                ", Modello di Sviluppo ='" + modSviluppo + '\'' +
                ", Software ='" + software + '\'' +
                ", Description = " + description + '\'' +
                ", Projct Manager =" + pm +
                ", Project Team ='" + tm + '\'' +
                ", Documenti ='" + document + '\'' +
                '}';
    }

    public static String getModSviluppo() {
        return modSviluppo;
    }

    public void setModSviluppo(String modSviluppo) {
        this.modSviluppo = modSviluppo;
    }

    public static String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public static String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static String getDescriprion() {
        return description;
    }

    public void setDescriprion(String descriprion) { this.description = descriprion;
    }

    public static String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public static String getTm() {
        return tm;
    }

    public void setTm(String tm) {this.tm = tm; }

    public static ArrayList<String> getDocument() { return document; }

    public void setDocument(ArrayList<String> document) { this.document = document;    }

}