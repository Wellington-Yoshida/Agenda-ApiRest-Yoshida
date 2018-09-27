package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.service;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Usuario;

public interface UsuarioService {

    void salvar(Usuario usuario);

    Usuario findByEmail(String email);
}
