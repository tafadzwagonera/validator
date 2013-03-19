/**
 * A validation strategy for a name
 *
 * @package com.example.helper.validate
 *
 * @author Tafadzwa Gonera <tafadzwagonera@gmail.com>
 * @version 1.0v
 * @since 1.0v @filesource NameValidator.java
 */
package com.example.helper.validate;

public class NameValidator extends AbstractValidator {

    public NameValidator() {
    }

    /**
     * validates a name
     *
     * @see Validator
     * @author Tafadzwa Gonera
     */
    @Override
    public boolean validate(final String subject) {
        return match(Validator.NAME_REGEX, subject);
    }
}
