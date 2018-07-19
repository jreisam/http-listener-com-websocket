package br.com.institutoitn.gps.services;

import br.com.institutoitn.gps.models.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    @Autowired
    private SimpMessageSendingOperations webSocketMessaging;
    /*
    Recebe array do dispositivo GPS
    e monta message para enviar ao websocket (sala monitoramento)
    */
    public void SendGPS(TeltonikaFMB920 teltonikaFMB920){
        // mensurar o tempo de criação e envio do message
        long start = System.currentTimeMillis();

        webSocketMessaging.convertAndSend("/monitoramento", teltonikaFMB920);
        // out
        logger.info("GPS IMEI: " + teltonikaFMB920.getImei());
        logger.info("LAT  --> " + teltonikaFMB920.getLat());
        logger.info("LONG --> " + teltonikaFMB920.getLng());
        logger.info("Tempo do envio (ws): " + (System.currentTimeMillis() - start));
     }

}
