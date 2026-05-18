package br.com.coleta_inteligente.controller.publico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.coleta_inteligente.dto.publico.LocalDescarteConsultaDTO;
import br.com.coleta_inteligente.model.LocalDescarte;
import br.com.coleta_inteligente.model.LocalDescarteTipoResiduo;
import br.com.coleta_inteligente.repository.LocalDescarteRepository;
import br.com.coleta_inteligente.repository.LocalDescarteTipoResiduoRepository;

@RestController
@RequestMapping("/api/publico/consulta")
@CrossOrigin(origins = "*")
public class ConsultaPublicaController {

    @Autowired
    private LocalDescarteRepository localDescarteRepository;

    @Autowired
    private LocalDescarteTipoResiduoRepository vinculoRepository;

    @GetMapping
    public List<LocalDescarteConsultaDTO> listar() {

        List<LocalDescarte> locais =
                localDescarteRepository.findAll();

        List<LocalDescarteConsultaDTO> resposta =
                new ArrayList<>();

        for (LocalDescarte local : locais) {
            List<LocalDescarteTipoResiduo> vinculos = vinculoRepository.findByLocalDescarteLocalDescarteId(local.getLocalDescarteId());
            List<String> residuos = vinculos.stream().map(v -> v.getTipoResiduo().getNomeTipo()).toList();
            LocalDescarteConsultaDTO dto = new LocalDescarteConsultaDTO();
            dto.setId(local.getLocalDescarteId());
            dto.setTitulo(local.getTitulo());
            dto.setDescricao(local.getDescricao());
            dto.setLogradouro(local.getLogradouro());
            dto.setNumero(local.getNumero());
            dto.setComplemento(local.getComplemento());
            dto.setReferencia(local.getReferencia());
            dto.setBairro(local.getBairro());
            dto.setCidade(local.getCidade());
            dto.setUf(local.getUf());
            dto.setTiposResiduo(residuos);
            resposta.add(dto);
        }

        return resposta;
    }

    @GetMapping("/bairro")
    public List<LocalDescarteConsultaDTO> listarPorBairro(@RequestParam String bairro) {
        List<LocalDescarteConsultaDTO> todos = listar();
        return todos.stream().filter(local -> local.getBairro() != null && local.getBairro().equalsIgnoreCase(bairro)).toList();
    }

    @GetMapping("/residuo")
    public List<LocalDescarteConsultaDTO> listarPorResiduo(@RequestParam String residuo) {
        List<LocalDescarteConsultaDTO> todos = listar();
        return todos.stream().filter(local -> local.getTiposResiduo().stream().anyMatch(tipo -> tipo.equalsIgnoreCase(residuo))).toList();
    }
}