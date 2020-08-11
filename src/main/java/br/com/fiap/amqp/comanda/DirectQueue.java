package br.com.fiap.amqp.comanda;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.amqp.comanda.configuracao.Configuracao;


public class DirectQueue {

    //public static void main(String[] args) {
	public static void ProducerSendMessage (String msg) {
        //Set up queue, exchanges and bindings
        RabbitAdmin admin = new RabbitAdmin(Configuracao.getConnection());
        
        Queue queueCommandPagtos = new Queue("comanda.virtual.pagamentos");
        
        admin.declareQueue(queueCommandPagtos);
        
        DirectExchange exchange = new DirectExchange("exchange.comanda.virtual");
        admin.declareExchange(exchange);
        
        admin.declareBinding(BindingBuilder.bind(queueCommandPagtos).to(exchange).with("comanda.virtual.pagamentos"));

        RabbitTemplate template = new RabbitTemplate(Configuracao.getConnection());

        template.convertAndSend("exchange.comanda.virtual", "comanda.virtual.pagamentos", msg);
       
    }
	
	public static void ConsumerMsgToSlack(String msg) {
		//call slack
	}
}
