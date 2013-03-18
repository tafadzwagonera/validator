package com.example.helper.validate;

public class UnitTest {

    public static void main(String[] args) {
        ValidatorFactory validatorFactory = new ValidatorFactory();
        Validator validator = validatorFactory.getValidator("email");
        ValidatorContext validatorContext = new ValidatorContext(validator);
        System.out.println(validatorContext.validate("tafadzwagonera@gmail.com"));
    }
}
