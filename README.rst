Validator
=========

Validator is a small, extensible API for performing validations on incoming
data. Validator is built using the Strategy_ Pattern which makes it scalable
and flexible in applying validations for Java based applications.

.. _Strategy: http://en.wikipedia.org/wiki/Strategy_pattern/

Configuring Validator
_____________________

Configuring Validator to work in your project is easy. Simply download_ the
API and copy the ``java\com`` folder into the source
package of your project.

.. _Download: https://github.com/tafadzwagonera/validator

To change the package name of the API perform the following instruction:

Open the following file: ``com\example\helper\validate\ValidatorFactory.java``
and modify the ``String`` initializing ``packageName`` variable in 
ValidatorFactory.java from:: 

  private String packageName = "com.example.helper.validate.";

to::

  private String packageName = "com.companyname.helper.validate.";
  // where "companyname" is the actual name of your company

That's all, we are done!

  **NB:** Don't forget to rename your package to
  ``com\companyname\helper\validate``

Using Validator
_______________

By default the API supports validation of the following data respective to 
their validation strategies

1. Emails         => EmailValidator.java
2. Names          => NameValidator.java
3. Phone numbers  => PhoneValidator.java
4. Streets        => StreetValidator.java
5. Zip codes      => ZipcodeValidator.java
6. Address        => AddressValidator.java

  **NB** : The API does not guarantee that the default Perl Compatible
  Regular Expressions (PCREs) implemented in these validation strategies
  are comprehensive. You should implement appropriate PCREs that cater to
  the needs of the application you are building.

Inorder for the API to apply the appropriate validation strategy to your
data you need to bind that data to a validation class. To bind your data to
a validation strategy open ``com\example\helper\validate\ValidatorFactory.java``
and add a new binding in the static block::

  static {
    Map<String, String> map = new HashMap<String, String>();
    map.put("firstname", "NameValidator");
    map.put("fieldToBeValidated" , "FooValidator");
    // other bindings ...        
    validators = Collections.unmodifiableMap(map);
  }

**NB:** The string ``"FooValidator"`` should literally represent a Java class that
extends ``AbstractValidator.java`` and should override its method:
``boolean validate(final String subject);``

Add the following code where you want to apply the validation::

  ... some code 
  ValidatorFactory validatorFactory = new ValidatorFactory();
  Validator validator = validatorFactory.getValidator("fieldToBeValidated");
  ValidatorContext validatorContext = new ValidatorContext(validator);
  validatorContext.validate("fieldData");
  // fieldData is the data associated with the field to be validated 
  ... some code

**NB:** If you are working on a JavaEE application you might want to go through
``java\com\example\web\FormController.java`` which illustrates how to use the
Validator API in a JavaEE environment using a ``Servlet`` as a naive example.  

That's all folks.
