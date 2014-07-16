/***************************************************************************
 * Copyright 2014 Kicker Project (http://kicker-monitoring.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kicker.tools.opad.filter;

import kicker.analysis.IProjectContext;
import kicker.analysis.plugin.annotation.InputPort;
import kicker.analysis.plugin.annotation.OutputPort;
import kicker.analysis.plugin.annotation.Plugin;
import kicker.analysis.plugin.annotation.Property;
import kicker.analysis.plugin.filter.AbstractFilterPlugin;
import kicker.common.configuration.Configuration;
import kicker.tools.opad.record.StorableDetectionResult;

/**
 * 
 * This filter separates input values by their reach of a certain threshold (parameter). It takes events of type {@link StorableDetectionResult} and channels them
 * into two output ports, depending on whether the threshold was reached or not. This filter has configuration properties for the (critical) threshold. Although the
 * configuration of the critical threshold is possible, the value is currently not used by the filter.
 * 
 * @author Tillmann Carlos Bielefeld
 * 
 * @since 1.9
 */
@Plugin(name = "Anomaly Detection Filter",
		outputPorts = {
			@OutputPort(eventTypes = { StorableDetectionResult.class }, name = AnomalyDetectionFilter.OUTPUT_PORT_ANOMALY_SCORE_IF_ANOMALY),
			@OutputPort(eventTypes = { StorableDetectionResult.class }, name = AnomalyDetectionFilter.OUTPUT_PORT_ANOMALY_SCORE_ELSE) },
		configuration = {
			@Property(name = AnomalyDetectionFilter.CONFIG_PROPERTY_NAME_THRESHOLD,
					defaultValue = AnomalyDetectionFilter.CONFIG_PROPERTY_VALUE_THRESHOLD),
			@Property(name = AnomalyDetectionFilter.CONFIG_PROPERTY_NAME_THRESHOLD_CRITICAL,
					defaultValue = AnomalyDetectionFilter.CONFIG_PROPERTY_VALUE_THRESHOLD_CRITICAL)
		})
public class AnomalyDetectionFilter extends AbstractFilterPlugin {

	public static final String INPUT_PORT_ANOMALY_SCORE = "anomalyscore";

	public static final String OUTPUT_PORT_ANOMALY_SCORE_IF_ANOMALY = "anomalyscore_anomaly";
	public static final String OUTPUT_PORT_ANOMALY_SCORE_ELSE = "anomalyscore_else";

	public static final String CONFIG_PROPERTY_NAME_THRESHOLD = "threshold";
	public static final String CONFIG_PROPERTY_NAME_THRESHOLD_CRITICAL = "thresholdcritical";

	public static final String CONFIG_PROPERTY_VALUE_THRESHOLD = "0.5";
	public static final String CONFIG_PROPERTY_VALUE_THRESHOLD_CRITICAL = "0.95";

	private final double threshold;
	private final double thresholdCritical;

	public AnomalyDetectionFilter(final Configuration configuration, final IProjectContext projectContext) {
		super(configuration, projectContext);

		final String sThreshold = super.configuration.getStringProperty(CONFIG_PROPERTY_NAME_THRESHOLD);
		this.threshold = Double.parseDouble(sThreshold);

		final String sThresholdCritical = super.configuration.getStringProperty(CONFIG_PROPERTY_NAME_THRESHOLD_CRITICAL);
		this.thresholdCritical = Double.parseDouble(sThresholdCritical);
	}

	@Override
	public Configuration getCurrentConfiguration() {
		final Configuration config = new Configuration();

		config.setProperty(CONFIG_PROPERTY_NAME_THRESHOLD, Double.toString(this.threshold));
		config.setProperty(CONFIG_PROPERTY_NAME_THRESHOLD_CRITICAL, Double.toString(this.thresholdCritical));

		return config;
	}

	@InputPort(eventTypes = { StorableDetectionResult.class }, name = AnomalyDetectionFilter.INPUT_PORT_ANOMALY_SCORE)
	public void inputForecastAndMeasurement(final StorableDetectionResult anomalyScore) {
		if (anomalyScore.getScore() >= this.thresholdCritical) {
			super.deliver(OUTPUT_PORT_ANOMALY_SCORE_IF_ANOMALY, anomalyScore);
		} else if (anomalyScore.getScore() >= this.threshold) {
			super.deliver(OUTPUT_PORT_ANOMALY_SCORE_IF_ANOMALY, anomalyScore);
		} else {
			super.deliver(OUTPUT_PORT_ANOMALY_SCORE_ELSE, anomalyScore);
		}
	}

}