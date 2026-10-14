package negocio;

import dados.Email;
import dados.Usuario;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Usuario> usuarios;
    private List<Email> emails;
    private Usuario usuarioLogado;
    private int proximoIdEmail;

    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.usuarioLogado = null;
        this.proximoIdEmail = 1;
    }

    public boolean cadastrarUsuario(String nome, String email, String senha) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuarioExistente = usuarios.get(i);
            if (usuarioExistente.getEmail().toLowerCase().equals(email.toLowerCase())) {
                return false;
            }
        }
        Usuario novoUsuario = new Usuario(nome, email, senha);
        usuarios.add(novoUsuario);
        return true;
    }

    public boolean realizarLogin(String email, String senha) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuarioAtual = usuarios.get(i);
            if (usuarioAtual.getEmail().toLowerCase().equals(email.toLowerCase()) && usuarioAtual.getSenha().equals(senha)) {
                usuarioLogado = usuarioAtual;
                return true;
            }
        }
        return false;
    }

    public boolean enviarEmail(Usuario destinatario, String corpo) {
        if (usuarioLogado == null) {
            return false;
        }

        if (usuarioLogado.equals(destinatario)) {
            return false;
        }

        LocalDateTime agora = LocalDateTime.now();
        int deslocamento = calcularDeslocamento(proximoIdEmail, agora);
        String corpoCifrado = CifraDeCesar.cifrar(corpo, deslocamento);

        Email novoEmail = new Email(proximoIdEmail, corpoCifrado, agora, usuarioLogado, destinatario);
        emails.add(novoEmail);
        proximoIdEmail++;

        return true;
    }

    public boolean responderEmail(Email emailOriginal, String corpo) {
        if (usuarioLogado == null) {
            return false;
        }
        return enviarEmail(emailOriginal.getRemetente(), corpo);
    }

    public List<Email> listarEmailsRecebidos() {
        if (usuarioLogado == null) {
            return new ArrayList<>();
        }

        List<Email> emailsRecebidos = new ArrayList<>();
        for (int i = 0; i < emails.size(); i++) {
            Email emailAtual = emails.get(i);
            if (emailAtual.getDestinatario().equals(usuarioLogado)) {
                emailsRecebidos.add(emailAtual);
            }
        }
        return emailsRecebidos;
    }

    public boolean excluirEmail(int id) {
        if (usuarioLogado == null) {
            return false;
        }

        for (int i = 0; i < emails.size(); i++) {
            Email emailAtual = emails.get(i);
            if (emailAtual.getId() == id && emailAtual.getDestinatario().equals(usuarioLogado)) {
                emails.remove(i);
                return true;
            }
        }
        return false;
    }

    public String getCorpoDecifrado(Email email) {
        int deslocamento = calcularDeslocamento(email.getId(), email.getDataHoraEnvio());
        return CifraDeCesar.decifrar(email.getCorpoCifrado(), deslocamento);
    }

    private int calcularDeslocamento(int id, LocalDateTime dataHora) {
        int unidadeId = id % 10;
        int dia = dataHora.getDayOfMonth();
        return unidadeId + dia;
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuarioAtual = usuarios.get(i);
            if (usuarioAtual.getEmail().toLowerCase().equals(email.toLowerCase())) {
                return usuarioAtual;
            }
        }
        return null;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}