package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.enums.TipoUsuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL")
    @Email(message = "*Por favor, forneça um email válido")
    @NotEmpty(message = "*Por favor, forneça um email")
    private String email;

    @Column(name = "PASSWORD")
    @Length(min = 5, message = "*Sua senha deve ter pelo menos 5 caracteres")
    @NotEmpty(message = "*Por favor, forneça sua senha")
    private String password;

    @Column(name = "NOME")
    @NotEmpty(message = "*Por favor, forneça seu nome")
    private String nome;

    @Column(name = "SOBRE_NOME")
    @NotEmpty(message = "*Por favor, forneça seu sobrenome")
    private String sobreNomee;

    @Column(name = "active")
    private boolean active;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipoUsuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNomee() {
        return sobreNomee;
    }

    public void setSobreNomee(String sobreNomee) {
        this.sobreNomee = sobreNomee;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
