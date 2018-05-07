package com.microservice.currency.conversion.currencyconversionservice;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("exchange-rate-service")
@RibbonClient(name = "exchange-rate-service")
public interface ExchangeRateServiceProxy {


    @GetMapping("exchange-rate/from/{from}/to/{to}")
    public CurrencyConversionData retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
