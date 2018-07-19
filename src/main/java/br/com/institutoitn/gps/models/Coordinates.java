package br.com.institutoitn.gps.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Coordinates {
    private Double longitude;
    private Double latitude;
}
