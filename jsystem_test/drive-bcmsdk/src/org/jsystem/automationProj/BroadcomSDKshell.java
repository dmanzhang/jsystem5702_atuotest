package org.jsystem.automationProj;

import java.util.ArrayList;

import systemobject.terminal.Prompt;

import jsystem.framework.system.SystemObjectImpl;

public class BroadcomSDKshell extends SystemObjectImpl implements Cloneable{
	private String connectMode;
	private ShellConnectionImpl shellConnection;
	
	public BroadcomSDKshell() {
		// TODO Auto-generated constructor stub
	}
	
	public BroadcomSDKshell(String linkMode, String[] param, int port, String connectedPrompt) throws Exception{
		this();
		report.report("connection:" + " " + connectedPrompt + linkMode);
		connectMode = linkMode;
		if (connectMode.compareTo("SSH") == 0)
        {
            report.report("connect by SSH");
	        shellConnection = new RawSSHCliConnection(param[0], param[1], param[2]);
	         	
	        shellConnection.init();
        }
		else if (connectMode.compareTo("RAWSSH") == 0)
        {
            report.report("connect by raw SSH");
	        shellConnection = new RawSSHCliConnection(param[0], param[1], param[2]);
			ArrayList<Prompt> prompts = new ArrayList<Prompt>();
	        Prompt p = new Prompt();
	        p.setPrompt("login:");
			p.setStringToSend("root");
			prompts.add(p);
			p = new Prompt();
			p.setPrompt("~#");
			p.setCommandEnd(true);
			prompts.add(p);
			shellConnection.setPrompts(prompts.toArray(new Prompt[prompts.size()]));
			shellConnection.init(); 
        }
        else if (connectMode.compareTo("TELNET") == 0)
        {
	        report.report("connect by telnet");
	        shellConnection = new RawTelnetCliConnection(param[0], param[1], param[2]);
	        Prompt p = new Prompt(connectedPrompt,false);
	        p.setCommandEnd(true);
	        shellConnection.addPrompts(new Prompt[]{p});
	        shellConnection.init();
        }
        else if (connectMode.compareTo("RAWTELNET") == 0)
        {
	        report.report("connect by raw telnet");
	        shellConnection = new RawTelnetCliConnection(param[0], param[1], param[2], port);
	        //Prompt p = new Prompt(connectedPrompt,false);
	        //p.setCommandEnd(true);
	        //shellConnection.addPrompts(new Prompt[]{p});
	        shellConnection.init();
        }
        else if (connectMode.compareTo("RS232") == 0)
        {
	        report.report("connect by RS232");
	        shellConnection = new RawRS232CliConnection(param[0], port);
			//ArrayList<Prompt> prompts = new ArrayList<Prompt>();
	        //Prompt p = new Prompt();
	        //p.setPrompt("login:");
			//p.setStringToSend("root");
			//prompts.add(p);
			//p = new Prompt();
			//p.setPrompt("~#");
			//p.setCommandEnd(true);
			//prompts.add(p);
			//shellConnection.setPrompts(prompts.toArray(new Prompt[prompts.size()]));
	        shellConnection.init();
        }
        else if (connectMode.compareTo("RAWRS232") == 0)
        {
	        report.report("connect by raw RS232");
	        shellConnection = new RawRS232TestConnection(param[0], port);
	        report.report("#" + shellConnection.isUseBuffer() + "#");
	        //Prompt p = new Prompt(connectedPrompt,false);
	        //p.setCommandEnd(true);
	        //shellTestConnection.addPrompts(new Prompt[]{p});
	        shellConnection.init();
        }
		
	}
	public void init() throws Exception {
        super.init();
        report.report("In init method");
        //shellConnection.init();
    }
	
    public void close(){
        report.report("In close method");
        super.close();
	}
    
    public Object clone() throws CloneNotSupportedException {
    	return super.clone();
    }
	
    public void rawCommand(String rawCommand, String prompt) 
        throws Exception {
    	RawCliCommand command = 
	        new RawCliCommand(rawCommand);
	    command.setPromptString(prompt);
        shellConnection.handleCliCommand(rawCommand, command);
        setTestAgainstObject(command.getResult());
	}

	public void rawCommandNoTimeOut(String rawCommand, String prompt) 
	        throws Exception {
		RawCliCommand command = 
		        new RawCliCommand(rawCommand);
		    command.setPromptString(prompt);
		    command.setTimeout(2*3600*1000);
		    shellConnection.handleCliCommand(rawCommand, command);

	        setTestAgainstObject(command.getResult());
	}
	
	public void rawCommand(String rawCommand, String prompt, boolean rawTest) 
	        throws Exception {
		    RawCliCommand command = 
		        new RawCliCommand(rawCommand);
		    command.setPromptString(prompt);
		    shellConnection.handleCliCommand(rawCommand, command);
	        setTestAgainstObject(command.getResult());
	}

	public void rawCommandNoTimeOut(String rawCommand, String prompt, boolean rawTest) 
		        throws Exception {
		        RawCliCommand command = 
			        new RawCliCommand(rawCommand);
			    command.setPromptString(prompt);
			    command.setTimeout(2*3600*1000);
			    shellConnection.handleCliCommand(rawCommand, command);

		        setTestAgainstObject(command.getResult());
	}
}


