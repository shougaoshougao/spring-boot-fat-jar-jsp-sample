package io.github.hengyunabc.sample;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.catalina.*;
import org.apache.catalina.WebResourceRoot.ResourceSetType;
import org.springframework.util.ResourceUtils;

/**
 * Add main class fat jar/exploded directory into tomcat ResourceSet.
 *
 * @author hengyunabc 2017-07-29
 * @author Wilson 2019-11-15
 *
 */
public class StaticResourceConfigurer implements LifecycleListener {

    private final Context context;

    private final Class applicationClass;

    public StaticResourceConfigurer(Context context, Class applicationClass) {
        this.context = context;
        this.applicationClass = applicationClass;
    }

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        if (event.getType().equals(Lifecycle.CONFIGURE_START_EVENT)) {
            URL location = applicationClass.getProtectionDomain().getCodeSource().getLocation();

            String locationStr = location.toString();
            // when run as fat jar
            if (locationStr.endsWith("/BOOT-INF/classes!/")) {
                locationStr = locationStr.substring(0, locationStr.length() - "/BOOT-INF/classes!/".length() + 1);
                try {
                    location = new URL(locationStr);
                } catch (MalformedURLException e) {
                    throw new IllegalStateException("Can not add tomcat resources", e);
                }
                // maven jar
                this.context.getResources().createWebResourceSet(WebResourceRoot.ResourceSetType.RESOURCE_JAR, "/", location, "/META-INF/resources");
                // gradle jar
                this.context.getResources().createWebResourceSet(WebResourceRoot.ResourceSetType.RESOURCE_JAR, "/", location, "/BOOT-INF/classes/META-INF/resources");
            }

        }
    }
}
