package org.jsystem.quickstart;

import jsystem.extensions.analyzers.text.FindText;
import org.junit.Before;
import org.junit.Test;
import org.jsystem.quickstart.MySwitchOpen;
import junit.framework.SystemTestCase4;

public class twopairswitch extends SystemTestCase4 {
	private String switchPort = "20";
	private String routedPort = "1";
	private String switchPortMode = "access";
	private String switchPortVlan = "20";
	private String ipInterface = "2";
	private String ipAddress = "2.22.23.31";
	private String iPMask = "255.255.255.0";
	private String switchPortSUT1 = "20";
	private String routedPortSUT1 = "1";
	private String switchPortModeSUT1 = "access";
	private String switchPortVlanSUT1 = "20";
	private String ipInterfaceSUT1 = "2";
	private String ipAddressSUT1 = "2.22.23.33";
	private String iPMaskSUT1 = "255.255.255.0";
	private MySwitchOpen mySwitchOpen;
	private MySwitchOpen switchSut1;
	/*private MySwitchOpen switchSut2; */
	
	@Before
	public void before() throws Exception{
		mySwitchOpen =  
	              (MySwitchOpen)system.getSystemObject("my_switch1");
		switchSut1 =  
	              (MySwitchOpen)system.getSystemObject("my_sut1");  

	}
	
	@Test
	public void OpenDUT() throws Exception {
		report.report("Calling MySwitchOpen operation");
		mySwitchOpen.enterIscli();
		mySwitchOpen.analyze(new FindText(">"));
		mySwitchOpen.enable();
		mySwitchOpen.analyze(new FindText("#"));
		mySwitchOpen.configureTerminal();
		mySwitchOpen.analyze(new FindText("(config)#"));		
		
		switchSut1.enterIscli();
		switchSut1.analyze(new FindText(">"));
		switchSut1.enable();
		switchSut1.analyze(new FindText("#"));
		switchSut1.configureTerminal();
		switchSut1.analyze(new FindText("(config)#"));
	}

