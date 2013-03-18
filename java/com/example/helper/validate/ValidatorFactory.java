package com.example.helper.validate;

/**
 * Creates a Validator implementation that performs validation on incoming data
 *
 * @package com.example.helper.validate
 * @author Tafadzwa Gonera <tafadzwagonera@gmail.com>
 * @version 1.0v
 * @since 1.0v
 * @filesource ValidatorFactory.java
 */
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ValidatorFactory {

    /**
     * @var packageName - a package name of Validator implementations
     *
     * @access private
     */
    private String packageName = "com.example.helper.validate.";
    /**
     * @var validator - a hash map to hold Validator implementations
     *
     * @access private
     */
    private static Map<String, String> validators;

    static {
        Map<String, String> map = new HashMap<String, String>();
        map.put("firstname", "NameValidator");
        map.put("lastname", "NameValidator");
        map.put("address", "AddressValidator");
        map.put("street", "StreetValidator");
        map.put("zipcode", "ZipcodeValidator");
        map.put("phone", "PhoneValidator");
        map.put("email", "EmailValidator");
        validators = Collections.unmodifiableMap(map);
    }

    public ValidatorFactory() {
    }

    /**
     * instantiates a Validator implementaton
     *
     * @access public
     * @param key - a Validator implementation to be created
     * @return boolean - an implementation of Validator
     * @throws IllegalArgumentException
     * @author Tafadzwa Gonera
     */
    public Validator getValidator(String key) {
        if (validators.containsKey(key)) {

            //creating a "fully qualified class name"
            String className = packageName + validators.get(key);
            Validator validator = null;
            try {
                validator = (Validator) Class.forName(className).newInstance();
            } catch (ClassNotFoundException e) {

                //do something meaningful with the exception
                System.out.println(e);
            } catch (InstantiationException e) {
                
                //do something meaningful with the exception
                System.out.println(e);
            } catch (IllegalAccessException e) {
                
                //do something meaningful with the exception
                System.out.println(e);
            }
            return validator;
        } else {
            throw new IllegalArgumentException();
        }
    }
}