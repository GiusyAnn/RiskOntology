package model.bean;

public class RiskBean {
    private String nome;
    private String causa;
    private String prevenzione;
    private String mitigazione;
    private String trigger;

    @Override
    public String toString() {
        return "Rischio{" +
                "nome='" + nome + '\'' +
                ", causa='" + causa + '\'' +
                ", trigger='" + trigger + '\'' +
                ", prevenzione=" + prevenzione +
                ", mitigazione='" + mitigazione + '\'' +
                '}';
    }


    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getPrevenzione() {
        return prevenzione;
    }

    public void setPrevenzione(String prevenzione) {this.prevenzione = prevenzione; }

    public String getMitigazione() {
        return mitigazione;
    }

    public void setMitigazione(String mitigazione) {
        this.mitigazione = mitigazione;
    }

}