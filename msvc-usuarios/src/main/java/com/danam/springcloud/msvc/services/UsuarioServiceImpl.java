package com.danam.springcloud.msvc.services;

import com.danam.springcloud.msvc.models.entity.Usuario;
import com.danam.springcloud.msvc.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioServiceImpl implements  UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true) // springboot package
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }
    @Override
    public Usuario update(Usuario usuario, Long id) {
        Optional<Usuario> usuarioOptional = repository.findById(id);
        Usuario usuarioDB = usuarioOptional.get();
        usuarioDB.setNombre(usuario.getNombre());
        usuarioDB.setEmail(usuario.getEmail());
        return repository.save(usuarioDB);
    }
    @Transactional
    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }
    @Transactional
    @Override
    public void DeleteById(Long id) {
        repository.deleteById(id);

    }
}
