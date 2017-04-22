package ru.project.master.slave.routing;

import javax.validation.constraints.NotNull;

import ru.project.master.slave.datasources.DataSourceType;

/**
 * Интерфейс работы с типами datasource.
 * 
 * @author Alimurad A. Ramazanov
 * @since 23.04.2017
 * @version 1.0.0
 *
 */
public interface DbHolder {

	/**
	 * Устанавливает тип ds.
	 * <p>
	 * 
	 * @see DataSourceType
	 * @param type
	 *            - тип datasource для использования в запросе.
	 * @throws RuntimeException
	 *             - если {@code type == null}.
	 */
	void setDbType(@NotNull DataSourceType type);

	/**
	 * Возвращает тип ds.
	 * <p>
	 * 
	 * @see DataSourceType
	 * @return тип datasource для использования в запросе, не может быть
	 *         {@code null}.
	 */
	@NotNull
	DataSourceType getDbType();

	/**
	 * Удаление threadlocal-переменной.
	 * <p>
	 * 
	 * @see ThreadLocal
	 */
	void clearDbType();
}
