package com.cibertec.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.modelos.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
    @Query("SELECT c FROM Categoria c WHERE c.id_categoria = :id")
    public Categoria buscarPorId(Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO CATEGORIA (nombre) VALUES (:nombre)", nativeQuery = true)
    void agregar(String nombre);

    @Transactional
    @Modifying
    @Query("UPDATE Categoria c SET c.nombre = :nombre WHERE c.id_categoria = :id")
    void actualizar(Integer id, String nombre);

    @Transactional
    @Modifying
    @Query("DELETE FROM Categoria c WHERE c.id_categoria = :id")
    void eliminar(Integer id);

    @Query("SELECT MAX(c.id_categoria) FROM Categoria c WHERE c.nombre = :nombre")
    Integer nuevoId(String nombre);

    @Query("SELECT c FROM Categoria c WHERE c.nombre LIKE %:nombre%")
    public List<Categoria> buscarPorNombre(String nombre);

    @Query("SELECT c.nombre FROM Categoria c")
    List<String> listarTodosLosNombres();
    
    Categoria findByNombre(String nombre);
}
