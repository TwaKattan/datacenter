package com.iot.datacenter.datacenter.controller;

import com.iot.datacenter.datacenter.dto.PaginationRequestDto;
import com.iot.datacenter.datacenter.dto.ResponseDto;
import com.iot.datacenter.datacenter.exception.ExceptionUtil;
import com.iot.datacenter.datacenter.model.DeviceResult;
import com.iot.datacenter.datacenter.service.DeviceResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/dataResult.json")
public class DataResultController {

    private Logger logger = LoggerFactory.getLogger(DataResultController.class);

    @Autowired
    private DeviceResultService deviceResultService;

    /**
     * This method help us to save the sensro data into database this called the creation process
     * Endpoint :- http://localhost:8080/dataResult.json/createDeviceResult
     * Request Payload :- {"deviceId":"1589-97827-878278-454545","temperature":"1548","humidity":"1545"}
     * */
    @RequestMapping(value = "/createDeviceResult", method = RequestMethod.POST)
    public ResponseEntity<?> createDeviceResult(@RequestBody DeviceResult deviceResult) {
        logger.info("##### createDeviceResult Start");
        try {
            logger.info("##### createDeviceResult End");
            return new ResponseEntity<>(new ResponseDto("SUCCESS", "Device Result Added",
                this.deviceResultService.createDeviceResult(deviceResult)), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("An error occurred while createDeviceResult file", ExceptionUtil.getRootCauseMessage(ex));
            return new ResponseEntity<>(new ResponseDto("ERROR", "Some error accrue contact to support."), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method help us to fetch the date from the database and show on the ui grid this process call fetch process
     * Endpoint :- http://localhost:8080/dataResult.json/getAllDeviceResultInPagination
     * Endpoint :- http://localhost:8080/dataResult.json/getAllDeviceResultInPagination
     * */
    @RequestMapping(value = "/getAllDeviceResultInPagination", method = RequestMethod.POST)
    public ResponseDto getAllDeviceResultInPagination(@RequestBody PaginationRequestDto paginationRequest) {
        ResponseDto response;
        try {
            logger.info("Request for getAllDeviceResultInPagination " + paginationRequest);
            return this.deviceResultService.getAllDeviceResultInPagination(paginationRequest);
        } catch (Exception ex) {
            logger.info("Error during login " + ExceptionUtil.getRootCause(ex));
            response = new ResponseDto ("ERROR", "Exception in system contact with support.",
                    ExceptionUtil.getRootCauseMessage(ex));
        }
        return response;
    }

}
