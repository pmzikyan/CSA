/**
 *	FirstAssignment.java
 *	Display a brief description of your summer vacation on the screen.
 *
 *	To compile Linux:	javac -cp .:mvAcm.jar FirstAssignment.java
 *	To execute Linux:	java -cp .:mvAcm.jar FirstAssignment
 *
 *	To compile MS Powershell:	javac -cp ".;mvAcm.jar" FirstAssignment.java
 *	To execute MS Powershell:	java -cp ".;mvAcm.jar" FirstAssignment
 *
 *	@author	Petros Mzikyan
 *	@since	August 23, 2024
 */
import java.awt.Font;

import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

public class FirstAssignment extends GraphicsProgram {
    
    public void run() {
    	//	The font to be used
    	Font f = new Font("Serif", Font.BOLD, 18);
    	
    	//	Line 1
    	GLabel s1 = new GLabel("My summer this year wasn't that eventful.", 10, 20);
    	GLabel s2 = new GLabel("Every summer we had travelled somewhere, but this time we'd stayed home.", 10, 40);
    	GLabel s3 = new GLabel("", 10, 60);
    	GLabel s4 = new GLabel("", 10, 80);
    	
    	s1.setFont(f);
    	s2.setFont(f);
    	s3.setFont(f);
    	s4.setFont(f);
    	
    	add(s1);
    	add(s2);
    	add(s3);
    	add(s4);
    	    	
    	//	Continue adding lines until you have 12 to 15 lines
    	
    }
    
}
