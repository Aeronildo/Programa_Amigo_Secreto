package br.ufpb.dcx.amigosecreto;

import java.util.*;

public class SistemaAmigoMap {

    private List<Mensagem> mensagens;
    private Map<String, Amigo> amigos;

    public SistemaAmigoMap(){
        this.mensagens = new ArrayList<>();
        this.amigos = new HashMap<>();
    }


    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException {
        if (amigos.containsKey(emailAmigo)){
            throw new AmigoJaExisteException("Este amigo" +emailAmigo+ "ja existe");
        }
        Amigo amigoNovo = new Amigo(nomeAmigo,emailAmigo);
        amigos.put(emailAmigo, new Amigo(nomeAmigo, emailAmigo));
    }


    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException{
        for (Amigo a: this.amigos.values()) {
            if (a.getEmail().equals(emailAmigo)){
                return a;
            }

        }
        throw new AmigoInexistenteException("Amigo de email" +emailAmigo+ "nao encontrado");
    }


    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima) {
        MensagemParaTodos msg = new MensagemParaTodos(texto, emailRemetente, ehAnonima);
        this.mensagens.add(msg);
    }

    public void enviarMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean ehAnonima) {
        MensagemParaAlguem msg = new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, ehAnonima);
        this.mensagens.add(msg);
    }

    public List<Mensagem> pesquisaTodasAsMensagens() {
        return this.mensagens;
    }

    //Qunado um método retorna uma lista é interessante criar uma lista temporária
    public List<Mensagem> pesquisaMensagensAnonimas() {
        List<Mensagem> msgAnon = new ArrayList<>();
        for(Mensagem msg: this.mensagens){
            if (msg.ehAnonima()){
                msgAnon.add(msg);
            }
        }
        return msgAnon;
    }

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws  AmigoInexistenteException{
        for (Amigo a: this.amigos.values()){
            if (a.getEmail().equals(emailDaPessoa)){
                a.setEmailAmigoSorteado(emailAmigoSorteado);
                return; //embora o método seja void pode-se dar um return
            }
        }
        throw new AmigoInexistenteException("Nãom existe essa pessoa com email:" +emailDaPessoa);
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException {
        for (Amigo a: this.amigos.values()){
            if (a.getEmail().equals(emailDaPessoa)){
                String emailAmigoSorteado = a.getEmailAmigoSorteado();
                if (emailAmigoSorteado==null) {
                    throw new AmigoNaoSorteadoException("O amigo secreto de pessoa de email:" + emailDaPessoa + "ainda não foi sorterado");
                }else{
                    return emailAmigoSorteado;
                }
            }
        }
        throw new AmigoInexistenteException(emailDaPessoa+ "não encontrado");
    }

}
