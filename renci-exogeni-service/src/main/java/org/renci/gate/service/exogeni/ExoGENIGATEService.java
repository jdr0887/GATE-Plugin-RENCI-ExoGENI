package org.renci.gate.service.exogeni;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final Logger logger = LoggerFactory.getLogger(ExoGENIGATEService.class);

    private String username;

    public ExoGENIGATEService() {
        super();
    }

    @Override
    public Boolean isValid() throws GATEException {
        logger.debug("ENTERING isValid()");
        return false;
    }

    @Override
    public List<GlideinMetric> lookupMetrics() throws GATEException {
        logger.debug("ENTERING lookupMetrics()");
        Map<String, GlideinMetric> metricsMap = new HashMap<String, GlideinMetric>();

        List<Queue> queueList = getSite().getQueueList();
        for (Queue queue : queueList) {
            metricsMap.put(queue.getName(), new GlideinMetric(getSite().getName(), queue.getName(), 0, 0));
        }

        List<GlideinMetric> metricList = new ArrayList<GlideinMetric>();
        metricList.addAll(metricsMap.values());

        return metricList;
    }

    @Override
    public void createGlidein(Queue queue) throws GATEException {
        logger.debug("ENTERING createGlidein(Queue)");

        File submitDir = new File("/tmp", System.getProperty("user.name"));
        submitDir.mkdirs();

    }

    @Override
    public void deleteGlidein(Queue queue) throws GATEException {
        logger.debug("ENTERING deleteGlidein(Queue)");
    }

    @Override
    public void deletePendingGlideins() throws GATEException {
        logger.debug("ENTERING deletePendingGlideins()");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
