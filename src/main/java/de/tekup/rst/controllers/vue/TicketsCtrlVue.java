package de.tekup.rst.controllers.vue;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.tekup.rst.dto.models.ClientReqDTO;
import de.tekup.rst.dto.models.TicketDTO;
import de.tekup.rst.services.ClientService;
import de.tekup.rst.services.TicketService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TicketsCtrlVue {

	private TicketService ticketService;
	private ClientService clientService;
	
	@GetMapping("/tickets/add")
	public String getAddTicket(Model model) {
		model.addAttribute("ticket", new TicketDTO());
		model.addAttribute("listClient", clientService.getAllClients());
		return "tickets/add-ticket";
	}
}
