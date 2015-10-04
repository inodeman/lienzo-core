/*
   Copyright (c) 2014,2015 Ahome' Innovation Technologies. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
// TODO - review DSJ

package com.ait.lienzo.client.core.shape.wires;

import java.util.HashMap;
import java.util.Map;

import com.ait.lienzo.client.core.Attribute;
import com.ait.lienzo.client.core.event.AttributesChangedEvent;
import com.ait.lienzo.client.core.event.AttributesChangedHandler;
import com.ait.lienzo.client.core.event.NodeDragEndEvent;
import com.ait.lienzo.client.core.event.NodeDragEndHandler;
import com.ait.lienzo.client.core.event.NodeDragMoveEvent;
import com.ait.lienzo.client.core.event.NodeDragMoveHandler;
import com.ait.lienzo.client.core.event.NodeDragStartEvent;
import com.ait.lienzo.client.core.event.NodeDragStartHandler;
import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.IPrimitive;
import com.ait.lienzo.client.core.shape.Node;
import com.ait.lienzo.client.core.shape.Shape;
import com.ait.lienzo.client.core.types.BoundingBox;
import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.client.core.types.Point2DArray;
import com.ait.lienzo.shared.core.types.ColorName;
import com.ait.lienzo.shared.core.types.Direction;
import com.ait.lienzo.shared.core.types.DragMode;

public class MagnetManager implements IMagnetManager
{
    public static final double         CONTROL_RADIUS       = 5;

    public static final double         CONTROL_STROKE_WIDTH = 2;

    private Map<String, IMagnets>      m_magnetRegistry     = new HashMap<String, IMagnets>();

    private static final MagnetManager m_instance           = new MagnetManager();

    public static final MagnetManager getInstance()
    {
        return m_instance;
    }

    public IMagnets createMagnets(Shape<?> shape, Point2DArray points)
    {
        return createMagnets(shape, null, points);
    }

    public Map<String, IMagnets> getMagnetRegistry()
    {
        return m_magnetRegistry;
    }

    public IMagnets createMagnets(Shape<?> shape, Group group, Point2DArray points)
    {
        ControlHandleList list = new ControlHandleList(shape);
        BoundingBox box = shape.getBoundingBox();

        double left = box.getX();
        double right = left + box.getWidth();
        double top = box.getY();
        double bottom = top + box.getHeight();

        Magnets magnets = new Magnets(this, list, shape, group);

        //Point2D absLoc = shape.getAbsoluteLocation();

        for (Point2D p : points)
        {
            Magnet m = new Magnet(magnets, null, 0, p.getX(), p.getY(), getControlPrimitive(p.getX(), p.getY(), shape), true);
            Direction d = getDirection(p, left, right, top, bottom);
            m.setDirection(d);
            list.add(m);
        }

        m_magnetRegistry.put(shape.uuid(), magnets);

        return magnets;
    }

    public IMagnets getMagnets(Shape<?> shape)
    {
        return m_magnetRegistry.get(shape.uuid());
    }

    public Direction getDirection(Point2D point, double left, double right, double top, double bottom)
    {
        double x = point.getX();
        double y = point.getY();

        double leftDist = Math.abs(x - left);
        double rightDist = Math.abs(x - right);

        double topDist = Math.abs(y - top);
        double bottomDist = Math.abs(y - bottom);

        boolean moreLeft = leftDist < rightDist;
        boolean moreTop = topDist < bottomDist;

        if (leftDist == rightDist && topDist == bottomDist)
        {
            // this is the center, so return NONE
            return Direction.NONE;
        }

        if (moreLeft)
        {
            if (moreTop)
            {
                if (topDist < leftDist)
                {
                    return Direction.NORTH;
                }
                else if (topDist > leftDist)
                {
                    return Direction.WEST;
                }
                else
                {
                    return Direction.NORTH_WEST;
                }
            }
            else
            {
                if (bottomDist < leftDist)
                {
                    return Direction.SOUTH;
                }
                else if (bottomDist > leftDist)
                {
                    return Direction.WEST;
                }
                else
                {
                    return Direction.SOUTH_WEST;
                }
            }
        }
        else
        {
            if (moreTop)
            {
                if (topDist < rightDist)
                {
                    return Direction.NORTH;
                }
                else if (topDist > rightDist)
                {
                    return Direction.EAST;
                }
                else
                {
                    return Direction.NORTH_EAST;
                }
            }
            else
            {
                if (bottomDist < rightDist)
                {
                    return Direction.SOUTH;
                }
                else if (bottomDist > rightDist)
                {
                    return Direction.EAST;
                }
                else
                {
                    return Direction.SOUTH_EAST;
                }
            }
        }
    }

    private static Circle getControlPrimitive(double x, double y, Shape<?> shape)
    {
        Point2D absLoc = shape.getAbsoluteLocation();
        return new Circle(CONTROL_RADIUS).setFillColor(ColorName.RED).setFillAlpha(0.4).setX(x + absLoc.getX()).setY(y + absLoc.getY()).setDraggable(true).setDragMode(DragMode.SAME_LAYER).setStrokeColor(ColorName.BLACK).setStrokeWidth(CONTROL_STROKE_WIDTH);
    }

    public static class Magnets implements IMagnets, AttributesChangedHandler, NodeDragStartHandler, NodeDragMoveHandler, NodeDragEndHandler
    {
        private IControlHandleList m_list;

        private MagnetManager      m_magnetManager;

        private Shape<?>           m_shape;

        private Group              m_group;

        private boolean            m_isDragging;

        public Magnets(MagnetManager magnetManager, IControlHandleList list, Shape<?> shape, Group group)
        {
            m_list = list;
            m_magnetManager = magnetManager;
            m_shape = shape;
            m_group = group;

            Node<?> node = getNodeTarget();
            node.addAttributesChangedHandler(Attribute.X, this);
            node.addAttributesChangedHandler(Attribute.Y, this);
            node.addNodeDragMoveHandler(this);
        }

        public Node<?> getNodeTarget()
        {
            return m_group != null ? m_group : m_shape;
        }

        public IPrimitive<?> getPrimTarget()
        {
            return m_group != null ? m_group : m_shape;
        }

        public void onAttributesChanged(AttributesChangedEvent event)
        {
            if (!m_isDragging && event.any(Attribute.X, Attribute.Y))
            {
                shapeMoved();
            }
        }

        @Override
        public void onNodeDragStart(NodeDragStartEvent event)
        {
            m_isDragging = true;
        }

        @Override
        public void onNodeDragEnd(NodeDragEndEvent event)
        {
            m_isDragging = false;
        }

        @Override
        public void onNodeDragMove(NodeDragMoveEvent event)
        {
            shapeMoved();
        }

        public void shapeMoved()
        {
            IPrimitive<?> prim = getPrimTarget();

            double x = prim.getX();
            double y = prim.getY();
            for (int i = 0; i < m_list.size(); i++)
            {
                Magnet m = (Magnet) m_list.getHandle(i);
                m.shapeMoved(x, y);
            }
            if (m_list.getLayer() != null)
            {
                // it can be null, if the magnets are not currently displayed
                m_list.getLayer().batch();
            }
        }

        public void show()
        {
            m_list.show();
        }

        public void hide()
        {
            m_list.hide();
        }

        public void destroy()
        {
            m_list.destroy();
            m_magnetManager.m_magnetRegistry.remove(m_shape.uuid());
        }

        public void destroy(Magnet magnet)
        {

            m_list.remove(magnet);
        }

        public IControlHandleList getMagnets()
        {
            return m_list;
        }

        @Override
        public int size()
        {
            return m_list.size();
        }

        @Override
        public Shape<?> getShape()
        {
            return m_shape;
        }

        public Group getGroup()
        {
            return m_group;
        }

        public Magnet getMagnet(int index)
        {
            return (Magnet) m_list.getHandle(index);
        }
    }
}