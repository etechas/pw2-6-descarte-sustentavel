package br.com.etechoracio.descarte_sustentavel.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.etechoracio.descarte_sustentavel.entity.DescarteEletronico;
import br.com.etechoracio.descarte_sustentavel.service.DescarteEletronicoService;

@RestController
@RequestMapping("/api/descartes")
public class DescarteEletronicoController {

    private final DescarteEletronicoService service;

    public DescarteEletronicoController(DescarteEletronicoService service) {
        this.service = service;
    }

    @GetMapping
    public List<DescarteEletronico> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public DescarteEletronico buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DescarteEletronico salvar(@Valid @RequestBody DescarteEletronico descarte) {
        return service.salvar(descarte);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PostMapping("/{id}/computar-pontos")
    public DescarteEletronico computarPontos(@PathVariable Long id) {
        return service.computarPontos(id);
    }

    @PutMapping("/{id}")
    public DescarteEletronico atualizar(
            @PathVariable Long id,
            @Valid @RequestBody DescarteEletronico descarte) {
        return service.atualizar(id, descarte);
    }
}
