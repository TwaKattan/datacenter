package com.iot.datacenter.datacenter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "DeviceResult")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceResult {

    @GenericGenerator(
        name = "deviceResultSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "deviceResult_source_Seq"),
            @Parameter(name = "initial_value", value = "1000"),
            @Parameter(name = "increment_size", value = "1")
        }
    )
    @Id
    @GeneratedValue(generator = "deviceResultSequenceGenerator")
    private Long id;
    @Column(length = 1000, nullable = false)
    private String deviceId;
    @Column(length = 1000)
    private String temperature;
    @Column(length = 1000)
    private String humidity;
    @Column(nullable = false)
    private Timestamp dateCreated;

    public DeviceResult() {
    }

    @PrePersist
    protected void onCreate() {
        this.setDateCreated(new Timestamp(System.currentTimeMillis()));
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTemperature() {
        return temperature;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
