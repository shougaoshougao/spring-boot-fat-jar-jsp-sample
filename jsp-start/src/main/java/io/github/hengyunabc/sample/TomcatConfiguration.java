package io.github.hengyunabc.sample;

import org.apache.catalina.Context;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author hengyunabc 2017-07-29
 *
 */
@Configuration
@ConditionalOnProperty(name = "tomcat.staticResourceCustomizer.enabled", matchIfMissing = true)
public class TomcatConfiguration {

	@Bean
	public WebServerFactoryCustomizer WebServerFactoryCustomizer() {
		return (WebServerFactoryCustomizer<TomcatServletWebServerFactory>) factory -> factory.addContextCustomizers(
				context -> context.addLifecycleListener(new StaticResourceConfigurer(context, Application.class))
		);
	}

}
