package org.jsystem.quickstart;

import jsystem.extensions.analyzers.text.FindText;
import org.junit.Before;
import org.junit.Test;
import org.jsystem.quickstart.MySwitchOpen;
import junit.framework.SystemTestCase4;

public class SwitchSanityTest extends SystemTestCase4 {
	private String switchPort = "20";
	private String routedPort = "1";
	private String switchPortMode = "access";
	private String switchPortVlan = "20";
	private String ipInterface = "2";
	private String ipAddress = "2.22.23.31";
	private String iPMask = "255.255.255.0";
	private String imagePash = "temp/hobartzhang";
	private String version = "7.7.0.19";
	private String managePort = "mgt";
	private MySwitchOpen mySwitchOpen;
	
	final private int MAXIPINTERFACE = 125;
    final private int STARTIPINTERFACE = 2;
    final private int MAXVLAN = 1024;
    
	@Before
	public void before() throws Exception{
		mySwitchOpen =  
	              (MySwitchOpen)system.getSystemObject("my_switch1");  

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
	}
	
	@Test
	public void OpenDUTMode1() throws Exception {
		report.report("Calling MySwitchOpen operation");
		mySwitchOpen.enable();
		mySwitchOpen.analyze(new FindText("#"));
		mySwitchOpen.configureTerminal();
		mySwitchOpen.analyze(new FindText("(config)#"));
	}
	
	@Test
	public void UpdateBoot() throws Exception {
		report.report("UpdateBoot");
		mySwitchOpen.rawCommand("copy tftp boot-image address " + mySwitchOpen.getTftpServer() + 
				" filename " + getImagePash() + "/" + mySwitchOpen.getPromptString() + "-RS-" + getVersion() + "_Boot.img", "]:");
		mySwitchOpen.rawCommand(managePort, "(y/n) ?");
		mySwitchOpen.rawCommandNoTimeOut("y", "(config)#");
		mySwitchOpen.analyze(new FindText("contains Software Version"));
	}
	
	@Test
	public void UpdateImage1() throws Exception {
		report.report("UpdateImage1");
		mySwitchOpen.rawCommand("boot image image1", "(config)#");
		mySwitchOpen.rawCommand("copy tftp image1 address " + mySwitchOpen.getTftpServer() + 
				" filename " + getImagePash() + "/" + mySwitchOpen.getPromptString() + "-RS-" + getVersion() + "_OS.img", "]:");
		mySwitchOpen.rawCommand(managePort, "(y/n) ?");
		mySwitchOpen.rawCommandNoTimeOut("y", "(config)#");
		mySwitchOpen.analyze(new FindText("FLASH successful"));
		mySwitchOpen.analyze(new FindText(getVersion()));
	}
	
	@Test
	public void UpdateImage2() throws Exception {
		report.report("UpdateImage2");
		mySwitchOpen.rawCommand("boot image image2", "(config)#");
		mySwitchOpen.rawCommand("copy tftp image2 address " + mySwitchOpen.getTftpServer() + 
				" filename " + getImagePash() + "/" + mySwitchOpen.getPromptString() + "-RS-" + getVersion() + "_OS.img", "]:");
		mySwitchOpen.rawCommand(managePort, "(y/n) ?");
		mySwitchOpen.rawCommandNoTimeOut("y", "(config)#");
		mySwitchOpen.analyze(new FindText("FLASH successful"));
		mySwitchOpen.analyze(new FindText(getVersion()));
	}
	
	@Test
	public void UpdateBuildBoot() throws Exception {
		report.report("UpdateBuildBoot");
		mySwitchOpen.rawCommand("copy tftp boot-image address " + mySwitchOpen.getTftpServer() + 
				" filename " + getImagePash() + "/" + "BladeBoot", "]:");
		mySwitchOpen.rawCommand(managePort, "(y/n) ?");
		mySwitchOpen.rawCommandNoTimeOut("y", "(config)#");
		mySwitchOpen.analyze(new FindText("contains Software Version"));
	}
	
