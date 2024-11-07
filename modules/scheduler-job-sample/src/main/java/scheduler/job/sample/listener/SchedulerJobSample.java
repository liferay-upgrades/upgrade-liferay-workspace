package scheduler.job.sample.listener;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.MessageListener;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import scheduler.job.sample.listener.configuration.SchedulerJobSampleConfiguration;

@Component(
		configurationPid = "scheduler.job.sample.listener.configuration.SchedulerJobSampleConfiguration",
		immediate = true,
		service = MessageListener.class
)
public class SchedulerJobSample extends BaseMessageListener {

	@Activate
	protected void activate(Map<String, Object> properties) {
		_schedulerJobSampleConfiguration = ConfigurableUtil.createConfigurable(
				SchedulerJobSampleConfiguration.class, properties);

		Class<?> clazz = getClass();

		String className = clazz.getName();

		Trigger trigger = _triggerFactory.createTrigger(
				className, className, null, null,
				_schedulerJobSampleConfiguration.checkJobInterval(),
				TimeUnit.MINUTE);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
				className, trigger);

		_schedulerEngineHelper.register(
				this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("Doing something cool");
	}

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

	private volatile SchedulerJobSampleConfiguration _schedulerJobSampleConfiguration;

	private static final Log _log = LogFactoryUtil.getLog(SchedulerJobSample.class);

}