package com.chetan.firstjobapp.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Long id,Company company);
    void createCompany(Company company);

    boolean deleteById(Long id);

    Company getCompanyById(Long id);
}
