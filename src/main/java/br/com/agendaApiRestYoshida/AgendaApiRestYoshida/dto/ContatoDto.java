package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dto;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Agenda;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.enums.TipoFone;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ContatoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Nome da Agenda não pode ser vazio.")
    @Size(min = 3, max = 100, message = "Tamanho do nome do contato da agenda deve ter no minimo 3 e no máximo 100 caracteres.")
    private String nomeContato;

    @Column(nullable = false)
    @NotEmpty(message = "Fone não pode ser vazio.")
    @Size(max = 10, message = "Tamanho máximo de número do telefone deve ser 10 digitos.")
    private String fone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoFone tipoFone;

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
