package helper;

/*
 * Coded by Leonardo Ciaccio from Grab Any Media to AdobeHDS.php script
 * 
 * 
 * @Today 		: 11.09.2014
 * @Author		: Leonardo Ciaccio
 * @WebSite 	: Grab Any Media
 * @Contact		: grabanymedia@altervista.org
 * 
 * @PS			: If you want more options please comment on official post
 * 					http://grabanymedia.altervista.org/adobehds-php-helper/
 * 
 * */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Helper extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hVersion = "1.0.0.3"; 
	private String pSep 	= File.separator;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtManifest;
	private JLabel lblOptions;
	private JLabel lblAuth;
	private JTextField txtAuth;
	private JLabel lblOutputFileName;
	private JTextField txtOutput;
	private JTextField txtUA;
	private JTextField txtReferer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Helper frame = new Helper();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Helper() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Helper.class.getResource("/resource/Grab_Any_Media48_off.png")));
		setTitle("Grab Any Media | AdobeHDS.php Helper | " + hVersion);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblOptions = new JLabel("Options :");
		lblOptions.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblAuth = new JLabel("Auth");
		
		txtAuth = new JTextField();
		txtAuth.setToolTipText("Optional : authentication string for fragment requests");
		txtAuth.setColumns(10);
		txtAuth.addMouseListener(new ContextMenuMouseListener());
		
		final JCheckBox chkDelete = new JCheckBox("Delete");
		chkDelete.setToolTipText("Optional : delete fragments after processing");
		chkDelete.setSelected(true);
		
		JLabel lblUserAgent = new JLabel("User Agent");
		
		txtUA = new JTextField();
		txtUA.setEnabled(false);
		txtUA.setText("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36");
		txtUA.setToolTipText("Optional : user-Agent to use for emulation of browser requests");
		txtUA.setColumns(10);
		txtUA.addMouseListener(new ContextMenuMouseListener());
		
		final JComboBox cmbUA = new JComboBox();
		
		cmbUA.setModel(new DefaultComboBoxModel(new String[] {"Chrome", "Firefox", "IE", "Other"}));
		
		JLabel lblReferer = new JLabel("Referer");
		
		txtReferer = new JTextField();
		txtReferer.setToolTipText("Optional : referer to use for emulation of browser requests");
		txtReferer.setColumns(10);
		txtReferer.addMouseListener(new ContextMenuMouseListener());
		
		final JButton btnStart = new JButton("START");
		btnStart.setToolTipText("Run AdobeHDS script");
		
		JButton btnHelp = new JButton("?");
		btnHelp.setToolTipText("Go to website for more information");
		
		
		JButton btnReset = new JButton("Reset");
		btnReset.setToolTipText("Reset all field");
		
		JSeparator separator = new JSeparator();
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JLabel lblManifest = new JLabel("Manifest or Fragment");
		
		txtManifest = new JTextField();
		txtManifest.setToolTipText("Require : manifest file for downloading or fragment downloaded without last number");
		txtManifest.setColumns(10);
		txtManifest.addMouseListener(new ContextMenuMouseListener());
		
		lblOutputFileName = new JLabel("Output file name");
		
		txtOutput = new JTextField();
		txtOutput.setToolTipText("Require : filename to use for output file, all file in 'output' folder");
		txtOutput.setColumns(10);
		txtOutput.addMouseListener(new ContextMenuMouseListener());
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(txtManifest, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblManifest, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOutputFileName, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtOutput, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblOutputFileName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtOutput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblManifest)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtManifest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(23))
		);
		panel.setLayout(gl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		
		final JTextArea txtTerminal = new JTextArea();
		txtTerminal.setToolTipText("Log terminal");
		txtTerminal.setFont(new Font("Verdana", Font.BOLD, 10));
		txtTerminal.setText(" [Helper] Coded by Leonardo Ciaccio | Grab Any Media for AdobeHDS.php script");
		txtTerminal.addMouseListener(new ContextMenuMouseListener());
		
		scrollPane.setViewportView(txtTerminal);
		
		final JLabel lblTerminal = new JLabel("");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblUserAgent, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblReferer, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtReferer, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAuth, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAuth, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
					.addGap(6)
					.addComponent(chkDelete, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(cmbUA, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(txtUA, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTerminal, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblOptions, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnHelp, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblOptions)
					.addGap(18)
					.addComponent(lblUserAgent)
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(cmbUA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblReferer)
							.addComponent(txtReferer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAuth)
							.addGap(3)
							.addComponent(txtAuth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addComponent(chkDelete)))
					.addGap(26)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnReset)
							.addComponent(btnHelp))
						.addComponent(btnStart))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTerminal)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		// All events **************************************************************************************************************************
		
		cmbUA.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				String selectedValue = (String)cmbUA.getSelectedItem();
				txtUA.setEnabled(false);
				
				switch(selectedValue.toUpperCase()){
					
					case "CHROME":
						txtUA.setText("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36");
						break;
						
					case "FIREFOX":
						txtUA.setText("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");
						break;
						
					case "IE":
						txtUA.setText("Mozilla/5.0 (compatible; MSIE 10.6; Windows NT 6.1; Trident/5.0; InfoPath.2; SLCC1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 2.0.50727) 3gpp-gba UNTRUSTED/1.0");
						break;
						
					default:
						txtUA.setEnabled(true);
				
				}
				
				
			}
		});
		
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				txtManifest.setText("");
				txtOutput.setText("");
				txtAuth.setText("");
				txtReferer.setText("");	
				chkDelete.setSelected(true);
				cmbUA.setSelectedIndex(0);
				txtTerminal.setText(" [Helper] Coded by Leonardo Ciaccio | Grab Any Media for AdobeHDS.php script\r\n");
				
			}
		});
		
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String manifest = txtManifest.getText();
				String output = txtOutput.getText().replaceAll("\\s+",".");
				String auth = txtAuth.getText();
				String referer = txtReferer.getText();
				String UA = txtUA.getText();
				String adobeLibs = "libs" + pSep + "AdobeHDS.php";
				
				File myPHPLibs = new File(adobeLibs);
				File folder = new File("output");
				
				if(manifest.length() < 1 || output.length() < 1){
					
					JOptionPane.showMessageDialog(null,"Require manifest link or fragment file and output name file es:GrabAnyMedia.flv", "Error message:", JOptionPane.ERROR_MESSAGE);
										
				}else if(!myPHPLibs.exists()){
					
					JOptionPane.showMessageDialog(null,"Require AdobeHDS.php in Libs folder, not found !", "Error message:", JOptionPane.ERROR_MESSAGE);
					
				}else{
					
					try{
						
						txtTerminal.setText(" [Helper] Coded by Leonardo Ciaccio | Grab Any Media for AdobeHDS.php script\r\n");
						
						String command = "";
						
						if(manifest.indexOf("http") == 0){
							
							command = "php " + adobeLibs + " --manifest \"" + manifest + "\" --outfile \"output" + pSep + output + "\"";
						
						}else{
							
							command = "php " + adobeLibs + " --fragments \"fragments" + pSep + manifest + "\" --outfile \"output" + pSep + output + "\"";
							
						}
						
						if(chkDelete.isSelected())command+= " --delete";
						if(auth.length() > 0)command+=" --auth \"" + auth + "\"";
						if(referer.length() > 0)command+= " --referrer \"" + referer + "\"";
						if(UA.length() > 0)command+= " --useragent \"" + UA + "\"";
						
						if(!folder.exists()){
							
							folder.mkdirs();
							
						}
						
						Worker lab = new Worker(command, txtTerminal, btnStart, lblTerminal);
						lab.execute(); 
						
					}catch(Exception e){
						txtTerminal.append(e.getMessage() + "\r\n");
					}
					
				}				
				
			}
		});
		
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try 
		        {
		            Desktop.getDesktop().browse(new URL("http://grabanymedia.altervista.org/adobehds-php-helper/").toURI());
		        }           
		        catch (Exception e) {}
				
			}
		});
		
		//*********************************************************************************************************************************************
		
	}
}
