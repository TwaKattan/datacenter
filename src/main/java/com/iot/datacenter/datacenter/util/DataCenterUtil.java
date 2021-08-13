package com.iot.datacenter.datacenter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataCenterUtil {

    private Logger logger = LoggerFactory.getLogger(DataCenterUtil.class);

    public static boolean isEmpty(Long value) {
        return value == null ? true : false;
    }

    public static boolean isEmpty(Object value) {
        return value == null ? true : false;
    }

    public static boolean isEmpty(String value) {
        if (value == null) {
            return true;
        } else if (value.equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isNull(Boolean value) {
        return value == null ? true : false;
    }

    public static boolean isNull(Double value) {
        return value == null ? true : false;
    }

}