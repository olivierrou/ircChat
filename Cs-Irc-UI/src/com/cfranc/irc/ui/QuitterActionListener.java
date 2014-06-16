package com.cfranc.irc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class QuitterActionListener implements ActionListener {


    public void actionPerformed(ActionEvent e) {
    	SimpleChatClientApp.quitApp();
		
    }
    
}
