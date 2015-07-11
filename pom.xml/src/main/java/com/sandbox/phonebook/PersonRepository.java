package com.sandbox.phonebook;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Репозиторий контактов, поддерживающий различные запросы поиска
 *
 */

@RepositoryRestResource
public interface PersonRepository extends CrudRepository<Person, Integer> {
	
	// Поиск записи по ее идентификатору
	Person findById(@Param("id") Integer id);
	
	// Поиск всех записей
	Page<Person> findAll(Pageable pageable);
	 
	// Поиск по подстроке телефонного номера
	Page<Person> findByPhoneLike(@Param("phone") String phone, Pageable pageable);
 
	// Поиск по подстроке ФИО
	Page<Person> findByFullNameLike(@Param("fullName") String fullName, Pageable pageable);

	// Поиск по подстроке адреса
	Page<Person> findByAddressLike(@Param("address") String address, Pageable pageable);
 }