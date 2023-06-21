package com.toolbox.servicio;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.toolbox.generico.GenericServiceImp;
import com.toolbox.modelo.Administrador;
import com.toolbox.repositorio.RepositorioAdministrador;



@Service
public class AdministradorServicioImpl extends GenericServiceImp<Administrador, Integer> implements AdministradorServicio{

	@Autowired
	public RepositorioAdministrador repositorioAdministrador;
	
	@Override
	public CrudRepository<Administrador, Integer> getDao(){
		return repositorioAdministrador;
	}
	
	@Override
	public Administrador select(String email, String password) {
		return repositorioAdministrador.findByEmailAndPassword(email, password);
	}



	

}
