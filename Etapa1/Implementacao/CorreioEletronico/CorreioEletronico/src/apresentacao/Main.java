package apresentacao;

import dados.Email;
import dados.Usuario;
import negocio.Sistema;

import java.util.List;

public class Main {
	
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        
        System.out.println("=== TESTE - SISTEMA DE CORREIO ELETRÔNICO ===\n");
        
        System.out.println("--- Cadastrando usuários ---");
        if (sistema.cadastrarUsuario("Lucas Pereira", "lucas.pereira@edu.udesc.br", "senha123")) {
            System.out.println("Usuário Lucas cadastrado com sucesso!");
        }
        if (sistema.cadastrarUsuario("Gabriela Oliveira", "gabriela.oliveira@edu.udesc.br", "senha456")) {
            System.out.println("Usuária Gabriela cadastrada com sucesso!");
        }
        if (sistema.cadastrarUsuario("Rafael Lima", "rafael.lima@edu.udesc.br", "senha789")) {
            System.out.println("Usuário Rafael cadastrado com sucesso!");
        }
        
        if (!sistema.cadastrarUsuario("Outro Lucas", "lucas.pereira@edu.udesc.br", "senha1234!")) {
            System.out.println("Falha ao cadastrar: O E-mail já está em uso.");
        }
        System.out.println();
        
        System.out.println("--- Testando login ---");
        if (!sistema.realizarLogin("lucas.pereira@edu.udesc.br", "senhaErrada")) {
            System.out.println("Falha no login: E-mail ou senha incorretos!");
        }
        if (sistema.realizarLogin("lucas.pereira@edu.udesc.br", "senha123")) {
            System.out.println("Login realizado com sucesso! Bem-vindo, " + sistema.getUsuarioLogado().getNome());
        }
        System.out.println();
        
        System.out.println("--- Enviando e-mails ---");
        Usuario gabriela = sistema.buscarUsuarioPorEmail("gabriela.oliveira@edu.udesc.br");
        Usuario rafael = sistema.buscarUsuarioPorEmail("rafael.lima@edu.udesc.br");
        
        if (sistema.enviarEmail(gabriela, "Oi Gabi! Tudo bem por aí?")) {
            System.out.println("E-mail enviado para Gabriela!");
        }
        if (sistema.enviarEmail(rafael, "Oi Rafa! Pode me ajudar mais tarde?")) {
            System.out.println("E-mail enviado para Rafael!");
        }
        
        Usuario lucas = sistema.getUsuarioLogado();
        if (!sistema.enviarEmail(lucas, "Mensagem pra mim mesmo")) {
            System.out.println("Falha ao enviar: Você não pode enviar e-mails para si mesmo!");
        }
        System.out.println();
        
        System.out.println("--- Gabriela fazendo login ---");
        if (sistema.realizarLogin("gabriela.oliveira@edu.udesc.br", "senha456")) {
            System.out.println("Login realizado com sucesso! Bem-vinda, " + sistema.getUsuarioLogado().getNome());
        }
        System.out.println();
        
        System.out.println("--- E-mails recebidos por Gabriela ---");
        List<Email> emailsGabriela = sistema.listarEmailsRecebidos();
        if (emailsGabriela.isEmpty()) {
            System.out.println("Nenhum e-mail recebido.");
        } else {
            for (int i = 0; i < emailsGabriela.size(); i++) {
                Email emailAtual = emailsGabriela.get(i);
                System.out.println(emailAtual);
                System.out.println("Corpo: " + sistema.getCorpoDecifrado(emailAtual));
                System.out.println();
            }
        }
        
        System.out.println("--- Gabriela respondendo e-mail ---");
        if (!emailsGabriela.isEmpty()) {
            Email emailParaResponder = emailsGabriela.get(0);
            if (sistema.responderEmail(emailParaResponder, "Oi Lucas! Tudo bem sim, e você?")) {
                System.out.println("Resposta enviada com sucesso!");
            }
        }
        System.out.println();
        
        System.out.println("--- Lucas verificando e-mails ---");
        if (sistema.realizarLogin("lucas.pereira@edu.udesc.br", "senha123")) {
            System.out.println("Login realizado com sucesso!");
        }
        List<Email> emailsLucas = sistema.listarEmailsRecebidos();
        System.out.println("Lucas tem " + emailsLucas.size() + " e-mail(s)");
        for (int i = 0; i < emailsLucas.size(); i++) {
            Email emailAtual = emailsLucas.get(i);
            System.out.println(emailAtual);
            System.out.println("Corpo: " + sistema.getCorpoDecifrado(emailAtual));
            System.out.println();
        }
        
        System.out.println("--- Excluindo e-mail ---");
        if (!emailsLucas.isEmpty()) {
            if (sistema.excluirEmail(emailsLucas.get(0).getId())) {
                System.out.println("E-mail excluído com sucesso!");
            } else {
                System.out.println("Erro ao excluir o e-mail!");
            }
            System.out.println("E-mails restantes: " + sistema.listarEmailsRecebidos().size());
        }
        
        System.out.println("\n=== TESTE CONCLUÍDO ===");
    }
}
