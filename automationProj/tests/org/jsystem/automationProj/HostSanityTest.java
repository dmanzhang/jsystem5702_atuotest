package org.jsystem.automationProj;

import jsystem.extensions.analyzers.text.FindText;
import org.junit.Before;
import org.junit.Test;
import org.jsystem.automationProj.MyStation;
import junit.framework.SystemTestCase4;

public class HostSanityTest extends SystemTestCase4 {
	private String pingDestination = "10.38.24.1";
	private int    packetSize = 1024;
	private MyStation myStation;
	
	@Before
	public void before() throws Exception{
	    myStation =  
	              (MyStation)system.getSystemObject("my_station");  

	}

	public HostSanityTest(){
		setFixture(InstallPatch.class);
	}
	
	@Test
	public void pingFromDUT() throws Exception {
		report.report("Calling myStation ping operation");
        myStation.ping(getPingDestination(),getPacketSize());
        myStation.analyze(new FindText("0% packet loss"));
	}

	@Test
	public void enterView() throws Exception {
		report.report("enter view " + myStation.getCcView());
		myStation.rawCommand("bash", ":~ ]");
		myStation.rawCommand("ct setview "  + myStation.getCcView(), ":~ ]");
		myStation.rawCommand("bert", "bert ]");
	}
	
	@Test
	public void buildGryphonFCstkc() throws Exception {
		report.report("GryphonFCstkc");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_GRYPHON_FC_STKC mcp", "bert ]");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanGryphonFCstkc() throws Exception {
		report.report("GryphonFCstkc");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_GRYPHON_FC_STKC mcp clean", "bert ]");
	}
	
	@Test
	public void CopyGryphonFCstkcImage() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_GRYPHON_FC_STKC-1/usr/bin/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void CopyGryphonFCstkcBoot() throws Exception {
		report.report("CopyBoot");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_GRYPHON_FC_STKC-1/usr/bin/BladeBoot " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void buildGryphonStkc() throws Exception {
		report.report("Gryphonstkc");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_GRYPHON_STKC mcp", "bert ]");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanGryphonStkc() throws Exception {
		report.report("Gryphonstkc");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_GRYPHON_STKC mcp clean", "bert ]");
	}
	
	@Test
	public void CopyGryphonStkcImage() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_GRYPHON_STKC-1/usr/bin/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void CopyGryphonStkcBoot() throws Exception {
		report.report("CopyBoot");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_GRYPHON_STKC-1/usr/bin/BladeBoot " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void cleanEagleStkc() throws Exception {
		report.report("Eaglestkc");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_EAGLE_STKC mcp clean", "bert ]");
	}
	
	@Test
	public void buildEagleStkc() throws Exception {
		report.report("Eaglestkc");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_EAGLE_STKC mcp", "bert ]");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void CopyEagleStkcImage() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_EAGLE_STKC-1/usr/bin/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void CopyEagleStkcBoot() throws Exception {
		report.report("CopyBoot");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_EAGLE_STKC-1/usr/bin/BladeBoot " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void buildBundleStkc() throws Exception {
		report.report("Bundle");
		myStation.rawCommandNoTimeOut("buildbert -bundle_only -hw HW_GRYPHON_STKC -hw HW_EAGLE_STKC", "bert ]");
		myStation.analyze(new FindText("Bundle build - Final Bundling"));
	}
	
	@Test
	public void CopyBundleImage() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/bundle/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void CopyBundleBoot() throws Exception {
		report.report("CopyBoot");
		myStation.rawCommand("cp /vobs/webos_export/bundle/BladeBoot " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void buildCompass() throws Exception {
		report.report("buildCompass");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_COMPASS mcp", "bert ]");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanCompass() throws Exception {
		report.report("cleanCompass");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_COMPASS mcp clean", "bert ]");
	}
	
	@Test
	public void CopyCompassImage() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_COMPASS-1/usr/bin/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void CopyCompassBoot() throws Exception {
		report.report("CopyBoot");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_COMPASS-1/usr/bin/BladeBoot " + 
				myStation.getTftpRoot(), "bert ]");
	}

	@Test
	public void buildCompass_rs() throws Exception {
		report.report("buildCompass_rs");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_COMPASS_RS mcp", "bert ]");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanCompass_rs() throws Exception {
		report.report("cleanCompass_rs");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_COMPASS_RS mcp clean", "bert ]");
	}
	
	@Test
	public void CopyCompassrsImage() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_COMPASS_RS-1/usr/bin/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void CopyCompassrsBoot() throws Exception {
		report.report("CopyBoot");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_COMPASS_RS-1/usr/bin/BladeBoot " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void buildPiglet68() throws Exception {
		report.report("buildPiglet68");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_PIGLET_68  mcp", "bert ]");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanPiglet68() throws Exception {
		report.report("cleanPiglet68");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_PIGLET_68  mcp clean", "bert ]");
	}

	@Test
	public void CopyPigletImage68() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_PIGLET_68-1/usr/bin/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void CopyPigletBoot68() throws Exception {
		report.report("CopyBoot");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_PIGLET_68-1/usr/bin/BladeBoot " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void buildPiglet() throws Exception {
		report.report("buildPiglet");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_PIGLET  mcp", "bert ]");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanPiglet() throws Exception {
		report.report("cleanPiglet");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_PIGLET  mcp clean", "bert ]");
	}

	@Test
	public void CopyPigletImage() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_PIGLET-1/usr/bin/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void CopyPigletBoot() throws Exception {
		report.report("CopyBoot");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_PIGLET-1/usr/bin/BladeBoot " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void buildPigletRs() throws Exception {
		report.report("buildPigletrs");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_PIGLET_RS  mcp", "bert ]");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanPigletRs() throws Exception {
		report.report("cleanPiglet");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_PIGLET_RS  mcp clean", "bert ]");
	}

	@Test
	public void CopyPigletImageRs() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_PIGLET_RS-1/usr/bin/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void CopyPigletBootRs() throws Exception {
		report.report("CopyBoot");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_PIGLET_RS-1/usr/bin/BladeBoot " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void buildGryphon() throws Exception {
		report.report("buildGryphon");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_GRYPHON_RS  mcp", "bert ]");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanGryphon() throws Exception {
		report.report("cleanGryphon");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_GRYPHON_RS  mcp clean", "bert ]");
	}

	@Test
	public void CopyGryphonImage() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_GRYPHON_RS-1/usr/bin/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void CopyGryphonBoot() throws Exception {
		report.report("CopyBoot");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_GRYPHON_RS-1/usr/bin/BladeBoot " + 
				myStation.getTftpRoot(), "bert ]");
	}

	@Test
	public void buildJanice() throws Exception {
		report.report("buildJanice");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_JANICE_RS  mcp", "bert ]");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanJanice() throws Exception {
		report.report("cleanJanice");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_JANICE_RS  mcp clean", "bert ]");
	}

	@Test
	public void CopyJaniceImage() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_JANICE_RS-1/usr/bin/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void CopyJaniceBoot() throws Exception {
		report.report("CopyBoot");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_JANICE_RS-1/usr/bin/BladeBoot " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void buildScooter() throws Exception {
		report.report("buildScooter");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_SCOOTER_68  mcp", "bert ]");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanScooter() throws Exception {
		report.report("cleanScooter");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_SCOOTER_68  mcp clean", "bert ]");
	}

	@Test
	public void CopyScooterImage() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_SCOOTER_68-1/usr/bin/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void CopyScooterBoot() throws Exception {
		report.report("CopyBoot");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_SCOOTER_68-1/usr/bin/BladeBoot " + 
				myStation.getTftpRoot(), "bert ]");
	}

	@Test
	public void buildPollux() throws Exception {
		report.report("buildPollux");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_POLLUX  mcp", "bert ]");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanPollux() throws Exception {
		report.report("cleanPollux");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_POLLUX  mcp clean", "bert ]");
	}

	@Test
	public void CopyPolluxImage() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_POLLUX-1/usr/bin/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void CopyPolluxBoot() throws Exception {
		report.report("CopyBoot");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_POLLUX-1/usr/bin/BladeBoot " + 
				myStation.getTftpRoot(), "bert ]");
	}
	
	@Test
	public void buildKraken() throws Exception {
		report.report("buildKraken");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_KRAKEN  mcp", "bert ]");
		myStation.analyze(new FindText(myStation.getStripOk()));
	}
	
	@Test
	public void cleanKraken() throws Exception {
		report.report("cleanKraken");
		myStation.rawCommandNoTimeOut("buildbert -hw HW_KRAKEN  mcp clean", "bert ]");
	}

	@Test
	public void CopyKrakenImage() throws Exception {
		report.report("CopyImage");
		myStation.rawCommand("cp /vobs/webos_export/ALT-HW_KRAKEN-1/usr/bin/BladeOS " + 
				myStation.getTftpRoot(), "bert ]");
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
