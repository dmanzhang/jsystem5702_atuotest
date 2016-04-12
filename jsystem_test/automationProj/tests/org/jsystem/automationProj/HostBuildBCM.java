package org.jsystem.automationProj;

import jsystem.extensions.analyzers.text.FindText;
import org.junit.Before;
import org.junit.Test;
import org.jsystem.automationProj.MyStation;
import junit.framework.SystemTestCase4;

public class HostBuildBCM extends SystemTestCase4 {
	private String pingDestination = "10.38.24.1";
	private int    packetSize = 1024;
	private MyStation myStation;
	
	@Before
	public void before() throws Exception{
	    myStation =  
	              (MyStation)system.getSystemObject("my_station");  

	}
	
	@Test
	public void pingFromDUT() throws Exception {
		report.report("Calling myStation ping operation");
        myStation.ping(getPingDestination(),getPacketSize());
        myStation.analyze(new FindText("0% packet loss"));
	}

	@Test
	public void enterBuildView() throws Exception {
		report.report("enter view " + myStation.getCcView());
		myStation.rawCommand("su", "Password:");
		myStation.rawCommand("hlhzjj82", "]#");
		myStation.rawCommand("cd " + myStation.getCcView(), "]#");
	}
	
	@Test
	public void buildCommon() throws Exception {
		report.report("make");
		myStation.rawCommandNoTimeOut("make", "]#");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanCommon() throws Exception {
		report.report("make clean");
		myStation.rawCommandNoTimeOut("make clean", "]#");
	}
	
	@Test
	public void FileSyncDown() throws Exception {
		report.report("FileSyncDown");
		myStation.rawCommand("cp -f " + "/vobs/webos/src/bert" + myStation.getCodeDir() + myStation.getFile1Name() + " " +
				myStation.getTftpRoot() + myStation.getFile1Name(), "bert ]");
	}
	
	@Test
	public void FileSyncUp() throws Exception {
		report.report("FileSyncUp");
		myStation.rawCommand("cp -f " + myStation.getTftpRoot() + myStation.getFile1Name() + " " +
				"/vobs/webos/src/bert" + myStation.getCodeDir() + myStation.getFile1Name(), "bert ]");
	}
	
	@Test
	public void FileSyncDown1() throws Exception {
		report.report("FileSyncDown1");
		myStation.rawCommand("cp -f " + "/vobs/webos/src/bert" + myStation.getCodeDir() + myStation.getFile2Name() + " " +
				myStation.getTftpRoot() + myStation.getFile2Name(), "bert ]");
	}
	
	@Test
	public void FileSyncUp1() throws Exception {
		report.report("FileSyncUp1");
		myStation.rawCommand("cp -f " + myStation.getTftpRoot() + myStation.getFile2Name() + " " +
				"/vobs/webos/src/bert" + myStation.getCodeDir() + myStation.getFile2Name(), "bert ]");
	}
	
	@Test
	public void enterP1022BuildView() throws Exception {
		report.report("enter view " + myStation.getCcView());
		myStation.rawCommand("cd " + myStation.getCcView(), "]$");
		myStation.rawCommand("source build_p1022ds_release/bitbake.rc", "]$");
	}
	
	@Test
	public void buildP1022Common() throws Exception {
		report.report("make");
		myStation.rawCommandNoTimeOut("bitbake virtual/kernel", "]$");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanP1022Common() throws Exception {
		report.report("make clean");
		myStation.rawCommandNoTimeOut("bitbake -c configure  -f virtual/kernel", "]$");
	}
	
	@Test
	public void enterMDMBuildView() throws Exception {
		report.report("enter view " + myStation.getCcView());
		myStation.rawCommand("su", "Password:");
		myStation.rawCommand("hlhzjj82", "]#");
		myStation.rawCommand("cd " + myStation.getCcView(), "]#");
	}
	
	@Test
	public void buildMDMCommon() throws Exception {
		report.report("make");
		myStation.rawCommandNoTimeOut("make", "]#");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanMDMCommon() throws Exception {
		report.report("make clean");
		myStation.rawCommandNoTimeOut("make clean", "]#");
	}
	
	public String getPingDestination() {
	    return pingDestination;
	}

	public void setPingDestination(String pingDestination) {
	    this.pingDestination = pingDestination;
	}

	public int getPacketSize() {
	    return packetSize;
	}

	public void setPacketSize(int packetSize) {
	    this.packetSize = packetSize;
	}	
}
