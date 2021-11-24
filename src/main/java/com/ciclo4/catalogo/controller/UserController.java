package com.ciclo4.catalogo.controller;

import com.ciclo4.catalogo.model.User;
import com.ciclo4.catalogo.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que define la API para captura, consulta y validación de datos.
 * La define como controlador REST y establece la URL base com '/api/user'.
 * @author William David Ramírez Blauvelt
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {
    
    /**
     * Indica que los servicios se deben conectan automaticamente con SpringBoot
     */
    @Autowired
    private UserService userService;
    
    /**
     * Establece URL para consulta de datos de todos los usuarios creados
     * @return: id, nombre, correo y contraseña
     */
    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAll();
    }

    /**
     * Establece URL para carga de nuevos usuarios
     * @param: nombre, correo y contraseña
     * @return id, nombre, correo y contraseña
     */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User registrar(@RequestBody User user) {
        return userService.registrar(user);
    }

    /**
     * Establece URL para validar existencia de combinación correo y contraseña
     * (para autenticación de usuario en aplicación)
     * @param: correo y contraseña
     * @return id, nombre, correo y contraseña
     */
    @GetMapping("/{email}/{password}")
    public User autenticarUsuario(@PathVariable("email") String email, @PathVariable("password") String password) {
        return userService.autenticarUsuario(email, password);
    }

    /**
     * Establece URL para consulta de correos existentes (para evitar duplicados)
     * @param: correo
     * @return true o false
     */
    @GetMapping("/{email}")
    public boolean existeEmail(@PathVariable("email") String email) {
        return userService.existeEmail(email);
    }
}
