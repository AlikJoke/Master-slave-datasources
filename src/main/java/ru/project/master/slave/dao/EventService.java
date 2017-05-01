package ru.project.master.slave.dao;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import ru.project.master.slave.orm.Event;

/**
 * Интерфейс доступа к данным.
 * 
 * @author Alimurad A. Ramazanov
 * @since 23.04.2017
 * @version 1.0.0
 *
 */
public interface EventService {

	/**
	 * Получение списка всех событий.
	 * <p>
	 * 
	 * @see Event
	 * @return не может быть {@code null}.
	 */
	@NotNull
	List<Event> getEvents();
	
	/**
	 * Получение события по идентификатору.
	 * <p>
	 * 
	 * @see Event
	 * @param id
	 *            - идентификатор, не может быть {@code null}.
	 * @return может быть {@code null}.
	 */
	@Null
	Event getEventById(@NotNull String id);

	/**
	 * Обновление события.
	 * <p>
	 * 
	 * @see Event
	 * @param event
	 *            - обновляемое событие, не может быть {@code null}.
	 */
	void updateEvent(@NotNull Event event);

	/**
	 * Создание события.
	 * <p>
	 * 
	 * @see Event
	 * @param event
	 *            - создаваемое событие, не может быть {@code null}.
	 */
	void createEvent(@NotNull Event event);

	/**
	 * Удаление события.
	 * <p>
	 * 
	 * @see Event
	 * @param event
	 *            - удаляемое событие, не может быть {@code null}.
	 */
	void delete(@NotNull Event event);
}
