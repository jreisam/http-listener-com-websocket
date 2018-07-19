package br.com.institutoitn.gps.models;

import lombok.Data;

@Data
public class Summary {
    private int full_occurrences;
    private int progress_occurrences;
    private int ai_alerts;
    private int vehicle_alerts;
}
