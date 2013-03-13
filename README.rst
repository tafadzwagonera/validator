Dutu
====

Dutu is a small PHP dependency injection container for PHP 5.3 that
consists of a file with about a hundred lines of code. Dutu is a 
derivation of Fabien Pontencier's Pimple **but** its style of 
operation is different from Pimple. Dutu aims for **intuitive** use.

To use Dutu download it and require it in your code::

    require_once '/path/to/Dutu.php';

and then create the container by instantiating the ``Dutu`` class::

    $dutu = new Dutu();


Defining parameters and services
_______________________

There are two ways to define parameterananan in dutu. The first method
involves passing an associative array to Dutu's ``__construct``
method during instantion::

  //the list of parameters
  $parameters = array(
    'pdoImpl' => 'PDOImpl',
    'mysqliImpl' => 'mysqliImpl'
  );

Passing parameters to the container::
  
  //passing parameters to Dutu
  $dutu = Dutu($parameters);

  //instatiates your database class
  $dutu->pdoImpl;

The second method involves passing an associative array of 
parameters to Dutu's ``registerComponents`` method::

  $dutu->registerComponents($components);

or we can register components with their dependencies too::

  $dutu->registerComponents($components, $dutu->config);

At a glance Dutu does not explicitly draw a marked distinction
between parameters and services when defining them instead it
hides this distinction.
