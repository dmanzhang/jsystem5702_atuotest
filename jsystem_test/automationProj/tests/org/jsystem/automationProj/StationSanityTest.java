package org.jsystem.automationProj;

import org.junit.Before;
import org.junit.Test;

import jsystem.extensions.analyzers.text.FindText;
import junit.framework.SystemTestCase4;

public class StationSanityTest extends SystemTestCase4 {
	private String pingDestination = "10.38.24.1";
	private int    packetSize = 1024;
	private IbmNosxStation myStation;
	
	@Before
	public void before() throws Exception{
	    myStation =  
	              (IbmNosxStation)system.getSystemObject("nosx_station");  

	}
	
	@Test
	public void pingFromDUT() throws Exception {
		report.report("Calling myStation ping operation");
        myStation.ping(getPingDestination(),getPacketSize());
        myStation.analyze(new FindText("0% packet loss"));
	}
	
	@Test
	public void enterImish() throws Exception {
		report.report("enter imish ");
		myStation.rawCommand("/nosx/bin/imish", myStation.getPromptString() + ">");
	}
	
	@Test
	public void enable() throws Exception {
		report.report("Calling MySwitchOpen operation");
		myStation.rawCommand("enable", myStation.getPromptString() + "#");
	}
	
	@Test
	public void configure() throws Exception {
		report.report("Calling MySwitchOpen operation");
		myStation.rawCommand("configure terminal", myStation.getPromptString() + "(config)#");
	}
	
	@Test
	public void InterfaceUpDownWithDelay() throws Exception {
		report.report("InterfaceUpDown");
		int i;
		
		myStation.rawCommand("interface " + myStation.getSUTPort(), myStation.getPromptString() + "(config-if)#");
		for (i=0; i<1000; i++)
		{
		    myStation.rawCommand("shutdown", myStation.getPromptString() + "(config-if)#");
		    Thread.sleep(5);
		    myStation.rawCommand("no shutdown", myStation.getPromptString() + "(config-if)#");
		}
	}
	
	@Test
	public void InterfaceUpDown() throws Exception {
		report.report("InterfaceUpDown");
		int i;
		
		myStation.rawCommand("interface " + myStation.getSUTPort(), myStation.getPromptString() + "(config-if)#");
		for (i=0; i<100; i++)
		{
		    myStation.rawCommand("shutdown", myStation.getPromptString() + "(config-if)#");
		    myStation.rawCommand("no shutdown", myStation.getPromptString() + "(config-if)#");
		}
	}
	
	@Test
	public void InterfaceSwitchportWithDelay() throws Exception {
		report.report("InterfaceSwitchport");
		int i;
		
		myStation.rawCommand("interface " + myStation.getSUTPort(), myStation.getPromptString() + "(config-if)#");
		for (i=0; i<1000; i++)
		{
		    myStation.rawCommand("switchport", myStation.getPromptString() + "(config-if)#");
		    Thread.sleep(5);
		    myStation.rawCommand("no switchport", myStation.getPromptString() + "(config-if)#");
		}
	}
	
	@Test
	public void InterfaceSwitchport() throws Exception {
		report.report("InterfaceSwitchport");
		int i;
		
		myStation.rawCommand("interface " + myStation.getSUTPort(), myStation.getPromptString() + "(config-if)#");
		for (i=0; i<100; i++)
		{
		    myStation.rawCommand("switchport", myStation.getPromptString() + "(config-if)#");
		    myStation.rawCommand("no switchport", myStation.getPromptString() + "(config-if)#");
		}
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
