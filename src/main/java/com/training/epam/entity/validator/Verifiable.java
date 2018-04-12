package com.training.epam.entity.validator;

public interface Verifiable<T> {

    boolean isValid(T underTest);
}
