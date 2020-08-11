package br.com.fiap.amqp.comanda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.fiap.amqp.comanda.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/comanda/producer")
public class ProducerController {
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> create(@RequestBody String msg,
			UriComponentsBuilder builder) {
		DirectQueue.ProducerSendMessage(msg);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
}
