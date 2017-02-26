package org.planning.io.service;

import org.planning.domain.model.DomainModel;

/**
 * @author pascalstammer
 */
public interface DomainModelClassMapper extends Mapper<String, Class<? extends DomainModel>> {
}
