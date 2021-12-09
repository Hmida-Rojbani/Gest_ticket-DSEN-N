package de.tekup.rst.controllers.vue;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.tekup.rst.dto.models.ClientReqDTO;
import de.tekup.rst.services.ClientService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ClientCtrVue {
	
	private ClientService clientService;
	
	@GetMapping("/clients/add")
	public String getAddClient(Model model) {
		model.addAttribute("client", new ClientReqDTO());
		return "clients/add-client";
	}
	

	@PostMapping("/clients/add")
	public String postAddClient(@Valid @ModelAttribute("client") ClientReqDTO dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "clients/add-client";
		}
		
		clientService.saveToDB(dto);
		return "redirect:/";
	}

}
