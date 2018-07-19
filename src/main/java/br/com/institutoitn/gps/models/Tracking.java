package br.com.institutoitn.gps.models;

import lombok.Data;

@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Tracking {

  private Coordinates coordinates;
}


/*

**sockets models**
        export class SocketResponse {
    data: any;
    type: SocketType;
}

export class Sumario {
    full_occurrences: number;
    progress_occurrences: number;
    ai_alerts: number;
    vehicle_alerts: number;
}

export enum SocketType {
    SUMARIO,
    V_ALERT,
    AI_ALERT
}*/
