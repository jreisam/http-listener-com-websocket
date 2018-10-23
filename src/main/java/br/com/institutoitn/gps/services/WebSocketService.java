package br.com.institutoitn.gps.services;

import br.com.institutoitn.gps.models.*;

import br.com.institutoitn.gps.persistence.models.DeviceRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
    public void SendGPS(DeviceRecords deviceRecords){
        // mensurar o tempo de criação e envio do message
        long start = System.currentTimeMillis();
        // TODO: pegar o vehicle_id referente ao IMEI do GPS, e incluir, ou incluir ao persistir
        deviceRecords.setVeid((long)1);
        webSocketMessaging.convertAndSend("/monitoramento", deviceRecords);
        // out
        logger.info("GPS IMEI: " + deviceRecords.getImei());
        logger.info("VEHICLE_ID --> " + deviceRecords.getVeid());
        logger.info("LAT  --> " + deviceRecords.getLatitude());
        logger.info("LONG --> " + deviceRecords.getLongitude());
        logger.info("Tempo do envio (ws): " + (System.currentTimeMillis() - start));
     }

//    @MessageMapping("/{parametro}")
//    @SendTo("/monitoramento/{parametro}") (para redirecionar para outro método
//    public void String(@DestinationVariable String parametro) {
//        webSocketMessaging.convertAndSend("/" + parametro);
//    }

}
