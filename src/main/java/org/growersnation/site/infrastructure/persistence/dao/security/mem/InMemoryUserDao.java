package org.growersnation.site.infrastructure.persistence.dao.security.mem;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.bson.types.ObjectId;
import org.growersnation.site.infrastructure.persistence.dao.security.UserDao;
import org.growersnation.site.interfaces.rest.api.PaginationData;
import org.growersnation.site.interfaces.rest.api.security.UserDto;

import java.util.List;
import java.util.UUID;

/**
 * <p>DAO to provide the following to {@link org.growersnation.site.interfaces.rest.api.security.UserDto}:</p>
 * <ul>
 * <li>A small scale implementation of a simple User repository</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class InMemoryUserDao implements UserDao {

}
