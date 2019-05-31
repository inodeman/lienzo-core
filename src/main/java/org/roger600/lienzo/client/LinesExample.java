package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.client.core.types.Point2DArray;
import com.ait.lienzo.shared.core.types.Color;

public class LinesExample extends BaseExample implements Example {

	private Line[] lines = new Line[10];
	public LinesExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		final double x1 = width * 0.25;  
        double y1 = height * 0.15;  
          
        final double x2 = width * 0.75;  
        double y2 = height * 0.15;  
  
        for (int i = 0; i < 10; i++) {  
            lines[i] = new Line(x1,y1, x2, y2);  
            lines[i].setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(1+i).setFillColor(Color.getRandomHexColor());  
            layer.add(lines[i]);  
            y1 += 50;  
            y2 += 50;  
        }  
	}
	
	 @Override
	    public void onResize() {
	        super.onResize();
	        console.log("ReDrawing Lines on Resize...--->>");
	    
	        final double x1 = width * 0.25;  
            double y1 = height * 0.15;  
              
            final double x2 = width * 0.75;  
            double y2 = height * 0.15;  
	        
	        for (Line line: lines) {
	        	Point2D p1 = new Point2D(x1, y1);
	        	Point2D p2 = new Point2D(x2, y2);
	        	line.setPoint2DArray(Point2DArray.fromArrayOfPoint2D(p1, p2));
	        	y1 += 50;  
	            y2 += 50;  
	        }
	        layer.batch();
	    }
}
