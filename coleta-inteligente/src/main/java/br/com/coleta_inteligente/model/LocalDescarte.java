package br.com.coleta_inteligente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "local_descarte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalDescarte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long localDescarteId;

    @Column(nullable = false)
    private String titulo;

    @Column
    private String descricao;

    @Column
    private String logradouro;

    @Column
    private Long numero;
    
    @Column
    private String complemento;

    @Column
    private String referencia;

    @Column
    private String bairro;

    @Column
    private String cidade;

    @Column
    private String uf;
   

    public LocalDescarte(String titulo, String descricao, String logradouro, Long numero, String complemento,
            String referencia, String bairro, String cidade, String uf) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.referencia = referencia;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }   
        
}
