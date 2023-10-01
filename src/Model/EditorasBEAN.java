package Model;


public class EditorasBEAN {
	private int idEditora;
	private String razaoSocial;
	private String status;

	public EditorasBEAN(int idEditora, String razaoSocial, String status) {
		this.idEditora = idEditora;
		this.razaoSocial = razaoSocial;
		this.status = status;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public int getId() {
		return idEditora;
	}
}