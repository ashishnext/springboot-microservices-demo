package com.microservice.currency.conversion.currencyconversionservice;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class CurrencyConversionController {

    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @Autowired
    private ExchangeRateServiceProxy exchangeRateServiceProxy;


    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionData convertCurrency(@PathVariable String from, @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversionData> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/exchange-rate/from/{from}/to/{to}", CurrencyConversionData.class,
                uriVariables);
        CurrencyConversionData response = responseEntity.getBody();
        return new CurrencyConversionData(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionData convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
                                                       @PathVariable BigDecimal quantity) {
        CurrencyConversionData response = exchangeRateServiceProxy.retrieveExchangeValue(from, to);
        logger.info("{}", response);
        return new CurrencyConversionData(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }

    @GetMapping("/currency-converter/{name}")
    public ResponseData fetchEmployeeDetails(@PathVariable String name) {
        System.out.println("Name ========== " + name);
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("name", name);

        ResponseEntity<ResponseData> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/employee-details/{name}", ResponseData.class, uriVariables);
        ResponseData responseData = responseEntity.getBody();
        System.out.println("Rest template response ====== " + responseData.toString());
        return new ResponseData(responseData.getId(), responseData.getName(), responseData.getEmail(), responseData.getAddress(), responseData.getPort());
    }

    @GetMapping("/currency-converter-feign/{name}")
    public ResponseData fetchEmployeeDetailsFeign(@PathVariable String name){
        ResponseData responseData = proxy.fetchDetails(name);
        logger.info(responseData.toString());
        return new ResponseData(responseData.getId(), responseData.getName(), responseData.getEmail(), responseData.getAddress(), responseData.getPort());
    }
}
