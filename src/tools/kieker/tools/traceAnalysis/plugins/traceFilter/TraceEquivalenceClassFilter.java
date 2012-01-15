/***************************************************************************
 * Copyright 2011 by
 *  + Christian-Albrechts-University of Kiel
 *    + Department of Computer Science
 *      + Software Engineering Group 
 *  and others.
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

package kieker.tools.traceAnalysis.plugins.traceFilter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import kieker.analysis.plugin.port.InputPort;
import kieker.analysis.plugin.port.OutputPort;
import kieker.analysis.plugin.port.Plugin;
import kieker.common.configuration.Configuration;
import kieker.common.logging.Log;
import kieker.common.logging.LogFactory;
import kieker.tools.traceAnalysis.plugins.AbstractExecutionTraceProcessingPlugin;
import kieker.tools.traceAnalysis.plugins.traceReconstruction.InvalidTraceException;
import kieker.tools.traceAnalysis.systemModel.Execution;
import kieker.tools.traceAnalysis.systemModel.ExecutionTrace;
import kieker.tools.traceAnalysis.systemModel.MessageTrace;
import kieker.tools.traceAnalysis.systemModel.repository.AbstractRepository;
import kieker.tools.traceAnalysis.systemModel.repository.SystemModelRepository;

/**
 * This class has exactly one input port named "in" and two output ports named
 * "messageTraceOutput", "executionTraceOutput".
 * 
 * @author Andre van Hoorn
 */
@Plugin(outputPorts = {
	@OutputPort(
			name = TraceEquivalenceClassFilter.MSG_TRACES_OUTPUT_PORT_NAME,
			description = "Message Traces",
			eventTypes = { MessageTrace.class }),
	@OutputPort(
			name = TraceEquivalenceClassFilter.EXECUTION_TRACES_OUTPUT_PORT_NAME,
			description = "Execution Traces",
			eventTypes = { ExecutionTrace.class })
})
public class TraceEquivalenceClassFilter extends AbstractExecutionTraceProcessingPlugin {

	public static final String MSG_TRACES_OUTPUT_PORT_NAME = "messageTracesOutput";
	public static final String EXECUTION_TRACES_OUTPUT_PORT_NAME = "executionTracesOutput";
	public static final String EXECUTION_TRACES_INPUT_PORT_NAME = "newExecutionTrace";
	private static final Log LOG = LogFactory.getLog(TraceEquivalenceClassFilter.class);

	public static enum TraceEquivalenceClassModes {
		DISABLED, ASSEMBLY, ALLOCATION
	}

	private final Execution rootExecution;
	private final TraceEquivalenceClassModes equivalenceMode;
	/** Representative x # of equivalents */
	private final Map<AbstractExecutionTraceHashContainer, AtomicInteger> eTracesEquivClassesMap = new HashMap<AbstractExecutionTraceHashContainer, AtomicInteger>(); // NOPMD

	// TODO Change constructor to plugin-default-constructor
	public TraceEquivalenceClassFilter(final Configuration configuration, final AbstractRepository repositories[],
			final TraceEquivalenceClassModes traceEquivalenceCallMode) {
		super(configuration, repositories);
		/* Load the root execution from the repository if possible. */
		if ((repositories.length >= 1) && (repositories[0] instanceof SystemModelRepository)) {
			this.rootExecution = ((SystemModelRepository) repositories[0]).getRootExecution();
		} else {
			this.rootExecution = null;
		}
		this.equivalenceMode = traceEquivalenceCallMode;
	}

	@InputPort(description = "Execution traces", eventTypes = { ExecutionTrace.class })
	public void newExecutionTrace(final Object data) {
		final ExecutionTrace et = (ExecutionTrace) data;
		try {
			if (this.equivalenceMode == TraceEquivalenceClassFilter.TraceEquivalenceClassModes.DISABLED) {
				super.deliver(TraceEquivalenceClassFilter.EXECUTION_TRACES_OUTPUT_PORT_NAME, et);
				super.deliver(TraceEquivalenceClassFilter.MSG_TRACES_OUTPUT_PORT_NAME, et.toMessageTrace(this.rootExecution));
			} else { // mode is ASSEMBLY or ALLOCATION
				final AbstractExecutionTraceHashContainer polledTraceHashContainer;
				if (this.equivalenceMode == TraceEquivalenceClassFilter.TraceEquivalenceClassModes.ASSEMBLY) {
					polledTraceHashContainer = new ExecutionTraceHashContainerAssemblyEquivalence(et);
				} else if (this.equivalenceMode == TraceEquivalenceClassFilter.TraceEquivalenceClassModes.ALLOCATION) {
					polledTraceHashContainer = new ExecutionTraceHashContainerAllocationEquivalence(et);
				} else { // just to make sure
					TraceEquivalenceClassFilter.LOG.error("Invalid trace equivalence mode: " + this.equivalenceMode);
					this.reportError(et.getTraceId());
					return;
				}

				AtomicInteger numOccurences = this.eTracesEquivClassesMap.get(polledTraceHashContainer);
				if (numOccurences == null) {
					numOccurences = new AtomicInteger(1);
					this.eTracesEquivClassesMap.put(polledTraceHashContainer, numOccurences);
					super.deliver(TraceEquivalenceClassFilter.EXECUTION_TRACES_OUTPUT_PORT_NAME, et);
					super.deliver(TraceEquivalenceClassFilter.MSG_TRACES_OUTPUT_PORT_NAME, et.toMessageTrace(this.rootExecution));
				} else {
					numOccurences.incrementAndGet();
				}
			}
			this.reportSuccess(et.getTraceId());
		} catch (final InvalidTraceException ex) {
			TraceEquivalenceClassFilter.LOG.error("InvalidTraceException", ex);
			this.reportError(et.getTraceId());
		}
	}

	@Override
	public boolean execute() {
		return true; // do nothing
	}

	@Override
	public void terminate(final boolean error) {
		// do nothing
	}

	public Map<ExecutionTrace, Integer> getEquivalenceClassMap() {
		final Map<ExecutionTrace, Integer> map = new HashMap<ExecutionTrace, Integer>(); // NOPMD (UseConcurrentHashMap)
		for (final Entry<AbstractExecutionTraceHashContainer, AtomicInteger> entry : this.eTracesEquivClassesMap.entrySet()) {
			map.put(entry.getKey().getExecutionTrace(), entry.getValue().intValue());
		}
		return map;
	}

	@Override
	protected Configuration getDefaultConfiguration() {
		final Configuration defaultConfiguration = new Configuration();
		return defaultConfiguration;
	}

	@Override
	public Configuration getCurrentConfiguration() {
		final Configuration configuration = new Configuration();

		// TODO: Save the current configuration

		return configuration;
	}

	@Override
	public String getExecutionTraceInputPortName() {
		return TraceEquivalenceClassFilter.EXECUTION_TRACES_INPUT_PORT_NAME;
	}

	@Override
	protected AbstractRepository[] getDefaultRepositories() {
		return new AbstractRepository[0];
	}
}
