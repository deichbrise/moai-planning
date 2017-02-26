package org.planning.io.service;

/**
 * @author pascalstammer
 */
public interface Mapper<I, O> {

    O map(I input);
}
