package com.example.sagarsingh.watchlist.entity.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RatingAnnotationLogic implements ConstraintValidator<Rating,Float> {

	@Override
	public boolean isValid(Float value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return value>0 && value<=10;
	}

}
