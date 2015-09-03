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

import org.htmldr.net.WebSourceRetriever;

public class UrlDialog extends JFrame {
	private JTextField textField;
	public UrlDialog() {
		setTitle("Set a URL ...");
		setSize(580, 326);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Type a URL :");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(30);
			
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Get source");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					textArea.setText(
							WebSourceRetriever.getSource(textField.getText())
					);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		panel.add(btnNewButton);

		
		
	}

}
