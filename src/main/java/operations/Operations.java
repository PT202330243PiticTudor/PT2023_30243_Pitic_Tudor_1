package operations;

import model.Monomial;
import model.Polynomial;

public abstract class Operations {
    public static Polynomial add(Polynomial poly1, Polynomial poly2) {
        Polynomial finalPoly = new Polynomial();
        finalPoly.setPoly(poly1.getPoly());
        for (Integer k : poly2.getPoly().keySet()) {
            finalPoly.add(poly2.getPoly().get(k));
        }
        finalPoly.updateDegree();
        System.out.println("Final poly degree: " + finalPoly.getDegree());
        return finalPoly;
    }

    public static Polynomial subtract(Polynomial poly1, Polynomial poly2) {
        Polynomial finalPoly = new Polynomial();
        finalPoly.setPoly(poly1.getPoly());
        Monomial aux = new Monomial();
        for (Integer k : poly2.getPoly().keySet()) {
            aux = poly2.getPoly().get(k);
            aux.setCoefficient(aux.getCoefficient() * -1.0d);
            finalPoly.add(aux);
        }
        finalPoly.updateDegree();
        System.out.println("Final poly degree: " + finalPoly.getDegree());
        return finalPoly;
    }

    public static Polynomial multiply(Polynomial poly1, Polynomial poly2) {
        Polynomial finalPoly = new Polynomial();
        Polynomial auxPoly = new Polynomial();
        Monomial auxMonomial;
        for(Integer key : poly2.getPoly().keySet()) {
            auxMonomial = poly2.getPoly().get(key);
            for(Integer k : poly1.getPoly().keySet()) {
                auxPoly.add(new Monomial(auxMonomial.getDegree() + poly1.getPoly().get(k).getDegree(),
                        auxMonomial.getCoefficient() * poly1.getPoly().get(k).getCoefficient()));
            }
            finalPoly = Operations.add(finalPoly, auxPoly);
            auxPoly.clear();
        }
        finalPoly.updateDegree();
        System.out.println("Final poly degree: " + finalPoly.getDegree());
        return finalPoly;
    }

    public static Polynomial derive(Polynomial poly1) {
        Integer degree;
        for(Integer k : poly1.getPoly().keySet()) {
            degree = poly1.getPoly().get(k).getDegree();
            if(degree <= 0) {
                poly1.getPoly().get(k).setCoefficient(0.0d);
            }else {
                Double aux = poly1.getPoly().get(k).getCoefficient() * poly1.getPoly().get(k).getDegree();
                poly1.getPoly().get(k).setCoefficient(aux);
                poly1.getPoly().get(k).setDegree(--degree);
            }
        }
        poly1.updateDegree();
        System.out.println("Final poly degree: " + poly1.getDegree());
        return poly1;
    }

    public static Polynomial integrate(Polynomial poly1) {
        Integer degree;
        for(Integer k : poly1.getPoly().keySet()) {
            degree = poly1.getPoly().get(k).getDegree();
            Double aux = poly1.getPoly().get(k).getCoefficient() / (poly1.getPoly().get(k).getDegree() + 1);
            poly1.getPoly().get(k).setCoefficient(aux);
            poly1.getPoly().get(k).setDegree(++degree);
        }
        poly1.updateDegree();
        System.out.println("Final poly degree: " + poly1.getDegree());
        return poly1;
    }

    public static Polynomial division(Polynomial dividend, Polynomial divisor) {
        dividend.updateDegree();
        divisor.updateDegree();
        System.out.println("poly1 degree: " + dividend.getDegree());
        System.out.println("poly2 degree: " + divisor.getDegree());
        Polynomial q = new Polynomial();
        Polynomial r = dividend;

        while(!r.getPoly().isEmpty() && (r.getDegree() >= divisor.getDegree())){
            Double auxCoefficient = r.getPoly().get(r.getPoly().lastKey()).getCoefficient() / divisor.getPoly().get(divisor.getPoly().lastKey()).getCoefficient();
            Integer auxDegree = r.getDegree() - divisor.getDegree();
            Monomial aux = new Monomial(auxDegree, auxCoefficient);
            Polynomial auxPolynomial = new Polynomial();
            auxPolynomial.add(aux);
            q.add(aux);
            r = subtract(r, multiply(auxPolynomial, divisor));
        }
        return q;
    }
}
