package org.renci.gate.service.exogeni;

import java.io.File;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.renci.jlrm.JLRMException;
import org.renci.jlrm.Queue;
import org.renci.jlrm.Site;
import org.renci.jlrm.pbs.ssh.PBSSSHJob;
import org.renci.jlrm.pbs.ssh.PBSSSHSubmitCondorGlideinCallable;

@Command(scope = "renci-exogeni", name = "create-glidein", description = "Create Glidein")
@Service
public class CreateGlideinAction implements Action {

    @Option(name = "--username", required = true, multiValued = false)
    private String username;

    @Option(name = "--submitHost", required = true, multiValued = false)
    private String submitHost;

    @Option(name = "--queueName", required = true, multiValued = false)
    private String queueName;

    @Option(name = "--collectorHost", required = true, multiValued = false)
    private String collectorHost;

    public CreateGlideinAction() {
        super();
    }

    @Override
    public Object execute() {
        Site site = new Site();
        site.setName("ExoGENI");
        site.setSubmitHost(submitHost);
        site.setUsername(username);

        Queue queue = new Queue();
        queue.setName(queueName);
        queue.setRunTime(5760L);
        queue.setNumberOfProcessors(4);

        File submitDir = new File("/tmp");
        try {
            PBSSSHSubmitCondorGlideinCallable callable = new PBSSSHSubmitCondorGlideinCallable();
            callable.setSite(site);
            callable.setQueue(queue);
            callable.setSubmitDir(submitDir);
            callable.setCollectorHost(collectorHost);
            callable.setHostAllowRead("*.unc.edu");
            callable.setHostAllowWrite("*.unc.edu");
            callable.setRequiredMemory(40);
            callable.setUsername(System.getProperty("user.name"));
            callable.setJobName(String.format("glidein-%s", site.getName().toLowerCase()));

            PBSSSHJob job = callable.call();
            System.out.println(job.getId());
        } catch (JLRMException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubmitHost() {
        return submitHost;
    }

    public void setSubmitHost(String submitHost) {
        this.submitHost = submitHost;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getCollectorHost() {
        return collectorHost;
    }

    public void setCollectorHost(String collectorHost) {
        this.collectorHost = collectorHost;
    }

}