package it.prova.gestionepermessi.repository.richiestaPermesso;

import java.util.List;

import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepermessi.model.RichiestaPermesso;

public interface RichiestaPermessoRepository extends CrudRepository<RichiestaPermesso, Long> {

	Page<RichiestaPermesso> findAll(Specification<RichiestaPermesso> specificationCriteria, Pageable paging);

	@Query(value = "select r.* from richiestapermesso r left join dipendente d on d.id=r.dipendente_id "
			+ " inner join utente u on d.utente_id=u.id left join ruolo ru on ru.id=u.id where  u.username like ?1 ",
			nativeQuery = true)
	List<RichiestaPermesso> trovaRichiesteUtente(String username);
	
}
