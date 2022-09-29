package br.ufpb.dcx.amigosecreto;

import javax.swing.*;
import java.util.List;

public class TestaSistemaAmigoGUI {
    public static void main (String[] args){
        int maxMensagens = Integer.parseInt(JOptionPane.showInputDialog("Digite o número maximo de mensagens"));
        SistemaAmigo sistema = new SistemaAmigo();
        int maxAmigos = Integer.parseInt(JOptionPane.showInputDialog("Digite o número maximo de amigos"));

        for (int k = 0; k<maxAmigos; k++){
            String nome = JOptionPane.showInputDialog("Digite o nome da pessoa");
            String email = JOptionPane.showInputDialog("Digite o email da pessoa");
            try {
                sistema.cadastraAmigo(nome, email);
            } catch (AmigoJaExisteException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        for (int k = 0; k<maxAmigos; k++) {
            String emailDaPessoa = JOptionPane.showInputDialog("Digite o email da pessoa");
            String emailAnigoSecretoDaPessoa = JOptionPane.showInputDialog("Digite o email do amigo secreto da pessoa");
            try {
                sistema.configuraAmigoSecretoDe(emailDaPessoa, emailAnigoSecretoDaPessoa);
            } catch (AmigoInexistenteException e) {
                    throw new RuntimeException(e);
            }

        }

        String textoMensagem = JOptionPane.showInputDialog("Digite a mensagem");
        String remetente = JOptionPane.showInputDialog("Digite o email do remetente");
        String ehNaoEhAnonima = JOptionPane.showInputDialog("A mensagem é anonima? Sim(S) ou não(N)");
        Boolean isAnonimous = false;
        if (ehNaoEhAnonima.equals("S")){
            isAnonimous = true;
        }
        sistema.enviarMensagemParaTodos(textoMensagem,remetente, isAnonimous);


    }


}
