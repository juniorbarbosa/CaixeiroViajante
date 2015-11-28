package br.univel.trajeto;

import java.io.Serializable;

public class LigacaoCidades implements Serializable {

    private Cidade origem;
    private Cidade destino;
    private double distancia;
    
    public LigacaoCidades() {
        
    }
    
    public LigacaoCidades(Cidade origem, Cidade destino) {
        this.origem = origem;
        this.destino = destino;
    }

    public Cidade getOrigem() {
        return origem;
    }

    public void setOrigem(Cidade origem) {
        this.origem = origem;
    }

    public Cidade getDestino() {
        return destino;
    }

    public void setDestino(Cidade destino) {
        this.destino = destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
    

}
