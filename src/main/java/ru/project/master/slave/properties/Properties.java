package ru.project.master.slave.properties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.core.env.Environment;

/**
 * Интерфейс для получения свойств из файлов конфигурации.
 * 
 * @author Alimurad A. Ramazanov
 * @since 22.04.2017
 * @version 1.0.0
 *
 */
public interface Properties {

	/**
	 * Получение некоторого свойства по имени.
	 * <p>
	 * 
	 * @see Environment
	 * @param name
	 *            - имя свойства, не может быть {@code null}.
	 * @return свойство, может быть {@code null}.
	 */
	@Null
	String getProperty(@NotNull String name);

	/**
	 * Проверка наличия некоторого свойства в конфигурации.
	 * <p>
	 * 
	 * @see Environment
	 * @param name
	 *            - имя свойства, не может быть {@code null}.
	 * @return {@code true} - если свойство установлено и значение не
	 *         {@code null}, {@code false} - иначе.
	 */
	boolean contains(@NotNull String name);
}
