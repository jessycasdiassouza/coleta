package br.com.coleta_inteligente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coleta_inteligente.model.LocalDescarte;

public interface LocalDescarteRepository extends JpaRepository<LocalDescarte, Long> {

}