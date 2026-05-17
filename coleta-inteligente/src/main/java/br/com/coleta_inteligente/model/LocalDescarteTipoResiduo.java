package br.com.coleta_inteligente.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "local_descarte_tipo_residuo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalDescarteTipoResiduo {

    @EmbeddedId
    private LocalDescarteTipoResiduoId id;

    @ManyToOne
    @MapsId("localDescarteId")
    @JoinColumn(name = "local_descarte_id")
    private LocalDescarte localDescarte;

    @ManyToOne
    @MapsId("tipoResiduoId")
    @JoinColumn(name = "tipo_residuo_id")
    private TipoResiduo tipoResiduo;
    
}
