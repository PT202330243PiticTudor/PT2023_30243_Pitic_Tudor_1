package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Monomial {
    private Integer degree;
    private Double coefficient;

    public int compareTo(Monomial o) {
        Integer compareDegree = o.getDegree();
        return compareDegree - this.degree;
    }
}
