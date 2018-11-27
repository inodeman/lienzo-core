/*
   Copyright (c) 2017 Ahome' Innovation Technologies. All rights reserved.

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

package com.ait.lienzo.client.core.image.filter;

import com.ait.lienzo.client.core.Attribute;
import com.ait.lienzo.client.core.shape.json.validators.ValidationContext;
import com.ait.lienzo.client.core.shape.json.validators.ValidationException;
import com.ait.lienzo.client.core.types.ImageDataUtil;
import com.ait.lienzo.shared.core.types.ImageFilterType;
import com.google.gwt.json.client.JSONObject;

import elemental2.core.Uint8ClampedArray;
import elemental2.dom.ImageData;

public abstract class AbstractValueTransformImageDataFilter<T extends AbstractValueTransformImageDataFilter<T>> extends AbstractValueImageDataFilter<T>
{
    protected AbstractValueTransformImageDataFilter(final ImageFilterType type, final double value)
    {
        super(type, value);
    }

    protected AbstractValueTransformImageDataFilter(final ImageFilterType type, final JSONObject node, final ValidationContext ctx) throws ValidationException
    {
        super(type, node, ctx);
    }

    @Override
    public ImageData filter(ImageData source, final boolean copy)
    {
        if (null == source)
        {
            return null;
        }
        if (copy)
        {
            source = ImageDataUtil.copy(source);
        }
        if (false == isActive())
        {
            return source;
        }
        final Uint8ClampedArray data = source.data;

        if (null == data)
        {
            return source;
        }
        final ImageData result = ImageDataUtil.create(source);

        FilterCommonOps.doFilterTransform(data, result.data, getTransform(getValue()), source.width, source.height);

        return result;
    }

    @Override
    public final boolean isTransforming()
    {
        return true;
    }

    protected abstract FilterTransformFunction getTransform(double value);

    protected static abstract class ValueTransformImageDataFilterFactory<T extends AbstractValueTransformImageDataFilter<T>> extends ImageDataFilterFactory<T>
    {
        protected ValueTransformImageDataFilterFactory(final ImageFilterType type)
        {
            super(type);

            addAttribute(Attribute.VALUE, true);
        }
    }
}
