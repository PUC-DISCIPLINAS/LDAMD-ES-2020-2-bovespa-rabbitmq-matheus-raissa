package entities;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class StockConnection extends Thread {

    private String host;
    private String topic;
    private String message;

    public StockConnection(String host, String topic, String message) {
        this.host = host;
        this.topic = topic;
        this.message = message;
    }

    @Override
    public void run() {

    	//criando conexao com o servidor
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);

        try {
        	//criando canal para realizar as tarefas
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //declara ativamente uma troca não autodelete sem argumentos extras
            channel.exchangeDeclare("BOLSADEVALORES", BuiltinExchangeType.TOPIC);
            //publicar uma mensagem na fila
            channel.basicPublish("BOLSADEVALORES", topic, null, message.getBytes("UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}