package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.shape.BezierCurve;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.shared.core.types.LineCap;

public class CubicCurveExample extends BaseExample implements Example {

	private BezierCurve[] curves = new BezierCurve[30];
	
	public CubicCurveExample(String title) {
		super(title);
		topPadding = 20;
		bottomPadding = 100;
		rightPadding = 50;
		leftPadding = 0;
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		for (int i = 0; i < 30; i++) {  
            curves[i] = new BezierCurve(188, 130, 140, 10, 388, 10, 388, 170);  
            curves[i].setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(2).setDraggable(true).setLineCap(LineCap.ROUND);  
            layer.add(curves[i]);  
        }  
		setLocation();
		
	}
	
	@Override
    public void onResize() {
        super.onResize();
        console.log("ReDrawing Cubic Curve Example on Resize --->>");
        setLocation();   	
        layer.batch();
    }
	
	private void setLocation() {
	    
	    for (int i = 0; i < 30; i++) {  
	    	 setRandomLocation(curves[i]);
	    }
	}

}
