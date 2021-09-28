package com.iot.datacenter.datacenter.service.impl;

import com.iot.datacenter.datacenter.dto.PaginationRequestDto;
import com.iot.datacenter.datacenter.dto.ResponseDto;
import com.iot.datacenter.datacenter.exception.ExceptionUtil;
import com.iot.datacenter.datacenter.model.DeviceResult;
import com.iot.datacenter.datacenter.repository.DataResultRepository;
import com.iot.datacenter.datacenter.service.DeviceResultService;
import com.iot.datacenter.datacenter.service.query.QueryServices;
import com.iot.datacenter.datacenter.service.query.QueryUtil;
import com.iot.datacenter.datacenter.util.PagingUtil;
import com.iot.datacenter.datacenter.util.DataCenterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DeviceResultServiceImpl implements DeviceResultService {

    private Logger logger = LoggerFactory.getLogger(DeviceResultServiceImpl.class);

    @Autowired
    private QueryServices queryServices;
    @Autowired
    private QueryUtil queryUtil;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private DataResultRepository dataResultRepository;

    public DeviceResult createDeviceResult(DeviceResult deviceResult) {
        logger.info("createDeviceResult :- " + deviceResult );
        deviceResult = this.dataResultRepository.save(deviceResult);
        try {
            this.simpMessagingTemplate.convertAndSend("/topic/temperature",deviceResult);
        } catch (Exception ex) {
            logger.error("An error occurred while createDeviceResult file", ExceptionUtil.getRootCauseMessage(ex));
        }
        return deviceResult;
    }

    @Override
    public ResponseDto getAllDeviceResultInPagination(PaginationRequestDto paginationRequest) {
        ResponseDto responseDto;
        Object countQueryResult = this.queryServices.executeQueryForSingleResult(
            this.queryUtil.getAllDeviceResultInPagination(true, paginationRequest));
        if (!DataCenterUtil.isEmpty(countQueryResult)) {
            /* fetch Record According to Pagination*/
            Pageable paging = PagingUtil.applyPagingAndSorting(paginationRequest.getOrder(), paginationRequest.getColumnName(),
                paginationRequest.getPage(), paginationRequest.getLimit());
            List<DeviceResult> result = (List<DeviceResult>) this.queryServices.executeQuery(
                    this.queryUtil.getAllDeviceResultInPagination(false, paginationRequest), paging, "DeviceResult");
            responseDto = new ResponseDto("SUCCESS", "Fetch successfully.", result, PagingUtil.convertEntityToPagingDTO(Long.valueOf(countQueryResult.toString()), paging));
        } else {
            responseDto = new ResponseDto("ERROR", "Data not found.");
        }
        return responseDto;
    }
}
