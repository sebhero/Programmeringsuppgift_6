package se.mvc.controller;


import se.Color.Color;
import se.mvc.model.Array7x7;
import se.mvc.view.ViewWindows;
import se.patterns.strategy.*;

import java.util.Random;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 * 20:00 2015-12-13.
 */
public class Controller {

    private FillAlgorithm filler;
    Array7x7 model;
    ViewWindows view;
//    ViewAndroid view;
//test
    /**
     * A controller to handles communication with the Array7x7 model and diffrent strategies for filling it.
     * @param model the Array7x7 model
     * @param view the view we are displaying the matrix on
     */
    public Controller(Array7x7 model, ViewWindows view) {
        this.model = model;
        this.view = view;
        this.view.setCtrl(this);
        filler = getFiller(FILLERTYPE.NUMBERS);
        updateView();
    }


    /**
     * Add the view to the controller
     * @param view
     */
    public void addView(ViewWindows view) {
        this.view = view;
        this.view.setCtrl(this);

    }

    /**
     * Add the model to the controller.
     * @param model
     */
    public void addModel(Array7x7 model) {
        this.model = model;
    }

    /**
     * Update the view
     */
    private void updateView() {
        view.updateView(model.getMatrix());
    }



    /**
     * Btn call from view
     * Shift right the matrix
     */
    public void shiftRight() {
        //TODO add shiftRight algorithm
        updateView();
    }

    public void shiftLeft() {
        //TODO add shiftLeft algorithm
        updateView();
    }


    /**
     * btn click
     * Show total random numbers in display
     */
    public void showRandom() {
        filler = getFiller(FILLERTYPE.NUMBERS);
        model = filler.fillWithRandom();
        updateView();
    }

    /**
     * btn click
     * show 1-7 numbers
     */
    public void showNumbers1_7() {
        filler = getFiller(FILLERTYPE.NUMBERS);
        model = filler.fillWithInGaining();
        updateView();
    }

    /**
     * btn click from view
     * show same random number
     */
    public void showRandomSame() {
        filler = getFiller(FILLERTYPE.NUMBERS);

        int rnd = new Random().nextInt(7)+1;
        model = filler.fillWithOneType(rnd);
        updateView();
    }

    private FillAlgorithm getFiller(FILLERTYPE typeOfFiller) {
        //TODO use singelton
        switch (typeOfFiller) {

            case COLORS:
                return new FillColor();
            case CHARACTERS:
                return new FillCharacter();
            case NUMBERS:
            default:
                return new FillNumbers();
        }
    }

    public void showRandomColor() {
        filler = getFiller(FILLERTYPE.COLORS);
        model = filler.fillWithRandom();
        updateViewColor();
    }

    private void updateViewColor() {
        view.updateViewColor(model.getMatrix());
    }

    public void showSameColor() {
        filler = getFiller(FILLERTYPE.COLORS);
        model = filler.fillWithOneType(Color.BLUE);
        updateViewColor();
    }

    public void showGradiantColor() {
        filler = getFiller(FILLERTYPE.COLORS);
        model = filler.fillWithInGaining();
        updateViewColor();
    }

    public void showRanomChar() {
        filler = getFiller(FILLERTYPE.CHARACTERS);
        model = filler.fillWithRandom();

        updateViewColor();
    }
}
