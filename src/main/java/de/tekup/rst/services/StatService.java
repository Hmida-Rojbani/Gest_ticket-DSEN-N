package de.tekup.rst.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import de.tekup.rst.dto.models.ClientResDTO;
import de.tekup.rst.dto.models.MetDTO;
import de.tekup.rst.entities.ClientEntity;
import de.tekup.rst.entities.MetEntity;
import de.tekup.rst.entities.Plat;
import de.tekup.rst.entities.TicketEntity;
import de.tekup.rst.repositories.ClientRepository;
import de.tekup.rst.repositories.MetRepository;
import de.tekup.rst.repositories.TicketRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatService {
	
	private MetRepository metRepository;
	private ClientRepository clientRepository;
	private TicketRepository ticketRepository;
	private ModelMapper mapper;
	
	public MetDTO platPlusAcheter(LocalDate deb, LocalDate fin) {
		
		List<MetEntity> metEntities = metRepository.findAll();
		
		metEntities.removeIf(met -> !(met instanceof Plat));
		
		for (MetEntity plat : metEntities) {
			plat.getTickets().removeIf(t-> t.getDateTime().toLocalDate().isBefore(deb) ||
					t.getDateTime().toLocalDate().isAfter(fin));
		}
		
		int max = -1;
		MetEntity entity = null;
		
		for (MetEntity metEntity : metEntities) {
			if(metEntity.getTickets().size()> max) {
				max = metEntity.getTickets().size();
				entity = metEntity;
			}
		}
		
		MetDTO dto = mapper.map(entity, MetDTO.class);
		dto.setType("Plat");
		return dto;
	}

	public MetDTO platPlusAcheterQuery(LocalDate deb, LocalDate fin) {
		

		MetEntity entity = metRepository.platPlutAcheter(deb, fin);
	
		MetDTO dto = mapper.map(entity, MetDTO.class);
		dto.setType("Plat");
		return dto;
	}
	
	public ClientResDTO clientPlusFidele() {
		ClientEntity client = clientRepository.findAll()
								.stream()
								.max(Comparator
										.comparing(c->c.getTickets().size()))
								.get();
		
		return mapper.map(client, ClientResDTO.class);
	}
	
	public String jourPlusReservee(int clientId) {
		
		ClientEntity clientEntity = clientRepository.findById(clientId).get();
		List<TicketEntity> ticketEntities = clientEntity.getTickets();
		
		List<DayOfWeek> days = ticketEntities.stream()
								.map(t-> t.getDateTime().getDayOfWeek())
								.collect(Collectors.toList());
		System.out.println(days);
		
		Map<DayOfWeek, Long> map = days.stream()
						.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(map);
		System.out.println(map.entrySet());
		DayOfWeek day = map.entrySet()
							.stream()
							.max(Map.Entry.comparingByValue())
							.get().getKey();
		return day.getDisplayName(TextStyle.FULL, new Locale("fr"));
	}
	
	public Map<String, Double> revenue() {
		List<TicketEntity> ticketEntities = ticketRepository.findAll();
		LocalDate today = LocalDate.now();
		
		double todayAddition = ticketEntities.stream()
								.filter(t -> t.getDateTime().toLocalDate().isEqual(today))
								.mapToDouble(t -> t.getAddition())
								.sum();
		
		TemporalField weekInYear = WeekFields.ISO.weekOfWeekBasedYear();
		int nbWeekInYear = today.get(weekInYear);
		
		double weekAddition = ticketEntities.stream()
				.filter(t -> t.getDateTime().get(weekInYear)==today.get(weekInYear)
				&& t.getDateTime().getYear()==today.getYear())
				.mapToDouble(t -> t.getAddition())
				.sum();
		
		double monthAddition = ticketEntities.stream()
				.filter(t -> t.getDateTime().getMonthValue()==today.getMonthValue()
				&& t.getDateTime().getYear()==today.getYear())
				.mapToDouble(t -> t.getAddition())
				.sum();
		
		Map<String, Double> map = new HashMap<>();
		map.put("Jour", todayAddition);
		map.put("Semaine", weekAddition);
		map.put("Mois", monthAddition);
		
		return map;
		
	}

}