	@Test
	public void UpdateBuildImage1() throws Exception {
		report.report("UpdateBuildImage1");
		mySwitchOpen.rawCommand("boot image image1", "(config)#");
		mySwitchOpen.rawCommand("copy tftp image1 address " + mySwitchOpen.getTftpServer() + 
				" filename " + getImagePash() + "/" + "BladeOS", "]:");
		mySwitchOpen.rawCommand(managePort, "(y/n) ?");
		mySwitchOpen.rawCommandNoTimeOut("y", "(config)#");
		mySwitchOpen.analyze(new FindText("FLASH successful"));
	}
	
	@Test
	public void UpdateBuildImage2() throws Exception {
		report.report("UpdateBuildImage2");
		mySwitchOpen.rawCommand("boot image image2", "(config)#");
		mySwitchOpen.rawCommand("copy tftp image2 address " + mySwitchOpen.getTftpServer() + 
				" filename " + getImagePash() + "/" +  "BladeOS", "]:");
		mySwitchOpen.rawCommand(managePort, "(y/n) ?");
		mySwitchOpen.rawCommandNoTimeOut("y", "(config)#");
		mySwitchOpen.analyze(new FindText("FLASH successful"));
	}
	
	@Test
	public void Reload() throws Exception {
		report.report("Reload");
		mySwitchOpen.rawCommand("write", "(config)#");
		mySwitchOpen.rawCommand("reload", "(y/n) ?");
		mySwitchOpen.rawCommandNoTimeOut("y", "Resetting");
	}
	
	@Test
	public void SwitchPortMode() throws Exception {
		report.report("Set switch port mode");
		mySwitchOpen.enterInterfacePort(getSwitchPort());
		mySwitchOpen.setSwitchPortMode(getSwitchPortMode());
		mySwitchOpen.exitInterface();
		mySwitchOpen.showInterfacePort(getSwitchPort());
		mySwitchOpen.analyze(new FindText("Switch port"));
	}
	
	@Test
	public void SwitchPortAccessVlan() throws Exception {
		report.report("Set switch port access vlan");
		mySwitchOpen.enterInterfacePort(getSwitchPort());
		mySwitchOpen.setSwitchPortAccessVlan(getSwitchPortVlan());
		mySwitchOpen.exitInterface();
		mySwitchOpen.showInterfacePort(getSwitchPort());
		mySwitchOpen.analyze(new FindText("VLANs:" + getSwitchPortVlan()));
	}
	
	@Test
	public void SwitchPortTrunkVlanAdd() throws Exception {
		report.report("Switch port trunk vlan add");
		mySwitchOpen.enterInterfacePort(getSwitchPort());
		mySwitchOpen.setSwitchPortTrunkVlan("add", getSwitchPortVlan());
		mySwitchOpen.exitInterface();
		mySwitchOpen.showInterfacePort(getSwitchPort());
		mySwitchOpen.analyze(new FindText("VLANs:" + getSwitchPortVlan()));
	}
	
	@Test
	public void SwitchPortTrunkVlanDelete() throws Exception {
		report.report("Switch port access vlan remove");
		mySwitchOpen.enterInterfacePort(getSwitchPort());
		mySwitchOpen.setSwitchPortTrunkVlan("remove", getSwitchPortVlan());
		mySwitchOpen.exitInterface();
		mySwitchOpen.showInterfacePort(getSwitchPort());
		mySwitchOpen.analyze(new FindText("VLANs"));
	}
	
	@Test
	public void ClearMacAddressTable() throws Exception {
		report.report("Clear mac address table");
		mySwitchOpen.clearMacAddress();
		mySwitchOpen.analyze(new FindText("Dynamic FDB entries have been removed"));
	}
	
