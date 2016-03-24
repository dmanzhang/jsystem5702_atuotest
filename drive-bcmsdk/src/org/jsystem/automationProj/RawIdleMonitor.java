package org.jsystem.automationProj;

import junit.framework.SystemTestCase;

public class RawIdleMonitor extends Thread {

	RawCliConnection cli;
	long timeout;
	boolean stop = false;
	
	/**	 
	 * @param cli CliConnection 
	 * @param timeout (miliSeconds) the maximum idleTime
	 */
	public RawIdleMonitor(RawCliConnection cli, long timeout){
		this.cli = cli;
		this.timeout = timeout;
	}
	
	public void run(){
		System.out.println("Idle monitor was started");
		while(!stop){
			long lastCommandTime = cli.getLastCommandTime();
			if(lastCommandTime == 0){
				try {
					Thread.sleep(timeout/2);
				} catch (InterruptedException e) {
					return;
				}
				continue;
			}
			if(System.currentTimeMillis() - lastCommandTime > (timeout * 0.9)){
				RawCliCommand cmd = new RawCliCommand();
				cmd.setCommands(new String[]{""});
				cli.command(cmd);
				if(cmd.isFailed()){
					SystemTestCase.report.report(cli.getName() + " idle monitor failed");
				} else {
					SystemTestCase.report.report(cli.getName() + " idle monitor keepalive success");
				}
			} else {
				try {
					long toSleep = (long)(timeout * 0.9) - (System.currentTimeMillis() - lastCommandTime);
					if(toSleep > 0){
						Thread.sleep(toSleep);
					}
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}
	public void setStop(){
		stop = true;
	}
}

