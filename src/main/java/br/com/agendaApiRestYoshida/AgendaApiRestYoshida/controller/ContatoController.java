package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.controller;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Contato;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dto.ContatoDto;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.response.Response;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.service.AgendaService;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
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

   @Autowired
   private AgendaService agendaService;

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
        response.setSucesso("Lista de Contatos");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<Response<ContatoDto>> findById(@PathVariable("id") Long id){
        Response<ContatoDto> response = new Response<ContatoDto>();
        try{
            Contato contato = contatoService.findById(id);
            response.setData(converteContatoParaContatoDto(contato));
        }catch (Exception e){
            response.getErrors().add("Não foi encontrato contato com o id: " + id +  ", por favor verifique.");
            return ResponseEntity.badRequest().body(response);
        }finally {
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping(value = "/cadastra/Contato")
    public ResponseEntity<Response<ContatoDto>> cadastra(@Valid @RequestBody ContatoDto contatoDto, BindingResult result)
                                                         throws NoSuchAlgorithmException {
        Response<ContatoDto> response = new Response<ContatoDto>();
        Contato contato = converterContatoDtoParaContato(contatoDto);

        if(result.hasErrors()){
            for(ObjectError erros : result.getAllErrors()){
                response.getErrors().add(erros.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(response);
        }

        contatoService.salvar(contato);
        response.setData(converteContatoParaContatoDto(contato));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/deletaContato/{id}")
    public ResponseEntity<Response<ContatoDto>> deletar(@PathVariable("id") Long id){
        Response<ContatoDto> response = new Response<ContatoDto>();
        try {
            contatoService.deletar(id);
        }catch (Exception e){
            response.getErrors().add("Não foi encontrato contato com o id: " + id + " para ser removido.");
            return ResponseEntity.badRequest().body(response);
        }finally {
            response.setSucesso(MENSAGEM_REMOVIDO_COM_SUCESSO);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping(value = "/atualizaContato/{id}")
    public ResponseEntity<Response<ContatoDto>> atualizaContato(@Valid @RequestBody ContatoDto contatoDto,
                                                                BindingResult result, @PathVariable("id") Long id){
        Response<ContatoDto> response = new Response<ContatoDto>();

        Contato contato = converterContatoDtoParaContato(contatoDto);
        contato.setId(id);
        if(result.hasErrors()){
            for(ObjectError erros : result.getAllErrors()){
                response.getErrors().add(erros.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(response);
        }
        contatoService.atualizar(contato);
        response.setData(converteContatoParaContatoDto(contato));
        response.setSucesso(MENSAGEM_ATUALIZADO_COM_SUCESSO);
        return ResponseEntity.ok(response);
    }

    private ContatoDto converteContatoParaContatoDto(Contato contato) {
        ContatoDto contatoDto = new ContatoDto();
        contatoDto.setId(contato.getId());
        contatoDto.setNomeContato(contato.getNomeContato());
        contatoDto.setTipoFone(contato.getTipoFone());
        contatoDto.setFone(contato.getFone());
        contatoDto.setAgendaId(contato.getAgenda().getId());
        contatoDto.setAgendaNome(contato.getAgenda().getNome());
        return contatoDto;
    }

    private Contato converterContatoDtoParaContato(ContatoDto contatoDto) {
        Contato contato = new Contato();
        contato.setNomeContato(contatoDto.getNomeContato());
        contato.setTipoFone(contatoDto.getTipoFone());
        contato.setFone(contatoDto.getFone());
        contato.setAgenda(agendaService.findById(contatoDto.getAgendaId()));
        return contato;
    }

    private List<ContatoDto> converteContatoParaContatoDto(List<Contato> contatos, List<ContatoDto> contatoDtoList) {
        for(Contato contato : contatos){
            ContatoDto contatoDto = new ContatoDto();
            contatoDto.setId(contato.getId());
            contatoDto.setFone(contato.getFone());
            contatoDto.setNomeContato(contato.getNomeContato());
            contatoDto.setTipoFone(contato.getTipoFone());
            contatoDto.setAgendaId(contato.getAgenda().getId());
            contatoDto.setAgendaNome(contato.getAgenda().getNome());
            contatoDtoList.add(contatoDto);
        }
        return contatoDtoList;
    }
}
