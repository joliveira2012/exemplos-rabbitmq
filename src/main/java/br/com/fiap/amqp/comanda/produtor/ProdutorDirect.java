package br.com.fiap.amqp.comanda.produtor;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import br.com.fiap.amqp.comanda.configuracao.Configuracao;

public class ProdutorDirect {

    public static void main(String[] args) {

        //Set up queue, exchanges and bindings
        RabbitAdmin admin = new RabbitAdmin(Configuracao.getConnection());
        
        Queue queueCommandPagtos = new Queue("comanda.virtual.pagamentos");
        
        admin.declareQueue(queueCommandPagtos);
        
        DirectExchange exchange = new DirectExchange("exchange.comanda.virtual");
        admin.declareExchange(exchange);
        
        admin.declareBinding(BindingBuilder.bind(queueCommandPagtos).to(exchange).with("comanda.virtual.pagamentos"));

        RabbitTemplate template = new RabbitTemplate(Configuracao.getConnection());

        template.convertAndSend("exchange.comanda.virtual", "comanda.virtual.pagamentos", "Aqui entra a mensagem 2!");
       
    }
}
