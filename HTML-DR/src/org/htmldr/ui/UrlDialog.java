package org.htmldr.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.htmldr.net.WebSourceRetriever;
import org.htmldr.parser.HypertextLinkParser;

@SuppressWarnings("serial")
public class UrlDialog extends JFrame {
	private JPanel panURLinput = new JPanel();
	private JLabel lblTypeURL = new JLabel("Type a URL :");
	private JTextField txtURL = new JTextField();
	private JTextArea textCode = new JTextArea();
	private JPanel panListLinks = new JPanel();
	private JList<String> listLinks = new JList<String>();
	
	private JButton btnGetSource = new JButton("Get source");
	private JButton btnParse = new JButton("Parse");
	
	public UrlDialog() {
		setTitle("Set a URL ...");
		setSize(650, 470);
		setFont(new Font("Segoe UI Semibold", 0, 12)); 
		
		getContentPane().add(panURLinput, BorderLayout.NORTH);
		
		txtURL.setText("http://www.google.fr");
		txtURL.setColumns(30);
		
		panURLinput.add(lblTypeURL);
		panURLinput.add(txtURL);
		
			
		textCode.setFont(new Font("Monospaced", Font.PLAIN, 13));
		
		getContentPane().add(new JScrollPane(textCode), BorderLayout.CENTER);
		
		
		btnGetSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String url = txtURL.getText();
					WebSourceRetriever retriever = new WebSourceRetriever(url);
					textCode.setText(
							retriever.getSourceAsString()
					);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url = txtURL.getText();
				
				listLinks.setModel(new AbstractListModel<String>() {
					ArrayList<String> values = new HypertextLinkParser(url).getListLinks();
					public int getSize() {
						return values.size();
					}
					public String getElementAt(int index) {
						return values.get(index);
					}
				});
			}
		});
		
		panURLinput.add(btnGetSource);
		panURLinput.add(btnParse);
		
		getContentPane().add(panListLinks, BorderLayout.EAST);
		
		panListLinks.add(new JScrollPane(listLinks), BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		
		try { 
				for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) { 
					if ("Nimbus".equals(info.getName())) { 
						javax.swing.UIManager.setLookAndFeel(info.getClassName()); 
						break; 
					} 
			    } 
		} catch (ClassNotFoundException ex) { 
			java.util.logging.Logger.getLogger(UrlDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); 
	    } catch (InstantiationException ex) { 
	        java.util.logging.Logger.getLogger(UrlDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); 
	    } catch (IllegalAccessException ex) { 
	        java.util.logging.Logger.getLogger(UrlDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); 
	    } catch (javax.swing.UnsupportedLookAndFeelException ex) { 
	        java.util.logging.Logger.getLogger(UrlDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); 
	    } 

		java.awt.EventQueue.invokeLater(new Runnable() { 
			public void run() { 
				new UrlDialog().setVisible(true); 
			} 
		}); 


	}

}
