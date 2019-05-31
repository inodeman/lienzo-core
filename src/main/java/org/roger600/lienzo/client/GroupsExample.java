package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.Star;
import com.ait.lienzo.shared.core.types.Color;

public class GroupsExample extends BaseExample implements Example {

	private Star[] stars = new Star[5];
	
	public GroupsExample(String title) {
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
		final Group group = new Group();  
        group.setDraggable(true);  
  
        for (int i = 0; i < 5; i++) {  
            final int strokeWidth = Util.randomNumber(2, 10);  
            stars[i] = new Star((int) (Math.random() * 10), 25, 50);  
            stars[i].setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setFillColor(Color.getRandomHexColor());  
            group.add(stars[i]);  
        }  
        setLocation();
        layer.add(group);
	}
	
	@Override
    public void onResize() {
        super.onResize();
        console.log("ReDrawing Group Example on Resize --->>");
        setLocation();   	
        layer.batch();
    }
	
	private void setLocation() {
	    
	    for (int i = 0; i < 5; i++) {  
	    	 setRandomLocation(stars[i]);
	    }
	}

}
