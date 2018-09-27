package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dao;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UsuarioDaoImpl implements UsuarioDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void salvar(Usuario usuario) {
        entityManager.persist(usuario);
    }

    @Override
    public Usuario findByEmail(String email) {
        return entityManager.createQuery("select u from Usuario u where u.email = :email", Usuario.class)
                            .setParameter("email", email)
                            .getSingleResult();
    }
}
