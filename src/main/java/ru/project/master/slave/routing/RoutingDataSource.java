package ru.project.master.slave.routing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Service;

@Service
public class RoutingDataSource extends AbstractRoutingDataSource {

	@Autowired
	private DbHolder dbHolder;

	@Override
	protected Object determineCurrentLookupKey() {
		return dbHolder.getDbType();
	}

}
