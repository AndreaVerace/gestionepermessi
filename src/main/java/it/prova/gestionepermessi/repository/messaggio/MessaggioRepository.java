package it.prova.gestionepermessi.repository.messaggio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioRepository extends CrudRepository<Messaggio, Long> {

	Page<Messaggio> findAll(Specification<Messaggio> specificationCriteria, Pageable paging);

	Long countByLetto(boolean b);
	
	List<Messaggio> findAllByLettoIs(boolean letto);
	
	@EntityGraph(attributePaths = { "richiestaPermesso" })
	Messaggio findByRichiestaPermesso_IdIs(Long idRichiesta);
}
