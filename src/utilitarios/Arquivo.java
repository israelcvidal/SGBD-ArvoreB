package utilitarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import dados.RDados;
import indices.EDados;
import indices.EIndice;

public class Arquivo {

	public static void excluirArquivo() throws IOException {
		new File("/Users/israelcvidal/Documents/workspace/Dados").delete();
		new File("/Users/israelcvidal/Documents/workspace/Indice").delete();
	}
	
	public static void gerarArquivoDados() throws IOException {
		String[] linha = new String[13];
		File file = new File("/Users/israelcvidal/Documents/workspace/Dados");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		
				  //VCod	Prod	Cepa	Colh	Pais
		linha[0] = "V0\tLeyda\tChardonnay\t2008\tChile";
		linha[1] = "V1\tViña Cobos\tChardonnay\t2008\tArgentina";
		linha[2] = "V2\tBaron Philippe de Rothschild\tSauvignon Blanc\t2009\tChile";
		linha[3] = "V3\tVillaggio Grando Boutique Winery\tPinot Noir\t2010\tBrasil";
		linha[4] = "V4\tMiolo\tPinot Noir\t2005\tBrasil";
		linha[5] = "V5\tIsla Negra\tCabernet Sauvignon\t2007\tChile";
		linha[6] = "V6\tCatena Zapata\tCabernet Sauvignon\t2006\tArgentina";
		linha[7] = "V7\tPulenta Estate\tMalbec\t2008\tArgentina";
		linha[8] = "V8\tBodega Monteviejo\tMalbec\t2007\tArgentina";
		linha[9] = "V9\tConcha y Toro\tMerlot\t2007\tChile";
		linha[10] = "V10\tSanta Rita\tSauvignon Blanc\t2008\tChile";
		linha[11] = "V11\tDal Pizzol\tCabernet Sauvignon\t2011\tBrasil";
		linha[12] = "V12\tSanta Augusta\tCabernet Franc\t2012\tBrasil";
		
		for(String l : linha) {
			bw.write(l);
			bw.newLine();
		}
		
		bw.close();
		fw.close();
		
	}
	
	public static void gerarArquivoIndice() throws IOException {
		String[] linha = new String[15];
		File file = new File("/Users/israelcvidal/Documents/workspace/Indice");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		
		//Tipo	chave	esq	dir
		linha[0] = "EInd\t2009\t1\t2";
		linha[1] = "EInd\t2007\t3\t4";
		linha[2] = "EInd\t2011\t5\t6";
		linha[3] = "EInd\t2006\t7\t8";
		linha[4] = "EInd\t2008\t9\t10";
		linha[5] = "EInd\t2010\t11\t12";
		linha[6] = "EInd\t2012\t13\t14";
		
		//Tipo	chave	listaDeLinhas
		linha[7] = "EDad\t2005\t4";
		linha[8] = "EDad\t2006\t6";
		linha[9] = "EDad\t2007\t5\t8\t9";
		linha[10] = "EDad\t2008\t0\t1\t7\t10";
		linha[11] = "EDad\t2009\t2";
		linha[12] = "EDad\t2010\t3";
		linha[13] = "EDad\t2011\t11";
		linha[14] = "EDad\t2012\t12";
		
		for(String l : linha) {
			bw.write(l);
			bw.newLine();
		}
		
		bw.close();
		fw.close();
		
	}
	
	public static void lerRegistro(int linha) throws IOException {
		RDados rd = RDados.getInstance();
		
		FileReader fr = new FileReader("/Users/israelcvidal/Documents/workspace/Dados");
		BufferedReader br = new BufferedReader(fr);
		
		for(int i = 0; i < linha; i++)
			br.readLine();
		
		String s = br.readLine();
		
		StringTokenizer st = new StringTokenizer(s, "\t");
		
		rd.setvCod(st.nextToken());
		rd.setProdutor(st.nextToken());
		rd.setCepa(st.nextToken());
		rd.setColheita(Integer.parseInt(st.nextToken()));
		rd.setPais(st.nextToken());
		
		br.close();
		fr.close();
		
	}
	
	public static void lerEntrada(int linha) throws IOException {
		FileReader fr = new FileReader("/Users/israelcvidal/Documents/workspace/Indice");
		BufferedReader br = new BufferedReader(fr);
		
		for(int i = 0; i < linha; i++)
			br.readLine();
		
		String s = br.readLine();
		
		StringTokenizer st = new StringTokenizer(s, "\t");
		
		String str = st.nextToken(); 
		
		if(str.equals("EInd")) {
			EIndice.createInstance();
			EIndice.getInstance().setChave(Integer.parseInt(st.nextToken()));
			EIndice.getInstance().setEsq(Integer.parseInt(st.nextToken()));
			EIndice.getInstance().setDir(Integer.parseInt(st.nextToken()));
			
		}
		else {
			EDados.createInstance();
			
			EDados.getInstance().setChave(Integer.parseInt(st.nextToken()));
			
			ArrayList<Integer> array = new ArrayList<Integer>();
			
			while(st.hasMoreTokens()) {
				array.add(Integer.parseInt(st.nextToken()));
			}
			
			EDados.getInstance().setDados(array);
			
		}
		
		br.close();
		fr.close();
		
	}
	
	public static List<Integer> buscar(int chave) throws IOException {
		if(chave < EIndice.raiz.getChave()) {
			Arquivo.lerEntrada(EIndice.raiz.getEsq());
		}
		else {
			Arquivo.lerEntrada(EIndice.raiz.getDir());
		}
		
		while(EDados.getInstance() == null) {
			if(chave < EIndice.getInstance().getChave()) {
				Arquivo.lerEntrada(EIndice.getInstance().getEsq());
			}
			else {
				Arquivo.lerEntrada(EIndice.getInstance().getDir());
			}
		}
		
		if(chave == EDados.getInstance().getChave()) {
			return EDados.getInstance().getDados();
		}
		else {
			return null;
		}
		
	}
	
}
