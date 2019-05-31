package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.client.core.types.Point2DArray;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.shared.core.types.LineCap;

public class LinesCapExample extends BaseExample implements Example {

	private Line[] lines = new Line[3];
	
	public LinesCapExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		lines[0] = new Line();  
        lines[0].setStrokeWidth(30).setFillColor(Color.getRandomHexColor()).setStrokeColor(Color.getRandomHexColor()).setLineCap(LineCap.BUTT);  
        layer.add(lines[0]);  
  
        lines[1] = new Line();  
        lines[1].setStrokeWidth(30).setFillColor(Color.getRandomHexColor()).setStrokeColor(Color.getRandomHexColor()).setLineCap(LineCap.ROUND);  
        layer.add(lines[1]);  
  
        lines[2] = new Line();  
        lines[2].setStrokeWidth(30).setFillColor(Color.getRandomHexColor()).setStrokeColor(Color.getRandomHexColor()).setLineCap(LineCap.SQUARE);  
        layer.add(lines[2]);  
        setLocation();
	}
	
	@Override
    public void onResize() {
        super.onResize();
        console.log("ReDrawing Caps on Lines on Resize...--->>>");
        setLocation();   	
        layer.batch();
    }
	
	private void setLocation() {
		final int middleX = width / 2;  
	    final int middleY = height / 2;
	    
		lines[0].setPoint2DArray(Point2DArray.fromArrayOfPoint2D(new Point2D(middleX - middleX / 2, middleY - 100), new Point2D(middleX + middleX / 2, middleY - 100)));
        lines[1].setPoint2DArray(Point2DArray.fromArrayOfPoint2D(new Point2D(middleX - middleX / 2, middleY), new Point2D(middleX + middleX / 2, middleY)));
        lines[2].setPoint2DArray(Point2DArray.fromArrayOfPoint2D(new Point2D(middleX - middleX / 2, middleY + 100), new Point2D(middleX + middleX / 2, middleY + 100)));
     }
}
