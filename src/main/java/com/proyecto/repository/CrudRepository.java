package com.proyecto.repository;

import java.util.List;

public interface CrudRepository<T, ID> {

    void guardar(T entidad);
    T buscarPorId(ID id);
    List<T> buscarTodos();
    void actualizar(T entidad);
    void eliminar(ID id);
}
