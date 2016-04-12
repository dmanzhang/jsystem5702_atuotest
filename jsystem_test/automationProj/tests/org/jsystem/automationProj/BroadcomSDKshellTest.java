package org.jsystem.automationProj;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.SystemTestCase4;

public class BroadcomSDKshellTest extends SystemTestCase4 {	
	private BroadcomSDKshellParam testParam;	
	private static BroadcomSDKshell connection;
	
	@Before
	public void before() throws Exception{
		report.report("Before operation");
		testParam =  
	              (BroadcomSDKshellParam)system.getSystemObject("broadcom_shell");
		
	}

	@Test
	public void OpenDUT() throws Exception {
		report.report("Calling operation");
		ArrayList <String>inparams = new ArrayList<String>();
		if (testParam.getConnectMode().compareTo("SSH") == 0)
        {
            inparams.add(testParam.getHost());
			inparams.add(testParam.getName());
			inparams.add(testParam.getPassword());
			connection = new BroadcomSDKshell(testParam.getConnectMode(), inparams.toArray(new String[inparams.size()]),
				                              testParam.getTelnetPort(), testParam.getConnectedPrompt());
        }
		else if (testParam.getConnectMode().compareTo("RAWSSH") == 0)
        {
            inparams.add(testParam.getHost());
			inparams.add(testParam.getName());
			inparams.add(testParam.getPassword());
			connection = new BroadcomSDKshell(testParam.getConnectMode(), inparams.toArray(new String[inparams.size()]),
				                              testParam.getTelnetPort(), testParam.getConnectedPrompt());
        }
        else if (testParam.getConnectMode().compareTo("TELNET") == 0)
        {
	        inparams.add(testParam.getHost());
			inparams.add(testParam.getName());
			inparams.add(testParam.getPassword());
			connection = new BroadcomSDKshell(testParam.getConnectMode(), inparams.toArray(new String[inparams.size()]),
				                              testParam.getTelnetPort(), testParam.getConnectedPrompt());
        }
        else if (testParam.getConnectMode().compareTo("RAWTELNET") == 0)
        {
	        inparams.add(testParam.getHost());
			inparams.add(testParam.getName());
			inparams.add(testParam.getPassword());
			connection = new BroadcomSDKshell(testParam.getConnectMode(), inparams.toArray(new String[inparams.size()]),
				                              testParam.getTelnetPort(), testParam.getConnectedPrompt());
        }
        else if (testParam.getConnectMode().compareTo("RS232") == 0)
        {
	        inparams.add(testParam.getSerialPort());
			connection = new BroadcomSDKshell(testParam.getConnectMode(), inparams.toArray(new String[inparams.size()]),
				                              testParam.getSerialBaudRate(), testParam.getConnectedPrompt());
        }
        else if (testParam.getConnectMode().compareTo("RAWRS232") == 0)
        {
	       inparams.add(testParam.getSerialPort());
	       connection = new BroadcomSDKshell(testParam.getConnectMode(), inparams.toArray(new String[inparams.size()]),
				                              testParam.getSerialBaudRate(), testParam.getConnectedPrompt());
        }
		else
		{
		    return;
		}

		connection.init();
		connection.rawCommand("ls", "#", true); 
	}	
	
	@Test
	public void changeDir() throws Exception {
		report.report("Calling changeDir");
		connection.rawCommand("cd " + testParam.getTftpRoot(), "#"); 
	}
	
	@Test
	public void chmod777() throws Exception {
		report.report("Calling chmod777");
		connection.rawCommand("chmod 777 *", "#");
	}
	
	@Test
	public void setEth0Ip() throws Exception {
		report.report("Calling setEth0Ip");
		connection.rawCommand("ifconfig eth0 " + testParam.getHost() + " netmask 255.255.255.0", "#"); 
	}
	
	@Test
	public void setEth1Ip() throws Exception {
		report.report("Calling setEth1Ip");
		connection.rawCommand("ifconfig eth1 " + testParam.getHost() + " netmask 255.255.255.0", "#"); 
	}
	
	@Test
	public void delay1000() throws Exception {
		report.report("Calling delay1000");
		Thread.sleep(1000); 
	}
	
	@Test
	public void insmodKernelBDE() throws Exception {
		report.report("Calling insmodKernelBDE");
		connection.rawCommand("insmod linux-kernel-bde.ko", "#");
	}
	
	@Test
	public void insmodKernelBDEWith() throws Exception {
		report.report("Calling insmodKernelBDE");
		connection.rawCommand("insmod linux-kernel-bde.ko maxpayload=128", "#");
	}
	
	@Test
	public void insmodBcmCore() throws Exception {
		report.report("Calling insmodBcmCore");
		connection.rawCommand("insmod linux-bcm-core.ko", "#");
	}
	
	@Test
	public void insmodUkProxy() throws Exception {
		report.report("Calling insmodUkProxy");
		connection.rawCommand("insmod linux-uk-proxy.ko", "#");
	}
	
	@Test
	public void mknodUkProxy() throws Exception {
		report.report("Calling mknodUkProxy");
		connection.rawCommand("mknod /dev/linux-uk-proxy c 125 0", "#");
	}
	
	@Test
	public void insmodKernelMDM() throws Exception {
		report.report("Calling insmodKernelMDM");
		connection.rawCommand("insmod linux_mdm_module.ko mdm_drive_debug=0x8", "#");
	}
	
	@Test
	public void insmodBcmDiag() throws Exception {
		report.report("Calling insmodBcmDiag");
		connection.rawCommand("insmod linux-bcm-diag.ko", "#");
	}
	
	@Test
	public void insmodBcmFullDiag() throws Exception {
		report.report("Calling insmodBcmDiag");
		connection.rawCommand("insmod linux-bcm-diag-full.ko", "#");
	}
	
	@Test
	public void insmodEswDrv() throws Exception {
		report.report("Calling insmodEswDrv");
		connection.rawCommand("./install", "#");
	}
	
	@Test
	public void runTerminerApp() throws Exception {
		report.report("Calling runTerminerApp");
		connection.rawCommand("./hyies", "DONE.");
		connection.rawCommand(" ", testParam.getConnectedPrompt());
	}
	
	@Test
	public void hyiesEnable() throws Exception {
		report.report("Calling hyiesEnable");
		connection.rawCommand("enable", "#");
	}
}

