package com.ait.lienzo.client.core.image;

import com.ait.lienzo.client.core.Context2D;
import com.ait.lienzo.client.core.config.LienzoCore;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Image;

import elemental2.dom.HTMLImageElement;
import jsinterop.base.Js;

// @FIXME need rogoer to help me understand this, before I continue (mdp)
public class ImageElementProxy {
    private HTMLImageElement imageElement;
    private Image imageWidget;

    public ImageElementProxy() {
    }

    ImageElementProxy(HTMLImageElement imageElement, Image imageWidget) {
        this.imageElement = imageElement;
        this.imageWidget = imageWidget;
    }

    public void load(final String url,
                     final Runnable callback) {
        assert null == imageWidget;
        assert null == imageElement;
        imageWidget = new Image();

        new ImageLoader(url,
                        Js.uncheckedCast(imageWidget.getElement())) {

            @Override
            public void onImageElementLoad(final HTMLImageElement image) {
                ImageElementProxy.this.imageElement = image;
                callback.run();
            }

            @Override
            public void onImageElementError(final String errorMessage) {
                LienzoCore.get().error("Error loading Image. Message: [" + errorMessage + "]");
            }
        };
    }

    public void draw(final Context2D context) {
        context.drawImage(imageElement, 0, 0);
    }

    public void draw(final Context2D context,
                     final ImageClipBounds clipBounds) {
        final double clipX = clipBounds.getClipXPos();
        final double clipY = clipBounds.getClipYPos();
        final int width = getWidth();
        final int height = getHeight();
        final double _clipWide = clipBounds.getClipWide();
        final double clipWide = _clipWide > 0 ? _clipWide : width;
        final double _clipHigh = clipBounds.getClipHigh();
        final double clipHigh = _clipHigh > 0 ? _clipHigh : height;
        final double _destWide = clipBounds.getDestWide();
        final double destWide = _destWide > 0 ? _destWide : width;
        final double _destHigh = clipBounds.getDestHigh();
        final double destHigh = _destHigh > 0 ? _destHigh : height;
        context.drawImage(imageElement, clipX, clipY, clipWide, clipHigh, 0, 0, destWide, destHigh);
    }

    public boolean isLoaded() {
        return null != imageElement;
    }

    public int getWidth() {
        return isLoaded() ? imageElement.width : 0;
    }

    public int getHeight() {
        return isLoaded() ? imageElement.height : 0;
    }

    public void destroy() {
        RootPanel.get().remove(imageWidget);
        imageWidget.removeFromParent();
        imageElement = null;
        imageWidget = null;
    }
}
