package org.jsystem.quickstart;

import com.aqua.sysobj.conn.CliConnectionImpl;
import org.apache.commons.net.telnet.*;
import com.aqua.sysobj.conn.Position;

import java.util.ArrayList;
import java.io.*;

import systemobject.terminal.Prompt;
import systemobject.terminal.VT100FilterInputStream;

public class SwitchCliConnection extends CliConnectionImpl {
	private String host;
    private String userName;
    private String password;
    private int protocalPort;
    
    private TelnetClient telnet;  
    private InputStream in;  
    private PrintStream out;  
    private char prompt = '>';  
    
	public void init() throws Exception {
        super.init();
        report.report("In init method");
        telnet = new TelnetClient();
        
    }

    public void close(){
         super.close();
         report.report("In close method");
     }
    
    public void connection(String host,String user,String password, int port) {  
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

/*
	public SwitchCliConnection(){
		setDump(true);
		setUseTelnetInputStream(true);
		setProtocol("telnet");
	}

	public void setparam(String host,String user,String password, int port){
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
*/
	@Override
	public Position[] getPositions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
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

	public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getProtocalPort() {
        return protocalPort;
    }

    public void setProtocalPort(int protocalPort) {
        this.protocalPort = protocalPort;
    }    
}
