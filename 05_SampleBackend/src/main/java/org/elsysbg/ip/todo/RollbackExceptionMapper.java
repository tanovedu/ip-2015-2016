package org.elsysbg.ip.todo;

import javax.persistence.RollbackException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RollbackExceptionMapper implements
	ExceptionMapper<RollbackException> {

	@Override
	public Response toResponse(RollbackException exception) {
		return Response.status(Response.Status.CONFLICT).
			entity(exception.getMessage()).
			type("text/plain").build();
	}

}
