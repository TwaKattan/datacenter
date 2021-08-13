package com.iot.datacenter.datacenter.service.query;

import com.iot.datacenter.datacenter.dto.PaginationRequestDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class QueryUtil {

    public Logger logger = LogManager.getLogger(QueryUtil.class);

    public String getAllDeviceResultInPagination(boolean isCount, PaginationRequestDto paginationRequest) {
        String selectPortion = "";
        if (isCount) {
            selectPortion = "select count(*) as result from device_result";
        } else {
            selectPortion = "select * from device_result";
        }
        selectPortion += String.format(" order by %s %s", paginationRequest.getColumnName(), paginationRequest.getOrder());
        return selectPortion;
    }

}
