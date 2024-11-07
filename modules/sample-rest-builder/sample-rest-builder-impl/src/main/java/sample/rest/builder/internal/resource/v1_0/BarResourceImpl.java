package sample.rest.builder.internal.resource.v1_0;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import sample.rest.builder.resource.v1_0.BarResource;

/**
 * @author me
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/bar.properties",
	scope = ServiceScope.PROTOTYPE, service = BarResource.class
)
public class BarResourceImpl extends BaseBarResourceImpl {
}