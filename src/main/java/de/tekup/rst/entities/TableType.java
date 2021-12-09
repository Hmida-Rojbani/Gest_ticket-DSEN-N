package de.tekup.rst.entities;

import lombok.Getter;

public enum TableType {
	
	OUTSIDE("Outside area"), INSIDE_SMOKER("Inside smoker area"), INSIDE_NO_SMOKER("Inside no smoker area");
	
	@Getter private String displayValue;

	private TableType(String displayValue) {
		this.displayValue = displayValue;
	}
	
	

}
