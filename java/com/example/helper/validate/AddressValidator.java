/**
 * A validation strategy for an address
 *
 * @package com.example.helper.validate
 *
 * @author Tafadzwa Gonera <tafadzwagonera@gmail.com>
 * @version 1.0v
 * @since 1.0v @filesource AddressValidator.java
 */
package com.example.helper.validate;

public class AddressValidator extends AbstractValidator {

    public AddressValidator() {
    }

    /**
     * validates an address
     *
     * @see Validator
     * @author Tafadzwa Gonera
     */
    @Override
    public boolean validate(final String subject) {
        return match(Validator.ADDRESS_REGEX, subject);
    }
}
