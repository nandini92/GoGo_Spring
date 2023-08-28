package com.gogo_ecommerce.gogo.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public AllCompaniesResponse getCompanies(){
        return new AllCompaniesResponse(HttpStatus.OK, companyService.allCompanies(), "Data retrieved from database." );
    }

    @GetMapping("/company/{id}")
    public CompanyResponse getCompany(@PathVariable int id){
        Optional<Company> response = companyService.singleCompany(id);

        if(response.isPresent()){
            return new CompanyResponse(HttpStatus.OK, response, "Data retrieved from database." );
        } else {
            return new CompanyResponse(HttpStatus.NOT_FOUND, response, "Data not found." );
        }
    }

        record AllCompaniesResponse(
        HttpStatusCode status,
        List<Company> data,
        String message
    ){}

    record CompanyResponse(
        HttpStatusCode status,
        Optional<Company> data,
        String message
    ){}
}
