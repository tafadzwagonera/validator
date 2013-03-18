package com.example.helper.validate;

/**
 * Describes the behavior of a Validator implementation
 *
 * @package com.example.helper.validate
 * @author Tafadzwa Gonera <tafadzwagonera@gmail.com>
 * @version 1.0v
 * @since 1.0v
 * @filesource Validator.java
 */
public interface Validator {

    /**
     * @var NAME_REGEX - a pattern for matching a name
     *
     * @access public
     */
    String NAME_REGEX = "^[a-zA-Z_-]{2,15}$";
    /**
     * @var ADDRESS_REGEX - a pattern for matching an address
     *
     * @access public
     */
    String ADDRESS_REGEX = "^[a-zA-Z0-9 #_-]{5,30}$";    
    /**
     * @var ZIPCODE_REGEX - a pattern for matching a zipcode
     *
     * @access public
     */
    String ZIPCODE_REGEX = "^[0-9-]{2,4}$";
    /**
     * @var PHONE_REGEX - a pattern for matching a phone number
     *
     * @access public
     */
    String PHONE_REGEX = "^[0-9-]{5,15}$";
    /**
     * @var STREET_REGEX - a pattern for matching a street
     *
     * @access public
     */
    String STREET_REGEX = "^[a-zA-Z0-9 _-]{2,30}$";
    /**
     * @var EMAIL_REGEX - a pattern for matching an email
     *
     * @access public
     */
    String EMAIL_REGEX = "^[a-z]+@[a-z]{5,7}\\.[a-z]{2,4}[\\.a-z]{0,3}$";

    /**
     * performs validation on incoming data
     *
     * this function is implemented by the AbstractValidator and should be
     * overriden by a concrete validator to provide a specific validation
     * algorithm
     *
     * @access public
     * @param subject - a string to be matched against the regex
     * @return boolean - returns true if the string matches, false otherwise
     * @author Tafadzwa Gonera
     */
    boolean validate(final String subject);

    /**
     * matches a subject to a regular expression pattern
     *
     * @access public
     * @param regex - a regular expression pattern
     * @param subject - a string to be matched against the regex
     * @return boolean - returns true if the string matches, false otherwise
     * @author Tafadzwa Gonera
     */
    boolean match(String regex, final String subject);
}