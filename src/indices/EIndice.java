package indices;

import java.io.IOException;

import utilitarios.Arquivo;

public class EIndice {
	private int chave, esq, dir;
	public static final EIndice raiz = lerRaiz();
	private static EIndice instance = null;
	
	private EIndice() {
		
	}
	
	private static EIndice lerRaiz() {
		try {
			Arquivo.lerEntrada(0);
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return instance;
	}
	
	public static void setNull() {
		instance = null;
	}
	
	public static EIndice getInstance() {
		return instance;
	}
	
	public static EIndice createInstance() {
		EDados.setNull();
		instance = new EIndice();
		return instance;
	}
	
	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}
	
	public int getEsq() {
		return esq;
	}

	public void setEsq(int esq) {
		this.esq = esq;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
	
}
