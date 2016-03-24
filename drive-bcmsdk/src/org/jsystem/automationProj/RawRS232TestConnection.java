package org.jsystem.automationProj;

import java.util.ArrayList;

import systemobject.terminal.Prompt;
import systemobject.terminal.VT100FilterInputStream;

import com.aqua.sysobj.conn.Position;

public class RawRS232TestConnection extends ShellConnectionImpl {

	public RawRS232TestConnection(){
		//setLeadingEnter(true);
		setProtocol("com");
	}

	public RawRS232TestConnection(String serialPort,int serialBaudRate){
		this();
		setUseBuffer(true);
        setHost(serialPort + ";" + serialBaudRate + ";" + 8 + ";"  + 1 + ";" + 0 + ";");
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