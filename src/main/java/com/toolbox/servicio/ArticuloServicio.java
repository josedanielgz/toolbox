package com.toolbox.servicio;

import java.util.List;

import com.toolbox.generico.GenericService;
import com.toolbox.modelo.Articulo;

public interface ArticuloServicio extends GenericService<Articulo, Integer>{

	public Articulo select(String categoria);

	public Articulo selectPorID(int id);

    public List<Articulo> getByCategoria(String categoria);

    public Articulo buscarPorIdyCategoria(Integer id, String categoria);
}

