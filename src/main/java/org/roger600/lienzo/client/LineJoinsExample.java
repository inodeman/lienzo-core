package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.shape.Star;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.shared.core.types.LineJoin;

public class LineJoinsExample extends BaseExample implements Example {

	private Star[] stars = new Star[15];
	
	public LineJoinsExample(String title) {
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
		
		for (int i = 0; i < 15; i++) {  
			  
			if (i % 3 == 0) {
				stars[i] = new Star(5, 30, 80);  
				stars[i].setStrokeColor(Color.getRandomHexColor()).setFillColor(Color.getRandomHexColor())  
                .setLineJoin(LineJoin.BEVEL).setStrokeWidth(15).setDraggable(true);  
			} else if (i % 3 == 1) {
				stars[i] = new Star(10, 30, 80);  
				stars[i].setStrokeColor(Color.getRandomHexColor()).setFillColor(Color.getRandomHexColor())  
                .setLineJoin(LineJoin.MITER).setStrokeWidth(15).setDraggable(true);  
			} else if (i % 3 == 2) {
				stars[i] = new Star(7, 30, 80);  
				stars[i].setStrokeColor(Color.getRandomHexColor()).setFillColor(Color.getRandomHexColor())  
                .setLineJoin(LineJoin.ROUND).setStrokeWidth(15).setDraggable(true);  
			}
            
            layer.add(stars[i]);  
        }  
		setLocation();
		
	}
	
	
	@Override
    public void onResize() {
        super.onResize();
        console.log("ReDrawing Line Joins on Resize...--->>");
        setLocation();   	
        layer.batch();
    }
	
	private void setLocation() {
	    
	    for (int i = 0; i < 15; i++) {  
	    	 setRandomLocation(stars[i]);
	    }
	}
	
}
