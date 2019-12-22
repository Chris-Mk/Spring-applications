package org.softuni.cardealer.web;

import org.softuni.cardealer.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupplierController {

//    private final SupplierService supplierService;

//    public SupplierController(SupplierService supplierService) {
//        this.supplierService = supplierService;
//    }

    @GetMapping("/supplier")
    public String saveSupplier() {

        return "index";
    }
}
