/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rijecnik;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author moster-source
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class Gui extends javax.swing.JFrame {
    
    public void DoCro()
            
            /**
             * This method populate right text area, it searches
             * croatian database.
             */
    {     
        String strEngLn;                // variable that holds one line from dictionary, eng and cro
        String strEng1 = "";            //variable that holds english word
        String strCro1 = "";            //variable that holds croatian word
        String strEngDouble = "";       // variable that holds last found word because there are multiple same words in dictionary
        String strFinish = "";          //variable that holds result
        boolean blnFirstTime = true;    //if run for first time, cosmetic...
        
        jTextArea2.setText("");
        
        if (jTextField1.getText().length() > 0)
        {
        //word to be found
        String trazi = jTextField1.getText().toLowerCase(); 
 
            /**
             * Leftovers from previous solutions
             * 
            * try (Scanner scanner = new Scanner(new File("C:\\Users\\virt7\\Documents\\NetBeansProjects\\rijecnik\\src\\main\\resources\\EHCro-ansi.txt")))
            * try (Scanner scanner = new Scanner(new File(Gui.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath()+ "EHEng-ansi.txt"))
            * try (Scanner scanner = new Scanner(new File("./EHCro-ansi.txt")))
            * try (Scanner scanner = new Scanner(new File("EHCro-ansi.txt")))
            * while (scanner.hasNext()) 
            * strEngLn = scanner.nextLine().toLowerCase(); 
            */
            
            //Load databases directly from .jar
             InputStream inp = getClass().getClassLoader().getResourceAsStream("EHCro-ansi.txt");
             BufferedReader rd = new BufferedReader(new InputStreamReader(inp));
             
             try 
             {
                while (null != (strEngLn = rd.readLine()))  //first line from dictionary (eng and cro together separated with tab 
                {
                   //find location of TabKey which separates translation
                   int a = strEngLn.indexOf("\t");
                   if(a != -1)
                   {
                       // find out if wonted word is founded in current line after tab 
                       boolean b = strEngLn.toLowerCase().startsWith(trazi, a+1);         
                            if (b)
                            {
                                //separate english and croatian words
                                strEng1 = strEngLn.substring(0,a);         
                                strCro1 = strEngLn.substring(a+1);         
                            
                                //if cro word is same as last one from the loop
                                //there are possibly many same words with different meaning
                                if (strEngDouble.equals(strCro1))          
                                {
                                    //if cro word is same like last one, just add cro word on top
                                    strFinish = strFinish + ", " + strCro1; 
                                }
                                else
                                {
                                    //if everything was run for the first time, cosmetic polish to not have first line empty in textbox
                                    if (blnFirstTime)                      
                                    {
                                        //if original cro word was found, make whole line eng and cro words together
                                        strFinish = strFinish + strCro1 + " - " + strEng1;  
                                        blnFirstTime = false;          
                                    }
                                else
                                    {
                                    //if cro word was original, but not first time running search add new line
                                    strFinish = strFinish + "\n" + strCro1 + " - " + strEng1;  
                                    }
                            }
                                //copy last founded word for comparison in next loop if there is duplicates
                                strEngDouble = strCro1;            
                            }
                    }
              
                }
                
                //finally make everything appear, and scroll text to the top
                jTextArea2.setText(strFinish);  
                jTextArea2.setCaretPosition(0); 
            }
             
            //catch (FileNotFoundException e) 
            catch(IOException e)
            {
                //show error in text area
                jTextArea2.setText(e.getMessage());
                //JOptionPane.showMessageDialog(JFrame, e.getMessage());
            }
    }
    }
    
    public void DoEng()
            
             /**
             * This method is almost same as DoCro, it populates
             * left text area.
             * In future it will be combined
             */
    
    {     
        String strEngLn;
        String strEng1 = "";
        String strEng2 = "";
        String strEngDouble = "";
        String strFinish = "";
        boolean blnFirstTime = true;
        
        jTextArea1.setText("");
        if (jTextField1.getText().length() > 0)                      //if search text is not empty, only then search
        {
        String trazi = jTextField1.getText().toLowerCase();
 
        //try (Scanner scanner = new Scanner(new File("C:\\Users\\virt7\\Documents\\NetBeansProjects\\rijecnik\\src\\main\\resources\\EHEng-ansi.txt")))
        //try (Scanner scanner = new Scanner(new File(Gui.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath()+ "EHEng-ansi.txt"))
        //try (Scanner scanner = new Scanner(new File("./EHEng-ansi.txt")))
        //try (Scanner scanner = new Scanner(new File("EHEng-ansi.txt")))
        InputStream inp = getClass().getClassLoader().getResourceAsStream("EHEng-ansi.txt");
             BufferedReader rd = new BufferedReader(new InputStreamReader(inp));
             try
        {
            
            while (null != (strEngLn = rd.readLine())) 
            {
                //strEngLn = scanner.nextLine().toLowerCase();                         //first line from dictionary (eng and cro together separated with tab
        
                   boolean b = strEngLn.toLowerCase().startsWith(trazi);             // find out if current line begins with wanted word
                   if (b)
                   {
                        int a = strEngLn.indexOf("\t");                //find location of tab key which eng and cro words are separated with
                        if (a != -1)                                   //if there is tab character, separate eng and cro word in two string variables
                        {
                            strEng1 = strEngLn.substring(0,a);         //english word
                            strEng2 = strEngLn.substring(a+1);         //cro word
                            
                            if (strEngDouble.equals(strEng1))          //if eng word is same as last one from the loop
                            {
                               strFinish = strFinish + ", " + strEng2; //if eng word is same like last one, just add cro word on top
                            }
                            else
                            {
                                if (blnFirstTime)                      //if everything was run for the first time, cosmetic polish to not have first line empty in textbox
                                {
                                    strFinish = strFinish + strEng1 + " - " + strEng2;  //if original eng word was found, make whole line eng and cro words together
                                    blnFirstTime = false;               //disable variable for discovering first time run
                                }
                                else
                                {
                                strFinish = strFinish + "\n" + strEng1 + " - " + strEng2;  //if eng word was original, but not first time running search add new line
                                }
                            }
                            strEngDouble = strEng1;                     //variable that copy last eng word to see is it the same like last one, because database has duplicates
                        }
                   }
              }
            jTextArea1.setText(strFinish);  //finally make everything appear
            jTextArea1.setCaretPosition(0); //scroll text to the top
         }
            //catch (FileNotFoundException e)
            catch(IOException e)
            {
                jTextArea1.setText(e.getMessage());
                //print error in text area
            }
        }
    }


    /**
     * Creates new form Gui
     */
    public Gui() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("English-Croatian dictionary");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jButton1.setText("Search");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(237, 237, 237)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            // TODO add your handling code here:
            System.exit(0);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DoEng();
        DoCro();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            DoEng();
            DoCro();
        }
    }//GEN-LAST:event_jTextField1KeyPressed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
