package com.microservice.companyms.Company;

import com.microservice.companyms.Company.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Long id,Company company);
    void createCompany(Company company);

    boolean deleteById(Long id);

    Company getCompanyById(Long id);
}
