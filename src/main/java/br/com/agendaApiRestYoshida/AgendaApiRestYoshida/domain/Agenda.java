package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "AGENDA")
public class Agenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
    private List<Contato> contatosList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Contato> getContatosList() {
        return contatosList;
    }

    public void setContatosList(List<Contato> contatosList) {
        this.contatosList = contatosList;
    }
}
