package br.com.etechoracio.descarte_sustentavel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.etechoracio.descarte_sustentavel.entity.DescarteEletronico;

public interface DescarteEletronicoRepository extends JpaRepository<DescarteEletronico, Long> {

}
