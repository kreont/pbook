package com.sandbox.phonebook;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

/**
 * Маппер для отображения объекта доступа к данным на простой объект
 *
 */
public class PersonMapper {

	public static PersonDto map(Person Person) {
		PersonDto dto = new PersonDto();
		dto.setId(Person.getId());
		dto.setFullName(Person.getFullName());
		dto.setPhone(Person.getPhone());
		dto.setAddress(Person.getAddress());
		return dto;
	}

	public static List<PersonDto> map(Page<Person> Persons) {
		List<PersonDto> dtos = new ArrayList<PersonDto>();
		for (Person Person : Persons) {
			dtos.add(map(Person));
		}
		return dtos;
	}
}