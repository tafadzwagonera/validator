/**
 * A validation strategy for an email
 *
 * @package com.example.helper.validate
 *
 * @author Tafadzwa Gonera <tafadzwagonera@gmail.com>
 * @version 1.0v
 * @since 1.0v @filesource EmailValidator.java
 */
package com.example.helper.validate;

public class EmailValidator extends AbstractValidator {

    public EmailValidator() {
    }

    /**
     * validates an email
     *
     * @see Validator
     * @author Tafadzwa Gonera
     */
    @Override
    public boolean validate(final String subject) {
        return match(Validator.EMAIL_REGEX, subject);
    }
}
