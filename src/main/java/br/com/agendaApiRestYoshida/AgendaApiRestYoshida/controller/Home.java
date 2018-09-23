package br.com.agendaApiRestYoshida.AgendaApiRestYoshida.controller;

import br.com.agendaApiRestYoshida.AgendaApiRestYoshida.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class Home {

    public Home(){}

    @GetMapping("/")
    public ResponseEntity<Response<String>> home(){
        Response<String> responde = new Response<String>();
        responde.setData("Home");
        return ResponseEntity.ok(responde);
    }

    @GetMapping("/home")
    public ResponseEntity<Response<String>> homeHome(){
        Response<String> responde = new Response<String>();
        responde.setData("Home");
        return ResponseEntity.ok(responde);
    }
}
