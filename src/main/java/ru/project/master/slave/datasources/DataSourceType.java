package ru.project.master.slave.datasources;

/**
 * Перечисление возможных типов data source.
 * 
 * @author Alimurad A. Ramazanov
 * @since 22.04.2017
 * @version 1.0.0
 *
 */
public enum DataSourceType {

	/**
	 * master - для операций write.
	 */
	MASTER,

	/**
	 * slave - для операций read.
	 */
	SLAVE;
}
