package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration principale Spring IoC avec activation du ComponentScan et PropertySource.
 */
@Configuration
@ComponentScan(basePackages = {"dao", "metier", "config"})
@PropertySource(value = "classpath:app.properties", ignoreResourceNotFound = true)
public class AppConfig {
}
