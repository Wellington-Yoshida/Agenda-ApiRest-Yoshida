package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dao;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Agenda;

import java.util.List;

public interface AgendaDao {

    void salvar(Agenda agenda);
    void atualizar(Agenda agenda);
    void deletar(Long id);
    List<Agenda> findAll();
    Agenda findById(Long id);
}
