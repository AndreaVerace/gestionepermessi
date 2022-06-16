package it.prova.gestionepermessi.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.TipoPermesso;

public class RichiestaPermessoDTO {

	private Long id;
	
	private TipoPermesso tipoPermesso;
	
	private Date dataInizio;
	
	private Date dataFine;
	
	private boolean approvato;
	
	@NotBlank(message = "{codiceCertificato.notblank}")
	private String codiceCertificato;
	
	private String nota;
	
	private Attachment attachment;
	
	private Dipendente dipendente;
	
	public RichiestaPermessoDTO() {
		
	}

	public RichiestaPermessoDTO(Long id, TipoPermesso tipoPermesso, boolean approvato,
			String codiceCertificato, String nota,
			Dipendente dipendente,Attachment attachment) {
		super();
		this.id = id;
		this.tipoPermesso = tipoPermesso;
		this.approvato = approvato;
		this.codiceCertificato = codiceCertificato;
		this.nota = nota;
		this.dipendente = dipendente;
		this.attachment=attachment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPermesso getTipoPermesso() {
		return tipoPermesso;
	}

	public void setTipoPermesso(TipoPermesso tipoPermesso) {
		this.tipoPermesso = tipoPermesso;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public boolean isApprovato() {
		return approvato;
	}

	public void setApprovato(boolean approvato) {
		this.approvato = approvato;
	}

	public String getCodiceCertificato() {
		return codiceCertificato;
	}

	public void setCodiceCertificato(String codiceCertificato) {
		this.codiceCertificato = codiceCertificato;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	
	public RichiestaPermesso buildRichiestaPermessoModel() {
		RichiestaPermesso result = new RichiestaPermesso(this.id,this.tipoPermesso,this.dataInizio,this.dataFine,
				this.approvato,this.codiceCertificato,this.nota,this.dipendente,this.attachment);
		
		return result;
	}
	
	public static RichiestaPermessoDTO buildRichiestaPermessoDTOFromModel(RichiestaPermesso richiestaPermessoModel) {
		RichiestaPermessoDTO result = new RichiestaPermessoDTO(richiestaPermessoModel.getId(),richiestaPermessoModel.getTipoPermesso(),richiestaPermessoModel.isApprovato(),
				richiestaPermessoModel.getCodiceCertificato(),richiestaPermessoModel.getNota(),richiestaPermessoModel.getDipendente(),
				richiestaPermessoModel.getAttachment());


		return result;
	}
	
	
	public static List<RichiestaPermessoDTO> createRichiestaPermessoDTOListFromModelList(List<RichiestaPermesso> modelListInput){
		return modelListInput.stream().map(richiestaPermessoEntity -> {
			return RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(richiestaPermessoEntity);
		}).collect(Collectors.toList());
	}
	
	
	
}
