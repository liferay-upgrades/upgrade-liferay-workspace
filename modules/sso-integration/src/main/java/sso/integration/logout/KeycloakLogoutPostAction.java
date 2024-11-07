package sso.integration.logout;

import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectProvider;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectProviderRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

import static com.liferay.portal.kernel.json.JSONFactoryUtil.createJSONObject;

@Component(
		immediate = true,
		property = "key=logout.events.post",
		service = LifecycleAction.class
)
public class KeycloakLogoutPostAction implements LifecycleAction {

	private static final Log LOG = LogFactoryUtil.getLog(KeycloakLogoutPostAction.class);

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) {
		try {
			Collection<String> openIdConnectProviderNames =
					_openIdConnectProviderRegistry.getOpenIdConnectProviderNames();

			if (openIdConnectProviderNames == null || openIdConnectProviderNames.isEmpty()) {
				LOG.warn("No OpenID Connect Providers found.");
				return;
			}

			String openIdConnectProviderName = openIdConnectProviderNames.iterator().next();
			OpenIdConnectProvider<?, ?> openIdConnectProvider =
					_openIdConnectProviderRegistry.getOpenIdConnectProvider(openIdConnectProviderName);

			Object oidcProviderMetadata = openIdConnectProvider.getOIDCProviderMetadata();

			JSONObject oidcJsonObject = createJSONObject(oidcProviderMetadata.toString());

			String logoutEndpoint = StringUtil.replaceLast(
					oidcJsonObject.getString("authorization_endpoint"), "/auth", "/logout");

			String logoutUrl = logoutEndpoint + "?redirect_uri=" + getRedirectUrl(lifecycleEvent.getRequest());

			lifecycleEvent.getResponse().sendRedirect(logoutUrl);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String getRedirectUrl(HttpServletRequest request) {
		return _portal.getPortalURL(request) + "/";
	}


	@Reference
	private Portal _portal;

	@Reference
	private OpenIdConnectProviderRegistry<?, ?> _openIdConnectProviderRegistry;
}