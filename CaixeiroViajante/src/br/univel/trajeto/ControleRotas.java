package br.univel.trajeto;

import java.util.ArrayList;
import java.util.List;

public class ControleRotas {

    private BancoDados bancoDados;

    public ControleRotas(BancoDados bancoDados) {
        this.bancoDados = bancoDados;
    }

    public BancoDados getBancoDados() {
        return bancoDados;
    }

    public void setBancoDados(BancoDados bancoDados) {
        this.bancoDados = bancoDados;
    }

    public void cadastraCidade(Cidade cidade) {
        if (cidade.getNome() == null) {
            throw new NullPointerException("O nome da cidade deve ser informado");
        }
        if (getBancoDados().getCidadesPorNome().containsKey(cidade.getNome())) {
            throw new RuntimeException("Cidade já cadastrada com o nome \"" + cidade.getNome() + "\"");
        }
        getBancoDados().getCidades().add(cidade);
        getBancoDados().getCidadesPorNome().put(cidade.getNome(), cidade);
        cidade.setId(getBancoDados().getCidades().size());
    }

    public void cadastraLigacaoCidades(Cidade origem, Cidade destino, double distancia) {
        if (origem == null) {
            throw new RuntimeException("Cidade de origem deve ser informada");
        }
        if (destino == null) {
            throw new RuntimeException("Cidade de destino deve ser informada");
        }
        if (origem.getId() == null || destino.getId() == null) {
            throw new RuntimeException("As cidades de origem e destino devem ser cadastradas antes de cadastrar ligações");
        }
        if (origem.getId().equals(destino.getId())) {
            throw new IllegalArgumentException("As cidades de origem e de destino não devem ser a mesma");
        }
        LigacaoCidades ligacaoDeIda = getBancoDados().getLigacoesCidadesPorIds().get(origem.getId() + "_" + destino.getId());
        if (ligacaoDeIda == null) {
            ligacaoDeIda = new LigacaoCidades(origem, destino);
            getLigacoesCidadePorCidade(origem).add(ligacaoDeIda);
        }
        ligacaoDeIda.setDistancia(distancia);

        getBancoDados().getLigacoesCidadesPorIds().put(origem.getId() + "_" + destino.getId(), ligacaoDeIda);
        LigacaoCidades ligacaoDeVolta = getBancoDados().getLigacoesCidadesPorIds().get(destino.getId() + "_" + origem.getId());
        if (ligacaoDeVolta == null) {
            ligacaoDeVolta = new LigacaoCidades(destino, origem);
            getLigacoesCidadePorCidade(destino).add(ligacaoDeVolta);
        }
        ligacaoDeVolta.setDistancia(distancia);
        getBancoDados().getLigacoesCidadesPorIds().put(origem.getId() + "_" + destino.getId(), ligacaoDeIda);
        getBancoDados().getLigacoesCidadesPorIds().put(destino.getId() + "_" + origem.getId(), ligacaoDeVolta);
    }

    public Trajeto getMelhorRota(Cidade origem, Cidade destino) {
        return new BuscadorTrajetos(this, origem, destino).buscaMelhorRota();
    }

    private List<LigacaoCidades> getLigacoesCidadePorCidade(Cidade cidade) {
        List<LigacaoCidades> rtn = getBancoDados().getLigacoesCidadesPorCidade().get(cidade);
        if (rtn == null) {
            rtn = new ArrayList<>();
            getBancoDados().getLigacoesCidadesPorCidade().put(cidade, rtn);
        }
        return rtn;
    }

    public List<LigacaoCidades> getLigacoesCidadesPorCidade(Cidade cidade) {
        return getBancoDados().getLigacoesCidadesPorCidade().get(cidade);
    }

}
