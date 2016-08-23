/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wipro.ats.bdre.md.api.oozie;

import com.wipro.ats.bdre.exception.MetadataException;
import com.wipro.ats.bdre.md.api.TermStep;
import com.wipro.ats.bdre.md.beans.TermStepInfo;
import com.wipro.ats.bdre.util.OozieUtil;
import org.apache.log4j.Logger;

/**
 * Created by leela on 12-12-2014.
 */
public class OozieTermStep {
    private static final Logger LOGGER = Logger.getLogger(OozieTermStep.class);

    /**
     * default constructor
     */
    private OozieTermStep() {

    }

    /**
     * This method calls execute method and persist the output till runtime.
     *
     * @param args String array having environment and process-id with their command line notations.
     */
    public static void main(String[] args) {
        TermStep fs = new TermStep();
        TermStepInfo termStepInfo = fs.execute(args);
        OozieUtil oozieUtil = new OozieUtil();
        try {
            oozieUtil.persistBeanData(termStepInfo, false);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new MetadataException(e);
        }
    }
}
