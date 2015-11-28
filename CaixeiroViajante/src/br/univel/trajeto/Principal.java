package br.univel.trajeto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

public class Principal extends Application {

    private static final File arquivoBancoDados = new File("BancoDados.bin");
    private static final ControleRotas controleRotas = new ControleRotas(getBancoDados());

    @Override
    public void start(Stage primaryStage) {
        exibeMenu();
//        exibeLigacoes();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static void exibeLigacoes() {
        List<String> ligacoes = new ArrayList<>();
        controleRotas.getBancoDados().getLigacoesCidadesPorIds().keySet().forEach(chave -> {
            LigacaoCidades ligacao = controleRotas.getBancoDados().getLigacoesCidadesPorIds().get(chave);
            ligacoes.add("origem: " + ligacao.getOrigem().getNome() + ", destino: " + ligacao.getDestino().getNome() + ", distancia: " + ligacao.getDistancia());
        });
        ligacoes.stream().sorted().forEach(linha -> {
            System.out.println(linha);
        });

    }

    private static void exibeMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========================");
        System.out.println(" Informe a op��o desejada");
        System.out.println("==========================");
        System.out.println("[1] Cadastrar cidade");
        System.out.println("[2] Cadastrar liga��o entre cidades");
        System.out.println("[3] Percorrer");
        System.out.println("[9] Sair");
        try {
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    cadastrarCidade();
                    exibeMenu();
                    break;
                case 2:
                    cadastrarLigacaoCidades();
                    exibeMenu();
                    break;
                case 3:
                    trajeto();
                    exibeMenu();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Op��o inv�lida, Tente novamente...");
                    exibeMenu();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Op��o inv�lida, Tente novamente...");
            exibeMenu();
        }

    }

    private static void cadastrarCidade() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nome da cidade");
        String nome = scanner.nextLine();
        Cidade cidade = new Cidade();
        cidade.setNome(nome.toUpperCase());
        try {
            controleRotas.cadastraCidade(cidade);
            salvaBancoDados();
        } catch (Throwable th) {
            System.out.println("N�o foi poss�vel cadastrar a cidade");
            System.out.println("Motivo: " + th.getMessage());
        }
    }

    private static void cadastrarLigacaoCidades() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================================");
        controleRotas.getBancoDados().getCidades().stream().forEach(cidade -> {
            System.out.println("[" + cidade.getId() + "] " + cidade.getNome());
        });
        System.out.println("Informe o c�digo da cidade de origem");
        int idCidadeOrigem = scanner.nextInt();
        System.out.println("Informe o c�digo da cidade de destino");
        int idCidadeDestino = scanner.nextInt();
        System.out.println("Informe a dist�ncia em KM entre estas cidades");
        int distancia = scanner.nextInt();

        try {
            Cidade origem = controleRotas.getBancoDados().getCidades().get(idCidadeOrigem - 1);
            Cidade destino = controleRotas.getBancoDados().getCidades().get(idCidadeDestino - 1);
            controleRotas.cadastraLigacaoCidades(origem, destino, distancia);
            salvaBancoDados();
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("N�o foi poss�vel cadastrar liga��o entre as cidades");
            System.out.println("Motivo: c�digo inv�lido");
        } catch (Throwable th) {
            System.out.println("N�o foi poss�vel cadastrar liga��o entre as cidades");
            System.out.println("Motivo: " + th.getMessage());
        }
    }

    private static void trajeto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================================");
        controleRotas.getBancoDados().getCidades().stream().forEach(cidade -> {
            System.out.println("[" + cidade.getId() + "] " + cidade.getNome());
        });
        System.out.println("Informe o c�digo da cidade de origem");
        int idCidadeOrigem = scanner.nextInt();
        System.out.println("Informe o c�digo da cidade de destino");
        int idCidadeDestino = scanner.nextInt();

        try {
            Cidade origem = controleRotas.getBancoDados().getCidades().get(idCidadeOrigem - 1);
            Cidade destino = controleRotas.getBancoDados().getCidades().get(idCidadeDestino - 1);
            Trajeto rota = controleRotas.getMelhorRota(origem, destino);
            if (rota == null) {
                System.out.println("N�o foram encontradas rotas poss�veis para este trajeto");
            } else {
//                System.out.println("Melhor rota encontrada ("+ rota.getDistancia()+")");
//                System.out.println(rota);
                System.out.println("============== Melhor caminho poss�vel ========================");
                System.out.println(rota + " (" + rota.getDistancia() + " Km)");
                System.out.println("===============================================================");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("N�o foi poss�vel tra�ar a rota entre as cidades");
            System.out.println("Motivo: C�digo de cidade inv�lido");
        } catch (Throwable th) {
            System.out.println("N�o foi poss�vel tra�ar a rota entre as cidades");
            System.out.println("Motivo: " + th.getMessage());
            th.printStackTrace(System.err);
        }
    }

    private static BancoDados getBancoDados() {
        if (arquivoBancoDados.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoBancoDados));
                return (BancoDados) ois.readObject();
            } catch (Throwable th) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, th);
                return new BancoDados();
            }
        } else {
            return new BancoDados();
        }
    }

    private static void salvaBancoDados() {
        try {
            ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(arquivoBancoDados));
            ous.writeObject(controleRotas.getBancoDados());
        } catch (Throwable th) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, th);
        }
    }

}
