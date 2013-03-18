<?php

/**
 * Thrown to indicate that a method has been passed an illegal or
 * inappropriate argument.
 *
 * @package    
 * @subpackage  lib
 *
 * @author      Tafadzwa Gonera <tafadzwagonera@gmail.com>
 * @version     v1.0.0
 * @since       v1.0.0
 *
 * @filesource      InvalidArgumentException.php
 */
class InvalidArgumentException extends Exception {

    /**
     * @var exception - a message
     *
     * @access private
     */
    private $exception;

    function __construct($exception = '') {
        $this->exception = $exception;
    }

    function __toString() {
        return $this->exception;
    }

}

