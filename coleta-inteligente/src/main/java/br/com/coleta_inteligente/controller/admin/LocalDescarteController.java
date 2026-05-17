package br.com.coleta_inteligente.controller.admin;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coleta_inteligente.model.LocalDescarte;
import br.com.coleta_inteligente.service.LocalDescarteService;
import br.com.coleta_inteligente.service.LocalDescarteTipoResiduoService;

@RestController
@RequestMapping("/api/admin/locais-descarte")
@CrossOrigin("*")
public class LocalDescarteController {

    private final LocalDescarteService service;
    private final LocalDescarteTipoResiduoService vinculoService;

    public LocalDescarteController(LocalDescarteService service, LocalDescarteTipoResiduoService vinculoService) {
        this.service = service;
        this.vinculoService = vinculoService;
    }

    @PostMapping
    public LocalDescarte criar(@RequestBody LocalDescarte local) {

        return service.criar(local);
    }

    @GetMapping
    public List<LocalDescarte> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDescarte> buscar(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public LocalDescarte atualizar(
            @PathVariable Long id,
            @RequestBody LocalDescarte local) {

        return service.atualizar(id, local);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (!vinculoService.listar().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não é possível excluir. Existem vínculos associados.");
        }
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}