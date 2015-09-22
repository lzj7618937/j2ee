package com.sishuok.es.stock.company.service;

import com.sishuok.es.common.service.BaseService;
import com.sishuok.es.stock.company.entity.CompanyDetail;
import com.sishuok.es.stock.company.repository.CompanyDetailRepository;
import com.sishuok.es.sys.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>User: jaylee
 * <p>Date: 15-4-30
 * <p>Version: 1.0
 */
@Service
public class CompanyDetailService extends BaseService<CompanyDetail, Long> {

    @Autowired
    private CompanyDetailRepository getCompanyDetailRepositoryRepository() {
        return (CompanyDetailRepository) baseRepository;
    }

    public void deleteAll(){
        getCompanyDetailRepositoryRepository().deleteAllCompany();
    }
}
