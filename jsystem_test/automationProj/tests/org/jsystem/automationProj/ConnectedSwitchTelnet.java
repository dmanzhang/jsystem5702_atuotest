package org.jsystem.automationProj;

import junit.framework.SystemTestCase4;
import org.junit.Before;
import org.junit.Test;

public class ConnectedSwitchTelnet extends SystemTestCase4 {
	private ConfigureTestParam testParam;	
	private SwitchCliConnection connection;	

	@Before
	public void before() throws Exception{
		testParam =  
	              (ConfigureTestParam)system.getSystemObject("my_switchcli");
		connection = new SwitchCliConnection(testParam.getHost(), testParam.getName(), testParam.getPassword(), testParam.getProtocalPort());
		connection.init();

	}

	@Test
	public void OpenDUT() throws Exception {
		report.report("Calling operation");
		connection.connect(); 
	}
	
	@Test
	public void changeDir() throws Exception {
		report.report("Calling operation");
		connection.rawCommand("? ", "Router>"); 
	}
}
