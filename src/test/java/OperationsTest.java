import gui.Model;
import model.Monomial;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

public class OperationsTest {
    private static Model model;
    private String polynomial1;
    private String polynomial2;
    @BeforeAll
    static void init() {
        model = new Model();
    }

    @BeforeEach
    void initPolynomial() {
        this.polynomial1 = "4x^3 + x^2 + 4x^1 + 1";
        this.polynomial2 = "4x^5 + x^3 + 4x^2 + 1";
    }

    @Test
    void testAddition() {
        TreeMap<Integer, Monomial> expectedPoly = new TreeMap<>();
        expectedPoly.put(0, new Monomial(0, 2.0));
        expectedPoly.put(1, new Monomial(1, 4.0));
        expectedPoly.put(2, new Monomial(2, 5.0));
        expectedPoly.put(3, new Monomial(3, 5.0));
        expectedPoly.put(5, new Monomial(5, 4.0));
        this.model.add(this.polynomial1, this.polynomial2);
        Assertions.assertThat(this.model.getPoly().getPoly())
                .hasSameSizeAs(expectedPoly);
        for(Monomial m : this.model.getPoly().getPoly().values()) {
            org.junit.jupiter.api.Assertions.assertEquals(expectedPoly.get(m.getDegree()).toString(), m.toString());
        }
    }

    @Test
    void testSubtraction() {
        TreeMap<Integer, Monomial> expectedPoly = new TreeMap<>();
        expectedPoly.put(1, new Monomial(1, 4.0));
        expectedPoly.put(2, new Monomial(2, -3.0));
        expectedPoly.put(3, new Monomial(3, 3.0));
        expectedPoly.put(5, new Monomial(5, -4.0));
        this.model.subtract(this.polynomial1, this.polynomial2);
        Assertions.assertThat(this.model.getPoly().getPoly())
                .hasSameSizeAs(expectedPoly);
        for(Monomial m : this.model.getPoly().getPoly().values()) {
            org.junit.jupiter.api.Assertions.assertEquals(expectedPoly.get(m.getDegree()).toString(), m.toString());
        }
    }

    @Test
    void testMultiplication() {
        TreeMap<Integer, Monomial> expectedPoly = new TreeMap<>();
        expectedPoly.put(0, new Monomial(0, 4.0));
        expectedPoly.put(1, new Monomial(1, 19.0));
        expectedPoly.put(2, new Monomial(2, 16.0));
        expectedPoly.put(3, new Monomial(3, 19.0));
        expectedPoly.put(4, new Monomial(4, 12.0));
        String multiplicationPoly = "3x + 4";
        this.model.multiply(this.polynomial1, multiplicationPoly);
        Assertions.assertThat(this.model.getPoly().getPoly())
                .hasSameSizeAs(expectedPoly);
        for(Monomial m : this.model.getPoly().getPoly().values()) {
            org.junit.jupiter.api.Assertions.assertEquals(expectedPoly.get(m.getDegree()).toString(), m.toString());
        }
    }

    @Test
    void testDivision() {
        TreeMap<Integer, Monomial> expectedPoly = new TreeMap<>();
        expectedPoly.put(0, new Monomial(0, 13.25));
        expectedPoly.put(1, new Monomial(1, -4.5));
        expectedPoly.put(2, new Monomial(2, 2.0));
        String divisionPoly = "2x + 5";
        this.model.division(this.polynomial1, divisionPoly);
        Assertions.assertThat(this.model.getPoly().getPoly())
                .hasSameSizeAs(expectedPoly);
        for(Monomial m : this.model.getPoly().getPoly().values()) {
            org.junit.jupiter.api.Assertions.assertEquals(expectedPoly.get(m.getDegree()).toString(), m.toString());
        }
    }

    @Test
    void testDerivative() {
        TreeMap<Integer, Monomial> expectedPoly = new TreeMap<>();
        expectedPoly.put(0, new Monomial(0, 4.0));
        expectedPoly.put(1, new Monomial(1, 2.0));
        expectedPoly.put(2, new Monomial(2, 12.0));
        String derivativePoly = "4x^3 + x^2 + 4x^1";
        this.model.derivate(derivativePoly);
        Assertions.assertThat(this.model.getPoly().getPoly())
                .hasSameSizeAs(expectedPoly);
        for(Monomial m : this.model.getPoly().getPoly().values()) {
            org.junit.jupiter.api.Assertions.assertEquals(expectedPoly.get(m.getDegree()).toString(), m.toString());
        }
    }

    @Test
    void testIntegration() {
        TreeMap<Integer, Monomial> expectedPoly = new TreeMap<>();
        expectedPoly.put(1, new Monomial(1, 4.0));
        expectedPoly.put(2, new Monomial(2, 1.0));
        expectedPoly.put(3, new Monomial(3, 4.0));
        String integratePoly = "4.0x^0 + 2.0x^1 + 12.0x^2";
        this.model.integrate(integratePoly);
        Assertions.assertThat(this.model.getPoly().getPoly())
                .hasSameSizeAs(expectedPoly);
        for(Monomial m : this.model.getPoly().getPoly().values()) {
            org.junit.jupiter.api.Assertions.assertEquals(expectedPoly.get(m.getDegree()).toString(), m.toString());
        }
    }
}
