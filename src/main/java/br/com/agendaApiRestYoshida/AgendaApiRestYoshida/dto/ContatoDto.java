package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private String tipoFone;

    private Long agendaId;

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

    public String getTipoFone() {
        return tipoFone;
    }

    public void setTipoFone(String tipoFone) {
        this.tipoFone = tipoFone;
    }

    public Long getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Long agendaId) {
        this.agendaId = agendaId;
    }
}
