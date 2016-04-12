package com.jsystem.cliexample;

import jsystem.framework.system.SystemObjectImpl;
import systemobject.terminal.Prompt;

import com.aqua.sysobj.conn.CliCommand;
import com.aqua.sysobj.conn.CliConnectionImpl;

/**
 * Example system object which uses jsystem's cli driver to connect to a remote
 * Linux machine and perform operation on the remote machine.
 * 
 * @author goland
 */
public class MyStation extends SystemObjectImpl {
	
	public CliConnectionImpl connection;
	
	public void init() throws Exception {
		super.init();
		//alter the code below if the prompt of your machine is different 
		//from the default prompts which are defined by the default connection implementation 
		Prompt p = new Prompt("~$",false);
		p.setCommandEnd(true);
		connection.addPrompts(new Prompt[]{p});
		connection.init();
	}
	
	public void close(){
		super.close();
	}
	
	public void ping(String destination,int packetSize) throws Exception {
		CliCommand command = new CliCommand("ping " + destination + " -s " + packetSize + " -c 3");
		connection.handleCliCommand("Ping performed", command);
		setTestAgainstObject(command.getResult());
	}
}
