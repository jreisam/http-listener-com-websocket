package br.com.institutoitn.gps.persistence.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DeviceRecords {
	@Id
	@GeneratedValue
	private Long id;

	@CreatedDate
    @Column(updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

	private Long imei;
    private Long veid;
	private double lat;
	private double lng;


	public DeviceRecords() {
		super();
	}

	public DeviceRecords(Long id, Long imei, double lat, double lng, Long veid, Date createdDate) {
		super();
		this.id = id;
		this.imei = imei;
		this.lat = lat;
		this.lng = lng;
		this.veid = veid;
		this.createdDate = createdDate;
	}
		
}
