package dados;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Email {
	
	private int id;
	private String corpoCifrado;
	private LocalDateTime dataHoraEnvio;
	private Usuario remetente;
	private Usuario destinatario;

	public Email(int id, String corpoCifrado, LocalDateTime dataHoraEnvio, Usuario remetente, Usuario destinatario) {
		this.id = id;
		this.corpoCifrado = corpoCifrado;
		this.dataHoraEnvio = dataHoraEnvio;
		this.remetente = remetente;
		this.destinatario = destinatario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorpoCifrado() {
		return corpoCifrado;
	}

	public void setCorpoCifrado(String corpoCifrado) {
		this.corpoCifrado = corpoCifrado;
	}

	public LocalDateTime getDataHoraEnvio() {
		return dataHoraEnvio;
	}

	public void setDataHoraEnvio(LocalDateTime dataHoraEnvio) {
		this.dataHoraEnvio = dataHoraEnvio;
	}

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return "Email #" + id + "\nDe: " + remetente.getNome() + " (" + remetente.getEmail() + ")" + "\nPara: "
				+ destinatario.getNome() + " (" + destinatario.getEmail() + ")" + "\nData: "
				+ dataHoraEnvio.format(formatter);
	}
}