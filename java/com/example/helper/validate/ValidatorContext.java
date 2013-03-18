package com.example.helper.validate;

/**
 * Performs validation on incoming data using a strategy object to select an
 * appropriate validation algorithm
 *
 * @package com.example.helper.validate
 * @author Tafadzwa Gonera <tafadzwagonera@gmail.com>
 * @version 1.0v
 * @since 1.0v
 * @filesource ValidatorContext.java
 */
import java.util.List;
import java.util.Map;

public class ValidatorContext {

    /**
     * @var validator - a Validator implementation
     *
     * @access private
     */
    private Validator validator;

    /**
     *
     * @access public
     *
     * @param validator - a Validator implementation
     */
    public ValidatorContext(Validator validator) {
        this.validator = validator;
    }

    /**
     * validates a string
     *
     * @access public
     * @param subject - a string to be validated
     * @return boolean - returns true if validation was met, false otherwise
     * @author Tafadzwa Gonera
     */
    public boolean validate(final String subject) {
        return validator.validate(subject);
    }

    /**
     * checks if a value exists in an array
     *
     * @access public
     * @param haystack - the array to be searched
     * @param needle - the item to be searched
     * @return boolean - returns true if needle is found, false otherwise
     * @author Tafadzwa Gonera
     */
    public static boolean inArray(List haystack, Map.Entry needle) {
        if (haystack.contains(needle.getKey())) {
            return true;
        } else {
            return false;
        }
    }
}