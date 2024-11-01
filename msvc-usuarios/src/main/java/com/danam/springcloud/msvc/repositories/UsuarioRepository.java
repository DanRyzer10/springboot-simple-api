package com.danam.springcloud.msvc.repositories;

import com.danam.springcloud.msvc.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
    Usuario findUsuarioByEmail(String email);
}
