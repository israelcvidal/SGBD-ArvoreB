package indices;

import java.util.List;

public class EDados{
	private int chave;
	private List<Integer> dados;
	
	private static EDados instance = null;
	
	private EDados() {
		
	}
	
	public static void setNull() {
		instance = null;
	}
	
	public static EDados getInstance() {
		return instance;
	}
	
	public static EDados createInstance() {
		EIndice.setNull();
		instance = new EDados();
		return instance;
	}
	
	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}
	
	public List<Integer> getDados() {
		return dados;
	}

	public void setDados(List<Integer> dados) {
		this.dados = dados;
	}

}
