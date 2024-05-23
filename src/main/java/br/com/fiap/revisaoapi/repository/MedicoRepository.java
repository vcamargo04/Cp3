package br.com.fiap.revisaoapi.repository;

import br.com.fiap.revisaoapi.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
