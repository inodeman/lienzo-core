package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Star;
import com.ait.lienzo.shared.core.types.Color;

public class StarsExample extends BaseExample implements Example {

	private Star[] stars = new Star[40];
	
	public StarsExample(String title) {
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
            final int strokeWidth = Util.randomNumber(2, 10);  
            stars[i] = new Star((int) (Math.random() * 10), 25, 50);  
            stars[i].setStrokeColor(Color.getRandomHexColor())  
                    .setStrokeWidth(strokeWidth).setFillColor(Color.getRandomHexColor()).setDraggable(true);  
  
            layer.add(stars[i]);  
        }  
		
		setLocation();
	}
	
	@Override
    public void onResize() {
        super.onResize();
        console.log("ReDrawing Stars Example on Resize --->>");
        setLocation();   	
        layer.batch();
    }
	
	private void setLocation() {
	    
	    for (int i = 0; i < 40; i++) {  
	    	 setRandomLocation(stars[i]);
	    }
	}

}
