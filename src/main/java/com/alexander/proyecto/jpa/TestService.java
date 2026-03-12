package com.alexander.proyecto.jpa;

import java.util.Map;

public interface TestService {

    void registrarUsuario();

    void agregarPedido();

    Map<Object, String> consultarUsuarios();

    Map<Object, String> consultarPedidos();

}
