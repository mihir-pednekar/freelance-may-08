///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package msgDrivenBean;
//
//import java.io.IOException;
//import java.util.logging.FileHandler;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.logging.SimpleFormatter;
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.MessageDriven;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.TextMessage;
//
///**
// *
// * @author mihir
// */
//@MessageDriven(activationConfig = {
//    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/FreelanceDestQueue")
//    ,
//        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
//})
//public class FreelanceMsgDrivenBean implements MessageListener {
//
//    private Logger logger = Logger.getLogger(FreelanceMsgDrivenBean.class.getName());
//    private FileHandler fh; 
//    
//    public FreelanceMsgDrivenBean() {
//    }
//    
//    @Override
//    public void onMessage(Message message) {
//        try {
//            TextMessage txtMsg = (TextMessage) message;
//            System.out.println(System.getProperty("user.dir")+"/FreelanceServerLogFile.log");
//            fh = new FileHandler(System.getProperty("user.dir")+"/FreelanceServerLogFile.log");  
//            logger.addHandler(fh);
//            SimpleFormatter formatter = new SimpleFormatter();  
//            fh.setFormatter(formatter);  
//            logger.info(txtMsg.getText());  
//        } 
//        catch (JMSException | SecurityException | IOException ex) {
//            logger.log(Level.SEVERE, null, ex);
//        }
//    }
//    
//}
