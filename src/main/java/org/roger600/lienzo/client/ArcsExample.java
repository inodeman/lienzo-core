package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Arc;
import com.ait.lienzo.shared.core.types.Color;

public class ArcsExample extends BaseExample implements Example {

	private Arc[] arcs = new Arc[30];
	public ArcsExample(String title) {
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
		
		for (int i = 0; i < 30; i++) {  
			  
            arcs[i] = new Arc((int) (Util.randomNumber(10, 10)), 0, (Math.PI * 2) / 2);  
            arcs[i] .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(2).setFillColor(Color.getRandomHexColor()).setDraggable(true)  
                    .setRotationDegrees(Util.randomNumber(3, 10));  
            layer.add(arcs[i]);  
        }  
		setLocation();
		
	}
	

	@Override
    public void onResize() {
        super.onResize();
        console.log("ReDrawing Arcs on Resize..--->");
        setLocation();   	
        layer.batch();
    }
	
	private void setLocation() {
	    
	    for (int i = 0; i < 30; i++) {  
	    	 setRandomLocation(arcs[i]);
	    }
	}

}
