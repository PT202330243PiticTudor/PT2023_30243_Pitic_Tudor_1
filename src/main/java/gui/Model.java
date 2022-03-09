package gui;

import exceptions.InvalidInputException;
import model.Monomial;
import model.Polynomial;
import operations.Operations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
    private Polynomial poly;
    private final char variable = 'x';

    public Polynomial getPoly() {
        return this.poly;
    }

    public void add(String firstPoly, String secondPoly) {
        Polynomial poly1 = convertString2Polynomial(firstPoly);
        Polynomial poly2 = convertString2Polynomial(secondPoly);
        this.poly = Operations.add(poly1, poly2);
    }

    public void subtract(String firstPoly, String secondPoly) {
        Polynomial poly1 = convertString2Polynomial(firstPoly);
        Polynomial poly2 = convertString2Polynomial(secondPoly);
        this.poly = Operations.subtract(poly1, poly2);
    }

    public void multiply(String firstPoly, String secondPoly) {
        Polynomial poly1 = convertString2Polynomial(firstPoly);
        Polynomial poly2 = convertString2Polynomial(secondPoly);
        this.poly = Operations.multiply(poly1, poly2);
    }

    public void division(String firstPoly, String secondPoly) {
        Polynomial poly1 = convertString2Polynomial(firstPoly);
        Polynomial poly2 = convertString2Polynomial(secondPoly);
        this.poly = Operations.division(poly1, poly2);
    }

    public void derivate(String firstPoly) {
        Polynomial poly1 = convertString2Polynomial(firstPoly);
        this.poly = Operations.derive(poly1);
    }

    public void integrate(String firstPoly) {
        Polynomial poly1 = convertString2Polynomial(firstPoly);
        this.poly = Operations.integrate(poly1);
    }

    public String getResultString() {
        return setOutputString();
    }

    private String[] readMonomialsString(String inputPolynomial) throws InvalidInputException {
        // Acceptable pattern:
        //      +/- ax^n +/- bx^n+1 ...
        // polynomial must end in a coefficient
        // polynomial can begin in a + or -, if it doesn't begin with a sign, we assume +
        // polynomial must have a weight explicit

        // method:
        //      remove all whitespaces
        //      add sign at the beginning of the polynomial string
        //      split string into a set of strings when + or - is encountered
        //      for each monomial string:
        //          if sign is not followed by a number:
        //              add a 1 between a sign and variable "x"
        //          if variable "x" is not followed by '^' + number:
        //              add "^1" after variable "x"
        //          if

        String[] monomialsStrings = null;
        if(inputPolynomial == null){
            throw new InvalidInputException("A field is empty");
        }
        try{
            if(inputPolynomial.isBlank()){
                return null;
            }
            // Excess white spaces are cut out from input string
            inputPolynomial = inputPolynomial.replaceAll("\\s+", "");
//            inputPolynomial = inputPolynomial.replaceAll("^[0-9]|^[a-z]|^[A-Z]", "+");
            if(inputPolynomial.matches("^[0-9].*|^[a-z].*|^[A-Z].*")){
                inputPolynomial = '+' + inputPolynomial;
            }
            inputPolynomial = inputPolynomial.replaceAll("[+]", " +");
            inputPolynomial = inputPolynomial.replaceAll("[-]", " -");
            inputPolynomial = inputPolynomial.replaceAll("^\\s", "");
            monomialsStrings = inputPolynomial.split("\\s+");
            for(int i = 0; i < monomialsStrings.length; i++){
                // cases:
                // +/- float
                // ex: -3.3
                if(monomialsStrings[i].matches("^[+-][0-9]+(\\.[0-9]+)*$")){
                    monomialsStrings[i] = monomialsStrings[i] + "x^0";
                }
                // +/- x
                // ex: +x
                else if(monomialsStrings[i].matches("^[+-]([a-z]|[A-Z])$")){
                    monomialsStrings[i] = monomialsStrings[i].replaceAll("[+]", "+1.0");
                    monomialsStrings[i] = monomialsStrings[i].replaceAll("[-]", "-1.0");
                    monomialsStrings[i] += "^1";
                }
                // +/- x^int
                // ex: -x^42
                else if(monomialsStrings[i].matches("^[+-]([a-z]|[A-Z])\\^[0-9]+$")){
                    monomialsStrings[i] = monomialsStrings[i].replaceAll("[+]", "+1.0");
                    monomialsStrings[i] = monomialsStrings[i].replaceAll("[-]", "-1.0");
                }
                // +/- float x
                // ex: +3.43x
                else if(monomialsStrings[i].matches("^[+-][0-9]+(\\.[0-9]+)*([a-z]|[A-Z])$")){
                    monomialsStrings[i] += "^1";
                }
                // +/- float x ^ int
                // ex: -5.25x^10
                else if(monomialsStrings[i].matches("^[+-][0-9]+(\\.[0-9]+)*([a-z]|[A-Z])\\^[0-9]+$")){
                    // this is the correct form
                }
                else{
                    throw new InvalidInputException("Incorrect syntax!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return monomialsStrings;
    }

    public Polynomial convertString2Polynomial(String inputPolynomial) {
        // CONVERSION:
        // get a string of monomials
        // for each monomial string:
        //      convert string to Monomial
        //      add Monomial to workingPolynomial
        // return workingPolynomial

        Polynomial workingPolynomial = new Polynomial();
        String[] monomialsStrings = readMonomialsString(inputPolynomial);
        if(monomialsStrings == null){
            return workingPolynomial;
        }
        else{
            for(String s : monomialsStrings){
                workingPolynomial.add(new Monomial(Integer.parseInt(s.substring(s.indexOf(variable) + 2)),
                        Double.parseDouble(s.substring(0, s.indexOf(variable)))));
            }
        }

        return workingPolynomial;
    }

    private String setOutputString() {
        String output = null;
        for(Integer k : poly.getPoly().keySet()){
            if(output == null) {
                output = poly.getPoly().get(k).getCoefficient() + "x^"
                        + poly.getPoly().get(k).getDegree();
            }
            else if(poly.getPoly().get(k).getCoefficient() < 0.0 || (output == null && poly.getPoly().get(k).getCoefficient() < 0.0f)){
                output += " " + poly.getPoly().get(k).getCoefficient() + "x^"
                        + poly.getPoly().get(k).getDegree();
            }
            else {
                output += " + " + poly.getPoly().get(k).getCoefficient() + "x^"
                        + poly.getPoly().get(k).getDegree();
            }
        }

        System.out.println("output var is: " + output);
        return output;
    }
}
