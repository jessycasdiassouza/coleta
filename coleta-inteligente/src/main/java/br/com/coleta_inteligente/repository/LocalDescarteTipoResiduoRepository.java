package br.com.coleta_inteligente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coleta_inteligente.model.LocalDescarteTipoResiduo;
import br.com.coleta_inteligente.model.LocalDescarteTipoResiduoId;

public interface LocalDescarteTipoResiduoRepository extends JpaRepository<LocalDescarteTipoResiduo, LocalDescarteTipoResiduoId> {

    List<LocalDescarteTipoResiduo> findByLocalDescarteLocalDescarteId(Long id);

}