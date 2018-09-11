package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.enums.TipoFone;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "CONTATO")
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeContato;

    @Column(nullable = false)
    private String fone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoFone tipoFone;

    @ManyToOne
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public TipoFone getTipoFone() {
        return tipoFone;
    }

    public void setTipoFone(TipoFone tipoFone) {
        this.tipoFone = tipoFone;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
}
