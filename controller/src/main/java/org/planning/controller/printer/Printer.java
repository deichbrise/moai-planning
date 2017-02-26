package org.planning.controller.printer;

/**
 * @author pascalstammer
 * @version 26.02.17.
 */
public interface Printer<T> {
    public void print(T input);
}
