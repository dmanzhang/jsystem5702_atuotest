package org.jsystem.automationProj;

import java.util.ArrayList;

import systemobject.terminal.Prompt;
import systemobject.terminal.VT100FilterInputStream;

import com.aqua.sysobj.conn.Position;
/**
 * Raw telnet CliConnection for a Cli connection.
 * Protocol is Telnet.
 * 
 * @author goland
 */
public class RawTelnetCliConnection extends ShellConnectionImpl {

	public RawTelnetCliConnection(){
		setDump(true);
		setUseTelnetInputStream(true);
		setProtocol("telnet");
	}

	public RawTelnetCliConnection(String host,String user,String password){
		this();
		setPort(23);
		setUser(user);
		setPassword(password);
		setHost(host);
	}
	
	public RawTelnetCliConnection(String host,String user,String password, int port){
		this();
		setEnterStr("\r\r");
		setPort(port);
		setUser(user);
		setPassword(password);
		setHost(host);
	}	
	
	public void init() throws Exception {
		super.init();
	}
	
	public void connect() throws Exception {
		super.connect();
		terminal.addFilter(new VT100FilterInputStream());
	}
	
	public Position[] getPositions() {
		// TODO Auto-generated method stub
		return null;
	}

	public Prompt[] getPrompts() {
		ArrayList<Prompt> prompts = new ArrayList<Prompt>();
		Prompt p = new Prompt();
		p.setPrompt("login:");
		p.setStringToSend("root");
		prompts.add(p);
		p = new Prompt();
		p.setPrompt("#");
		p.setCommandEnd(true);
		prompts.add(p);
		return prompts.toArray(new Prompt[prompts.size()]);
	}
}

