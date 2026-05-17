package br.com.coleta_inteligente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.coleta_inteligente.model.TipoResiduo;
import br.com.coleta_inteligente.repository.TipoResiduoRepository;

@Service
public class TipoResiduoService {

    private final TipoResiduoRepository repository;

    public TipoResiduoService(TipoResiduoRepository repository) {
        this.repository = repository;
    }

    public TipoResiduo criar(TipoResiduo tipo) {
        return repository.save(tipo);
    }

    public List<TipoResiduo> listar() {
        return repository.findAll();
    }

    public Optional<TipoResiduo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public TipoResiduo atualizar(Long id, TipoResiduo tipo) {

        TipoResiduo existente = repository.findById(id).orElseThrow(() -> new RuntimeException("Tipo de resíduo não encontrado"));

        existente.setNomeTipo(tipo.getNomeTipo());
        existente.setDescricaoTipo(tipo.getDescricaoTipo());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
    
}