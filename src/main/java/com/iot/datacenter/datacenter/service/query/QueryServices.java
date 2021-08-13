package com.iot.datacenter.datacenter.service.query;

import com.iot.datacenter.datacenter.model.DeviceResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class QueryServices {

    public Logger logger = LogManager.getLogger(QueryServices.class);

    @PersistenceContext
    private EntityManager _em;

    public List<Object[]> executeQuery(String queryStr) {
        logger.debug("Execute Query :- " + queryStr);
        Query query = this._em.createNativeQuery(queryStr);
        return query.getResultList();
    }

    public int executeUpdateQuery(String queryStr) {
        logger.debug("Execute Query :- " + queryStr);
        Query query = this._em.createNativeQuery(queryStr);
        return query.executeUpdate();
    }

    public List<Object[]> executeQuery(String queryStr, Pageable paging) {
        logger.debug("Execute Query :- " + queryStr);
        Query query = this._em.createNativeQuery(queryStr);
        if (paging != null) {
            query.setFirstResult(paging.getPageNumber() * paging.getPageSize());
            query.setMaxResults(paging.getPageSize());
        }
        return query.getResultList();
    }

    public List<?> executeQuery(String queryStr, Pageable paging, String object) {
        logger.debug("Execute Query :- " + queryStr);
        Query query = null;
        if (object != null && object.equalsIgnoreCase("DeviceResult")) {
            query = this._em.createNativeQuery(queryStr, DeviceResult.class);
        }
        if (paging != null) {
            query.setFirstResult(paging.getPageNumber() * paging.getPageSize());
            query.setMaxResults(paging.getPageSize());
        }
        return query.getResultList();
    }

    public Object executeQueryForSingleResult(String queryStr) {
        logger.debug("Execute Query :- " + queryStr);
        Query query = this._em.createNativeQuery(queryStr);
        return query.getSingleResult();
    }

    public void delete(String queryStr) {
        this._em.createQuery(queryStr).executeUpdate();
    }
}
