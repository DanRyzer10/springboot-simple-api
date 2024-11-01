package com.danam.springcloud.msvc.controllers;

import com.danam.springcloud.msvc.models.entity.Usuario;
import com.danam.springcloud.msvc.services.UsuarioService;
import feign.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/")
    public List<Usuario> findAll(){
        return usuarioService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id){
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Usuario usuario, BindingResult result){
        if(result.hasErrors()){
            return validateForm(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    private static ResponseEntity<Map<String, String>> validateForm(BindingResult result) {
        Map<String,String> errors = new HashMap<>();
        result.getFieldErrors().forEach(er -> {
           errors.put(er.getField(),"El campo" + er.getField() + " " + er.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update( @Valid @RequestBody Usuario usuario,BindingResult result, @PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if(result.hasErrors()){
            return validateForm(result);
        }
        if(usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuarioDB = usuarioOptional.get();
        usuarioDB.setNombre(usuario.getNombre());
        usuarioDB.setEmail(usuario.getEmail());
        usuarioDB.setPassword(usuario.getPassword());
        return ResponseEntity.ok(usuarioService.update(usuarioDB,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        usuarioService.DeleteById(id);
        return ResponseEntity.noContent().build();
    }
}
