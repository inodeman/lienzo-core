package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.QuadraticCurve;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.shared.core.types.LineCap;

public class QuadraticCurveExample extends BaseExample implements Example {

	private QuadraticCurve[] curves = new QuadraticCurve[30];
	
	public QuadraticCurveExample(String title) {
		super(title);
		topPadding = 20;
		bottomPadding = 100;
		rightPadding = 120;
		leftPadding = -50;
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		final int strokeWidth = Util.randomNumber(2, 10);  
        
		for (int i = 0; i < 30; i++) {  
            curves[i] = new QuadraticCurve(130, 130, 200, 0, 230, 130);  
            curves[i].setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setDraggable(true).setLineCap(LineCap.ROUND);  
            layer.add(curves[i]);  
        }  
		
		setLocation();
	}
	
	@Override
    public void onResize() {
        super.onResize();
        console.log("ReDrawing Quadratic Curve Example on Resize ->>");
        setLocation();   	
        layer.batch();
    }
	
	private void setLocation() {
	    
	    for (int i = 0; i < 30; i++) {  
	    	 setRandomLocation(curves[i]);
	    }
	}

}
