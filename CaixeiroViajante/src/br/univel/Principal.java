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
		System.out.println("9 - Sair");
		System.out.println("Digite sua opção: ");

		opcao = sc.nextInt();

		switch (opcao) {
		case 1:
			System.out.println("Lista de cidades");
			menu();
		case 2:
			System.out.println("Ligação e distância");
			menu();
		case 3:
			System.out.println("Percorrer");
			menu();
		case 9:
			System.exit(0);
		default:
			System.out.println("\nOPÇÃO INVALIDA, TENTE NOVAMENTE...\n");
			menu();
			break;
		}
	}

}
