package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.RegularPolygon;
import com.ait.lienzo.client.core.types.Shadow;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.shared.core.types.LineJoin;

public class PolygonsExample extends BaseExample implements Example {

	private RegularPolygon[] polys = new RegularPolygon[40];
	
	public PolygonsExample(String title) {
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
	        
            polys[i] = new RegularPolygon(8, 60);  
            polys[i].setShadow(new Shadow("black", 6, 6, 6)).setFillColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth)  
                    .setStrokeColor(Color.getRandomHexColor()).setLineJoin(LineJoin.ROUND)
                    .setDraggable(true);  
            layer.add(polys[i]);  
        }  
		
		setLocation();
		
	}
	
	@Override
    public void onResize() {
        super.onResize();
        console.log("ReDrawing Polygons Example on Resize --->>");
        setLocation();   	
        layer.batch();
    }
	
	private void setLocation() {
	    
	    for (int i = 0; i < 40; i++) {  
	    	 setRandomLocation(polys[i]);
	    }
	}

}
