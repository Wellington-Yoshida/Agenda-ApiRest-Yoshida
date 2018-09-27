package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dao;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Usuario;

public interface UsuarioDao {

    void salvar(Usuario usuario);

    Usuario findByEmail(String email);
}
