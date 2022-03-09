package com.futurice.futuricecalculus.controller;

import com.futurice.futuricecalculus.model.Response;
import com.futurice.futuricecalculus.services.FuturiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class CalculusController {

    @Autowired
    FuturiceService futuriceService;

    @GetMapping(
            value = "/calculus")
    public Response calculus(@RequestParam String encodedString) {

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);

        String input = new String(decodedBytes);


        try {

            double result = futuriceService.evaluate(input);

            Response response = new Response();
            response.setError(false);
            response.setNumber(result);

            return response;

        }catch (Exception ex){
            Response response = new Response();
            response.setError(true);
            response.setMessage(ex.getMessage());

            return response;
        }

    }
}