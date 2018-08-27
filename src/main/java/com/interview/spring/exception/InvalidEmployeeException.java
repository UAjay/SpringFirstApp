package com.interview.spring.exception;

public class InvalidEmployeeException extends Exception {
  public InvalidEmployeeException(String message) {
      super(message);
  }
}