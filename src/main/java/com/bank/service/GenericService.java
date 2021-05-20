package com.bank.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID> {

	/**
	 * 
	 * @return List<T>
	 */
	public List<T> findAll();

	/**
	 * 
	 * @param id
	 * @return Optional<T>
	 */
	public Optional<T> findById(ID id);

	/**
	 * 
	 * @return Long
	 */
	public Long count();

	/**
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void validate(T entity) throws Exception;

	/**
	 * 
	 * @param entity
	 * @return T
	 * @throws Exception
	 */
	public T save(T entity) throws Exception;

	/**
	 * 
	 * @param entity
	 * @return T
	 * @throws Exception
	 */
	public T update(T entity) throws Exception;

	/**
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void delete(T entity) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(ID id) throws Exception;

}
