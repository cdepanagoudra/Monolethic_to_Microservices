package com.microservice.companyms.Company.Impl;
import com.microservice.companyms.Company.Company;
import com.microservice.companyms.Company.CompanyRepository;
import com.microservice.companyms.Company.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;
@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    @Override
    public boolean updateCompany(Long id,Company company) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company updatedCompany = companyOptional.get();
            updatedCompany.setName(company.getName());
            updatedCompany.setDescription(company.getDescription());
            //updatedCompany.setJobs(company.getJobs());
            companyRepository.save(updatedCompany);
            return true;
        }
        return false;
    }
    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }
    @Override
    public boolean deleteById(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}