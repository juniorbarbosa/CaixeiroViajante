package br.univel.trajeto;

import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BuscadorTrajetos {

    private final Cidade origem, destino;
    private final ControleRotas controleRotas;
    private final ObservableList<Trajeto> trajetosPossiveis = FXCollections.observableArrayList();

    public BuscadorTrajetos(ControleRotas controleRotas, Cidade origem, Cidade destino) {
        this.origem = origem;
        this.destino = destino;
        this.controleRotas = controleRotas;
    }

    public Trajeto buscaMelhorRota() {
        trajetosPossiveis.clear();
        buscaTrajetosPossiveis();
        if (trajetosPossiveis.isEmpty()) {
            return null;
        } else {
            Trajeto menorTrajeto = null;
            for (Trajeto trajeto : trajetosPossiveis) {
//                System.out.println("trajeto(" + trajeto.getDistancia() + "KM): " + trajeto);
                if (menorTrajeto == null) {
                    menorTrajeto = trajeto;
                } else {
                    if (trajeto.getDistancia() < menorTrajeto.getDistancia()) {
                        menorTrajeto = trajeto;
                    }
                }
            }
            return menorTrajeto;
        }
    }

    private void buscaTrajetosPossiveis() {
        List<LigacaoCidades> ligacoesOrigem = controleRotas.getLigacoesCidadesPorCidade(getOrigem());
        ligacoesOrigem.stream().forEach(ligacaoOrigem -> {
            procuraTrajetosDestino(getOrigem(), getDestino(), ligacaoOrigem);
        });
    }

    private TreeNodeTrajeto procuraTrajetosDestino(Cidade origemInicial, Cidade destinoFinal, LigacaoCidades ligacao) {
        return procuraTrajetosDestino(origemInicial, destinoFinal, ligacao, null);
    }

    private TreeNodeTrajeto procuraTrajetosDestino(Cidade origemInicial, Cidade destinoFinal, LigacaoCidades ligacao, TreeNodeTrajeto parentTreeNodeTrajeto) {
        TreeNodeTrajeto nodeTrajeto = new TreeNodeTrajeto(ligacao);
        if (parentTreeNodeTrajeto != null) {
            parentTreeNodeTrajeto.getChildNodes().add(nodeTrajeto);
        }
        if (ligacao.getDestino().equals(destinoFinal)) {
            registraTrajeto(nodeTrajeto);
        } else {
            List<LigacaoCidades> ligacoesDestino = controleRotas.getLigacoesCidadesPorCidade(ligacao.getDestino());
            ligacoesDestino.stream().forEach(ligacaoDestino -> {
                if (!ligacaoDestino.getDestino().equals(origemInicial) && !ligacaoDestino.getDestino().equals(ligacao.getOrigem())) {
                    procuraTrajetosDestino(origemInicial, destinoFinal, ligacaoDestino, nodeTrajeto);
                }
            });
        }
        return nodeTrajeto;
    }

    private void registraTrajeto(TreeNodeTrajeto nodeTrajeto) {
        List<TreeNode> caminho = nodeTrajeto.getAncestorsOrSelf();
        Collections.reverse(caminho);
        Trajeto trajeto = new Trajeto();
        caminho.stream().forEach(node -> {
            trajeto.getTreeNodeTrajetoList().add((TreeNodeTrajeto) node);
        });
        trajetosPossiveis.add(trajeto);
    }

    public Cidade getOrigem() {
        return origem;
    }

    public Cidade getDestino() {
        return destino;
    }

}
