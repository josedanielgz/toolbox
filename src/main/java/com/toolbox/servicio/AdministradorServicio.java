package com.toolbox.servicio;

import com.toolbox.generico.GenericService;
import com.toolbox.modelo.Administrador;


public interface AdministradorServicio extends GenericService<Administrador, Integer>{

	public Administrador select(String email, String password);

}

