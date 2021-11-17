package de.tekup.rst.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.rst.dto.models.TicketDTO;
import de.tekup.rst.services.TicketService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketCtrl {
	private TicketService ticketService;
	
	@PostMapping
	public double getAdition(@RequestBody TicketDTO ticketDTO) {
		return ticketService.createTicket(ticketDTO);
	}

}
