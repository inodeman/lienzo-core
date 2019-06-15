package org.roger600.lienzo.client;

import org.gwtproject.dom.style.shared.Display;

import com.ait.lienzo.client.core.config.LienzoCoreEntryPoint;
import com.ait.lienzo.client.core.shape.GridLayer;
import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.client.widget.LienzoPanel2;
import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsMethod;

import static elemental2.dom.DomGlobal.document;

public class LienzoExamples implements EntryPoint {
    HTMLDivElement panelDiv;

    LienzoPanel2   lienzo;

    private Example test;

    public void onModuleLoad() {
        new LienzoCoreEntryPoint().onModuleLoad();
        createTests(new StrokeAndFillingExample("Stroke and Filling"),
                    new GradientsAndShadowsExample("Gradients and Shadows"),
                    new ColorsAndTransparencyExample("Colors and Transparency"),
                    new HorizontalTextAlignmentExample("Horizontal Text Alignment"),
                    new VerticalTextAlignmentExample("Vertical Text Alignment"),
                    new MeasureTextExample("Measure Text"),
                    new ScaledTextExample("Scaled Text"),
                    new TextAroundArcExample("Text around Arc"),
                    new PanAndZoomExample("Pan and Zoom"),
                    new TweeningExample("Tweening"),
                    new TimersExample("Timers"),
                    new DragCirclesExample("Drag Circles"),
                    new FixedDragConstraintsExample("Fixed Drag Constraints"),
                    new CustomDragConstraintsExample("Custom Drag Constraints"),
                    new AnimatedCirclesExample("Animated Circles"),
                    new EventExample("Events"),
                    new SVGTigerExample("SVG Paths Tiger"),
                    new WiresExample("Wires"),
                    new Animate("Animations"),
                    new LionExample("Polygon Lion with Clipping"),
                    new CardinalIntersectsExample("Cardinal Intersects"),
                    new CornerRadiusExample("Corner Radius"),
                    new ShapesExample("Shapes Example"),
                    new MovieExample("Video"),
                    new DrawImageExample("Draw Image"),
                    new Transform3PointsExample("Transform 3Points"),
                    new SpriteExample("Sprite Example"),
                    new AsteroidsGameExample("Asteroids Game")
                   );
    }

    @JsMethod
    public void createTests(Example... tests)
    {
        for ( Example test : tests)
        {
            createTest(test);
        }
    }


    @JsMethod
    public void createTest(Example test)
    {
        HTMLDivElement e1 = (HTMLDivElement) document.createElement("div");
        HTMLDivElement top = (HTMLDivElement) document.getElementById("top");
        elemental2.dom.Text e1Text = document.createTextNode(test.getTitle());
        e1.appendChild(e1Text);
        e1.addEventListener("click", evt -> {
            top.style.display = Display.NONE.getCssName();
            createPanel(test);
            this.test = test;
            this.test.init(lienzo, top);
            this.test.run();

        });
        Element links = document.getElementById("nav");
        links.appendChild(e1);
    }

    private void createPanel(Example test)
    {
        if (this.test != null)
        {
            this.test.destroy();
            this.test = null;
        }

        panelDiv = (HTMLDivElement) document.createElement("div");
        panelDiv.style.display = "inline-block";
        HTMLDivElement main = (HTMLDivElement) document.getElementById("main");
        main.appendChild(panelDiv);

        lienzo = new LienzoPanel2(panelDiv, true, test.getWidthOffset(), test.getHeightOffset());
        applyGrid(lienzo);

        DomGlobal.window.addEventListener("resize", (e) ->
        {
            test.onResize();
        });
    }

    private void applyGrid( final LienzoPanel2 panel) {
        // Grid.
        Line line1 = new Line(0, 0, 0, 0 )
                .setStrokeColor( "#0000FF" )
                .setAlpha( 0.2 );
        Line line2 = new Line( 0, 0, 0, 0 )
                .setStrokeColor( "#00FF00"  )
                .setAlpha( 0.2 );

        line2.setDashArray( 2,
                2 );

        GridLayer gridLayer = new GridLayer(100, line1, 25, line2 );

        panel.setBackgroundLayer( gridLayer );
    }
}
