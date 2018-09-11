package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AgendaDto {

    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Nome da Agenda não pode ser vazio.")
    @Size(min = 3, max = 100, message = "Tamanho do nome da agenda deve ter no minimo 3 e no máximo 100 caracteres.")
    private String nome;

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
}
