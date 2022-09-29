package br.ufpb.dcx.amigosecreto;

import java.util.List;

public class SistemaAmigoMapTest extends SistemaAmigo {
    public static void main (String[] args) {
        SistemaAmigoMapTest sistema = new SistemaAmigoMapTest();
        try {
            sistema.cadastraAmigo("Jose", "jose@dcx.ufpb.br");
            sistema.cadastraAmigo("Maria", "maria@dcx.ufpb.br");
            System.out.println("Cadastro realizados com sucesso");
        } catch (AmigoJaExisteException e) {
            System.out.println(e.getMessage());
        }
        try {
            sistema.configuraAmigoSecretoDe("jose@dcx.ufpb.br", "maria@dcx.ufpb.br");
            sistema.configuraAmigoSecretoDe("maria@dcx.ufpb.br", "jose@dcx.ufpb.br");
            System.out.println("Amigos configurados");
        } catch (AmigoInexistenteException e) {
            System.out.println(e.getMessage());
        }

        sistema.enviarMensagemParaAlguem("Bom dia", "maria@dcx.ufpb.br", "jose@dcx.ufpb.br", true);
        sistema.enviarMensagemParaTodos("ae galera", "maria@dcx.ufpb.br", true);

        List<Mensagem> mensagensAnonimas = sistema.pesquisaMensagensAnonimas();
        for (Mensagem m: mensagensAnonimas){
            System.out.println(m.getTextoCompletoAExibir());
        }

        try {
            String emailDoAmigoDeJose = sistema.pesquisaAmigoSecretoDe("jose@dcx.ufpb.br");
        } catch (AmigoInexistenteException | AmigoNaoSorteadoException e) {
            System.out.println(e.getMessage());

        }

    }

}
