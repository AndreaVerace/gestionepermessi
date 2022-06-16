package it.prova.gestionepermessi.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.Sesso;
import it.prova.gestionepermessi.model.Utente;



public class DipendenteDTO {

	private Long id;
	
	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	
	@NotBlank(message = "{codiceFiscale.notblank}")
	private String codiceFiscale;
	
	@NotBlank(message = "{email.notblank}")
	private String email;
	
	private Date dataNascita;
	
	private Date dataAssunzione;
	
	private Date dataDimissioni;
	
	private Sesso sesso;
	
	private Utente utente;
	
	private Long[] richiestePermessoIds;

	public DipendenteDTO() {
		
	}

	public DipendenteDTO(Long id,String nome,String cognome,String codiceFiscale,String email, Sesso sesso, Utente utente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
		this.sesso = sesso;
		this.utente = utente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public Date getDataDimissioni() {
		return dataDimissioni;
	}

	public void setDataDimissioni(Date dataDimissioni) {
		this.dataDimissioni = dataDimissioni;
	}

	public Sesso getSesso() {
		return sesso;
	}

	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Long[] getRichiestePermessoIds() {
		return richiestePermessoIds;
	}

	public void setRichiestePermessoIds(Long[] richiestePermessoIds) {
		this.richiestePermessoIds = richiestePermessoIds;
	}
	
	public Dipendente buildDipendenteModel(boolean includeIdRichiestePermesso) {
		Dipendente result = new Dipendente(this.id, this.nome, this.cognome, this.codiceFiscale,
				this.email,this.dataNascita,this.dataAssunzione,this.dataDimissioni,this.sesso,this.utente);
		if (includeIdRichiestePermesso && richiestePermessoIds != null)
			result.setRichiestePermesso((Arrays.asList(richiestePermessoIds).stream().map(id -> new RichiestaPermesso(id)).collect(Collectors.toSet())));

		return result;
	}
	
	public static DipendenteDTO buildDipendenteDTOFromModel(Dipendente dipendenteModel) {
		DipendenteDTO result = new DipendenteDTO(dipendenteModel.getId(), dipendenteModel.getNome(), dipendenteModel.getCognome(),
				dipendenteModel.getCodiceFiscale(),dipendenteModel.getEmail(),
				dipendenteModel.getSesso(),dipendenteModel.getUtente());

		if (!dipendenteModel .getRichiestePermesso().isEmpty())
			result.richiestePermessoIds = dipendenteModel.getRichiestePermesso().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});

		return result;
	}
	
	
	public static List<DipendenteDTO> createDipendenteDTOListFromModelList(List<Dipendente> modelListInput){
		return modelListInput.stream().map(dipendenteEntity -> {
			return DipendenteDTO.buildDipendenteDTOFromModel(dipendenteEntity);
		}).collect(Collectors.toList());
	}
	
	
	
}
