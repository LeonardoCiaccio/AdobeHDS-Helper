package helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class Worker extends SwingWorker<Integer, String> {
	
	private String command;
	private JTextArea txtTerminal;
	private JButton btnStart;
	private JLabel lblTerminal;
	
	public Worker(String command, JTextArea txtTerminal, JButton btnStart, JLabel lblTerminal){
		
		this.command = command;
		this.txtTerminal = txtTerminal;
		this.btnStart = btnStart;
		this.lblTerminal = lblTerminal;

	}

	 @Override
	  protected Integer doInBackground() throws Exception {
		 
		 Process proc = Runtime.getRuntime().exec(this.command);
		
		 this.lblTerminal.setText("Please wait, processing ...");
		 this.txtTerminal.append("\r\n" + this.command + "\r\n");
		 this.btnStart.setEnabled(false);
			
	     BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

	     String line = "";
	     this.txtTerminal.append("\r\n\r\n");
	     
	     while((line = reader.readLine()) != null) {
	        	
	    	 publish(line + "\r\n");
	        
	     }

	     proc.waitFor();  
	     
	     this.lblTerminal.setText("");
	     this.btnStart.setEnabled(true);
		 
		 return 1;
	 }
	 
	 @Override
	  protected void process(final List<String> chunks) {
		 
		 for (final String string : chunks) {
			 
		      this.txtTerminal.append(string);
		      this.txtTerminal.setCaretPosition(this.txtTerminal.getDocument().getLength());
		 
		 }
		 
	 }
	
}
