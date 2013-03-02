<?php

/*
 * License
 *
 * Copyright (c) 2013 Tafadzwa Gonera
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is furnished
 * to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 * Removes hard-coded dependencies between framework components
 *
 * @package    
 * @subpackage  lib
 *
 * @author      Tafadzwa Gonera <tafadzwagonera@gmail.com>
 * @version     v1.0.0
 * @since       v1.0.0
 *
 * @filesource      Dutu.php
 */

class Dutu {

    /**
     * @var dutu - a dependency injection container object
     *
     * @access private
     */
    private $dutu;

    /**
     * @var values - an array to hold dynamically created properties
     *
     * @access protected
     */
    protected $values = array();    
    
    /**
     * injects a dependency into its corresponding dependent consumer
     *
     * @access private
     * @param key - a unique identifier for a class
     * @param value - a class name
     * @return object
     * @author Tafadzwa Gonera
     */
    private function register($key, $value, $dependency = '') {
        $classId = $key . '_class';
        $this->dutu->$classId = $value;
        $this->dutu->$key = function($container) use($classId, $dependency) {
            if (empty($dependency)) {
                return new $container->$classId();
            } else {
                return new $container->$classId($dependency);
            }
        };
    }    

    /**
     *
     * @access public
     * @param $components - an array to hold components
     * @author Tafadzwa Gonera
     */
    function __construct(array $components = array()) {
        $this->dutu = $this;
        $this->registerComponents($components);
    }

    /**
     * creates an object property dynamically
     *
     * @access public
     * @param key - a unique identifier for an object
     * @param value - a object associated with a key
     * @return 
     * @author Tafadzwa Gonera
     */
    public function __set($key, $value) {
        $this->values[$key] = $value;
    }

    /**
     * retrieves an object property dynamically
     *
     * @access public
     * @param key - a unique identifier for an object
     * @throws InvalidArgumentException if the identifier is not defined
     * @return object
     * @author Tafadzwa Gonera
     */
    public function __get($key) {
        if (!array_key_exists($key, $this->values)) {
            throw new InvalidArgumentException(sprintf('Identifier "%s" is not defined.', $key));
            die();
        }

        if (is_callable($this->values[$key])) {
            return $this->values[$key]($this);
        } else {
            return $this->values[$key];
        }
    }

    /**
     * registers a component with its dependency
     *
     * @access public
     * @param component - an associative array holding a set of components
     * @param dependency - a component's dependency
     * @return object
     * @author Tafadzwa Gonera
     */
    public function registerComponents(array $components, $dependency = '') {
        if (empty($components))
            die();
		
        if (empty($dependency)) {
            foreach ($components as $key => $value)
                $this->register($key, $value);
        } else {
            foreach ($components as $key => $value)
                $this->register($key, $value, $dependency);
        }
    }

}

