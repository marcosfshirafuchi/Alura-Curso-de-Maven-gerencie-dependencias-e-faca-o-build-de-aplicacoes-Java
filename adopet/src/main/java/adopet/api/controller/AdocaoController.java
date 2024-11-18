package adopet.api.controller;

import adopet.api.dto.AdocaoDTO;
import adopet.api.dto.AprovarAdocaoDTO;
import adopet.api.dto.ReprovarAdocaoDTO;
import adopet.api.dto.SolicitacaoDeAdocaoDTO;
import adopet.api.exception.AdocaoException;
import adopet.api.service.AdocaoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("adocao")
public class AdocaoController {

    @Autowired
    private AdocaoService service;

    @GetMapping
    public ResponseEntity<List<AdocaoDTO>> buscarTodos(){
        List<AdocaoDTO> adocoes = service.listarTodos();
        return ResponseEntity.ok(adocoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdocaoDTO> buscar(@PathVariable Long id){
        AdocaoDTO adocao = service.listar(id);
        return ResponseEntity.ok(adocao);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(@RequestBody @Valid SolicitacaoDeAdocaoDTO dados){
        this.service.solicitar(dados);
        return ResponseEntity.ok("Adoção solicitada com sucesso!");
    }

    @PutMapping("/aprovar")
    @Transactional
    public ResponseEntity<String> aprovar(@RequestBody @Valid AprovarAdocaoDTO dto){
        this.service.aprovar(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reprovar")
    @Transactional
    public ResponseEntity<String> reprovar(@RequestBody @Valid ReprovarAdocaoDTO dto){
        this.service.reprovar(dto);
        return ResponseEntity.ok().build();
    }
}