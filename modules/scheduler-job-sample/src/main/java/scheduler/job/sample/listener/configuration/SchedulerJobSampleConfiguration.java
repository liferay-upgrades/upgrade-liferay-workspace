package scheduler.job.sample.listener.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Rafael Praxedes
 */
@ExtendedObjectClassDefinition(category = "sample")
@Meta.OCD(
	id = "scheduler.job.sample.listener.configuration.SchedulerJobSampleConfiguration",
	localization = "content/Language",
	name = "scheduler-job-sample-configuration-name"
)
public interface SchedulerJobSampleConfiguration {

	@Meta.AD(
		deflt = "10", description = "check-job-interval-description",
		min = "1", name = "check-job-interval", required = false
	)
	public int checkJobInterval();


}