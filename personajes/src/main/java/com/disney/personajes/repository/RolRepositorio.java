package com.disney.personajes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disney.personajes.model.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long>{

	public Optional<Rol> findByNombre(String nombre);
	
}
