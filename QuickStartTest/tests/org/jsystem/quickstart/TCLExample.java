package org.jsystem.quickstart;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;

import jsystem.extensions.analyzers.text.FindText;
//import com.aqua.sysobj.conn.CliApplication;
import jsystem.sysobj.scripting.tcl.ShellCommand;
import jsystem.sysobj.scripting.tcl.TclShell;
import jsystem.sysobj.scripting.tcl.TclShellLocal;

import junit.framework.SystemTestCase;

public class TCLExample extends SystemTestCase {

	TclShell shell;
	
	@Before
	public void setUp() throws Exception {
		shell = new TclShellLocal(new File("C:/Program Files (x86)/Ixia/Tcl/8.4.14.0/bin/tclsh84.exe"));
		shell.launch();
	}
	
	@Test
	public void testActivateScript() throws Exception {
		shell.source(new FileInputStream("C:/Users/IBM_ADMIN/Desktop/data/eclipse/tcl/exampletclscript.tcl"));

		FindText textFinder = new FindText("hello tcl");
		textFinder.setTestAgainst(shell.getResults());
		textFinder.analyze();
		assertTrue(textFinder.getStatus());
		
		ShellCommand command = new ShellCommand("PrintText",new String[]{"Hello TCL procedure"});
		shell.executeCommand(command);
		textFinder = new FindText("Hello TCL procedure");
		textFinder.setTestAgainst(shell.getResults());
		textFinder.analyze();
		assertTrue(textFinder.getStatus());
		
		assertEquals("", command.getReturnValue());
		assertEquals("NONE", command.getErrorCode());
		assertEquals(null,command.getErrorString());
		
	}
	
	@Test
	public void ActivateScriptStMain() throws Exception {
		shell.source(new FileInputStream("C:/Users/IBM_ADMIN/Desktop/data/eclipse/tcl/scripts/tc/st_main.tcl"));

		FindText textFinder = new FindText("hello tcl");
		textFinder.setTestAgainst(shell.getResults());
		textFinder.analyze();
		assertTrue(textFinder.getStatus());
		
		ShellCommand command = new ShellCommand("PrintText",new String[]{"Hello TCL procedure"});
		shell.executeCommand(command);
		textFinder = new FindText("Hello TCL procedure");
		textFinder.setTestAgainst(shell.getResults());
		textFinder.analyze();
		assertTrue(textFinder.getStatus());
		
		assertEquals("", command.getReturnValue());
		assertEquals("NONE", command.getErrorCode());
		assertEquals(null,command.getErrorString());
		
	}
	
	public void tearDown(){
		shell.exit();
	}
}
