package sample.rest.builder.resource.v1_0;

import com.liferay.portal.vulcan.accept.language.AcceptLanguage;

import java.util.Locale;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.annotation.versioning.ProviderType;

import sample.rest.builder.dto.v1_0.Bar;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/sample-rest-builder/v1.0
 *
 * @author me
 * @generated
 */
@Generated("")
@ProviderType
public interface BarResource {

	public static Builder builder() {
		return FactoryHolder.factory.create();
	}

	public Bar postBar(Bar bar) throws Exception;

	public Bar deleteBar(Long barId) throws Exception;

	public Bar getBar(Long barId) throws Exception;

	public Bar patchBar(Long barId, Bar bar) throws Exception;

	public Bar putBar(Long barId, Bar bar) throws Exception;

	public default void setContextAcceptLanguage(
		AcceptLanguage contextAcceptLanguage) {
	}

	public void setContextCompany(
		com.liferay.portal.kernel.model.Company contextCompany);

	public default void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {
	}

	public default void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {
	}

	public default void setContextUriInfo(UriInfo contextUriInfo) {
	}

	public void setContextUser(
		com.liferay.portal.kernel.model.User contextUser);

	public static class FactoryHolder {

		public static volatile Factory factory;

	}

	@ProviderType
	public interface Builder {

		public BarResource build();

		public Builder checkPermissions(boolean checkPermissions);

		public Builder httpServletRequest(
			HttpServletRequest httpServletRequest);

		public Builder preferredLocale(Locale preferredLocale);

		public Builder user(com.liferay.portal.kernel.model.User user);

	}

	@ProviderType
	public interface Factory {

		public Builder create();

	}

}