package org.jsystem.automationProj;

import org.junit.Before;
import org.junit.Test;

import junit.framework.SystemTestCase4;


public class MyASCIITransform extends SystemTestCase4 {
    private ASCIItransform transform;
	
	@Before
	public void before() throws Exception{
		transform =  
	              (ASCIItransform)system.getSystemObject("my_transfer");  

	}
	
	@Test
	public void transformHexTemp() throws Exception {
		report.report("transformHex");
		transform.TransformHexToString("temp.txt");
	}
	
	@Test
	public void transformHexTemp1() throws Exception {
		report.report("transformHex");
		transform.TransformHexToString("temp1.txt");
	}
	
	@Test
	public void transforHtmlToCTemp() throws Exception {
		report.report("transHtmlToC");
		transform.HtmlToC("temp.txt");
	}
	
	@Test
	public void ParsePropertyBuild() throws Exception {
		report.report("Parse Build");
		transform.ParseProperty("build");
	}
}
