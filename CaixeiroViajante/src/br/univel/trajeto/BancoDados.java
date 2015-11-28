package br.univel.trajeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BancoDados implements Serializable {

    private final List<Cidade> cidades = new ArrayList<>();
    private final HashMap<String, LigacaoCidades> ligacoesCidadesPorIds = new HashMap<>();
    private final HashMap<String, Cidade> cidadesPorNome = new HashMap<>();
    private final HashMap<Cidade, List<LigacaoCidades>> ligacoesCidadesPorCidade = new HashMap<>();

    public List<Cidade> getCidades() {
        return cidades;
    }

    public HashMap<String, LigacaoCidades> getLigacoesCidadesPorIds() {
        return ligacoesCidadesPorIds;
    }

    public HashMap<String, Cidade> getCidadesPorNome() {
        return cidadesPorNome;
    }

    public HashMap<Cidade, List<LigacaoCidades>> getLigacoesCidadesPorCidade() {
        return ligacoesCidadesPorCidade;
    }

}
