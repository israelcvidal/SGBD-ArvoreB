import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import dados.RDados;
import utilitarios.Arquivo;

public class Main {
	private static Scanner scan;

	public static void main(String[] args) throws IOException {
		Arquivo.gerarArquivoDados();
		Arquivo.gerarArquivoIndice();

		int chave = 1;
		scan = new Scanner(System.in);
		System.out.println("Sistemas de Gerenciamento de Banco de Dados\nTrabalho: Arvore B+\n\nFrancisco Daniel B. de S. Praciano - 366389\nIsrael de Castro Vidal - 370019\n");
				
		while(chave != 0){
			System.out.println("Para encerrar o programa digite 0.\n");
			System.out.println("Digite o ANO dos vinhos que deseja buscar no Banco de Dados:");
			chave = scan.nextInt();
			
			List<Integer> list = Arquivo.buscar(chave);
			
			if(list == null){
				System.out.println("Nao existem vinhos deste ano no Banco de Dados!\n");
			}else{
				for(int i : list) {
					Arquivo.lerRegistro(i);
					System.out.println(RDados.getInstance().getvCod()+"\t"+RDados.getInstance().getProdutor()+"\t\t"+
							RDados.getInstance().getCepa()+"\t"+RDados.getInstance().getColheita()+"\t"+
							RDados.getInstance().getPais());
				}
				System.out.println();
			}
		}
			
		
		Arquivo.excluirArquivo();
	}
}
