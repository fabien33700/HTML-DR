package org.htmldr.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import org.htmldr.net.WebSourceRetriever;
import org.htmldr.parser.HypertextLinkParser;

import javax.swing.JList;
import javax.swing.AbstractListModel;

public class UrlDialog extends JFrame {
	private JTextField txtHttplocalhost;
	public UrlDialog() {
		setTitle("Set a URL ...");
		setSize(650, 470);
		
		JList<String> list = new JList<String>();
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Type a URL :");
		panel.add(lblNewLabel);
		
		txtHttplocalhost = new JTextField();
		txtHttplocalhost.setText("http://www.google.fr");
		panel.add(txtHttplocalhost);
		txtHttplocalhost.setColumns(30);
			
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Get source");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String url = txtHttplocalhost.getText();
					WebSourceRetriever retriever = new WebSourceRetriever(url);
					textArea.setText(
							retriever.getSourceAsString()
					);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Try parse");
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url = txtHttplocalhost.getText();
				
				list.setModel(new AbstractListModel<String>() {
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
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		
		
		getContentPane().add(list, BorderLayout.SOUTH);

		
		
	}

}
