package de.tekup.rst.controllers.vue;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.tekup.rst.dto.models.TableDTO;
import de.tekup.rst.services.TableService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TableCtrlVue {
	
	private TableService tableService;
	
	@GetMapping("/tables/add")
	public String getAdd(Model model) {
		model.addAttribute("table",new TableDTO());
		return "tables/add-table";
	}
	
	@PostMapping("/tables/add")
	public String postAdd(@ModelAttribute("table") TableDTO table) {
		tableService.saveTable(table);
		return "redirect:/";
	}

}
