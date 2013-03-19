/**
 * A validation strategy for a street
 *
 * @package com.example.helper.validate
 *
 * @author Tafadzwa Gonera <tafadzwagonera@gmail.com>
 * @version 1.0v
 * @since 1.0v @filesource StreetValidator.java
 */
package com.example.helper.validate;

public class StreetValidator extends AbstractValidator {

    public StreetValidator() {
    }

    /**
     * validates a street
     *
     * @see Validator
     * @author Tafadzwa Gonera
     */
    @Override
    public boolean validate(final String subject) {
        return match(Validator.STREET_REGEX, subject);
    }
}
