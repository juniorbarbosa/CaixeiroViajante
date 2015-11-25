package br.univel;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		menu();
	}

	private static void menu() {
		int opcao;

		Scanner sc = new Scanner(System.in);

		System.out.println("Menu principal");
		System.out.println("1 - Lista de Cidades");
		System.out.println("2 - Ligação e distância");
		System.out.println("3 - Percorrer");
		System.out.println("9 - Sair");
		System.out.println("Digite sua opção: ");

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
			System.out.println("Opção inválida, Tente novamente...");
			menu();
			break;
		}
	}

	private static void cadastrarCidades() {
		System.out.println("Quero");
		System.out.println("Quero");
		System.out.println("nao quero");

	}

	private static void ligacaoDistancia() {

	}

	private static void trajeto() {

	}

}
