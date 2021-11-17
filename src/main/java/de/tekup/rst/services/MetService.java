package de.tekup.rst.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import de.tekup.rst.dto.models.MetDTO;
import de.tekup.rst.entities.Dessert;
import de.tekup.rst.entities.Entree;
import de.tekup.rst.entities.MetEntity;
import de.tekup.rst.entities.Plat;
import de.tekup.rst.repositories.MetRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MetService {
	
	private MetRepository metRepository;
	private ModelMapper mapper;
	
	public MetDTO saveToDB(MetDTO metDTO) {
		
		MetEntity metEntity = null;
		
		switch (metDTO.getType()) {
		case "Plat": metEntity = mapper.map(metDTO, Plat.class);	
			break;
		case "Entree": metEntity = mapper.map(metDTO, Entree.class);	
		break;
		case "Dessert": metEntity = mapper.map(metDTO, Dessert.class);	
		break;

		}
		metRepository.save(metEntity);
		metDTO.setId(metEntity.getId());
		return metDTO;
	}
	
	public List<MetDTO> getAllMets(){
		return metRepository.findAll().stream()
									.map(ent -> {
										MetDTO dto = mapper.map(ent, MetDTO.class);
										dto.setType(ent.getClass().getSimpleName());
										return dto;
									})
									.collect(Collectors.toList());
	}

}
