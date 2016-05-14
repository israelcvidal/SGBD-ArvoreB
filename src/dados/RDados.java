package dados;

public class RDados {
	private String vCod, produtor, cepa, pais;
	private int colheita;
	
	private static RDados instance = new RDados();
	
	private RDados() {
		
	}
	
	public static RDados getInstance() {
		return instance;
	}
	
	public String getvCod() {
		return vCod;
	}
	public void setvCod(String vCod) {
		this.vCod = vCod;
	}
	public String getProdutor() {
		return produtor;
	}
	public void setProdutor(String produtor) {
		this.produtor = produtor;
	}
	public String getCepa() {
		return cepa;
	}
	public void setCepa(String cepa) {
		this.cepa = cepa;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getColheita() {
		return colheita;
	}
	public void setColheita(int colheita) {
		this.colheita = colheita;
	}
	
}
