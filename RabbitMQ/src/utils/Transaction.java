package utils;

import entities.*;

import java.time.LocalDateTime;
import java.util.Vector;


public class Transaction {
	
    private static Vector<String[]> transactions = new Vector<String[]>();

    public synchronized static void store(String host, String[] sale, String[] purchase) {

    	//gera uma transacao
        String[] data = new String[6];

        data[0] = LocalDateTime.now().toString();
        data[1] = sale[0];
        data[2] = Integer.parseInt(sale[1]) < Integer.parseInt(purchase[1]) ? sale[1] : purchase[1];
        data[3] = sale[2];
        data[4] = sale[3];
        data[5] = purchase[3];

        //adiciona uma transacao
        transactions.add(data);

        String topic = "transacao." + data[1];
        String message = "<" + data[0] + "; " + data[4] + "; " + data[5] + "; " + data[2] + "; " + data[3] + ">";

        //emite a transacao
        BrokerConnection bEmit = new BrokerConnection(host, topic, message);
        bEmit.start();
    }
}