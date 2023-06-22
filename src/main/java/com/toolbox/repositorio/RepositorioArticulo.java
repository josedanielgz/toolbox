package com.toolbox.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.toolbox.modelo.Articulo;



public interface RepositorioArticulo extends CrudRepository<Articulo, Integer> {


    List<Articulo> findByCategoria(String categoria);
    Articulo findByIdAndCategoria(Integer id, String categoria);

}
