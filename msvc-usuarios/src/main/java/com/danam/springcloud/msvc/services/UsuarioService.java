package com.danam.springcloud.msvc.services;

import com.danam.springcloud.msvc.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    Usuario update(Usuario usuario, Long id);
    void DeleteById(Long id);
}
