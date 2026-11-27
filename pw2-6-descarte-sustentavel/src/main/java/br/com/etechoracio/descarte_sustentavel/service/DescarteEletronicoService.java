package br.com.etechoracio.descarte_sustentavel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.etechoracio.descarte_sustentavel.entity.DescarteEletronico;
import br.com.etechoracio.descarte_sustentavel.repository.DescarteEletronicoRepository;

@Service
public class DescarteEletronicoService {

    private final DescarteEletronicoRepository repository;

    public DescarteEletronicoService(DescarteEletronicoRepository repository) {
        this.repository = repository;
    }

    public List<DescarteEletronico> listarTodos() {
        return repository.findAll();
    }

    public DescarteEletronico buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Descarte não encontrado."));
    }

    public DescarteEletronico salvar(DescarteEletronico descarte) {
        descarte.setId(null);
        descarte.setPontosGerados(0);
        descarte.setValidado(false);
        return repository.save(descarte);
    }

    public void excluir(Long id) {
        DescarteEletronico descarte = buscarPorId(id);
        repository.delete(descarte);
    }

    public DescarteEletronico computarPontos(Long id) {
        DescarteEletronico descarte = buscarPorId(id);

        if (Boolean.TRUE.equals(descarte.getValidado())) {
            throw new RuntimeException("Pontuação já homologada para este item.");
        }

        int fator;
        switch (descarte.getTipoItem()) {
            case "BATERIA":
                fator = 5;
                break;
            case "PLACA":
                fator = 4;
                break;
            case "CELULAR_ANTIGO":
                fator = 10;
                break;
            case "CABO":
            default:
                fator = 2;
                break;
        }

        int pontos = (int) ((descarte.getPesoGramas() / 100.0) * fator);
        descarte.setPontosGerados(pontos);
        descarte.setValidado(true);

        return repository.save(descarte);
    }

    public DescarteEletronico atualizar(Long id, DescarteEletronico novoDescarte) {
        DescarteEletronico descarte = buscarPorId(id);

        descarte.setMatriculaAluno(novoDescarte.getMatriculaAluno());
        descarte.setTipoItem(novoDescarte.getTipoItem());
        descarte.setPesoGramas(novoDescarte.getPesoGramas());

        // Se os dados forem alterados, a pontuação precisa ser calculada novamente.
        descarte.setPontosGerados(0);
        descarte.setValidado(false);

        return repository.save(descarte);
    }
}
