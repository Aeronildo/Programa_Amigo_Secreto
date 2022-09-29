package br.ufpb.dcx.amigosecreto;

public abstract class Mensagem {

    private String texto;
    private String emailRemetente;
    private boolean anonima;

    public Mensagem(String texto, String emailRemetente, boolean anonima){
        this.texto = texto;
        this.emailRemetente = emailRemetente;
        this. anonima = anonima;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setEmailRemetente(String emailRemetente) {
        this.emailRemetente = emailRemetente;
    }

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public String getTexto(){
        return texto;
    }

    public abstract String getTextoCompletoAExibir();

    public boolean ehAnonima(){
        return anonima;
    }
}
