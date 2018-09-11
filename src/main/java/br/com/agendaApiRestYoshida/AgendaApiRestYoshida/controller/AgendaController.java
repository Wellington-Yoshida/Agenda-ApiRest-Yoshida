package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.controller;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.domain.Agenda;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.dto.AgendaDto;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.response.Response;
import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/Agenda")
@CrossOrigin(origins = "*")
public class AgendaController {

    private final String MENSAGEM_SUCESSO_AGENDA = "Agenda Cadastrado com Sucesso.";
    private final String MENSAGEM_ATUALIZADO_COM_SUCESSO = "Agenda Atualizada com sucesso.";
    private final String MENSAGEM_REMOVIDO_COM_SUCESSO = "Agenda removido com sucesso.";

    @Autowired
    private AgendaService agendaService;

    public AgendaController() {
    }

    @GetMapping(value = "/listarAll")
    public ResponseEntity<Response<List<AgendaDto>>> listar() {
        Response<List<AgendaDto>> response = new Response<List<AgendaDto>>();
        List<Agenda> agendas = agendaService.findAll();

        if(agendas.isEmpty()){
            response.getErrors().add("Nenhuma empresa encontrada.");
            return ResponseEntity.badRequest().body(response);
        }

        List<AgendaDto> agendaDtoList = new ArrayList<AgendaDto>();
        response.setData(converterAgendaParaAgendaDto(agendas, agendaDtoList));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/buscarAgenda/{id}")
    public ResponseEntity<Response<AgendaDto>> buscaAgendaPeloId(@PathVariable("id") Long id){
        Response<AgendaDto> response = new Response<AgendaDto>();
        try {
            Agenda agenda = agendaService.findById(id);

            response.setData(converterAgendaParaAgendaDto(agenda));

        }catch (Exception e){
            response.getErrors().add("Nenhuma empresa encontrada com o id: " + id);
            return ResponseEntity.badRequest().body(response);
        }finally {
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping(value = "/cadastrarAgenda")
    public ResponseEntity<Response<AgendaDto>> cadastrar(@Valid @RequestBody AgendaDto agendaDto, BindingResult result) throws NoSuchAlgorithmException {
        Response<AgendaDto> response = new Response<AgendaDto>();

        Agenda agenda = converteDtoParaAgenda(agendaDto);
        if(result.hasErrors()){
            for (ObjectError erros : result.getAllErrors()) {
                response.getErrors().add(erros.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(response);
        }

        agendaService.salvar(agenda);
        response.setData(converterAgendaParaAgendaDto(agenda));
        response.setSucesso(MENSAGEM_SUCESSO_AGENDA);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/atualiza/{id}")
    public ResponseEntity<Response<AgendaDto>> atualizar(@PathVariable("id") Long id, @Valid @RequestBody AgendaDto agendaDto,
                                                         BindingResult result) throws ParseException {
        Response<AgendaDto> response = new Response<AgendaDto>();
        Agenda agenda = converteDtoParaAgenda(agendaDto);
        agenda.setId(id);
        if(result.hasErrors()){
            for (ObjectError erros : result.getAllErrors()) {
                response.getErrors().add(erros.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(response);
        }
        agendaService.atualizar(agenda);
        response.setData(converterAgendaParaAgendaDto(agenda));
        response.setSucesso(MENSAGEM_ATUALIZADO_COM_SUCESSO);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping(value = "/remover/{id}")
    public ResponseEntity<Response<AgendaDto>> remover(@PathVariable("id") Long id){
        Response<AgendaDto> response = new Response<AgendaDto>();
        agendaService.deletar(id);
        response.setSucesso(MENSAGEM_REMOVIDO_COM_SUCESSO);
        return ResponseEntity.ok(response);
    }

    private Agenda converteDtoParaAgenda(AgendaDto agendaDto) {
        Agenda agenda = new Agenda();
        agenda.setNome(agendaDto.getNome());
        return agenda;
    }

    private List<AgendaDto> converterAgendaParaAgendaDto(List<Agenda> agendas, List<AgendaDto> agendaDtoList) {
        agendaDtoList.clear();
        for(Agenda agenda : agendas){
            AgendaDto agendaDto = new AgendaDto();

            agendaDto.setId(agenda.getId());
            agendaDto.setNome(agenda.getNome());

            agendaDtoList.add(agendaDto);
        }
        return agendaDtoList;
    }

    private AgendaDto converterAgendaParaAgendaDto(Agenda agenda) {
            AgendaDto agendaDto = new AgendaDto();
            agendaDto.setId(agenda.getId());
            agendaDto.setNome(agenda.getNome());
        return agendaDto;
    }
}
