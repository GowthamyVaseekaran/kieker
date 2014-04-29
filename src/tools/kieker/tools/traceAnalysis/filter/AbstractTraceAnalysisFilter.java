/***************************************************************************
 * Copyright 2014 Kieker Project (http://kieker-monitoring.net)
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

package kieker.tools.traceAnalysis.filter;

import java.io.PrintStream;

import kieker.analysis.IProjectContext;
import kieker.analysis.plugin.annotation.Plugin;
import kieker.analysis.plugin.annotation.Property;
import kieker.analysis.plugin.annotation.RepositoryPort;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.common.configuration.Configuration;
import kieker.common.util.signature.Signature;
import kieker.tools.traceAnalysis.systemModel.AllocationComponent;
import kieker.tools.traceAnalysis.systemModel.AssemblyComponent;
import kieker.tools.traceAnalysis.systemModel.ComponentType;
import kieker.tools.traceAnalysis.systemModel.Execution;
import kieker.tools.traceAnalysis.systemModel.ExecutionContainer;
import kieker.tools.traceAnalysis.systemModel.Operation;
import kieker.tools.traceAnalysis.systemModel.repository.SystemModelRepository;

/**
 * @author Andre van Hoorn
 * 
 * @since 1.2
 */
@Plugin(configuration = { @Property(name = AbstractTraceAnalysisFilter.CONFIG_PROPERTY_NAME_VERBOSE,
		defaultValue = AbstractTraceAnalysisFilter.CONFIG_PROPERTY_VALUE_VERBOSE) },
		repositoryPorts = { @RepositoryPort(name = AbstractTraceAnalysisFilter.REPOSITORY_PORT_NAME_SYSTEM_MODEL, repositoryType = SystemModelRepository.class) })
public abstract class AbstractTraceAnalysisFilter extends AbstractFilterPlugin {

	public static final String CONFIG_PROPERTY_NAME_VERBOSE = "verbose";
	public static final String CONFIG_PROPERTY_VALUE_VERBOSE = "false";

	/** The name of the repository port for the system model repository. */
	public static final String REPOSITORY_PORT_NAME_SYSTEM_MODEL = "systemModelRepository";

	/**
	 * Output stream for info output addressed to users, e.g., number of traces processed, files processed etc.
	 */
	private volatile PrintStream outStream = System.out;

	/**
	 * Output stream for error output addressed to users, e.g., file not found.
	 */
	private volatile PrintStream errStream = System.err;

	private volatile SystemModelRepository systemEntityFactory;

	protected final boolean verbose;

	/**
	 * Creates a new instance of this class using the given parameters.
	 * 
	 * @param configuration
	 *            The configuration for this component.
	 * @param projectContext
	 *            The project context for this component.
	 */
	public AbstractTraceAnalysisFilter(final Configuration configuration, final IProjectContext projectContext) {
		super(configuration, projectContext);

		this.verbose = configuration.getBooleanProperty(CONFIG_PROPERTY_NAME_VERBOSE);
	}

	@Override
	public Configuration getCurrentConfiguration() {
		final Configuration configuration = new Configuration();

		configuration.setProperty(CONFIG_PROPERTY_NAME_VERBOSE, Boolean.toString(this.verbose));

		return configuration;
	}

	public static final Execution createExecutionByEntityNames(final SystemModelRepository systemModelRepository,
			final String executionContainerName, final String assemblyComponentTypeName, final String componentTypeName,
			final Signature operationSignature, final long traceId, final String sessionId, final int eoi, final int ess,
			final long tin, final long tout, final boolean assumed) {
		final String allocationComponentName = new StringBuilder(executionContainerName).append("::").append(assemblyComponentTypeName).toString();
		final String operationFactoryName = new StringBuilder(componentTypeName).append(".").append(operationSignature).toString();

		AllocationComponent allocInst = systemModelRepository.getAllocationFactory()
				.lookupAllocationComponentInstanceByNamedIdentifier(allocationComponentName);
		if (allocInst == null) { // Allocation component instance doesn't exist
			AssemblyComponent assemblyComponent = systemModelRepository.getAssemblyFactory()
					.lookupAssemblyComponentInstanceByNamedIdentifier(assemblyComponentTypeName);
			if (assemblyComponent == null) { // assembly instance doesn't exist
				ComponentType componentType = systemModelRepository.getTypeRepositoryFactory().lookupComponentTypeByNamedIdentifier(assemblyComponentTypeName);
				if (componentType == null) { // NOPMD NOCS (NestedIf)
					// Component type doesn't exist
					componentType = systemModelRepository.getTypeRepositoryFactory().createAndRegisterComponentType(assemblyComponentTypeName,
							assemblyComponentTypeName);
				}
				assemblyComponent = systemModelRepository.getAssemblyFactory()
						.createAndRegisterAssemblyComponentInstance(assemblyComponentTypeName, componentType);
			}
			ExecutionContainer execContainer = systemModelRepository.getExecutionEnvironmentFactory()
					.lookupExecutionContainerByNamedIdentifier(executionContainerName);
			if (execContainer == null) { // doesn't exist, yet
				execContainer = systemModelRepository.getExecutionEnvironmentFactory()
						.createAndRegisterExecutionContainer(executionContainerName, executionContainerName);
			}
			allocInst = systemModelRepository.getAllocationFactory()
					.createAndRegisterAllocationComponentInstance(allocationComponentName, assemblyComponent, execContainer);
		}

		Operation op = systemModelRepository.getOperationFactory().lookupOperationByNamedIdentifier(operationFactoryName);
		if (op == null) { // Operation doesn't exist
			op = systemModelRepository.getOperationFactory()
					.createAndRegisterOperation(operationFactoryName, allocInst.getAssemblyComponent().getType(), operationSignature);
			allocInst.getAssemblyComponent().getType().addOperation(op);
		}

		return new Execution(op, allocInst, traceId, sessionId, eoi, ess, tin, tout, assumed);
	}

