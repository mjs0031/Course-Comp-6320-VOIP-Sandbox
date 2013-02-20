// Package Declaration //
package VOIP;

// Java Package Support //
// { Not Applicable }

// Internal Package Support //
//import SocketReceiver;
//import SocketSender;

/**
 * 
 * VOIP/Controller.java
 * 
 * @author(s)	: Ian Middleton, Zach Ogle, Matthew J Swann
 * @version  	: 1.0
 * Last Update	: 2013-02-20
 * Update By	: Matthew J Swann
 * 
 * 
 * VOIP PACKAGE :: Source code for Comp 6360: Wireless & Mobile Networks
 * 	               Assignment 1 :: VOIP
 * 
 */

public class Controller {
	
	// Natural Java Constructor with no params.
	
	SocketReceiver receiver = new SocketReceiver();
	SocketSender   sender   = new SocketSender();
	
	
} // end_class_declaration

