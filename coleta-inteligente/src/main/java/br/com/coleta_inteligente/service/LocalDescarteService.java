package br.com.coleta_inteligente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.coleta_inteligente.model.LocalDescarte;
import br.com.coleta_inteligente.repository.LocalDescarteRepository;

@Service
public class LocalDescarteService {

    private final LocalDescarteRepository repository;

    public LocalDescarteService(LocalDescarteRepository repository) {
        this.repository = repository;
    }

    public LocalDescarte criar(LocalDescarte local) {
        return repository.save(local);
    }

    public List<LocalDescarte> listar() {
        return repository.findAll();
    }

    public Optional<LocalDescarte> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public LocalDescarte atualizar(Long id, LocalDescarte local) {
        LocalDescarte existente = repository.findById(id).orElseThrow(() -> new RuntimeException("Local de descarte não encontrado"));
        existente.setTitulo(local.getTitulo());
        existente.setDescricao(local.getDescricao());
        existente.setLogradouro(local.getLogradouro());
        existente.setNumero(local.getNumero());
        existente.setComplemento(local.getComplemento());
        existente.setReferencia(local.getReferencia());
        existente.setBairro(local.getBairro());
        existente.setCidade(local.getCidade());
        existente.setUf(local.getUf());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}