package com.example.helper.validate;

/**
 * Provides default behavior for a Validator implementation
 *
 * @package com.example.helper.validate
 * @author Tafadzwa Gonera <tafadzwagonera@gmail.com>
 * @version 1.0v
 * @since 1.0v
 * @filesource AbstractValidator.java
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractValidator implements Validator {

    /**
     * @var BAD_NUMBER - an error code denoting a string that cannot be
     * converted to a number
     *
     * @access private
     */
    private final int BAD_NUMBER = -1;
    /**
     * @var pattern - a Pattern object
     *
     * @access protected
     */
    protected Pattern pattern;
    /**
     * @var matcher - a Matcher object
     *
     * @access protected
     */
    protected Matcher matcher;

    public AbstractValidator() {
    }

    /**
     * matches a subject to a regular expression pattern
     *
     * @access public
     * @param regex - a regular expression pattern
     * @param subject - a string to be matched against the regex
     * @return boolean - returns true if the string matches, false otherwise
     * @author Tafadzwa Gonera
     */
    @Override
    public boolean match(String regex, final String subject) {
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(subject);
        return matcher.matches();
    }

    /**
     * performs validation on data
     *
     * @see Validator
     * @author Tafadzwa Gonera
     */
    @Override
    public boolean validate(final String subject) {
        //TODO add your code here
        return false;
    }

    /**
     * checks if a string can be converted to a number
     *
     * @access public
     * @param subject - a string to be converted to a number
     * @return integer - returns a number if the string is can be converted
     * to a number, BAD_NUMBER otherwise
     * @author Tafadzwa Gonera
     */
    public int isNumeric(final String subject) {
        int number = 0;
        boolean flag = true;
        try {
            number = Integer.parseInt(subject);
        } catch (NumberFormatException e) {
            flag = false;
        }

        if (flag) {
            return number;
        } else {
            return BAD_NUMBER;
        }
    }
}