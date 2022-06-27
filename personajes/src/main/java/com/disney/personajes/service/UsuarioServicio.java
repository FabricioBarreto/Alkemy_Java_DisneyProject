package com.disney.personajes.service;

import java.util.List;

import com.disney.personajes.controller.dto.UsuarioRegistroDTO;
import com.disney.personajes.model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();
	
}
