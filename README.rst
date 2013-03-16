Dutu
====

Dutu is a small PHP dependency injection container for PHP 5.3 that
consists of a file with about a hundred lines of code. Dutu is a 
derivation of Fabien Pontencier's Pimple **but** its style of 
operation is different from Pimple. Dutu's style of operation has 
one goal in mind: **intuitive** use.

To use Dutu download it and require it in your code::

    require_once '/path/to/Dutu.php';

and then create the container by instantiating the ``Dutu`` class::

    $dutu = new Dutu();

Defining Parameters
___________________

It is as easy as abc. This is how we do it::

    $dutu->cookie_name = 'SESSION_ID';

Defining Services
_________________

There are two ways to define services in dutu. The first method involves
passing an associative array to Dutu's ``__construct`` method during
instantiation::

    //a list of database services
    $parameters = array(
        'pdoImpl' => 'PDOImpl',
        'mysqliImpl' => 'mysqliImpl'
    );

Then passing the array to the container::

    $dutu = Dutu($parameters);

    //instantiates your database class
    $dutu->pdoImpl;

The second method involves passing an associative array of services to Dutu's
``registerComponents`` method::

    $dutu->registerComponents($components);

or we can register components with their dependencies too::

   //only works if all the components in the array
   //have a common dependency
   $dutu->registerComponents($components, $dutu->config);


