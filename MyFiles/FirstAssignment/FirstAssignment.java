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
 *	@author	Your name
 *	@since	Today's date
 */
import java.awt.Font;

import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

public class FirstAssignment extends GraphicsProgram {
    
    public void run() {
    	//	The font to be used
    	Font f = new Font("Serif", Font.BOLD, 18);
    	
    	//	Line 1
    	GLabel s1 = new GLabel("What I did on my summer vacation ...", 10, 20);
    	s1.setFont(f);
    	add(s1);
    	    	
    	//	Continue adding lines until you have 12 to 15 lines
    	GLabel line1 = new GLabel("This summer my family and I went to YellowStone with our friends and stayed for 2 weeks.",5,40);
		GLabel line2 = new GLabel("We saw a lot of pisons, and black bears. We rode horses and explored the beautiful nature there.",3,60);
		GLabel line3 = new GLabel("We visited the Grand Pismatic Spring, The Grand Canyon of the YellowStone, and the Mud",3,80);
		GLabel line4 = new GLabel("Volcano. The views were incredibly mismerizing and amazing. After that, we booked a flight to ",3,100);
		GLabel line5 = new GLabel("visit my relatives in Israel, We stayed there for a whole month. I ate a lot of delicious food, met", 3,120);
		GLabel line6 = new GLabel("my old friends and family memebers, and of course got money and gifts to make up all the" , 3,140);
		GLabel line7 = new GLabel("holidays I missed. Sadly on the day of my flight back they announced that there will be another", 3,160);
		GLabel line8 = new GLabel("war, which is very sad because it's been going on for almost a year. All Airlines cancelled their", 3, 180);
		GLabel line9 = new GLabel("flights, so my family and I were stuck there for couple weeks. We found a flight to Cyprus and",3,200);
		GLabel line10 = new GLabel("evacuated there. Later we found back to the United States with 5 connections which was kind of",3,220);
		GLabel line11 = new GLabel("crazy because we went to Cyprus on Sunday and landed in San Fransisco on Wednesday noon.", 3,240);
		GLabel line12 = new GLabel("After we arrived to our house I helped my mom empty out the suitcases then went and bought", 3,260);
		GLabel line13 = new GLabel("groceries from target. Two days later my friends and I gathered at my house. We made heart", 3 ,280);
		GLabel line14 = new GLabel("shaped pizza, and pizookie in which didn't get turn out well but we still ate it. Couple days later",3,300);
		GLabel line15 = new GLabel("I went to the mall  with a different we did back to school shopping and watched Inside Out2.",3,320);
		line1.setFont(f);
		line2.setFont(f);
		line3.setFont(f);
		line4.setFont(f);
		line5.setFont(f);
		line6.setFont(f);
		line7.setFont(f);
		line8.setFont(f);
		line9.setFont(f);
		line10.setFont(f);
		line11.setFont(f);
		line12.setFont(f);
		line13.setFont(f);
		line14.setFont(f);
		line15.setFont(f);
	   
		add(line1);
		add(line2);
		add(line3);
		add(line4);
		add(line5);
		add(line6);
		add(line7);
		add(line8);
		add(line9);
		add(line10);
		add(line11);
		add(line12);
		add(line13);
		add(line14);
		add(line15);
    }
    
}
