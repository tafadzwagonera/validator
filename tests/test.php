<?php
/*
* This code sample illustrates how Dutu Dependency Injection (DI)
* container can be used to resolve hard-coded dependencies
* between components.
*
* CLASSES:
* Config - a class holding database configuration information 
* Database - an interface containing query behavior
* MysqliImpl - an implementation of Database
* PDOImpl - an implementation of Database
* Service - a class the performs persistent related actions using
* a database object
* Dutu - a Dependeny Injection (DI) container
* 
* NB: 
* All the classes used in this sample have been bundled into a single
*  .php file for brevity's sake.
*/
require_once('../lib/Dutu.php');

class Config {
	/**
	 * @var db - array to hold database configuration information
	 *
	 * @access private
	 */
	private $db = array(
		'type' => 'mysql',
		'host' => '127.0.0.1',
	
		//change the database name to match the one you have 
		'dbname' => 'test',
		
		//set the username to match what you have 
		'username' => 'root', 
		
		//and change the password too
		'password' => '',
		'socket' => '3306',
		'port' => '',
	);
	/**
	 * @var dependencies - an array to hold dependencies
	 *
	 * @access private
	 */
	private $dependencies = array(
		'config' => 'Config',
	);
	public function __construct() {
	
	}
	public function host() {
		return $this->db['host'];
	}
	public function type() {
		return $this->db['type'];
	}
	public function dbname() {
		return $this->db['dbname'];
	}
	public function dsn() {
		$dsn = null;
		$dsn .= $this->db['type'];
		$dsn .= ':host=' . $this->db['host'];
		$dsn .= ';dbname=' . $this->db['dbname'];
		return $dsn;
	}

	public function username() {
		return $this->db['username'];
	}

	public function password() {
		return $this->db['password'];
	}

	public function port() {
		return $this->db['port'];
	}

	public function socket() {
		return $this->db['socket'];
	}

	public function dependencies() {
		return $this->dependencies;
	}		

}

interface Database {
	
	//the abstract behaviour of this interface
	//has been skipped for brevity's sake
	//but let us pretend it is there
}

/*
* MysqliImpl is a database implementation
* that extends the functonality of Mysqli class
*/
class MysqliImpl extends Mysqli implements Database {

	/**
	 *
	 * @access public
	 * @param $config -  a config object
	 * @author Tafadzwa Gonera
	 */
	function __construct(Config $config) {
		
		//connect to the database
		parent::__construct($config->host(), $config->username(), $config->password(), $config->dbname());
		//some code to handle errors ...
	}
	
	//the behaviour realized from the interface
	//has been skipped for brevity's sake
	//but let us pretend it is there
}

/*
* PDOImpl is a database implementation 
* that extends the functonality of PDO class
*/
class PDOImpl extends PDO implements Database {
	function __construct(Config $config) {
	
		//connect to the database
		parent::__construct($config->dsn(), $config->username(), $config->password());
		//some code to handle exceptions ... 
	}
	
	//the behaviour realized from the interface
	//has been skipped for brevity's sake
	//but let us pretend it is there
}

class Service {

	//a database resource
	private $db;
	
	function __construct(Database $db) {
		$this->db = $db;
	}
	
	function doSomething() {
		echo 'performed some action' . '<br/>';
	}
}

//PDOImpl and MysqliImpl classes are dependent consumers 
$components = array(
	'pdo' => 'PDOImpl',
	'mysqli' => 'MysqliImpl'
);

//and Config is their dependency
$config = new Config();
$dutu = new Dutu($config->dependencies());

/*
* alternatively we could have created a config object
* separately and passed it to registerDependency() along
* with the components that depend on it but the first 
* approach is better since we don't have to create the
* object explicitly. Dutu handles that for us.
*
* Here's how we could have done it the other way:
* 
* $dutu = new Dutu();
* $config = new Config();
* 	$components = array(
		'pdo' => 'PDOImpl',
		'mysqli' => 'MysqliImpl'
	);
* $dutu->registerDependency($components, $config);
*/

//inject the dependency into corresponding components
$dutu->registerComponents($components, $dutu->config);

//now you can inject a database class of your choice into a service
$dutu->registerComponents(array('service' => 'Service'), $dutu->pdo);
$dutu->service->doSomething();

//or even stub out one database class for another during testing
$dutu->registerComponents(array('service' => 'Service'), $dutu->mysqli);
$dutu->service->doSomething();
	
	
	
