package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.shared.core.types.Color;

public class RectangleExample extends BaseExample implements Example {

	private Rectangle[] rectangles = new Rectangle[30];
	
	public RectangleExample(String title) {
		super(title);
		 topPadding = 20;
		 bottomPadding = 100;
	}
	
	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		for (int i = 0; i < 30; i++) {  
            final int strokeWidth = 1;  
            rectangles[i] = new Rectangle(Math.random() * 220, Math.random() * 160) 
            .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setFillColor(Color.getRandomHexColor()).setDraggable(true);  
            layer.add(rectangles[i]);  
        }
		 setLocation();
		 layer.draw();
		
	}
	
	 @Override
	    public void onResize() {
	        super.onResize();
	        setLocation();
	        layer.batch();
	    }

	    private void setLocation() {
	    	console.log("Random Location for Rectangles --->###..#");
	        for (int i = 0; i < rectangles.length; i++) {
	            setRandomLocation(rectangles[i]);
	        }
	    }

}
