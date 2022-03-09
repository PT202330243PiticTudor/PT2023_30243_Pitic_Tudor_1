package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View theView;
    private Model theModel;

    public Controller(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.theView.addSumListener(new SumListener());
        this.theView.addSubtractListener(new SubtractListener());
        this.theView.addMultiplyListener(new MultiplyListener());
        this.theView.addDivisionListener(new DivisionListener());
        this.theView.addDeriveListener(new DeriveListener());
        this.theView.addIntegrateListener(new IntegrateListener());
    }
    class SumListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String firstPoly = null, secondPoly = null;
            try {
                firstPoly = theView.getFirstPoly();
                secondPoly = theView.getSecondPoly();
                theModel.add(firstPoly, secondPoly);
                theModel.getPoly().printPoly();
                theView.setCalcSolution(theModel.getResultString());
            }
            catch(Exception x) {
                theView.displayErrorMessage("Sum exception");
            }
        }
    }
    class SubtractListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String firstPoly = null, secondPoly = null;
            try {
                firstPoly = theView.getFirstPoly();
                secondPoly = theView.getSecondPoly();
                theModel.subtract(firstPoly, secondPoly);

                theView.setCalcSolution(theModel.getResultString());
            }
            catch(Exception x) {
                theView.displayErrorMessage("Subtraction exception");
            }
        }
    }
    class MultiplyListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String firstPoly = null, secondPoly = null;
            try {
                firstPoly = theView.getFirstPoly();
                secondPoly = theView.getSecondPoly();
                theModel.multiply(firstPoly, secondPoly);

                theView.setCalcSolution(theModel.getResultString());
            }
            catch(Exception x) {
                theView.displayErrorMessage("Multiplication exception");
            }
        }
    }

    class DivisionListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String firstPoly = null, secondPoly = null;
            try {
                firstPoly = theView.getFirstPoly();
                secondPoly = theView.getSecondPoly();
                theModel.division(firstPoly, secondPoly);

                theView.setCalcSolution(theModel.getResultString());
            }
            catch(Exception x) {
                theView.displayErrorMessage("Division exception");
            }
        }
    }

    class DeriveListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String firstPoly = null;
            try {
                firstPoly = theView.getFirstPoly();
                theModel.derivate(firstPoly);
                theView.setCalcSolution(theModel.getResultString());
            }
            catch(Exception x) {
                theView.displayErrorMessage("Derivative exception");
            }
        }
    }

    class IntegrateListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String firstPoly = null;
            try {
                firstPoly = theView.getFirstPoly();
                theModel.integrate(firstPoly);
                theView.setCalcSolution(theModel.getResultString());
            }
            catch(Exception x) {
                theView.displayErrorMessage("Integration exception");
            }
        }
    }
}