	@Test
	public void IpInterfaceAddressSet() throws Exception {
		String routedPort = "1";
		
		report.report("set ip address");
		mySwitchOpen.enterInterfaceIp(routedPort);
		mySwitchOpen.setIpAddress(getIpAddress(), getIPMask());
		mySwitchOpen.setIpAddressVlan(getSwitchPortVlan());
		mySwitchOpen.exitInterface();
		mySwitchOpen.showInterfaceIp(getIpInterface());
		mySwitchOpen.analyze(new FindText("IP4" + " " + getIpAddress()));
	}
	
	@Test
	public void NoIpInterfaceAddress() throws Exception {
		report.report("no ip address");
		mySwitchOpen.noIpAddress(getIpInterface());
		mySwitchOpen.showInterfaceIp(getIpInterface());
		mySwitchOpen.analyze(new FindText("IP4 0.0.0.0"));
	}
	
	@Test
	public void IpInterfaceAddDeleteWithClearMac() throws Exception {
		report.report("no ip address");
	    int i;
	    int sec_ip = 2;
	    int interfaceIndex;	    	    
	    
	    interfaceIndex = STARTIPINTERFACE;
	    for(i=0; i<MAXVLAN; i++)
	    {
	        if (interfaceIndex > MAXIPINTERFACE)
	        {
	            interfaceIndex = STARTIPINTERFACE;
	        }
	        
	        mySwitchOpen.enterInterfaceIp("" + interfaceIndex);
	        if ((i+1)%255 != 0)
	        {
	            i += 2;
	            sec_ip ++;
	        }
	        mySwitchOpen.setIpAddress(("2." + sec_ip + "." + (i%255) + ".1"), "255.255.255.0");
	        mySwitchOpen.setIpAddressVlan("" + (i+2));
	        mySwitchOpen.rawCommand("enable", "(config-ip-if)#");
	        mySwitchOpen.exitInterface();	        
	        mySwitchOpen.showInterfaceIp("" + interfaceIndex);
	        mySwitchOpen.analyze(new FindText("IP4" + " " + ("2." + sec_ip + "." + (i%255) + ".1")));
	        	               
	        report.report("Clear mac address table");
			mySwitchOpen.clearMacAddress();
			mySwitchOpen.analyze(new FindText("Dynamic FDB entries have been removed"));
			
			report.report("no ip address");
			mySwitchOpen.noIpAddress("" + interfaceIndex);
			mySwitchOpen.showInterfaceIp("" + interfaceIndex);
			mySwitchOpen.analyze(new FindText("IP4 0.0.0.0"));
			
			interfaceIndex++;
	    }
	}
	
	@Test
	public void AddVlan() throws Exception {
		report.report("add vlan");
		mySwitchOpen.rawCommand(("vlan 2-" + (MAXVLAN+2)), "(config-vlan)#");
	    mySwitchOpen.exitInterface();
	}
	
	@Test
	public void DeleteVlan() throws Exception {
		report.report("delete vlan");
		mySwitchOpen.rawCommand(("no vlan 2-" + (MAXVLAN+2)), "(config)#");
	}
	
