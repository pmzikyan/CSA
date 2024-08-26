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
    	GLabel s3 = new GLabel("Our grandparents (paternal) came from Armenia just before summer started.", 10, 60);
    	GLabel s4 = new GLabel("We had hoped to have gone to a couple places in that time, like Yosemite,", 10, 80);
		GLabel s5 = new GLabel("but my a few weeks into summer, my younger brother fell from his bike", 10, 100);
		GLabel s6 = new GLabel("and broke his arm. It was tough. We had to help him do a lot of things", 10, 120);
		GLabel s7 = new GLabel("that we might have taken for granted. We didn't let it ruin our summer though.", 10, 140);
		GLabel s8 = new GLabel("We still had fun, playing UNO with our grandparents during the day and", 10, 160);
		GLabel s9 = new GLabel("playing it with everyone during the night.", 10, 180);
		GLabel s10 = new GLabel("I met up with my friends a few times, at least while they weren't traveling.", 10, 200);
		GLabel s11 = new GLabel("I had plans before the summer to accomplish things that I ended up not doing.", 10, 220);
		GLabel s12 = new GLabel("Even if it wasn't as productive as it could've been, it was refreshing get a break.", 10, 240);
    	
    	s1.setFont(f);
    	s2.setFont(f);
    	s3.setFont(f);
    	s4.setFont(f);
		s5.setFont(f);
		s6.setFont(f);
		s7.setFont(f);
		s8.setFont(f);
		s9.setFont(f);
		s10.setFont(f);
		s11.setFont(f);
		s12.setFont(f);
    	
    	add(s1);
    	add(s2);
    	add(s3);
    	add(s4);
		add(s5);
		add(s6);
		add(s7);
		add(s8);
		add(s9);
		add(s10);
		add(s11);
		add(s12);
    	    	
    	//	Continue adding lines until you have 12 to 15 lines
    	
    }
    
}
