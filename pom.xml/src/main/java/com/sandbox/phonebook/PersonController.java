package com.sandbox.phonebook;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Контроллер отслеживает обращения по виртуальному пути /persons
 */
@Controller
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository repository;

	@Autowired
	private PersonService service;

	@RequestMapping
	public String getPersonsPage() {
		return "persons";
	}

	/**
	 * Обработка запроса на получение данных
	 * 
	 * @param search
	 *            Признак режима поиска
	 * @param filters
	 *            JSON с фильтрами
	 * @param page
	 *            Номер страницы
	 * @param rows
	 *            Количество рядов
	 * @param sidx
	 *            Поле сортировки
	 * @param sord
	 *            Направление сортировки
	 * @return
	 */
	@RequestMapping(method = GET, produces = "application/json")
	public @ResponseBody
	JsonResponse<PersonDto> records(@RequestParam("_search") Boolean search,
			@RequestParam(value = "filters", required = false) String filters,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {

		Pageable pageRequest = new PageRequest(page - 1, rows);

		if (search == true) {
			// Выдаем фильтрованные записи
			return getFilteredRecords(filters, pageRequest);
		} else {
			// Выдаем все записи
			return getAllRecords(pageRequest);
		}
	}

	/**
	 * Добавляем новый контакт в телефонную книгу
	 * 
	 * @param request
	 *            Запрос
	 * @param person
	 *            Свойства контакта
	 * @return
	 */
	@RequestMapping(method = POST)
	public @ResponseBody
	StatusResponse createPerson(HttpServletRequest request,
			@RequestBody Person person) {

		Boolean result = service.create(person);
		return new StatusResponse(result);
	}

	/**
	 * Удаление контакта
	 * 
	 * @param request
	 *            Запрос
	 * @param person
	 *            Свойства удаляемого контакта
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = DELETE)
	public @ResponseBody
	StatusResponse delete(HttpServletRequest request, @RequestBody Person person) {
		Boolean result = service.delete(person);
		return new StatusResponse(result);
	}

	/**
	 * Редактирование свойств контакта
	 * 
	 * @param request
	 *            Запрос
	 * @param existingPerson
	 *            Существующий контакт
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = PUT)
	public @ResponseBody
	StatusResponse update(HttpServletRequest request,
			@RequestBody Person existingPerson) {
		Boolean result = service.update(existingPerson);
		return new StatusResponse(result);
	}

	/**
	 * Возвращает страницу с записями без фильтров
	 * 
	 * @param pageRequest
	 *            Запрос
	 * @return
	 */
	public JsonResponse<PersonDto> getAllRecords(Pageable pageRequest) {

		Page<Person> Persons = repository.findAll(pageRequest);
		List<PersonDto> PersonDtos = PersonMapper.map(Persons);

		JsonResponse<PersonDto> response = new JsonResponse<PersonDto>();
		response.setRows(PersonDtos);
		response.setRecords(Long.valueOf(Persons.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(Persons.getTotalPages()).toString());
		response.setPage(Integer.valueOf(Persons.getNumber() + 1).toString());

		return response;

	}

	/**
	 * Получение отфильтрованых записей
	 */
	public JsonResponse<PersonDto> getFilteredRecords(String filters,
			Pageable pageRequest) {
		// Подстроки, по которым ищем
		String strFullName = null;
		String strPhone = null;
		String strAddress = null;

		JqGridFilter jqgridFilter = JqGridObjectMapper.map(filters);
		for (JqGridFilter.Rule rule : jqgridFilter.getRules()) {
			if (rule.getField().equals("fullName"))
				strFullName = rule.getData();
			else if (rule.getField().equals("phone"))
				strPhone = rule.getData();
			else if (rule.getField().equals("address"))
				strAddress = rule.getData();
		}

		Page<Person> Persons = null;
		// Воспользуемся автогенерируемыми методами
		if (strFullName != null)
			Persons = repository.findByFullNameLike("%" + strFullName + "%",
					pageRequest);
		if (strPhone != null)
			Persons = repository.findByPhoneLike("%" + strPhone + "%",
					pageRequest);
		if (strAddress != null)
			Persons = repository.findByAddressLike("%" + strAddress + "%",
					pageRequest);

		List<PersonDto> PersonDtos = PersonMapper.map(Persons);
		JsonResponse<PersonDto> response = new JsonResponse<PersonDto>();
		response.setRows(PersonDtos);
		response.setRecords(Long.valueOf(Persons.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(Persons.getTotalPages()).toString());
		response.setPage(Integer.valueOf(Persons.getNumber() + 1).toString());
		return response;
	}

}