	@Test
	public void ConfigAndShowRoutedPort() throws Exception {
		report.report("ConfigAndShowRoutedPort");
		if (mySwitchOpen.getSUTPort() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort());
		}
		else
		{
		    setRoutedPort("21");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));
		
		if (mySwitchOpen.getSUTPort1() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort1());
		}
		else
		{
		    setRoutedPort("25");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));
		
		mySwitchOpen.rawCommand("interface port mySwitchOpen.getSUTPort(),mySwitchOpen.getSUTPort1()", "(config)#");
		mySwitchOpen.analyze(new FindText("ERROR"));
				
		if (mySwitchOpen.getSUTPort() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort());
		}
		else
		{
		    setRoutedPort("21");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		if (mySwitchOpen.getSUTPort1() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort1());
		}
		else
		{
		    setRoutedPort("25");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void DisableRoutedPort() throws Exception {
		report.report("DisableRoutedPort");
		if (mySwitchOpen.getSUTPort1() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort1());
		}
		else
		{
		    setRoutedPort("25");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("ip address 15.15.15.15 255.255.255.0 enable", "(config-if)#");
		mySwitchOpen.exitInterface();
		mySwitchOpen.rawCommand("show interface ip", "(config)#");
		mySwitchOpen.analyze(new FindText(getRoutedPort() + ": IP4 15.15.15.15"));
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.analyze(new FindText("NOTICE"));
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void FlowcontrolOnRoutedPort() throws Exception {
		report.report("FlowcontrolOnRoutedPort");
		if (mySwitchOpen.getSUTPort1() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort1());
		}
		else
		{
		    setRoutedPort("25");
		}setRoutedPort("25");
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("flowcontrol both", "(config-if)#");
		mySwitchOpen.exitInterface();
		mySwitchOpen.showInterfacePort(getRoutedPort());
		mySwitchOpen.analyze(new FindText("fctl both"));
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void FloodBlockingOnRoutedPort() throws Exception {
		report.report("FloodBlockingOnRoutedPort");
		if (mySwitchOpen.getSUTPort1() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort1());
		}
		else
		{
		    setRoutedPort("25");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("flood-blocking", "(config-if)#");
		mySwitchOpen.rawCommand("show interface port " + getRoutedPort(), "(config-if)#");
		mySwitchOpen.analyze(new FindText("Flood blocking: enabled"));
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("flood-blocking", "(config-if)#");
		mySwitchOpen.analyze(new FindText("% Invalid input detected at '^' marker"));
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void L2LearningOnRoutedPort() throws Exception {
		report.report("L2LearningOnRoutedPort");
		if (mySwitchOpen.getSUTPort1() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort1());
		}
		else
		{
		    setRoutedPort("25");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no learning", "(config-if)#");
		mySwitchOpen.rawCommand("show interface port " + getRoutedPort(), "(config-if)#");
		mySwitchOpen.analyze(new FindText("L2 Learning: disabled"));
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("no learning", "(config-if)#");
		mySwitchOpen.analyze(new FindText("% Invalid input detected at '^' marker"));
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void CheckFDBOnRoutedPort() throws Exception {
		report.report("CheckFDBOnRoutedPort");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("show mac-address-table interface port " + getRoutedPort(), "(config-if)#");
		mySwitchOpen.analyze(new FindText("Internal"));
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void CheckLACPOnRoutedPort() throws Exception {
		report.report("CheckLACPOnRoutedPort");
		if (mySwitchOpen.getSUTPort1() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort1());
		}
		else
		{
		    setRoutedPort("25");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("lacp mode active", "(config-if)#");
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.analyze(new FindText("Error"));				
		
		mySwitchOpen.rawCommand("lacp mode off", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void SkipUsedVlanOnRoutedPort() throws Exception {
		report.report("SkipUsedVlanOnRoutedPort");
		if (mySwitchOpen.getSUTPort1() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort1());
		}
		else
		{
		    setRoutedPort("25");
		}
		mySwitchOpen.rawCommand("vlan 4094", "(config-vlan)#");
		mySwitchOpen.rawCommand("show vlan", "(config-vlan)#");
		mySwitchOpen.analyze(new FindText("VLAN 4094"));
		mySwitchOpen.rawCommand("vlan 4090", "(config-vlan)#");
		mySwitchOpen.rawCommand("show vlan", "(config-vlan)#");
		mySwitchOpen.analyze(new FindText("VLAN 4090"));
		mySwitchOpen.exitInterface();
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));
		mySwitchOpen.analyze(new FindText("VLAN 4093"));	
		
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
		mySwitchOpen.rawCommand("no vlan 4094", "(config)#");
		mySwitchOpen.rawCommand("no vlan 4090", "(config)#");
	}
	
	@Test
	public void SkipInternalVlanAtUserOnRoutedPort() throws Exception {
		report.report("SkipInternalVlanAtUser");
		if (mySwitchOpen.getSUTPort1() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort1());
		}
		else
		{
		    setRoutedPort("25");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));
		mySwitchOpen.exitInterface();
		mySwitchOpen.rawCommand("vlan 4094", "(config)#");
		mySwitchOpen.analyze(new FindText("Error"));	
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void ReleaseInternalVlanOnRoutedPort() throws Exception {
		report.report("SkipInternalVlanAtUser");
		if (mySwitchOpen.getSUTPort1() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort1());
		}
		else
		{
		    setRoutedPort("25");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));
		mySwitchOpen.exitInterface();
		mySwitchOpen.rawCommand("vlan 4094", "(config)#");
		mySwitchOpen.analyze(new FindText("Error"));	
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void CanNotUseForExternalVlanOnRoutedPort() throws Exception {
		report.report("CanNotUseForExternalVlanOnRoutedPort");
		if (mySwitchOpen.getSUTPort1() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort1());
		}
		else
		{
		    setRoutedPort("25");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));
		mySwitchOpen.exitInterface();
		
		mySwitchOpen.rawCommand("vlan 4", "(config-vlan)#");
		mySwitchOpen.rawCommand("member " + mySwitchOpen.getSUTPort() + "," + mySwitchOpen.getSUTPort1(), "(config-vlan)#");
		mySwitchOpen.analyze(new FindText("Error"));
		mySwitchOpen.exitInterface();
		
		mySwitchOpen.rawCommand("no vlan 4", "(config)#");
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void PingingOnRoutedPort() throws Exception {
		String tempIpAddress = "15.15.15.15";
		
		report.report("PingingOnRoutedPort");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("ip address " + tempIpAddress + " 255.255.255.0 enable", "(config-if)#");
		mySwitchOpen.exitInterface();
		mySwitchOpen.rawCommand("ping " + tempIpAddress + " data-port", "(config)#");
		mySwitchOpen.analyze(new FindText("ok"));
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void ConfigureIPOnRoutedPort() throws Exception {
		String tempIpAddress = "15.15.15.15";
		String tempNetWork = "15.15.15.";
		String tempMask = "255.255.255.0";
		
		report.report("ConfigureIPOnRoutedPort");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("ip address " + tempIpAddress + " " + tempMask + " enable", "(config-if)#");
		mySwitchOpen.rawCommand("show interface ip", "(config-if)#");
		mySwitchOpen.analyze(new FindText(getRoutedPort() + ": IP4 " + tempIpAddress));
		mySwitchOpen.rawCommand("show ip route", "(config-if)#");
		mySwitchOpen.analyze(new FindText(tempIpAddress));
		mySwitchOpen.analyze(new FindText(tempNetWork + "0"));
		mySwitchOpen.analyze(new FindText(tempNetWork + "255"));
		mySwitchOpen.rawCommand("show ip arp", "(config-if)#");
		mySwitchOpen.analyze(new FindText("routed"));
		
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void ConfigureStaticRouteOnRoutedPort() throws Exception {
		String tempNetWork = "15.15.15.";
		String tempMask = "255.255.255.0";
		String routeNetWork = "15.16.15.";
		
		report.report("ConfigureStaticRouteOnRoutedPort");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("ip route " + routeNetWork + "0" + " " + tempMask + " " + tempNetWork + "1" + 
		                        " port" + " " + getRoutedPort(), "(config)#");
		mySwitchOpen.analyze(new FindText("WARNING"));
		
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("ip address " + tempNetWork + "15" + " " + tempMask + " enable", "(config-if)#");
		mySwitchOpen.rawCommand("ip route " + routeNetWork + "0" + " " + tempMask + " " + tempNetWork + "1" + 
                " port" + " " + getRoutedPort(), "(config)#");
		mySwitchOpen.rawCommand("show ip route", "(config)#");
		mySwitchOpen.analyze(new FindText(routeNetWork + "0"));
		
		mySwitchOpen.rawCommand("no ip route " + tempNetWork + "0" + " " + tempMask, "(config)#");
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void ConfigureStaticARPOnRoutedPort() throws Exception {
		String tempNetWork = "15.15.15.";
		String tempMask = "255.255.255.0";
		String tempMAC = "08:17:f4:b0:df:00";
		
		report.report("ConfigureStaticARPOnRoutedPort");
		if (mySwitchOpen.getSUTPort1() != "")
		{
			setRoutedPort(mySwitchOpen.getSUTPort1());
		}
		else
		{
		    setRoutedPort("25");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("ip arp " + tempNetWork + "15" + " " + tempMAC + " " +
				                " port" + " " + getRoutedPort(), "(config)#");
		mySwitchOpen.analyze(new FindText("Error"));
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("ip address " + tempNetWork + "14" + " " + tempMask + " enable", "(config-if)#");
		mySwitchOpen.rawCommand("ip arp " + tempNetWork + "15"  + " " + tempMAC + " " +
                " port" + " " + getRoutedPort(), "(config)#");
		mySwitchOpen.rawCommand("show ip arp static", "(config)#");
		mySwitchOpen.analyze(new FindText(tempNetWork + "15"));
		mySwitchOpen.analyze(new FindText(" routed"));
		
		mySwitchOpen.rawCommand("show ip arp", "(config)#");
		mySwitchOpen.analyze(new FindText(tempNetWork + "16" + "           " + tempMAC + " routed"));
		mySwitchOpen.analyze(new FindText(getRoutedPort()));
		
		mySwitchOpen.rawCommand("ip arp " + tempNetWork + "16"  + " " + tempMAC + " " + "vlan 4094" +
                " port" + " " + getRoutedPort(), "(config)#");
		
		mySwitchOpen.rawCommand("no ip arp " + tempNetWork + "15", "(config)#");
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}

	@Test
	public void LearnDynamicARPOnRoutedPort() throws Exception {
		String tempNetWork = "15.15.15.";
		String tempMask = "255.255.255.0";
		String tempMAC = "00:03:6d:df:32:a8";
		
		report.report("LearnDynamicARPOnRoutedPort");
		report.report("PingingOnRoutedPort");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("ip arp " + tempNetWork + "15" + " " + tempMAC + " " +
				                " port" + " " + getRoutedPort(), "(config-if)#");
		mySwitchOpen.analyze(new FindText("Error"));
		mySwitchOpen.rawCommand("ip address " + tempNetWork + "15" + " " + tempMask + " enable", "(config-if)#");
		mySwitchOpen.rawCommand("ip arp " + tempNetWork + "15"  + " " + tempMAC + " " +
                " port" + " " + getRoutedPort(), "(config-if)#");
		mySwitchOpen.exitInterface();
		mySwitchOpen.rawCommand("show ip arp static", "(config)#");
		mySwitchOpen.analyze(new FindText(tempNetWork + "15"));
		mySwitchOpen.analyze(new FindText(" routed"));
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("ip arp " + tempNetWork + "16"  + " " + tempMAC + " " + "vlan 4094" +
                " port" + " " + getRoutedPort(), "(config-if)#");
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void ConfigureIGMPQuerierOnRoutedPort4223() throws Exception {
		
		report.report("ConfigureIGMPQuerierOnRoutedPort4223");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		mySwitchOpen.exitInterface();
		
		mySwitchOpen.rawCommand("ip igmp querier" + " port" + " " + getRoutedPort() + " " + "enable", "(config)#");
		mySwitchOpen.analyze(new FindText("Error"));
		mySwitchOpen.rawCommand("ip igmp querier" + " port" + " " + mySwitchOpen.getSUTPort1() + " " + "enable", "(config)#");
		mySwitchOpen.analyze(new FindText("Error"));
		mySwitchOpen.rawCommand("ip igmp querier" + " vlan" + " " + "4094" + " " + "enable", "(config)#");
		mySwitchOpen.analyze(new FindText("ERROR"));
		
		mySwitchOpen.rawCommand("show ip igmp querier" + " vlan" + " " + "4094", "(config)#");
		mySwitchOpen.analyze(new FindText("No querier info"));
		mySwitchOpen.rawCommand("no ip igmp querier" + " vlan" + " " + "4094", "(config)#");
		mySwitchOpen.analyze(new FindText("ERROR"));
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}

	@Test
	public void ConfigureIGMPSnoopOnRoutedPort4224() throws Exception {
		
		report.report("ConfigureIGMPSnoopOnRoutedPort4224");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		mySwitchOpen.exitInterface();
		
		mySwitchOpen.rawCommand("ip igmp snoop" + " port" + " " + getRoutedPort(), "(config)#");
		
		mySwitchOpen.rawCommand("ip igmp snoop" + " port" + " " + mySwitchOpen.getSUTPort1(), "(config)#");
		mySwitchOpen.analyze(new FindText("Error"));
		mySwitchOpen.rawCommand("ip igmp snoop" + " port" + " " + getRoutedPort(), "(config)#");
		mySwitchOpen.analyze(new FindText("Ignored"));
		mySwitchOpen.rawCommand("ip igmp snoop" + " vlan" + " " + "4094", "(config)#");
		mySwitchOpen.analyze(new FindText("ERROR"));
		
		mySwitchOpen.rawCommand("show ip igmp snoop", "(config)#");
		mySwitchOpen.analyze(new FindText("Current IGMP Advanced"));
		mySwitchOpen.rawCommand("no ip igmp snoop" + " vlan" + " " + "4094", "(config)#");
		mySwitchOpen.analyze(new FindText("ERROR"));
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void ConfigureIGMPFastleaveOnRoutedPort4225() throws Exception {
		
		report.report("ConfigureIGMPFastleaveOnRoutedPort4225");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		mySwitchOpen.exitInterface();
		
		mySwitchOpen.rawCommand("ip igmp fastleave" + " port" + " " + getRoutedPort(), "(config)#");
		mySwitchOpen.rawCommand("ip igmp fastleave" + " 4094", "(config)#");
		mySwitchOpen.analyze(new FindText("ERROR"));
		mySwitchOpen.rawCommand("ip igmp fastleave " + " port" + " " + mySwitchOpen.getSUTPort1(), "(config)#");
		mySwitchOpen.analyze(new FindText("Error"));
		
		mySwitchOpen.rawCommand("no ip igmp fastleave " + " vlan" + " " + "4094", "(config)#");
		mySwitchOpen.analyze(new FindText("ERROR"));
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void ConfigureMrouteOnRoutedPort4226() throws Exception {
		
		report.report("ConfigureMrouteOnRoutedPort4226");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		mySwitchOpen.exitInterface();
		
		mySwitchOpen.rawCommand("ip igmp mroute" + " port" + " " + getRoutedPort() + " 4094 2" , "(config)#");
		mySwitchOpen.analyze(new FindText("Error"));
		mySwitchOpen.rawCommand("ip igmp mroute" + " port" + " " + getRoutedPort() + " 1 2" , "(config)#");
		mySwitchOpen.analyze(new FindText("Error"));
		mySwitchOpen.rawCommand("no ip igmp mroute" + " port" + " " + getRoutedPort() + " 4094 2" , "(config)#");
		mySwitchOpen.analyze(new FindText("Ignored"));
		mySwitchOpen.rawCommand("no ip igmp mroute" + " port" + " " + getRoutedPort() + " 1 2" , "(config)#");
		mySwitchOpen.analyze(new FindText("Ignored"));
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void ConfigureStaticMrouteOnRoutedPort4227() throws Exception {
		
		report.report("ConfigureStaticMrouteOnRoutedPort4227");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		mySwitchOpen.exitInterface();
		
		mySwitchOpen.rawCommand("ip igmp snoop" + " port " + getRoutedPort(), "(config)#");
		mySwitchOpen.rawCommand("ip igmp snoop" + " enable", "(config)#");
		mySwitchOpen.rawCommand("show ip igmp snoop", "(config)#");
		mySwitchOpen.analyze(new FindText("snoop ena"));
		
		mySwitchOpen.rawCommand("ip igmp" + " enable", "(config)#");
		
		mySwitchOpen.rawCommand("ip mroute 225.0.0.1" + " 4094 1", "(config)#");
		mySwitchOpen.rawCommand("show ip mroute", "(config)#");
		mySwitchOpen.analyze(new FindText(" 225.0.0.1       4094"));
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void ConfigureIGMPQuerierOnRoutedPort4228() throws Exception {
		String tempNetWork = "15.15.15.";
		String tempMask = "255.255.255.0";
		String routeNetWork = "15.16.15.";
		
		report.report("ConfigureStaticRouteOnRoutedPort");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("ip route " + routeNetWork + "0" + " " + tempMask + " " + tempNetWork + "1" + 
		                        " port" + " " + getRoutedPort(), "(config)#");
		mySwitchOpen.analyze(new FindText("WARNING"));
		
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("ip address " + tempNetWork + "15" + " " + tempMask + " enable", "(config-if)#");
		mySwitchOpen.rawCommand("ip route " + routeNetWork + "0" + " " + tempMask + " " + tempNetWork + "1" + 
                " port" + " " + getRoutedPort(), "(config)#");
		mySwitchOpen.rawCommand("show ip route", "(config)#");
		mySwitchOpen.analyze(new FindText(routeNetWork + "0"));
		
		mySwitchOpen.rawCommand("no ip route " + tempNetWork + "0" + " " + tempMask, "(config)#");
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
	}
	
	@Test
	public void ConfigureIGMPQuerierOnRoutedPort4229() throws Exception {
		String tempNetWork = "15.15.15.";
		String tempMask = "255.255.255.0";
		String routeNetWork = "15.16.15.";
		
		report.report("ConfigureStaticRouteOnRoutedPort");
		if (mySwitchOpen.getLinkUpPort() != "")
		{
			setRoutedPort(mySwitchOpen.getLinkUpPort());
		}
		else
		{
		    setRoutedPort("41");
		}
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("no switchport", "(config-if)#");
		mySwitchOpen.showVlan();
		mySwitchOpen.analyze(new FindText("INTERNAL"));				
		
		mySwitchOpen.rawCommand("ip route " + routeNetWork + "0" + " " + tempMask + " " + tempNetWork + "1" + 
		                        " port" + " " + getRoutedPort(), "(config)#");
		mySwitchOpen.analyze(new FindText("WARNING"));
		
		
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("ip address " + tempNetWork + "15" + " " + tempMask + " enable", "(config-if)#");
		mySwitchOpen.rawCommand("ip route " + routeNetWork + "0" + " " + tempMask + " " + tempNetWork + "1" + 
                " port" + " " + getRoutedPort(), "(config)#");
		mySwitchOpen.rawCommand("show ip route", "(config)#");
		mySwitchOpen.analyze(new FindText(routeNetWork + "0"));
		
		mySwitchOpen.rawCommand("no ip route " + tempNetWork + "0" + " " + tempMask, "(config)#");
		mySwitchOpen.enterInterfacePort(getRoutedPort());
		mySwitchOpen.rawCommand("switchport", "(config-if)#");
		mySwitchOpen.exitInterface();
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
	
	public String getImagePash() {
	    return imagePash;
	}

	public void setImagePash(String imagePash) {
	    this.imagePash = imagePash;
	}
	
	public String getVersion() {
	    return version;
	}

	public void setVersion(String version) {
	    this.version = version;
	}
	
	public String getManagePort() {
	    return managePort;
	}

	public void setManagePort(String managePort) {
	    this.managePort = managePort;
	}
}
