package br.com.coleta_inteligente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.coleta_inteligente.model.TipoResiduo;

@Repository
public interface TipoResiduoRepository extends JpaRepository<TipoResiduo, Long>{

    Optional<TipoResiduo> findByNomeTipo(String nomeTipo);
    
}
