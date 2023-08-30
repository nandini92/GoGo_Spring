package com.gogo_ecommerce.gogo.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<AllCompaniesResponse> getCompanies(){
        AllCompaniesResponse response = new AllCompaniesResponse(200, companyService.allCompanies(), "Data retrieved from database." );

        return new ResponseEntity<AllCompaniesResponse>(response, null, 200);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyResponse> getCompany(@PathVariable int id){
        Optional<Company> company = companyService.singleCompany(id);

        if(company.isPresent()){
            CompanyResponse response = new CompanyResponse(200, company, "Data retrieved from database." );

            return new ResponseEntity<CompanyResponse>(response, null, 200);
        } else {
            CompanyResponse response = new CompanyResponse(404, company, "Data not found." );

            return new ResponseEntity<CompanyResponse>(response, null, 404);
        }
    }

    record AllCompaniesResponse(
        int status,
        List<Company> data,
        String message
    ){}

    record CompanyResponse(
        int status,
        Optional<Company> data,
        String message
    ){}
}
