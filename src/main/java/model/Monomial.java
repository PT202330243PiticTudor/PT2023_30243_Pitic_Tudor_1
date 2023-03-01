package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Monomial {
    private Integer degree;
    private Double coefficient;

    public int compareTo(Monomial o) {
        Integer compareDegree = o.getDegree();
        return compareDegree - this.degree;
    }
}
