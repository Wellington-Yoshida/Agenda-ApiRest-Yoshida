package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dao;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Contato;

import java.util.List;

public interface ContatoDao {

    void salvar(Contato contato);
    void atualizar(Contato contato);
    void deletar(Long id);
    List<Contato> findAll();
    Contato findById(Long id);
}
