package entities;

import gui.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.rabbitmq.client.*;

public class BrokerReceive extends Thread {

    private String host;
    private String topic;

    public BrokerReceive(String host, String topic) {
        this.host = host;
        this.topic = topic;
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
            //declarando fila para consumo
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, "BOLSADEVALORES", topic);

            //utilizando a interface para armazenar em buffer as mensagens enviadas
            DeliverCallback deliveryCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                String topic = delivery.getEnvelope().getRoutingKey();

                String[] splitTopic = topic.split("\\.");
                String[] splitMessage = message.split(">|;|<");

                String messageFormat;

                if (splitMessage.length > 5) {
                    messageFormat = splitTopic[0] + " " + splitTopic[1].toUpperCase() + " | data: " + splitMessage[1]
                            + ", crr_vd: " + splitMessage[2] + ", crr_cp: " + splitMessage[3] + ", qtde: "
                            + splitMessage[4] + ", val: " + splitMessage[5];
                } else {
                    messageFormat = splitTopic[0] + " " + splitTopic[1].toUpperCase() + " | qtde: " + splitMessage[1]
                            + ", val: " + splitMessage[2] + ", crr: " + splitMessage[3];
                }

                JLabel messageL = new JLabel(messageFormat);
                messageL.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                //mostrar mensagem na interface
                BrokerGUI.actionsP.add(messageL);
                BrokerGUI.actionsP.revalidate();
                BrokerGUI.actionsP.repaint();
            };
            //inicializando um consumidor nao local e nao exclusivo
            channel.basicConsume(queueName, true, deliveryCallback, consumerTag -> { });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}