	@Test
	public void PingingOnRoutedPort() throws Exception {
		String tempNetWork = "15.15.15.";
		String tempMask = "255.255.255.0";
		
		report.report("PingingOnRoutedPort");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		
		if (switchSut1.getLinkUpPort() != "")
		{
			setRoutedPort(switchSut1.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		
		switchSut1.enterInterfacePort(getRoutedPortSUT1());
		switchSut1.rawCommand("no switchport", "(config-if)#");
		switchSut1.showVlan();
		switchSut1.analyze(new FindText("INTERNAL"));
		
		switchSut1.rawCommand("ip address " + tempNetWork + "16" + " " + tempMask + " enable", "(config-if)#");
		switchSut1.rawCommand("ip ospf area 0.0.0.0", "(config-if)#");
		switchSut1.rawCommand("ip ospf enable", "(config-if)#");
		switchSut1.exitInterface();
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("ip address " + tempNetWork + "15" + " " + tempMask + " enable", "(config-if)#");
		mySwitchOpen.exitInterface();
		mySwitchOpen.rawCommand("ping " + tempNetWork + "15" + " data-port", "(config)#");
		mySwitchOpen.analyze(new FindText("ok"));
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
		switchSut1.enterInterfacePort(getRoutedPortSUT1());
		switchSut1.rawCommand("switchport", "(config-if)#");
		switchSut1.exitInterface();
	}
	
	@Test
	public void LearnDynamicARPOnRoutedPort() throws Exception {
		String tempNetWork = "15.15.15.";
		String tempMask = "255.255.255.0";
		
		report.report("PingingOnRoutedPort");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		
		if (switchSut1.getLinkUpPort() != "")
		{
			setRoutedPort(switchSut1.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		
		switchSut1.enterInterfacePort(getRoutedPortSUT1());
		switchSut1.rawCommand("no switchport", "(config-if)#");
		switchSut1.showVlan();
		switchSut1.analyze(new FindText("INTERNAL"));
		
		switchSut1.rawCommand("ip address " + tempNetWork + "16" + " " + tempMask + " enable", "(config-if)#");
		switchSut1.rawCommand("ip ospf area 0.0.0.0", "(config-if)#");
		switchSut1.rawCommand("ip ospf enable", "(config-if)#");
		switchSut1.exitInterface();
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("ip address " + tempNetWork + "15" + " " + tempMask + " enable", "(config-if)#");
		mySwitchOpen.exitInterface();
		mySwitchOpen.rawCommand("ping " + tempNetWork + "15" + " data-port", "(config)#");
		mySwitchOpen.analyze(new FindText("ok"));
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
		switchSut1.enterInterfacePort(getRoutedPortSUT1());
		switchSut1.rawCommand("switchport", "(config-if)#");
		switchSut1.exitInterface();
	}
	
	@Test
	public void ConfigureOSPFOnRoutedPort() throws Exception {
		String tempNetWork = "15.15.15.";
		String tempMask = "255.255.255.0";
		
		report.report("ConfigureOSPFOnRoutedPort");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
			report.report(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		
		if (switchSut1.getLinkUpPort() != "")
		{
			setRoutedPortSUT1(switchSut1.getLinkUpPort());
			report.report(switchSut1.getLinkUpPort());
		}
		else
		{
		    setRoutedPortSUT1("41");
		}
		
		mySwitchOpen.rawCommand("router ospf", "(config-router-ospf)#");
		mySwitchOpen.rawCommand("enable", "(config-router-ospf)#");
		mySwitchOpen.rawCommand("area 0 area-id 0.0.0.0", "(config-router-ospf)#");
		mySwitchOpen.rawCommand("area 0 enable", "(config-router-ospf)#");
		mySwitchOpen.exitInterface();
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("ip address " + tempNetWork + "15" + " " + tempMask + " enable", "(config-if)#");
		mySwitchOpen.rawCommand("ip ospf area 0.0.0.0", "(config-if)#");
		mySwitchOpen.rawCommand("ip ospf enable", "(config-if)#");
		mySwitchOpen.exitInterface();		
		
		switchSut1.rawCommand("router ospf", "(config-router-ospf)#");
		switchSut1.rawCommand("enable", "(config-router-ospf)#");
		switchSut1.rawCommand("area 0 area-id 0.0.0.0", "(config-router-ospf)#");
		switchSut1.rawCommand("area 0 enable", "(config-router-ospf)#");
		switchSut1.exitInterface();
		
		switchSut1.enterInterfacePort(getRoutedPortSUT1());
		switchSut1.rawCommand("no switchport", "(config-if)#");
		switchSut1.showVlan();
		switchSut1.analyze(new FindText("INTERNAL"));
		
		switchSut1.rawCommand("ip address " + tempNetWork + "16" + " " + tempMask + " enable", "(config-if)#");
		switchSut1.rawCommand("ip ospf area 0.0.0.0", "(config-if)#");
		switchSut1.rawCommand("ip ospf enable", "(config-if)#");
		switchSut1.exitInterface();
		
		mySwitchOpen.rawCommand("show ip ospf neighbor", "(config)#");
		mySwitchOpen.analyze(new FindText(getRoutedPort()));
		switchSut1.rawCommand("show ip ospf neighbor", "(config)#");
		switchSut1.analyze(new FindText(getRoutedPortSUT1()));
		mySwitchOpen.rawCommand("show ip ospf neighbor", "(config)#");
		mySwitchOpen.analyze(new FindText("Full           " + tempNetWork + "16"));
		switchSut1.rawCommand("show ip ospf neighbor", "(config)#");
		switchSut1.analyze(new FindText("Full           " + tempNetWork + "15"));		
	}
	
	public String getSwitchPort() {
	    return switchPort;
	}

	public void setSwitchPort(String switchPort) {
	    this.switchPort = switchPort;
	}
	
	public String getSwitchPortMode() {
	    return switchPortMode;
	}

	public void setSwitchPortMode(String switchPortMode) {
	    this.switchPortMode = switchPortMode;
	}
	
	public String getSwitchPortVlan() {
	    return switchPortVlan;
	}

	public void setSwitchPortVlan(String switchPortVlan) {
	    this.switchPortVlan = switchPortVlan;
	}
	
	public String getIpInterface() {
	    return ipInterface;
	}

	public void setIpInterface(String ipInterface) {
	    this.ipInterface = ipInterface;
	}

	public String getIpAddress() {
	    return ipAddress;
	}
	
	public void setIpAddress(String ipAddress) {
	    this.ipAddress = ipAddress;
	}
	
	public String getIPMask() {
	    return iPMask;
	}

	public void setIPMask(String iPMask) {
	    this.iPMask = iPMask;
	}
	
	public String getRoutedPort() {
	    return routedPort;
	}

	public void setRoutedPort(String routedPort) {
	    this.routedPort = routedPort;
	}
	
	public String getSwitchPortSUT1() {
	    return switchPortSUT1;
	}

	public void setSwitchPortSUT1(String switchPortSUT1) {
	    this.switchPortSUT1 = switchPortSUT1;
	}
	
	public String getSwitchPortModeSUT1() {
	    return switchPortModeSUT1;
	}

	public void setSwitchPortModeSUT1(String switchPortModeSUT1) {
	    this.switchPortModeSUT1 = switchPortModeSUT1;
	}
	
	public String getSwitchPortVlanSUT1() {
	    return switchPortVlanSUT1;
	}

	public void setSwitchPortVlanSUT1(String switchPortVlanSUT1) {
	    this.switchPortVlanSUT1 = switchPortVlanSUT1;
	}
	
	public String getIpInterfaceSUT1() {
	    return ipInterfaceSUT1;
	}

	public void setIpInterfaceSUT1(String ipInterfaceSUT1) {
	    this.ipInterfaceSUT1 = ipInterfaceSUT1;
	}

	public String getIpAddressSUT1() {
	    return ipAddressSUT1;
	}
	
	public void setIpAddressSUT1(String ipAddressSUT1) {
	    this.ipAddressSUT1 = ipAddressSUT1;
	}
	
	public String getIPMaskSUT1() {
	    return iPMaskSUT1;
	}

	public void setIPMaskSUT1(String iPMaskSUT1) {
	    this.iPMaskSUT1 = iPMaskSUT1;
	}
	
	public String getRoutedPortSUT1() {
	    return routedPortSUT1;
	}

	public void setRoutedPortSUT1(String routedPortSUT1) {
	    this.routedPortSUT1 = routedPortSUT1;
	}	
}
