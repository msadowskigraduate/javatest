package com.cdprojektred.javatest.gateway;

import com.cdprojektred.javatest.productfinder.ProductConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ProductConfiguration.class})
public class ServiceConfiguration {
}
