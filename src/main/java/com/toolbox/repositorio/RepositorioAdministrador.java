package com.toolbox.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.toolbox.modelo.Administrador;



public interface RepositorioAdministrador extends CrudRepository<Administrador, Integer> {

	public abstract Administrador findByEmailAndPassword(String email, String password);
}
