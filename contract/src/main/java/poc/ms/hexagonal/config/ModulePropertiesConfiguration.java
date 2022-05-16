package poc.ms.hexagonal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:contract_config.properties")
public class ModulePropertiesConfiguration {
}
