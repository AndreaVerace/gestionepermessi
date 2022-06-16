package it.prova.gestionepermessi.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;

public class MessaggioDTO {

	private Long id;
	
	@NotBlank(message = "{testo.notblank}")
	private String testo;
	
	@NotBlank(message = "{oggetto.notblank}")
	private String oggetto;
	
	private boolean letto;
	
	private Date dataInserimento;
	
	private Date dataLettura;
	
	private RichiestaPermesso richiestaPermesso;
	
	public MessaggioDTO() {
		
	}

	public MessaggioDTO(Long id, @NotBlank(message = "{testo.notblank}") String testo,
			@NotBlank(message = "{oggetto.notblank}") String oggetto, boolean letto, Date dataInserimento,
			Date dataLettura, RichiestaPermesso richiestaPermesso) {
		super();
		this.id = id;
		this.testo = testo;
		this.oggetto = oggetto;
		this.letto = letto;
		this.dataInserimento = dataInserimento;
		this.dataLettura = dataLettura;
		this.richiestaPermesso = richiestaPermesso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public boolean isLetto() {
		return letto;
	}

	public void setLetto(boolean letto) {
		this.letto = letto;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataLettura() {
		return dataLettura;
	}

	public void setDataLettura(Date dataLettura) {
		this.dataLettura = dataLettura;
	}

	public RichiestaPermesso getRichiestaPermesso() {
		return richiestaPermesso;
	}

	public void setRichiestaPermesso(RichiestaPermesso richiestaPermesso) {
		this.richiestaPermesso = richiestaPermesso;
	}
	
	public Messaggio buildDipendenteModel() {
		Messaggio result = new Messaggio(this.id, this.testo,this.oggetto,this.letto,this.dataInserimento,this.dataLettura,this.richiestaPermesso);
		
		return result;
	}
	
	public static MessaggioDTO buildMessaggioDTOFromModel(Messaggio messaggioModel) {
		MessaggioDTO result = new MessaggioDTO(messaggioModel.getId(),messaggioModel.getTesto(),messaggioModel.getOggetto(),messaggioModel.isLetto(),messaggioModel.getDataInserimento(),
				messaggioModel.getDataLettura(),messaggioModel.getRichiestaPermesso());

		return result;
	}
	
	
	public static List<MessaggioDTO> createMessaggioDTOListFromModelList(List<Messaggio> modelListInput){
		return modelListInput.stream().map(messaggioEntity -> {
			return MessaggioDTO.buildMessaggioDTOFromModel(messaggioEntity);
		}).collect(Collectors.toList());
	}
	
}
