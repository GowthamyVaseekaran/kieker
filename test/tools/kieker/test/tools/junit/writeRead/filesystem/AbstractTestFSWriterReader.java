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

package kieker.test.tools.junit.writeRead.filesystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import junit.framework.Assert;
import kieker.analysis.AnalysisController;
import kieker.analysis.exception.AnalysisConfigurationException;
import kieker.analysis.plugin.reader.AbstractReaderPlugin;
import kieker.analysis.plugin.reader.filesystem.FSReader;
import kieker.common.configuration.Configuration;
import kieker.common.record.IMonitoringRecord;
import kieker.monitoring.core.configuration.ConfigurationFactory;
import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;
import kieker.monitoring.writer.IMonitoringWriter;
import kieker.monitoring.writer.filesystem.AbstractAsyncFSWriter;
import kieker.monitoring.writer.filesystem.AsyncFsWriter;
import kieker.test.analysis.junit.plugin.SimpleSinkPlugin;
import kieker.test.tools.junit.writeRead.AbstractWriterReaderTest;
import kieker.test.tools.junit.writeRead.printStream.BasicPrintStreamWriterTestFile;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 * @author Andre van Hoorn
 */
public abstract class AbstractTestFSWriterReader extends AbstractWriterReaderTest {
	// TODO: constants are private in AbstractAsyncWriter ... why?
	private static final String CONFIG_ASYNC_WRITER_QUEUESIZE = "QueueSize";
	private static final String CONFIG_ASYNC_WRITER_BEHAVIOR = "QueueFullBehavior";
	private static final String CONFIG_ASYNC_WRITER_SHUTDOWNDELAY = "MaxShutdownDelay";

	@Rule
	public final TemporaryFolder tmpFolder = new TemporaryFolder();

	private volatile Class<? extends IMonitoringWriter> testedWriterClazz = AsyncFsWriter.class;

	protected abstract Class<? extends IMonitoringWriter> getTestedWriterClazz();

	private static final String ENCODING = "UTF-8";

	@Before
	public void setUp() throws IOException {
		this.testedWriterClazz = this.getTestedWriterClazz();
		this.tmpFolder.create();
	}

	@After
	public void tearDown() throws Exception {
		this.tmpFolder.delete();
	}

	@Override
	protected IMonitoringController createController(final int numRecordsWritten) {
		final Configuration config = ConfigurationFactory.createDefaultConfiguration();

		config.setProperty(ConfigurationFactory.WRITER_CLASSNAME, this.testedWriterClazz.getName());
		config.setProperty(this.testedWriterClazz.getName() + "." + AbstractAsyncFSWriter.CONFIG_TEMP, Boolean.FALSE.toString());
		try {
			config.setProperty(this.testedWriterClazz.getName() + "." + AbstractAsyncFSWriter.CONFIG_PATH, this.tmpFolder.getRoot().getCanonicalPath());
		} catch (final IOException e) {
			Assert.fail(e.getMessage());
		}
		config.setProperty(this.testedWriterClazz.getName() + "." + AbstractTestFSWriterReader.CONFIG_ASYNC_WRITER_QUEUESIZE,
				Integer.toString(numRecordsWritten * 2));
		config.setProperty(this.testedWriterClazz.getName() + "." + AbstractTestFSWriterReader.CONFIG_ASYNC_WRITER_BEHAVIOR, "0");
		config.setProperty(this.testedWriterClazz.getName() + "." + AbstractTestFSWriterReader.CONFIG_ASYNC_WRITER_SHUTDOWNDELAY, "-1");

		// Give extending classes the chance to refine the configuration
		this.refineConfiguration(config, numRecordsWritten);

		return MonitoringController.createInstance(config);
	}

	protected abstract void refineConfiguration(Configuration config, final int numRecordsWritten);

	@Override
	protected void checkControllerStateAfterRecordsPassedToController(final IMonitoringController monitoringController) {
		Assert.assertTrue("Expected monitoring controller to be enabled", monitoringController.isMonitoringEnabled());
	}

	protected abstract void doSomethingBeforeReading(final String[] monitoringLogs) throws IOException;

	@Override
	protected final void doBeforeReading() throws IOException {
		final String[] monitoringLogs = this.tmpFolder.getRoot().list(new KiekerLogDirFilter());
		for (int i = 0; i < monitoringLogs.length; i++) { // transform relative to absolute path
			monitoringLogs[i] = this.tmpFolder.getRoot().getAbsoluteFile() + File.separator + monitoringLogs[i]; // NOPMD (UseStringBufferForStringAppends)
		}
		this.doSomethingBeforeReading(monitoringLogs);
	}

