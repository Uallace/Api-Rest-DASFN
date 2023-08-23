package br.com.desafio.repository;

import br.com.desafio.model.entities.TaxaMensalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxaMensalRepository extends JpaRepository<TaxaMensalEntity, Long> {
    List<TaxaMensalEntity> findByanoMes(String anoMes);
}
