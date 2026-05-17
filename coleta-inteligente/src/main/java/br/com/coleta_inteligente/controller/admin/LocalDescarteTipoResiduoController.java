package br.com.coleta_inteligente.controller.admin;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.coleta_inteligente.dto.local_descarte_tipo_residuo.VinculoDTO;
import br.com.coleta_inteligente.model.LocalDescarteTipoResiduo;
import br.com.coleta_inteligente.service.LocalDescarteTipoResiduoService;

@RestController
@RequestMapping("/api/admin/local-residuo")
public class LocalDescarteTipoResiduoController {

    private final LocalDescarteTipoResiduoService service;

    public LocalDescarteTipoResiduoController(LocalDescarteTipoResiduoService service) {
        this.service = service;
    }

    @PostMapping
    public LocalDescarteTipoResiduo vincular(
            @RequestParam Long localId,
            @RequestParam Long tipoId) {
        return service.vincular(localId, tipoId);
    }

    @GetMapping
    public List<VinculoDTO> listar() {
        return service.listar();
    }

    @DeleteMapping
    public ResponseEntity<Void> remover(
            @RequestParam Long localId,
            @RequestParam Long tipoId) {
        service.remover(localId, tipoId);
        return ResponseEntity.noContent().build();
    }
}