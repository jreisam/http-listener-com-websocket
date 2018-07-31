package br.com.institutoitn.gps.persistence;

import br.com.institutoitn.gps.persistence.models.DeviceRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRecordsRepository extends JpaRepository<DeviceRecords, Long> {
// possíveis overrides
}
