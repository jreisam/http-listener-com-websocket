package br.com.institutoitn.gps.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BetweenDates {

//    private EMessageType type;
    private Long imei;
    private Long veid;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty(value = "data inicial")
    @NotNull
    private Date dataini;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty(value = "data final")
    @NotNull
    private Date datafim;
}


/*
    @JsonProperty("vehicle")

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty(value = "startTime")
    @NotNull
*/
