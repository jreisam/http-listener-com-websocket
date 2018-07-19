package br.com.institutoitn.gps.services;

import br.com.institutoitn.gps.models.TeltonikaFMB920;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class GpsService {
    private WebSocketService webSocketService;

    public GpsService(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

/*    @Async
    public CompletableFuture<gps> EnviarWSGPS( gps gpsAtual) throws InterruptedException{
        CompletableFuture<gps> retornoGpsAtual = CompletableFuture.completedFuture(gpsAtual);

        Thread.sleep(2000L);
        webSocketAssistsDAO.SendMsgTracking(gpsAtual);

        return retornoGpsAtual;
    }*/

    @Async
    public CompletableFuture<TeltonikaFMB920> EnviarGPSAsync(TeltonikaFMB920 teltonikaFMB920) throws InterruptedException{
        CompletableFuture<TeltonikaFMB920> retornoGpsAtual = CompletableFuture.completedFuture(teltonikaFMB920);

//        Thread.sleep(2000L);
        webSocketService.SendGPS(teltonikaFMB920);

        return retornoGpsAtual;
    }

}
