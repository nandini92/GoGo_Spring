package com.gogo_ecommerce.gogo.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> allCompanies(){
        return companyRepository.findAll();
    }

    public Optional<Company> singleCompany(int id){
        return companyRepository.findById(id);
    }
}
