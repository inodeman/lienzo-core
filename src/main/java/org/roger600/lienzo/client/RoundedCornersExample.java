package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.shared.core.types.Color;

public class RoundedCornersExample extends BaseExample implements Example {

	private Rectangle[] rectangles = new Rectangle[30];
	
	public RoundedCornersExample(String title) {
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
            rectangles[i] = new Rectangle(generateNumber(220), generateNumber(160), 20)
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
    	console.log("Random Location");
        for (int i = 0; i < rectangles.length; i++) {
            setRandomLocation(rectangles[i]);
        }
    }

	
	private double generateNumber(int number) {  
        double result = Math.random() * number;  
        return result < 100 ? generateNumber(number) : result;  
    }  
    	
}
