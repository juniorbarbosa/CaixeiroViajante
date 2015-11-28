package br.univel.trajeto;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Trajeto {

    private final ObservableList<TreeNodeTrajeto> nodes = FXCollections.observableArrayList();
    private final ReadOnlyDoubleWrapper distancia = new ReadOnlyDoubleWrapper(0);

    public Trajeto() {
        nodes.addListener((ListChangeListener.Change<? extends TreeNodeTrajeto> c) -> {
            while (c.next()) {
                if (c.wasRemoved()) {
                    c.getRemoved().stream().forEach(node -> {
                        distancia.set(distancia.get() - node.getLigacaoCidades().getDistancia());
                    });
                }
                if (c.wasAdded()) {
                    c.getAddedSubList().stream().forEach(node -> {
                        distancia.set(distancia.get() + node.getLigacaoCidades().getDistancia());
                    });
                }
            }
        });
    }

    public ObservableList<TreeNodeTrajeto> getTreeNodeTrajetoList() {
        return nodes;
    }

    @Override
    public String toString() {
        StringBuilder sbRtn = new StringBuilder();
        getTreeNodeTrajetoList().stream().forEach(nodeTrajeto -> {
            if (sbRtn.length() == 0) {
                sbRtn.append(nodeTrajeto.getLigacaoCidades().getOrigem().getNome());
            }
            sbRtn.append(" > ");
            sbRtn.append(nodeTrajeto.getLigacaoCidades().getDestino().getNome());
        });
        return sbRtn.toString();
    }

    public double getDistancia() {
        return distancia.get();
    }

    public ReadOnlyDoubleProperty distanciaProperty() {
        return distancia.getReadOnlyProperty();
    }

}
