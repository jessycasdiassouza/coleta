package br.com.coleta_inteligente.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class LocalDescarteTipoResiduoId {

    private Long localDescarteId;
    private Long tipoResiduoId;


    public Long getLocalDescarteId() {
        return localDescarteId;
    }
    public void setLocalDescarteId(Long localDescarteId) {
        this.localDescarteId = localDescarteId;
    }
    public Long getTipoResiduoId() {
        return tipoResiduoId;
    }
    public void setTipoResiduoId(Long tipoResiduoId) {
        this.tipoResiduoId = tipoResiduoId;
    }    
    
}
