/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.imgdata;

/**
 *
 * @author Casper
 */
public enum Filter {
    COLOR,
    SEPIA,
    BW;
    
    @Override
     public String toString() {
    switch(this) {
      case COLOR: return "kleur";
      case SEPIA: return "sepia";
      case BW: return "zwart-wit";
      default: throw new IllegalArgumentException();
    }
  }
}
