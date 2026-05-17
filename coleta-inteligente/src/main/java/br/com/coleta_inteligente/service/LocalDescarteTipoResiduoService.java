package br.com.coleta_inteligente.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.coleta_inteligente.dto.local_descarte_tipo_residuo.VinculoDTO;
import br.com.coleta_inteligente.model.LocalDescarte;
import br.com.coleta_inteligente.model.LocalDescarteTipoResiduo;
import br.com.coleta_inteligente.model.LocalDescarteTipoResiduoId;
import br.com.coleta_inteligente.model.TipoResiduo;
import br.com.coleta_inteligente.repository.LocalDescarteRepository;
import br.com.coleta_inteligente.repository.LocalDescarteTipoResiduoRepository;
import br.com.coleta_inteligente.repository.TipoResiduoRepository;

@Service
public class LocalDescarteTipoResiduoService {

    private final LocalDescarteTipoResiduoRepository repository;
    private final LocalDescarteRepository localRepository;
    private final TipoResiduoRepository tipoRepository;

    public LocalDescarteTipoResiduoService(
            LocalDescarteTipoResiduoRepository repository,
            LocalDescarteRepository localRepository,
            TipoResiduoRepository tipoRepository) {

        this.repository = repository;
        this.localRepository = localRepository;
        this.tipoRepository = tipoRepository;
    }

    public LocalDescarteTipoResiduo vincular(Long localId, Long tipoId) {
        LocalDescarte local = localRepository.findById(localId).orElseThrow(() -> new RuntimeException("Local não encontrado"));
        TipoResiduo tipo = tipoRepository.findById(tipoId).orElseThrow(() -> new RuntimeException("Tipo de resíduo não encontrado"));
        LocalDescarteTipoResiduo vinculo = new LocalDescarteTipoResiduo();
        LocalDescarteTipoResiduoId id = new LocalDescarteTipoResiduoId();
        id.setLocalDescarteId(localId);
        id.setTipoResiduoId(tipoId);
        vinculo.setId(id);
        vinculo.setLocalDescarte(local);
        vinculo.setTipoResiduo(tipo);
        return repository.save(vinculo);
    }

    public List<VinculoDTO> listar() {
        List<VinculoDTO> retorno = new ArrayList<>();
        for(LocalDescarteTipoResiduo obj: repository.findAll()){
            retorno.add(new VinculoDTO(obj.getLocalDescarte().getLocalDescarteId(), obj.getLocalDescarte().getTitulo(), 
            obj.getTipoResiduo().getTipoResiduoId(), obj.getTipoResiduo().getNomeTipo()));
        }
        return retorno;
    }

    public void remover(Long localId, Long tipoId) {
        LocalDescarteTipoResiduoId id = new LocalDescarteTipoResiduoId();
        id.setLocalDescarteId(localId);
        id.setTipoResiduoId(tipoId);
        repository.deleteById(id);
    }
}