package br.com.fiap.amqp.comanda.configuracao;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

public class Configuracao {
    private static CachingConnectionFactory connectionFactory;

    public static CachingConnectionFactory getConnection(){

        if(connectionFactory == null){
            connectionFactory = new CachingConnectionFactory("finch.rmq.cloudamqp.com");
            connectionFactory.setUsername("dahrcinj");
            connectionFactory.setPassword("VMsq8d3CyMfa1gouRm5O4BudEOhZBo0A");
            connectionFactory.setVirtualHost("dahrcinj");

            //Recommended settings
            connectionFactory.setRequestedHeartBeat(30);
            connectionFactory.setConnectionTimeout(30000);
        }

        return connectionFactory;
    }
}
