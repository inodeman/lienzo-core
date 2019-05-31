package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.shape.Ellipse;
import com.ait.lienzo.shared.core.types.Color;

public class EllipseExample extends BaseExample implements Example {

	private Ellipse[] ellipses = new Ellipse[40];
	public EllipseExample(String title) {
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
		final int strokeWidth = 1;  
		
		for (int i = 0; i < 40; i++) {  
		    ellipses[i] = new Ellipse(Math.random() * 160, Math.random() * 80);  
            ellipses[i].setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setFillColor(Color.getRandomHexColor()).setDraggable(true);  
            layer.add(ellipses[i]);  
        }  
		setLocation();
		
	}
	
	@Override
    public void onResize() {
        super.onResize();
        console.log("ReDrawing Ellipses Example on Resize ->>");
        setLocation();   	
        layer.batch();
    }
	
	private void setLocation() {
	    
	    for (int i = 0; i < 40; i++) {  
	    	 setRandomLocation(ellipses[i]);
	    }
	}

}
