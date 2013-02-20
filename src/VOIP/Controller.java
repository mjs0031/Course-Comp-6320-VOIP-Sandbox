// Package Declaration //
package VOIP;

// Java Package Support //
import java.io.IOException;
import java.lang.InterruptedException;

import javax.sound.sampled.LineUnavailableException;

// Internal Package Support //
import VOIP.SocketReceiver;
import VOIP.SocketSender;

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

public class Controller{
	
	// Natural Java Constructor with no params.
	public static void main(String[] args) throws InterruptedException,
											IOException, LineUnavailableException{
		SocketSender x = new SocketSender("131.204.20.63");
		SocketReceiver y = new SocketReceiver();
		x.start();
		y.start();
		
	}
	
} // end_class_declaration

