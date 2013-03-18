package com.example.helper.validate;

import java.util.regex.Pattern;

/**
 * A validation strategy for a zip code
 *
 * @package com.example.helper.validate
 * @author Tafadzwa Gonera <tafadzwagonera@gmail.com>
 * @version 1.0v
 * @since 1.0v
 * @filesource ZipcodeValidator.java
 */
public class ZipcodeValidator extends AbstractValidator {

    public ZipcodeValidator() {
    }

    /**
     * validates a zip code
     *
     * @see Validator
     * @author Tafadzwa Gonera
     */
    @Override
    public boolean validate(final String subject) {
        int number = isNumeric(subject);
        if (number < 0) {
            return false;
        } else {
            pattern = Pattern.compile(Validator.ZIPCODE_REGEX);
            matcher = pattern.matcher(subject);
            return matcher.matches();
        }
    }
}