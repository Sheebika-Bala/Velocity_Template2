package org.example;

import org.apache.velocity.*;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.VelocityContext;
import java.io.StringWriter;
import java.util.Map;

public class FormStepRenderer {
    private final VelocityEngine ve;

    public FormStepRenderer() {
        ve = new VelocityEngine();
        ve.setProperty("resource.loader", "class");
        ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.init();
    }

    public String render(Map<String, Object> field) {
        Template t = ve.getTemplate("templates/formpage.vm");
        VelocityContext context = new VelocityContext();
        context.put("field", field);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }
}



