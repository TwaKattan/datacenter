package com.iot.datacenter.datacenter.repository;

import com.iot.datacenter.datacenter.model.DeviceResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataResultRepository extends CrudRepository<DeviceResult, Long> {
}
