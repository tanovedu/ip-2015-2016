package org.elsysbg.ip.todo.test.clients;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.test.BaseIntegrationTest;
import org.glassfish.jersey.test.TestProperties;

public class BaseClient {
	private static final String PREFIX_URL_REST = "api/v1";
	
	private class LoadCookiesFilter implements ClientRequestFilter {
		@Override
		public void filter(ClientRequestContext requestContext) throws IOException {
			if (jerseyTest.getSavedCookies().size() == 0) {
				return;
			}
			final MultivaluedMap<String, Object> stringHeaders = requestContext.getHeaders();
			List<Object> cookies = stringHeaders.get(HttpHeaders.COOKIE);
			if (cookies == null) {
				cookies = new LinkedList<Object>();
				stringHeaders.put(HttpHeaders.COOKIE, cookies);
			}
			for (Entry<String, String> next : jerseyTest.getSavedCookies().entrySet()) {
				// TODO: fix setting cookies
				cookies.add(next.getKey() + "=" + next.getValue());
			}
		}
	}
	
	private class SaveCookiesFilter implements ClientResponseFilter {
		@Override
		public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext)
				throws IOException {
			for (Entry<String, NewCookie> next : responseContext.getCookies().entrySet()) {
				jerseyTest.getSavedCookies().put(next.getKey(), next.getValue().getValue());
			}
		}
	}

	private final BaseIntegrationTest jerseyTest;
	private final String endpoint;

	protected BaseClient(BaseIntegrationTest jerseyTest, String endpoint) {
		this.jerseyTest = jerseyTest;
		this.endpoint = endpoint;
	}

	protected WebTarget createTarget() {
		final WebTarget target = jerseyTest.client().target(getBaseUri());
		target.register(new LoadCookiesFilter());
		target.register(new SaveCookiesFilter());
		return target.path(PREFIX_URL_REST).path(endpoint);
	}

	/**
	 * Because jerseyTest.getTestContainer() is not accessible
	 */
	private URI getBaseUri() {
		// see JerseyTest.getBaseUri()
        final int port = Integer.parseInt(System.getProperty(TestProperties.CONTAINER_PORT), 10);
		return UriBuilder.fromUri("http://localhost/").port(port).build();
	}

}
