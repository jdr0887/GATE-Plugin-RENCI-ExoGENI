package org.renci.gate.plugin.exogeni;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.renci.gate.AbstractGATEService;
import org.renci.gate.GATEException;
import org.renci.gate.GlideinMetric;
import org.renci.jlrm.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author jdr0887
 */
public class ExoGENIGATEService extends AbstractGATEService {

    private final Logger logger = LoggerFactory.getLogger(ExoGENIGATEService.class);

    private String username;

    public ExoGENIGATEService() {
        super();
    }

    @Override
    public Boolean isValid() throws GATEException {
        logger.info("ENTERING isValid()");
        return false;
    }

    @Override
    public Map<String, GlideinMetric> lookupMetrics() throws GATEException {
        logger.info("ENTERING lookupMetrics()");
        Map<String, GlideinMetric> metricsMap = new HashMap<String, GlideinMetric>();

        // stub out the metricsMap
        Map<String, Queue> queueInfoMap = getSite().getQueueInfoMap();
        for (String key : queueInfoMap.keySet()) {
            Queue queue = queueInfoMap.get(key);
            metricsMap.put(queue.getName(), new GlideinMetric(0, 0, queue.getName()));
        }

        return metricsMap;
    }

    @Override
    public void createGlidein(Queue queue) throws GATEException {
        logger.info("ENTERING createGlidein(Queue)");

        if (StringUtils.isNotEmpty(getActiveQueues()) && !getActiveQueues().contains(queue.getName())) {
            logger.warn("queue name is not in active queue list...see etc/org.renci.gate.plugin.exogeni.cfg");
            return;
        }

        File submitDir = new File("/tmp", System.getProperty("user.name"));
        submitDir.mkdirs();

    }

    @Override
    public void deleteGlidein(Queue queue) throws GATEException {
        logger.info("ENTERING deleteGlidein(Queue)");
    }

    @Override
    public void deletePendingGlideins() throws GATEException {
        logger.info("ENTERING deletePendingGlideins()");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
