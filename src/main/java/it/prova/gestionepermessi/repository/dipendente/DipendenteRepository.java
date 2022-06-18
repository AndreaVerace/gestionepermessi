package it.prova.gestionepermessi.repository.dipendente;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepermessi.model.Dipendente;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long> {

	Page<Dipendente> findAll(Specification<Dipendente> specificationCriteria, Pageable paging);

	
	@Query("from Dipendente d left join fetch d.richiestePermesso where d.id = ?1")
	Optional<Dipendente> findByIdConRichieste(Long id);
	
	@Query("select d from Dipendente d left join fetch d.utente where d.id = ?1")
	Optional<Dipendente> caricaSingoloDipendenteConUtente(Long id);
	
	@Query("select d from Dipendente d left join fetch d.utente u where u.username = ?1")
	<Optional>Dipendente cercaPerUsername(String username);
}
