/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models;

/**
 *
 * @author Casper
 */
public enum UserType {
    ADMIN,
    PHOTOGRAPHER,
    STUDENT;
    
    @Override
     public String toString() {
    switch(this) {
      case ADMIN: return "admin";
      case PHOTOGRAPHER: return "photographer";
      case STUDENT: return "student";
      default: throw new IllegalArgumentException();
    }
  }
}
