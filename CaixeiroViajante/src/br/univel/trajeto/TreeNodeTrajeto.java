package br.univel.trajeto;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeTrajeto extends TreeNode {

    public List<TreeNodeTrajeto> treeNodesRotaValidos = new ArrayList<>();

    private LigacaoCidades ligacaoCidades;

    public TreeNodeTrajeto() {
        super();
    }

    public TreeNodeTrajeto(LigacaoCidades ligacaoCidades) {
        this.ligacaoCidades = ligacaoCidades;
    }

    public LigacaoCidades getLigacaoCidades() {
        return ligacaoCidades;
    }

    public void setLigacaoCidades(LigacaoCidades ligacaoCidades) {
        this.ligacaoCidades = ligacaoCidades;
    }

    public List<TreeNodeTrajeto> getTreeNodesRotaValidos() {
        return treeNodesRotaValidos;
    }
    
    @Override
    public String toString() {
        return "["+getLigacaoCidades().getOrigem().getNome() + " > " + getLigacaoCidades().getDestino().getNome()+"]("+ getLigacaoCidades().getDistancia() +")";
    }

}
