package br.univel.trajeto;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class TreeNode {

    private TreeNode parentNode = null;
    private final ObservableList<TreeNode> childNodes = FXCollections.observableArrayList();

    public TreeNode() {
        childNodes.addListener((ListChangeListener.Change<? extends TreeNode> c) -> {
            while (c.next()) {
                if (c.wasRemoved()) {
                    c.getRemoved().stream().forEach(node -> {
                        node.setParentNode(null);
                    });
                }
                if (c.wasAdded()) {
                    c.getAddedSubList().stream().forEach(node -> {
                        node.setParentNode(TreeNode.this);
                    });
                }
            }
        });
    }

    public ObservableList<TreeNode> getChildNodes() {
        return childNodes;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

    public TreeNode getRoot() {
        if (getParentNode() == null) {
            return this;
        } else {
            return this.getParentNode().getRoot();
        }
    }
    
    public boolean hasChildNodes() {
        return getChildNodes().isEmpty();
    }
    
    public List<TreeNode> getAncestorsOrSelf() {
        List<TreeNode> rtn = new ArrayList<>();
        if (getParentNode() == null) {
            rtn.add(this);
        } else {
            rtn.add(this);
            rtn.addAll(getParentNode().getAncestorsOrSelf());
        }
        return rtn;
    }
    
    @Override
    public String toString() {
        return (getParentNode() == null ? "" : getParentNode() + " > ") + "TreeNode";
    }

}
