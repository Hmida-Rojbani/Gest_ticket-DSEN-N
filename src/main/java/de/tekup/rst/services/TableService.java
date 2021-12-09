package de.tekup.rst.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import de.tekup.rst.dto.models.TableDTO;
import de.tekup.rst.entities.TableEntity;
import de.tekup.rst.repositories.TableRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TableService {

	private TableRepository tableRepository;
	private ModelMapper mapper;
	
	public void saveTable(TableDTO tableDTO) {
		tableRepository.save(mapper.map(tableDTO, TableEntity.class));
	}
	
	public List<TableDTO> getTables(){
		return tableRepository.findAll().stream()
				.map(ent->mapper.map(ent, TableDTO.class))
				.collect(Collectors.toList());
	}
}
