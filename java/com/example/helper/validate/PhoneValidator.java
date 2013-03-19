/**
 * A validation strategy for a phone number
 *
 * @package com.example.helper.validate
 *
 * @author Tafadzwa Gonera <tafadzwagonera@gmail.com>
 * @version 1.0v
 * @since 1.0v @filesource PhoneValidator.java
 */
package com.example.helper.validate;

import java.util.regex.Pattern;

public class PhoneValidator extends AbstractValidator {

    public PhoneValidator() {
    }

    /**
     * validates a phone number
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
            pattern = Pattern.compile(Validator.PHONE_REGEX);
            matcher = pattern.matcher(subject);
            return matcher.matches();
        }
    }
}
