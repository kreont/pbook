package com.sandbox.phonebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Служба работы с объектами, представляющими контакты. Получает простые объекты
 * и обращается к репозиторию с запросами, соответствующими CRUD операциям
 */

@Transactional
@Service
public class PersonService {
	@Autowired
	private PersonRepository repository;

	/**
	 * Создание контакта
	 * 
	 * @param Person
	 * @return
	 */
	public Boolean create(Person Person) {
		Person saved = repository.save(Person);
		if (saved == null)
			return false;

		return true;
	}

	/**
	 * Обновление контакта
	 * 
	 * @param person
	 * @return
	 */
	public Boolean update(Person person) {
		// Проверяем, существует ли такой объект
		Person existingPerson = repository.findById(person.getId());
		if (existingPerson == null)
			return false;

		existingPerson.setFullName(person.getFullName());
		existingPerson.setPhone(person.getPhone());
		existingPerson.setAddress(person.getAddress());

		Person saved = repository.save(existingPerson);
		if (saved == null)
			return false;

		return true;
	}

	/**
	 * Удаление контакта
	 * 
	 * @param person
	 * @return
	 */
	public Boolean delete(Person person) {
		// Проверяем, существует ли такой объект
		Person existingPerson = repository.findById(person.getId());
		if (existingPerson == null)
			return false;

		repository.delete(existingPerson);
		Person deletedPerson = repository.findById(person.getId());
		if (deletedPerson != null)
			return false;

		return true;
	}
}
