package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
@AllArgsConstructor
public class Polynomial {
    private TreeMap<Integer, Monomial> poly;
    private Integer degree;

    public Polynomial(){
        this.poly = new TreeMap<Integer, Monomial>();
        this.degree = 0;
    }

    public Polynomial(Polynomial polynomial){
        this.poly = polynomial.getPoly();
        this.degree = polynomial.getDegree();
    }

    public void updateDegree(){
        this.degree = this.poly.lastKey();
    }

    public void add(Monomial m) {
        if(this.poly.containsKey(m.getDegree())) {
            // we have to add the coef to the element that already exists in the hashmap
            Double aux = this.poly.get(m.getDegree()).getCoefficient();
            this.poly.replace(m.getDegree(),new Monomial (m.getDegree(), aux + m.getCoefficient()));
        }
        else {
            this.poly.put(m.getDegree(), m);
        }
        if(this.poly.get(m.getDegree()).getCoefficient() == 0) {
            this.poly.remove(m.getDegree());
        }
    }

    public void clear() {
        this.poly.clear();
    }

    public void printPoly() {
        for(Integer k : this.poly.keySet()) {
            if(this.poly.get(k).getDegree() < 0.0f){
                System.out.print(" - " + this.poly.get(k).getCoefficient() + "x^" + this.poly.get(k).getDegree());
            }
            else
                System.out.print(" + " + this.poly.get(k).getCoefficient() + "x^" + this.poly.get(k).getDegree());
        }
        System.out.println(" ");
    }
}
