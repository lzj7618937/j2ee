package com.sishuok.es.stock.company.repository;

import com.sishuok.es.common.repository.BaseRepository;
import com.sishuok.es.stock.company.entity.CompanyDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
 * Created by jaylee on 15-4-30.
 */
public interface CompanyDetailRepository extends BaseRepository<CompanyDetail, Long> {

    @Modifying
    @Query("delete from CompanyDetail bi where 1=1")
    public void deleteAllCompany();
}
