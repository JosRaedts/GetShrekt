/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.order;

/**
 *
 * @author Willem
 */
public enum OrderEnum {

    NIET_BETAALD,
    BETAALD,
    IN_BEHANDELING,
    VERZONDEN,
    ONTVANGEN;

    @Override
    public String toString() {
        switch (this) {
            case NIET_BETAALD:
                return "1";
            case BETAALD:
                return "2";
            case IN_BEHANDELING:
                return "3";
            case VERZONDEN:
                return "4";
            case ONTVANGEN:
                return "5";
            default:
                throw new IllegalArgumentException();
        }
    }
}
