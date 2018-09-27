package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.service;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dao.UsuarioDao;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void salvar(Usuario usuario) {
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        usuario.setActive(true);
        usuarioDao.salvar(usuario);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioDao.findByEmail(email);
    }
}
