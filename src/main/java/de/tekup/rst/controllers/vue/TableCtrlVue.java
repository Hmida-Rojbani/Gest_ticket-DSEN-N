package de.tekup.rst.controllers.vue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TableCtrlVue {
	
	@GetMapping("/tables/add")
	public String getAdd() {
		return "tables/add-table";
	}

}
