package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.controller;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Contato;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dto.ContatoDto;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.response.Response;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/Contato")
@CrossOrigin(origins = "*")
public class ContatoController {

    private final String MENSAGEM_SUCESSO_CONTATO = "Contato Cadastrado com Sucesso.";
    private final String MENSAGEM_ATUALIZADO_COM_SUCESSO = "Contato Atualizada com sucesso.";
    private final String MENSAGEM_REMOVIDO_COM_SUCESSO = "Contato removido com sucesso.";

    @Autowired
    private ContatoService contatoService;

    public ContatoController() {
    }

    @GetMapping("/listarAll")
    public ResponseEntity<Response<List<ContatoDto>>> findAll(){
        Response<List<ContatoDto>> response = new Response<List<ContatoDto>>();
        List<Contato> contatos = new ArrayList<Contato>();
        contatos = contatoService.findAll();

        if(contatos.isEmpty()){
            response.getErrors().add("Nenhum contato encontrado.");
            return ResponseEntity.badRequest().body(response);
        }

        List<ContatoDto> contatoDtoList = new ArrayList<ContatoDto>();
        response.setData(converteContatoParaContatoDto(contatos, contatoDtoList));
        return ResponseEntity.ok(response);
    }

    private List<ContatoDto> converteContatoParaContatoDto(List<Contato> contatos, List<ContatoDto> contatoDtoList) {
        for(Contato contato : contatos){
            ContatoDto contatoDto = new ContatoDto();

            contatoDto.setId(contato.getId());
            contatoDto.setFone(contato.getFone());
            contatoDto.setNomeContato(contato.getNomeContato());
            contatoDto.setTipoFone(contato.getTipoFone());
            contatoDto.setAgenda(contato.getAgenda());

            contatoDtoList.add(contatoDto);
        }
        return contatoDtoList;
    }
}
