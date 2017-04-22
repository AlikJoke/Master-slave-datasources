package ru.project.master.slave.routing;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.project.master.slave.datasources.DataSourceType;
import ru.project.master.slave.utils.ScopeType;

@Component
@Scope(ScopeType.SINGLETON)
public class DbHolderImpl implements DbHolder {

	private final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<DataSourceType>();

	@Override
	public void setDbType(DataSourceType dbType) {
		if (dbType == null)
			throw new RuntimeException("DbType can't be null");
		contextHolder.set(dbType);
	}

	@Override
	public DataSourceType getDbType() {
		return (DataSourceType) contextHolder.get();
	}

	@Override
	public void clearDbType() {
		contextHolder.remove();
	}
}
