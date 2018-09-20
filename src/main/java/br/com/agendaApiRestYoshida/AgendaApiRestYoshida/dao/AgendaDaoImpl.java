package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dao;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Agenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AgendaDaoImpl implements AgendaDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void salvar(Agenda agenda) {
        entityManager.persist(agenda);
    }

    @Override
    public void atualizar(Agenda agenda) {
        entityManager.merge(agenda);
    }

    @Override
    public void deletar(Long id) {
        entityManager.remove(entityManager.getReference(Agenda.class, id));
    }

    @Override
    public List<Agenda> findAll() {
        return entityManager.createQuery("select a from Agenda a order by id", Agenda.class).getResultList();
    }

    @Override
    public Agenda findById(Long id) {
        return entityManager.createQuery("select a from Agenda a where a.id = :id", Agenda.class)
                            .setParameter("id", id)
                            .getSingleResult();
    }
}
