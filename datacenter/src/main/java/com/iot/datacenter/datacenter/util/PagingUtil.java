package com.iot.datacenter.datacenter.util;

import com.iot.datacenter.datacenter.dto.PagingDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
import java.util.List;

public class PagingUtil {

    public Logger logger = LogManager.getLogger(PagingUtil.class);

    public static Object convertEntityToPagingDTO(Long totalCount, Pageable page) {
        PagingDto pdto = new PagingDto();
        pdto.setPageSize(new Long(page.getPageSize()));
        pdto.setCurrentPage(new Long(page.getPageNumber()+1));
        pdto.setTotalRecord(totalCount);
        return pdto;
    }

    /* Apply If Needed */
    public static Pageable applyPagingAndSorting(String orderBy, String direction, Long page, Long limit) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(getSortDirection(orderBy), !DataCenterUtil.isEmpty(direction) ? direction: "id"));
        if (DataCenterUtil.isEmpty(page)) {
            page = 0l;
        }
        if (DataCenterUtil.isEmpty(limit)) {
            limit = 10l;
        }
        return PageRequest.of(page.intValue(), limit.intValue(), Sort.by(orders));
    }

    private static Sort.Direction getSortDirection(String orderBy) {
        if (!DataCenterUtil.isEmpty(orderBy)) {
            if (orderBy.equalsIgnoreCase("asc")) {
                return Sort.Direction.ASC;
            } else if (orderBy.equalsIgnoreCase("desc")) {
                return Sort.Direction.DESC;
            }
        }
        return Sort.Direction.ASC;
    }
}
