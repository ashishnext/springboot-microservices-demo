package com.microservice.currency.conversion.currencyconversionservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-details-service")
@RibbonClient(name = "employee-details-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/employee-details/{name}")
    public ResponseData fetchDetails(@PathVariable("name") String name);
}
