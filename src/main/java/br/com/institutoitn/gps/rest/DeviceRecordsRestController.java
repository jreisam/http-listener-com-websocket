package br.com.institutoitn.gps.rest;

import br.com.institutoitn.gps.exceptions.DeviceRecordNotFoundException;
import br.com.institutoitn.gps.persistence.DeviceRecordsRepository;
import br.com.institutoitn.gps.persistence.models.DeviceRecords;
import br.com.institutoitn.gps.services.GpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
public class DeviceRecordsRestController {

    private final GpsService gpsService;

    @Autowired
    private DeviceRecordsRepository deviceRecordsRepository;

    @Autowired
    DeviceRecordsRestController(GpsService gpsService) {
        this.gpsService = gpsService;
    }

    //region CRUD records

    @GetMapping("/records")
    public List<DeviceRecords> retrieveAllDeviceRecords() {
        return deviceRecordsRepository.findAll();
    }

    @GetMapping("/records/{id}")
    public DeviceRecords retrieveDeviceRecord(@PathVariable long id) {
        Optional<DeviceRecords> deviceRecords = deviceRecordsRepository.findById(id);

        if (!deviceRecords.isPresent())
            throw new DeviceRecordNotFoundException("id-" + id);

        return deviceRecords.get();
    }

    @DeleteMapping("/records/{id}")
    public void deleteDeviceRecord(@PathVariable long id) {
        deviceRecordsRepository.deleteById(id);
    }

    @PostMapping("/records")
    public ResponseEntity<Object> createDeviceRecord(@RequestBody DeviceRecords deviceRecords) {
        DeviceRecords savedDeviceRecord = deviceRecordsRepository.save(deviceRecords);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedDeviceRecord.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/records/{id}")
    public ResponseEntity<Object> updateDeviceRecord(@RequestBody DeviceRecords deviceRecords, @PathVariable long id) {

        Optional<DeviceRecords> deviceRecordsOptional = deviceRecordsRepository.findById(id);

        if (!deviceRecordsOptional.isPresent())
            return ResponseEntity.notFound().build();

        deviceRecords.setId(id);

        deviceRecordsRepository.save(deviceRecords);

        return ResponseEntity.noContent().build();
    }


    @RequestMapping(method = RequestMethod.POST, path = "/gps/enviaGPS")
    public ResponseEntity<String> enviaGPSRecord(@RequestBody DeviceRecords deviceRecords) {
        try {
            CompletableFuture<DeviceRecords> retornoGPS = gpsService.EnviarGPSAsync(deviceRecords);
            CompletableFuture.allOf(retornoGPS).join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.createDeviceRecord(deviceRecords);
        return ResponseEntity.ok("Gps enviado: " + deviceRecords.getImei() + ".");
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/records/findBetween/{veid}/{dataIni}/{dataFim}")
    public ResponseEntity<?> findBetween(@PathVariable("veid") Long veid,
                                         @PathVariable("dataIni") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date dataIni,
                                         @PathVariable("dataFim") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date dataFim) {

        return ResponseEntity.ok(deviceRecordsRepository.findAllByVeidAndCreatedDateBetween(veid, dataIni, dataFim));
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/records/findBetweenWithImei/{veid}/{dataIni}/{dataFim}/{imei}")
    public ResponseEntity<?> findBetweenWithImei(@PathVariable("veid") Long veid,
                                                 @PathVariable("dataIni") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date dataIni,
                                                 @PathVariable("dataFim") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date dataFim,
                                                 @PathVariable("imei") Long imei) {

        return ResponseEntity.ok(deviceRecordsRepository.findAllByVeidAndImeiAndCreatedDateBetween(veid, imei, dataIni, dataFim));
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/records/findByVeid/{veid}")
    public ResponseEntity<?> findByVeid(@PathVariable("veid") Long veid) {

        return ResponseEntity.ok(deviceRecordsRepository.findAllByVeid(veid));
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/records/findFirstByVeidOrderByCreatedDate/{veid}")
    public ResponseEntity<?> findFirstByVeidOrderByCreatedDate(@PathVariable("veid") Long veid) {

        return ResponseEntity.ok(deviceRecordsRepository.findFirstByVeidOrderByCreatedDateDesc(veid));
    }


    //endregion
}
