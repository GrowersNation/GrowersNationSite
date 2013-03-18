package org.growersnation.site.resources;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * <p>Provider to provide the following to Jersey framework:</p>
 * <ul>
 * <li>Provision of exception to response mapping</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

  private static final Logger log = LoggerFactory.getLogger(RuntimeExceptionMapper.class);

  private final PublicErrorResource publicErrorResource;

  /**
   * Request scoped injection
   */
  @Context
  private HttpHeaders httpHeaders;

  @Inject
  public RuntimeExceptionMapper(PublicErrorResource publicErrorResource) {
    this.publicErrorResource = publicErrorResource;
    publicErrorResource.setHttpHeaders(httpHeaders);
  }

  @Override
  public Response toResponse(RuntimeException runtime) {


    // Build default response
    Response defaultResponse = Response
      .serverError()
      .entity(publicErrorResource.view500())
      .build();

    // Check for any specific handling
    if (runtime instanceof WebApplicationException) {

      return handleWebApplicationException(runtime, defaultResponse);
    }

    // Use the default
    log.error(runtime.getMessage(),runtime);
    return defaultResponse;

  }

  private Response handleWebApplicationException(RuntimeException exception, Response defaultResponse) {
    WebApplicationException webAppException = (WebApplicationException) exception;

    // No logging
    if (webAppException.getResponse().getStatus() == Response.Status.UNAUTHORIZED.getStatusCode()) {
      return Response
        .status(Response.Status.UNAUTHORIZED)
        .entity(publicErrorResource.view401())
        .build();
    }
    if (webAppException.getResponse().getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
      return Response
        .status(Response.Status.NOT_FOUND)
        .entity(publicErrorResource.view404())
        .build();
    }

    // Debug logging

    // Warn logging

    // Error logging
    log.error(exception.getMessage(),exception);

    return defaultResponse;
  }

}
