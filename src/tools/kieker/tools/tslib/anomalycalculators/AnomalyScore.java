/***************************************************************************
 * Copyright 2012 Kieker Project (http://kieker-monitoring.net)
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

package kieker.tools.tslib.anomalycalculators;

/**
 * @since 1.10
 * @author Tillmann Carlos Bielefeld
 * 
 */
public class AnomalyScore {

	private final double score;

	/**
	 * 
	 * @param score
	 *            score
	 */
	public AnomalyScore(final double score) {
		this.score = score;
	}

	public double getScore() {
		return this.score;
	}

}
