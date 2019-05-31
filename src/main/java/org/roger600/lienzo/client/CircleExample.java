package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.shared.core.types.Color;

public class CircleExample extends BaseExample implements Example {

	private Circle[] circles = new Circle[40];
	
	public CircleExample(String title) {
		super(title);
		topPadding = 20;
		bottomPadding = 100;
		rightPadding = 30;
		leftPadding = 20;
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {

		for (int i = 0; i < 40; i++) {  
            circles[i] = new Circle(Util.randomNumber(8, 10));  
            circles[i].setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(2).setFillColor(Color.getRandomHexColor()).setDraggable(true);  
            layer.add(circles[i]);  
  
        }  
		setLocation();
		layer.draw();
		
	}
	
	@Override
    public void onResize() {
        super.onResize();
        console.log("ReDrawing Circle Example on Resize.-->>");
        setLocation();   	
        layer.batch();
    }
	
	private void setLocation() {
	    
	    for (int i = 0; i < 40; i++) {  
	    	 setRandomLocation(circles[i]);
	    }
	}

}
