/**
 * 
 */
package ru.project.master.slave.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * Аннотация для маркировки методов, использующий соединение с базой только для
 * операция чтения.
 * 
 * @author Alimurad A. Ramazanov
 * @since 01.05.2017
 * @version 1.0.0
 *
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface ReadOnly {

}
