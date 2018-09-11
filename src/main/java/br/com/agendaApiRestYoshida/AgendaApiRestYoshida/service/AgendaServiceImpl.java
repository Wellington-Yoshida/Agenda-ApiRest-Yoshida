package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.service;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dao.AgendaDao;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Agenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgendaServiceImpl implements AgendaService{

    @Autowired
    private AgendaDao agendaDao;

    @Override
    public void salvar(Agenda agenda) {
        agendaDao.salvar(agenda);
    }

    @Override
    public void atualizar(Agenda agenda) {
        agendaDao.atualizar(agenda);
    }

    @Override
    public void deletar(Long id) {
        agendaDao.deletar(id);
    }

    @Override
    public List<Agenda> findAll() {
        return agendaDao.findAll();
    }

    @Override
    public Agenda findById(Long id) {
        return agendaDao.findById(id);
    }
}
