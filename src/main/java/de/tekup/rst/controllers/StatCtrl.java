package de.tekup.rst.controllers;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.rst.dto.models.ClientResDTO;
import de.tekup.rst.dto.models.MetDTO;
import de.tekup.rst.services.StatService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class StatCtrl {
	
	private StatService statService;
	
	@GetMapping("/api/stats/plat/acheter/{deb}/{fin}")
	public MetDTO getPlat(@PathVariable String deb,
			@PathVariable String fin) {
		LocalDate debDate = LocalDate.parse(deb);
		LocalDate finDate = LocalDate.parse(fin);
		return statService.platPlusAcheterQuery(debDate, finDate);
	}
	
	@GetMapping("/api/stats/client/fidele")
	public ClientResDTO clientFidele(){
		return statService.clientPlusFidele();
	}

	@GetMapping("/api/stats/client/jour/{id}")
	public String clientJour(@PathVariable int id){
		return statService.jourPlusReservee(id);
	}
}
