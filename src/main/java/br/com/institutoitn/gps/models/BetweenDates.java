package br.com.institutoitn.gps.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BetweenDates {

//    private EMessageType type;
    private Long imei;
    private Long veid;

//    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty(value = "dataini")
    @NotNull
    private Date dataini;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty(value = "datafim")
    @NotNull
    private Date datafim;
}


/*
    @JsonProperty("vehicle")

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty(value = "startTime")
    @NotNull
*/
