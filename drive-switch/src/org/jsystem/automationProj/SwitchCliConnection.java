package org.jsystem.automationProj;

import com.aqua.sysobj.conn.CliCommand;
import com.aqua.sysobj.conn.CliConnectionImpl;

import org.apache.commons.net.telnet.*;

import com.aqua.sysobj.conn.Position;

import java.util.ArrayList;
import java.io.*;

import systemobject.terminal.Prompt;
import systemobject.terminal.VT100FilterInputStream;

public class SwitchCliConnection extends CliConnectionImpl {
	
    private TelnetClient telnet;
    private InputStream in;  
    private PrintStream out;  
    private char prompt = '>';  

	public SwitchCliConnection(String host,String user,String password, int port){
		this();
		report.report("connection:" + host + " " + user + " " + password + " " + port);
		super.setPort(port);
		super.setUser(user);
		super.setPassword(password);
		super.setHost(host);
		super.setProtocol("telnet");
	}
	
	public void init() throws Exception {
        super.init();
 		report.report("In init method");
    }

    public void close(){
    	 report.report("In close method");
         super.close();
     }
    
	public void connect() throws Exception {
		report.report("In connect method");
		ArrayList<Prompt> prompts = new ArrayList<Prompt>();
        Prompt p = new Prompt();
		//p.setPrompt("login:");
		//p.setStringToSend(getUser());
		//prompts.add(p);
		//p = new Prompt();
		p.setPrompt("Password:");
		p.setStringToSend("zebra");
		prompts.add(p);
		//report.report(p.getPrompt());
		//report.report("#In connect method");
		//p = new Prompt();
		//p.setPrompt("iscli):");
		//p.setStringToSend("iscli");
		//prompts.add(p);
		p = new Prompt();
		p.setPrompt(">");
		p.setCommandEnd(true);
		prompts.add(p);
		//report.report(p.getPrompt());
		//report.report("##In connect method");
		super.setPrompts(prompts.toArray(new Prompt[prompts.size()]));
		//Prompt[] qr = prompts.toArray(new Prompt[prompts.size()]);
		//for (Prompt q : qr) {
		//	report.report(q.getPrompt());
		//}
		//Prompt[] pr = getPrompts();
		//for (Prompt q : pr) {
		//	report.report(q.getPrompt());
		//}
		//report.report("###In connect method");
		super.connect();
	}
	
	public void rawCommand(String rawCommand, String prompt) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand(rawCommand);
		    command.setPromptString(prompt);
		    super.handleCliCommand(rawCommand, command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void rawCommandNoTimeOut(String rawCommand, String prompt) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand(rawCommand);
		    command.setPromptString(prompt);
		    command.setTimeout(2*3600*1000);
		    super.handleCliCommand(rawCommand, command);
	        setTestAgainstObject(command.getResult());
	}
	
    public void connection(String host,String user,String password, int port) {  
    	
    	report.report("connection:" + host + " " + user + " " + password + " " + port);
		super.setPort(port);
		super.setUser(user);
		super.setPassword(password);
		super.setHost(host);
		super.setProtocol("telnet");
		
        try {
            telnet.connect(host, 23);  
            in = telnet.getInputStream();  
            out = new PrintStream(telnet.getOutputStream());  
            readUntil("assword: ");  
            write(password); 
            readUntil("iscli):");  
            write("iscli"); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public String readUntil(String pattern) {  
        try {  
            char lastChar = pattern.charAt(pattern.length() - 1);  
            StringBuffer sb = new StringBuffer();  
            boolean found = false;  
            char ch = (char) in.read();  
            while (true) {  
                System.out.print(ch);  
                sb.append(ch);  
                if (ch == lastChar) {  
                    if (sb.toString().endsWith(pattern)) {  
                        return sb.toString();  
                    }  
                }  
                ch = (char) in.read();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    
    public void write(String value) {  
        try {  
            out.println(value);  
            out.flush();  
            System.out.println(value);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    public String sendCommand(String command) {  
        try {  
            write(command);  
            return readUntil(prompt + " ");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    
    public void disconnect() {  
        try {  
            telnet.disconnect();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }   


	public SwitchCliConnection(){
		setDump(true);
		setUseTelnetInputStream(true);
		setProtocol("telnet");
	}

	public Position[] getPositions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Prompt[] getPrompts() {
		ArrayList<Prompt> prompts = new ArrayList<Prompt>();
		Prompt p = new Prompt();
		//p.setPrompt("login:");
		//p.setStringToSend(getUser());
		//prompts.add(p);
		//p = new Prompt();
		p.setPrompt("password:");
		p.setStringToSend(getPassword());
		prompts.add(p);
		//p = new Prompt();
		//p.setPrompt("iscli):");
		//p.setStringToSend("iscli");
		//prompts.add(p);
		p = new Prompt();
		p.setPrompt(">");
		p.setCommandEnd(true);
		prompts.add(p);
		return prompts.toArray(new Prompt[prompts.size()]);
	}
    
}
