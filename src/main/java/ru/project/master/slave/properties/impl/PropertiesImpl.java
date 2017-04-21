package ru.project.master.slave.properties.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ru.project.master.slave.properties.Properties;
import ru.project.master.slave.utils.ScopeType;

@Component
@Scope(ScopeType.SINGLETON)
@PropertySource(value = { "classpath:application.properties" })
public class PropertiesImpl implements Properties {

	@Autowired
	private Environment env;

	@Override
	public String getProperty(String name) {
		return env.getProperty(name);
	}

	@Override
	public boolean contains(String name) {
		return StringUtils.hasLength(env.getProperty(name));
	}

}
