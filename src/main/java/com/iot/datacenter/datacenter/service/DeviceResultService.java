package com.iot.datacenter.datacenter.service;

import com.iot.datacenter.datacenter.dto.PaginationRequestDto;
import com.iot.datacenter.datacenter.dto.ResponseDto;
import com.iot.datacenter.datacenter.model.DeviceResult;

public interface DeviceResultService {

    public DeviceResult createDeviceResult(DeviceResult deviceResult);

    public ResponseDto getAllDeviceResultInPagination(PaginationRequestDto paginationRequest);

}
