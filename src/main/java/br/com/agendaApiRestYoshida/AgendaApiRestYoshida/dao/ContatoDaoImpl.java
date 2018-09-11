package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dao;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ContatoDaoImpl implements ContatoDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void salvar(Contato contato) {
        entityManager.persist(contato);
    }

    @Override
    public void atualizar(Contato contato) {
        entityManager.merge(contato);
    }

    @Override
    public void deletar(Long id) {
        entityManager.remove(entityManager.getReference(Contato.class, id));
    }

    @Override
    public List<Contato> findAll() {
        return entityManager.createQuery("select c from Contato c", Contato.class).getResultList();
    }

    @Override
    public Contato findById(Long id) {
        return entityManager.createQuery("select c from Contato c where c.id = :id", Contato.class)
                            .setParameter("id", id)
                            .getSingleResult();
    }
}
