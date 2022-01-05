package co.kr.springboot.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class GlobalConfig {
	@Autowired ApplicationContext context;
	
	@Autowired ResourceLoader resourceLoader;
	
	private String uploadFilePath;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private boolean local;
	private boolean dev;
	private boolean prod;
	
	private String schedulerCronExample1;
	
	@PostConstruct
	public void init() {
		logger.info("init");
		String[] activeProfiles = context.getEnvironment().getActiveProfiles();
		
		String activeProfile = "local";
		
		if(ObjectUtils.isNotEmpty(activeProfiles)) {			
			activeProfile = activeProfiles[0];
		}
		
		String resourcePath = String.format("classpath:globals/global-%s.properties", activeProfiles);
		
		try {
			Resource resource = resourceLoader.getResource(resourcePath);
			Properties properties= PropertiesLoaderUtils.loadProperties(resource);
			uploadFilePath = properties.getProperty("uploadFile.path");
			schedulerCronExample1 = properties.getProperty("schedule.cron.example1");
			this.local = activeProfile.equals("local");
			this.dev = activeProfile.equals("dev");
			this.prod = activeProfile.equals("prod");
		} catch (IOException e) {
			logger.error("e", e);
		}
		
	}
	
	public String getUploadFilePath() {
		return this.uploadFilePath;
	}
	public String getSchedulerCronExample1() {
		return this.schedulerCronExample1;
	}
	
	public boolean isLocal() {
		return local;
	}
	public boolean isDev() {
		return dev;
	}
	public boolean isProd() {
		return prod;
	}
}
