package br.com.institutoitn.gps.services;

import br.com.institutoitn.gps.persistence.models.DeviceRecords;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class GpsService {
    private WebSocketService webSocketService;

    public GpsService(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @Async
    public CompletableFuture<DeviceRecords> EnviarGPSAsync(DeviceRecords deviceRecords) throws InterruptedException{
        CompletableFuture<DeviceRecords> retornoGpsAtual = CompletableFuture.completedFuture(deviceRecords);

        webSocketService.SendGPS(deviceRecords);

        return retornoGpsAtual;
    }

}
