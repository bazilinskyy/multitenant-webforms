/*
 * MHG Systems Oy Ltd 2011
 */
//MISC-31 JSF2
package com.mhgsystems.commons.jsf;


import com.sun.faces.renderkit.html_basic.MessageRenderer;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.context.ResponseWriterWrapper;
import javax.faces.render.FacesRenderer;

/**
 *
 * @author Veli-Matti Plosila
 */
@FacesRenderer(rendererType="javax.faces.Message", componentFamily="javax.faces.Message")
public class MessageRendererImpl extends MessageRenderer {

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final ResponseWriter originalResponseWriter = context.getResponseWriter();
        context.setResponseWriter(new ResponseWriterWrapper() {

            @Override
            public ResponseWriter getWrapped() {
                return originalResponseWriter;
            }

            @Override
            public void writeText(Object text, UIComponent component, String property)
                throws IOException
            {
                String string = String.valueOf(text);
                super.write(string);
              
            }
        });

        super.encodeEnd(context, component);
        context.setResponseWriter(originalResponseWriter); // Restore original writer.
    }
}
