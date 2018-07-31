package br.com.institutoitn.gps.persistence;

import br.com.institutoitn.gps.persistence.models.DeviceRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface DeviceRecordsRepository extends JpaRepository<DeviceRecords, Long> {
// possíveis overrides
    Collection<DeviceRecords> findAllByImeiOrVeidOrCreatedDateBetweenOrderByCreatedDate(Long imei, Long veid, Date dataini, Date datafim);
}