	public static final Execution createExecutionByEntityNames(final SystemModelRepository systemModelRepository,
			final String executionContainerName, final String assemblyComponentTypeName,
			final Signature operationSignature, final long traceId, final String sessionId, final int eoi, final int ess,
			final long tin, final long tout, final boolean assumed) {
		return AbstractTraceAnalysisFilter.createExecutionByEntityNames(systemModelRepository, executionContainerName, assemblyComponentTypeName,
				assemblyComponentTypeName, operationSignature, traceId, sessionId, eoi, ess, tin, tout, assumed);
	}

	protected final Execution createExecutionByEntityNames(final String executionContainerName, final String assemblyComponentTypeName,
			final String componentTypeName, final Signature operationSignature, final long traceId, final String sessionId, final int eoi, final int ess,
			final long tin, final long tout, final boolean assumed) {
		return AbstractTraceAnalysisFilter.createExecutionByEntityNames(this.getSystemEntityFactory(), executionContainerName, assemblyComponentTypeName,
				componentTypeName, operationSignature, traceId, sessionId, eoi, ess, tin, tout, assumed);
	}

	protected final Execution createExecutionByEntityNames(final String executionContainerName, final String assemblyComponentTypeName,
			final Signature operationSignature, final long traceId, final String sessionId, final int eoi, final int ess,
			final long tin, final long tout, final boolean assumed) {
		return AbstractTraceAnalysisFilter.createExecutionByEntityNames(this.getSystemEntityFactory(), executionContainerName, assemblyComponentTypeName,
				assemblyComponentTypeName, operationSignature, traceId, sessionId, eoi, ess, tin, tout, assumed);
	}

	/**
	 * Prints a message to the configured standard output stream. The output is prepended by a
	 * header which includes the name of this plugin instance.
	 * 
	 * @param lines
	 *            The lines to be printed.
	 */
	protected void printMessage(final String[] lines) {
		this.stdOutPrintln("");
		this.stdOutPrintln("#");
		this.stdOutPrintln("# Plugin: " + this.getName());
		for (final String l : lines) {
			this.stdOutPrintln(l);
		}
	}

	public final SystemModelRepository getSystemEntityFactory() {
		if (this.systemEntityFactory == null) {
			this.systemEntityFactory = (SystemModelRepository)
					this.getRepository(REPOSITORY_PORT_NAME_SYSTEM_MODEL);
		}
		if (this.systemEntityFactory == null) {
			LOG.error("Failed to connect to system model repository via repository port '"
					+ REPOSITORY_PORT_NAME_SYSTEM_MODEL + "' (not connected?)");
		}
		return this.systemEntityFactory;
	}

	/**
	 * Sets the for info output addressed to users, e.g., number of traces processed, files processed etc.
	 * If not set explicitly, this class uses {@link System#err}.
	 * 
	 * @param outStream
	 *            the outStream to set
	 */
	public void setOutStream(final PrintStream outStream) {
		synchronized (this) {
			this.outStream = outStream;
		}
	}

	/**
	 * Sets the for info output addressed to users, e.g., number of traces processed, files processed etc.
	 * If not set explicitly, this class uses {@link System#err}.
	 * 
	 * @param errStream
	 *            the errStream to set
	 */
	public void setErrStream(final PrintStream errStream) {
		synchronized (this) {
			this.errStream = errStream;
		}
	}

	/**
	 * Writes a line to the configured standard output stream for this plugin.
	 * 
	 * @param message
	 *            The message to be printed.
	 */
	protected void stdOutPrintln(final String message) {
		synchronized (this) {
			if (this.outStream != null) {
				this.outStream.println(message);
			}
		}
	}

	/**
	 * Writes a line to the configured error output stream for this plugin.
	 * 
	 * @param message
	 *            The message to be printed.
	 */
	protected void errOutPrintln(final String message) {
		synchronized (this) {
			if (this.errStream != null) {
				this.errStream.println(message);
			}
		}
	}
}
