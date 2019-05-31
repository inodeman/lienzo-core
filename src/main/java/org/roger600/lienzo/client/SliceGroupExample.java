package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.Slice;
import com.ait.lienzo.shared.core.types.ColorName;

public class SliceGroupExample extends BaseExample implements Example {

	private SliceGroup sliceGroup;
	
	public SliceGroupExample(String title) {
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
		final int w = width / 2;  
        final int h = height / 2;  
          
        sliceGroup = new SliceGroup(w);  
        sliceGroup.setRotation(-Math.PI / 2);  
        sliceGroup.setX(w - w/2).setY(h + w/2);  
          
        layer.add(sliceGroup); 
	}
	

	@Override
    public void onResize() {
        super.onResize();
        console.log("ReDrawing Slice Group Example on Resize --->>>");
        setLocation();   	
        layer.batch();
    }
	
	private void setLocation() {
		final int w = width / 2;  
        final int h = height / 2; 
        
	    sliceGroup.resize(w);
	    sliceGroup.setX(w - w/2).setY(h + w/2); 
	}
	
	
	private class SliceGroup extends Group {  
  	  
		private Slice[] slices = new Slice[3];
		private Rectangle rectangle;
		
		private double width;
		
        public SliceGroup(double width) {  
  
        	this.width = width;
        	rectangle = new Rectangle(width, width).setStrokeColor(ColorName.BLACK.getValue());
        	add(rectangle);  
  
            final double radius = width / 4;  
              
            slices[0] = new Slice(radius, 0, Math.PI / 2, true);  
            slices[0].setX(radius).setY(radius);
            slices[0].setFillColor(ColorName.RED.getValue());  
            slices[0].setAlpha(0.5);  
            slices[0].setDraggable(true);  
            add(slices[0]);  
  
            slices[1] = new Slice(radius, 0.75 * Math.PI, 3 * Math.PI / 2, true);  
            slices[1].setX(3 * radius).setY(radius);  
            slices[1].setScale(0.5);  
            slices[1].setFillColor(ColorName.GREEN.getValue());  
            slices[1].setAlpha(0.5);  
            slices[1].setDraggable(true);  
            add(slices[1]);  
  
            slices[2] = new Slice(radius, 0, Math.PI);  
            slices[2].setX(radius).setY(3 * radius);  
            slices[2].setRotation(Math.PI / 4);  
            slices[2].setFillColor(ColorName.BLUE.getValue());  
            slices[2].setAlpha(0.5);  
            slices[2].setDraggable(true);  
            add(slices[2]);  
        }  
        
        public void resize(int width) {
        	this.width = width;
        	setSizeAndLocation();
        }
        
        public void setSizeAndLocation() {
        	final double radius = width / 4;  
        	rectangle.setWidth(width);
        	rectangle.setHeight(width);
        	
        	slices[0].setRadius(radius).setStartAngle(0).setEndAngle(Math.PI / 2).setCounterClockwise(true)
        	.setX(radius).setY(radius);
        	
        	slices[1].setRadius(radius).setStartAngle(0.75 * Math.PI).setEndAngle(3 * Math.PI / 2).setCounterClockwise(true)
        	.setX(3 * radius).setY(radius);
        	
        	slices[2].setRadius(radius).setStartAngle(0).setEndAngle(Math.PI)
        	.setX(radius).setY(3 * radius);  
        }
  
    }  

}
