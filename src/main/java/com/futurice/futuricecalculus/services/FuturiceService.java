package com.futurice.futuricecalculus.services;

import com.futurice.futuricecalculus.configuration.ConfigurationProps;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FuturiceService {

    @Autowired
    private final ConfigurationProps keyConfig;

    public double evaluate(String expresion) {
        double result = 0;
        String operation = "";
        List<Character> openBrackets = new ArrayList<>();
        List<Character> closeBrackets = new ArrayList<>();
        StringBuilder innerInput =new StringBuilder();

        for (int i = 0; i < expresion.length(); i++) {
            char inputChar = expresion.charAt(i);
            if(openBrackets.isEmpty()){
                if (Character.isDigit(inputChar)) {

                    if (operation == "" && result == 0) {
                        result = Character.digit(inputChar, Character.MAX_RADIX);
                        continue;
                    } else if (operation != "") {
                        result = calculateSimpleExpression(operation, Character.digit(inputChar, Character.MAX_RADIX), result);
                        continue;
                    }
                }

                if (inputChar == '+' || inputChar == '-' || inputChar == '*' || inputChar == '/') {
                    operation = Character.toString(inputChar);
                    continue;
                }
            }
            if (inputChar == '(') {
                openBrackets.add(inputChar);
                continue;
            }
            if(inputChar ==')'){
                closeBrackets.add(inputChar);
                if(openBrackets.size() == closeBrackets.size()){
                    openBrackets.remove((Character)'(');
                    closeBrackets.remove((Character)')');
                    double evalResult =  evaluate(innerInput.toString());
                    result = calculateSimpleExpression(operation,evalResult,result);
                    innerInput.setLength(0);
                }
                if(openBrackets.size()> closeBrackets.size()){
                    continue;
                }
            }
            else{
                innerInput.append(inputChar);
            }
        }
        return result;
    }


    public  double calculateSimpleExpression(String operation, double inputChar, double output) {

        switch (operation) {
            case "+":
                output = output + inputChar;
                break;
            case "-":
                output = output - inputChar;
                break;
            case "*":
                output = output * inputChar;
                break;
            case "/":
                output = output / inputChar;
                break;
            default:
                break;
        }

        return output;
    }

}
