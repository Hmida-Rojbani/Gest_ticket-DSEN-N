package de.tekup.rst.dto.models;

import lombok.Data;

@Data
public class TicketDTO {
	
	private int nbCouverts;
	
	private Integer clientId;
	
	private Integer tableNumero;
	
	private Integer[] metsIds;
	

}
