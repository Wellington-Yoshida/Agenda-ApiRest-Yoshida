package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.service;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dao.ContatoDao;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Contato;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContatoServiceImpl implements ContatoService {

    @Autowired
    private ContatoDao contatoDao;

    @Override
    public void salvar(Contato contato) {
        contatoDao.salvar(contato);
    }

    @Override
    public void atualizar(Contato contato) {
        contatoDao.atualizar(contato);
    }

    @Override
    public void deletar(Long id) {
        contatoDao.deletar(id);
    }

    @Override
    public List<Contato> findAll() {
        return contatoDao.findAll();
    }

    @Override
    public Contato findById(Long id) {
        return contatoDao.findById(id);
    }
}
