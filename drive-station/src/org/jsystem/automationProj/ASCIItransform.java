package org.jsystem.automationProj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import jsystem.framework.system.SystemObjectImpl;

public class ASCIItransform extends SystemObjectImpl {
	private String localPath;
	private String property;
	
	public void init() throws Exception {
        super.init();
        report.report("In init method");       
    }

    public void close(){
         super.close();
         report.report("In close method");
    }
    
    public void TransformHexToString(String filename) throws IOException {        
        BufferedReader in = null;
        BufferedWriter out = null;
        String stringBuffer;
        String subString;
        int index = 0;
        int temp;
        char tempc;
        
		in = new BufferedReader(new FileReader(getLocalPath() + filename));
		out = new BufferedWriter(new FileWriter(getLocalPath() + "outputTemp.txt"));
		if (in != null) {
			for (stringBuffer = in.readLine(); stringBuffer != null; stringBuffer = in.readLine()) {
        		index = stringBuffer.indexOf("0x");
        		index = stringBuffer.indexOf("0x", index+2);
        		for (int i=0; i<4; i++) {
        		    subString = stringBuffer.substring((index+2), (index+10));
        		    for (int j=0; j<4; j++) {
        		        temp = Integer.parseInt(subString.substring(2*j, 2*j+2), 16);
        		        tempc = (char)temp;
        		        out.write(tempc);
        		    }
        		index = stringBuffer.indexOf("0x", index+2);
        		}
        		out.write("\n");
			}
		}
		in.close();
		out.close();
    }
    
    public void ParseProperty(String filename) throws IOException {        
        BufferedReader in = null;
        BufferedWriter out = null;
        String stringBuffer;
        
		in = new BufferedReader(new FileReader(getLocalPath() + filename));
		out = new BufferedWriter(new FileWriter(getLocalPath() + "outputTemp.txt"));
		if (in != null) {
			for (stringBuffer = in.readLine(); stringBuffer != null; stringBuffer = in.readLine()) {
        		CharSequence sequence3 = getProperty();
        		
				if (stringBuffer.contains("HW_"))
				{
					out.write(stringBuffer);
					out.write("\n");
				}
				
				if (stringBuffer.contains("fi"))
				{
					out.write(stringBuffer);
					out.write("\n");
				}
				
				if (stringBuffer.contains(";;"))
				{
					out.write(stringBuffer);
					out.write("\n");
				}
				
				if (stringBuffer.contains("esac"))
				{
					out.write(stringBuffer);
					out.write("\n");
				}
				
				if (stringBuffer.contains(sequence3))
				{
					out.write(stringBuffer);
					out.write("\n");
				}
				
			}
		}
		in.close();
		out.close();
    }
    
    public void HtmlToC(String filename) throws IOException {        
        BufferedReader in = null;
        BufferedWriter out = null;
        String stringBuffer;
        String stringTemp;
        String stringToBuffer;
        
		in = new BufferedReader(new FileReader(getLocalPath() + filename));
		out = new BufferedWriter(new FileWriter(getLocalPath() + "outputTemp.txt"));
		if (in != null) {
			for (stringBuffer = in.readLine(); stringBuffer != null; stringBuffer = in.readLine()) {
        		
				report.report(stringBuffer);
				stringTemp = stringBuffer.replaceAll("\"", "\\\\\"");
				stringBuffer = stringTemp.replaceAll("%", "%%");
        		report.report(stringTemp);
        		stringToBuffer = "websWrite(wp, T(\"" + stringBuffer + "  \\r\\n\"));";
        		report.report(stringToBuffer);
        		out.write(stringToBuffer);
				out.write("\n");
						
			}
		}
		in.close();
		out.close();
    }
    
    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
    
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
    
}
