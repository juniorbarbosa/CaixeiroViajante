package br.univel;

import java.util.HashMap;
import java.util.Scanner;

public class Principal {

	private static HashMap<Integer, String> mapaCidade = new HashMap<>();
	private static HashMap<Integer, Integer> mapaLigacaoCidades = new HashMap<>();
	private static HashMap<HashMap, Integer> distanciaCidades = new HashMap<>();

	public static void main(String[] args) {
		menu();
	}

	private static void menu() {
		int opcao;

		Scanner sc = new Scanner(System.in);

		System.out.println("Menu principal");
		System.out.println("1 - Lista de Cidades");
		System.out.println("2 - Liga��o e dist�ncia");
		System.out.println("3 - Percorrer");
		System.out.println("9 - Sair");
		System.out.println("Digite sua op��o: ");

		opcao = sc.nextInt();

		switch (opcao) {
		case 1:
			cadastrarCidades();
			menu();
		case 2:
			ligacaoDistancia();
			menu();
		case 3:
			trajeto();
			menu();
		case 9:
			System.exit(0);
		default:
			System.out.println("Op��o inv�lida, Tente novamente...");
			menu();
			break;
		}
	}

	private static void cadastrarCidades() {
		Scanner sc = new Scanner(System.in);

		int idCidade = sc.nextInt();
		String nomeCidade = sc.nextLine().trim();

		mapaCidade.put(idCidade, nomeCidade);

	}

	private static void ligacaoDistancia() {

		System.out.println(mapaCidade);

		Scanner sc = new Scanner(System.in);

		Integer idCidadeOrigem = sc.nextInt();
		Integer idCidadeDestino = sc.nextInt();
		Integer distanciaEntreCidades = sc.nextInt();

		if (!idCidadeOrigem.equals(idCidadeDestino)) {
			mapaLigacaoCidades.put(idCidadeOrigem, idCidadeDestino);
			distanciaCidades.put(mapaLigacaoCidades, distanciaEntreCidades);
		}

		System.out.println(distanciaCidades);

	}

	private static void trajeto() {

	}

}