	@Override
	protected List<IMonitoringRecord> readEvents() throws AnalysisConfigurationException {
		final String[] monitoringLogs = this.tmpFolder.getRoot().list(new KiekerLogDirFilter());
		for (int i = 0; i < monitoringLogs.length; i++) { // transform relative to absolute path
			monitoringLogs[i] = this.tmpFolder.getRoot().getAbsoluteFile() + File.separator + monitoringLogs[i]; // NOPMD (UseStringBufferForStringAppends)
		}

		return this.readLog(monitoringLogs);
	}

	/**
	 * Replaces the given search String by the given replacement String in all given files.
	 * 
	 * @param monitoringLogDirs
	 * @param findString
	 * @param replaceByString
	 * @throws IOException
	 */
	protected void replaceStringInMapFiles(final String[] monitoringLogDirs, final String findString, final String replaceByString) throws IOException {
		for (final String curLogDir : monitoringLogDirs) {
			final String[] mapFilesInDir = new File(curLogDir).list(new KiekerMapFileFilter());
			Assert.assertEquals("Unexpected number of map files", 1, mapFilesInDir.length);

			final String curMapFile = curLogDir + File.separator + mapFilesInDir[0];

			this.searchReplaceInFile(curMapFile, findString, replaceByString);
		}

	}

	private void searchReplaceInFile(final String filename, final String findString, final String replaceByString) throws IOException {
		final String mapFileContent = BasicPrintStreamWriterTestFile.readOutputFileAsString(new File(filename));
		final String manipulatedContent = mapFileContent.replaceAll(findString, replaceByString);
		PrintStream printStream = null;
		try {
			printStream = new PrintStream(new FileOutputStream(filename), false, ENCODING);
			printStream.print(manipulatedContent);
		} finally {
			if (printStream != null) {
				printStream.close();
			}
		}
	}

	@Override
	protected void inspectRecords(final List<IMonitoringRecord> eventsPassedToController, final List<IMonitoringRecord> eventFromMonitoringLog) {
		Assert.assertEquals("Unexpected set of records", eventsPassedToController, eventFromMonitoringLog);
	}

	private List<IMonitoringRecord> readLog(final String[] monitoringLogDirs) throws AnalysisConfigurationException {
		final AnalysisController analysisController = new AnalysisController();
		final Configuration readerConfiguration = new Configuration();
		readerConfiguration.setProperty(FSReader.CONFIG_PROPERTY_NAME_INPUTDIRS, Configuration.toProperty(monitoringLogDirs));
		readerConfiguration.setProperty(FSReader.CONFIG_PROPERTY_NAME_IGNORE_UNKNOWN_RECORD_TYPES,
				FSReader.CONFIG_PROPERTY_VALUE_IGNORE_UNKNOWN_RECORD_TYPES_DEFAULT);
		final AbstractReaderPlugin reader = new FSReader(readerConfiguration);
		final SimpleSinkPlugin<IMonitoringRecord> sinkPlugin = new SimpleSinkPlugin<IMonitoringRecord>(new Configuration());

		analysisController.registerReader(reader);
		analysisController.registerFilter(sinkPlugin);
		analysisController.connect(reader, FSReader.OUTPUT_PORT_NAME_RECORDS, sinkPlugin, SimpleSinkPlugin.INPUT_PORT_NAME);
		analysisController.run();

		return sinkPlugin.getList();
	}
}

/**
 * Accepts Kieker file system monitoring logs.
 * 
 * @author Andre van Hoorn
 * 
 */
class KiekerLogDirFilter implements FilenameFilter { // NOPMD (TestClassWithoutTestCases)
	public static final String LOG_DIR_PREFIX = "kieker-"; // TODO: do we have this constant in the FS Writer(s)?
	public static final String MAP_FILENAME = "kieker.map"; // TODO: do we have this constant in the FS Writer(s)?

	public boolean accept(final File dir, final String name) {
		if (!name.startsWith(KiekerLogDirFilter.LOG_DIR_PREFIX)) {
			return false;
		}

		final String potentialDirFn = dir.getAbsolutePath() + File.separatorChar + name;

		final File potentialDir = new File(potentialDirFn);

		if (!potentialDir.isDirectory()) {
			return false;
		}

		final String[] kiekerMapFiles = potentialDir.list(new FilenameFilter() {
			/**
			 * Accepts directories containing a `kieker.map` file.
			 */
			public boolean accept(final File dir, final String name) {
				return name.equals(KiekerLogDirFilter.MAP_FILENAME);
			}
		});
		return kiekerMapFiles.length == 1;
	}
}

/**
 * Accepts kieker.map files.
 * 
 * @author Andre van Hoorn
 * 
 */
class KiekerMapFileFilter implements FilenameFilter { // NOPMD (TestClassWithoutTestCases)
	public static final String MAP_FILENAME = "kieker.map"; // TODO: do we have this constant in the FS Writer(s)?

	/**
	 * Accepts the {@value #MAP_FILENAME} file in a monitoring log directory.
	 */
	public boolean accept(final File dir, final String name) {
		final boolean match = MAP_FILENAME.equals(name);
		return match;
	}
}
