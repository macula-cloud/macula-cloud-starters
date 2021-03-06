package org.macula.cloud.cainiao.configure;

import org.macula.cloud.cainiao.CainiaoLinkService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ CainiaoConfig.class })
public class CainiaoAutoConfiguration {

	@Bean
	public CainiaoLinkService cainiaoLinkServiceBean(CainiaoConfig config) {
		return new CainiaoLinkService(config);
	}
}
