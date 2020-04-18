package spaceinva;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Sebastian
 */
public class FrmSpace extends javax.swing.JFrame implements KeyListener {
    

    int even = 1;
    int locationX = 20;
    int locationY = 90;
    int sign = 1; 
    int velocidad=2;
    int disparar=0;
    int muerte=0;
    int rojo=1;
    int habilitar=1;
    int filas,columnas;
    int colision,con,auxiliarx,auxiliary,espacio,i,y,aux,puntaje,aleatorio,contador,contador2,invaderdisparar,con2,espacioy,tope;
    Space tank = new Space(100,595);
    Space Invader = new Space(-50,60);
    Space shot = new Space(tank.getDrawLocationX()+16,tank.getDrawLocationY()-15);
    Space invadershot = new Space(locationX+10,locationY);
    int mat[][]=new int [11][10];
    int vec[]=new int [13];
    
    Space invader1[][] = new Space[11][2];
    Space invader2[][]= new Space[11][2];
    Space invader3[]= new Space[11];
    Space escudo[]= new Space[13];
    
    BufferedImage imgTank=null;
    
    BufferedImage imgShot=null;
    BufferedImage imgInvadershot=null;
    
    BufferedImage imgInvader1a=null;
    BufferedImage imgInvader1b=null;
    
    BufferedImage imgInvader2a=null;
    BufferedImage imgInvader2b=null;
    
    BufferedImage imgInvader3a=null;
    BufferedImage imgInvader3b=null;
    
    BufferedImage imgExplosion=null;
    BufferedImage imgInvader=null;
    
    BufferedImage imgUno=null;
    BufferedImage imgDos=null;
    BufferedImage imgTres=null;
    //consntructor
    public FrmSpace() {
        initComponents();
        addKeyListener(this);
        this.getContentPane().setBackground(Color.WHITE);
        for(int i=0;i<=10;i++)
        {
            espacioy=0;
            for(y=0;y<=1;y++)
            {
                invader1[i][y] = new Space(locationX+espacio, locationY+150+espacioy);
                invader2[i][y] = new Space(locationX+espacio, locationY+50+espacioy);
                invader3[i] = new Space(locationX+espacio, locationY);
                espacioy+=50;
            }
            espacio+=50;
        }
        for(i=0;i<=10;i++)
        {
            for(y=0;y<5;y++)
            {
                mat[i][y]=1;
            }
        }
        for(i=0;i<12;i++)
        {
            vec[i]=1;
        }
        espacio=50;
        espacioy=0;
        y=0;
        for(int i=0;i<12;i++)
        {
            if(i==1+y)
            {
                escudo[i] = new Space(espacio,510);
                y+=3;
                espacioy=1;
            }
            else if(espacioy==2)
            {
                espacioy=0;
                espacio+=60;
                i--;
            }
            else
            {
                if(espacioy==1)
                {
                    espacioy=2;
                }
                escudo[i] = new Space(espacio,530);
            }
            espacio+=30;
        }
         try 
         {
             imgTank = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\tank.png"));
             imgShot = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\shot.png"));
             imgInvadershot = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\invadershot.png"));
             
             imgInvader1a = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\invaders3a.png"));
             imgInvader1b = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\invaders3b.png"));
            
             imgInvader2a = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\invaders1a.png"));
             imgInvader2b = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\invaders1b.png"));
            
             imgInvader3a = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\invaders2a.png"));
             imgInvader3b = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\invaders2b.png"));
             
             imgExplosion = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\explosion.png"));
             imgInvader = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\invader.png"));
             
             imgUno = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\1.png"));
             imgDos = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\2.png"));
             imgTres = ImageIO.read(new File("C:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Spaceinva\\src\\spaceinva\\3.png"));
         } 
         catch (IOException ex) 
         {
             Logger.getLogger(FrmSpace.class.getName()).log(Level.SEVERE, null, ex);
         }
                  
    }
    
    
    public void keyTyped(KeyEvent e) {}
    
    public void keyReleased(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e)
    {
        if(habilitar==1)
        {
           if(e.getKeyCode()==39 && tank.getDrawLocationX()<760)
           {
               tank.setDrawLocationX(tank.getDrawLocationX()+5);
           }
           else if(e.getKeyCode()==37 && tank.getDrawLocationX()>10) 
           { 
               tank.setDrawLocationX(tank.getDrawLocationX()-5);
           }   
            if(e.getKeyCode()==38)
            {
                if(disparar==0)
                {
                disparar=1;
                shot.setDrawLocationX(tank.getDrawLocationX()+16);
                }
            }
        }
    }
    
    
    
    
    Timer temporizador=new Timer(100,new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        { 
            if(disparar==1)
            {
                shot.setDrawLocationY(shot.getDrawLocationY()-40);
                for(int i=0;i<=10;i++)
                {
                    if(shot.getDrawLocationX()>=invader3[i].getDrawLocationX()-15 && shot.getDrawLocationX()<invader3[i].getDrawLocationX()+35)
                    {
                        if(shot.getDrawLocationY()>=invader3[i].getDrawLocationY()-5 && shot.getDrawLocationY()<invader3[i].getDrawLocationY()+50)
                        {
                            con=3;
                            mat[i][0]=0;
                            puntaje+=40;
                            lblPuntaje.setText(String.valueOf(puntaje));
                            colision=1;
                            shot.setDrawLocationY(30);
                            auxiliarx=i;
                            aux=0;
                            for(int x=0;x<=10;x++)
                            {
                                for(y=0;y<5;y++)
                                {
                                    if(mat[x][y]==1)
                                        aux++;
                                }
                            }
                            if(aux==0)
                            {
                                temporizador.stop();
                                JOptionPane.showMessageDialog(null,"GANASTE");
                            }
                        }
                    }
                }
                for(int i=0;i<=10;i++)
                {
                    for(y=0;y<=1;y++)
                    {
                    if(shot.getDrawLocationX()>=invader1[i][y].getDrawLocationX()-15 && shot.getDrawLocationX()<invader1[i][y].getDrawLocationX()+35)
                    {
                        if(shot.getDrawLocationY()>=invader1[i][y].getDrawLocationY()-10 && shot.getDrawLocationY()<invader1[i][y].getDrawLocationY()+55)
                        { 
                            con=1;
                            mat[i][y+3]=0;
                            puntaje+=10;
                            lblPuntaje.setText(String.valueOf(puntaje));
                            colision=1;
                            shot.setDrawLocationY(30);
                            auxiliarx=i;
                            auxiliary=y;
                            aux=0;
                            for(int x=0;x<=10;x++)
                            {
                                for(y=0;y<5;y++)
                                {
                                    if(mat[x][y]==1)
                                        aux++;
                                }
                            }
                            if(aux==0)
                            {
                                temporizador.stop();
                                JOptionPane.showMessageDialog(null,"GANASTE");
                            }
                        }
                    }
                    }
                }
                for(int i=0;i<=10;i++)
                {
                    for(y=0;y<=1;y++)
                    {
                    if(shot.getDrawLocationX()>=invader2[i][y].getDrawLocationX()-15 && shot.getDrawLocationX()<invader2[i][y].getDrawLocationX()+40)
                    {
                        if(shot.getDrawLocationY()>=invader2[i][y].getDrawLocationY()-10 && shot.getDrawLocationY()<invader2[i][y].getDrawLocationY()+50)
                        {
                            con=2;
                            mat[i][y+1]=0;
                            puntaje+=20;
                            lblPuntaje.setText(String.valueOf(puntaje));
                            colision=1;
                            shot.setDrawLocationY(30);
                            auxiliarx=i;
                            auxiliary=y;
                            aux=0;
                            for(int x=0;x<=10;x++)
                            {
                                for(y=0;y<5;y++)
                                {
                                    if(mat[x][y]==1)
                                        aux++;
                                }
                            }
                            if(aux==0)
                            {
                                temporizador.stop();
                                JOptionPane.showMessageDialog(null,"GANASTE");
                            }
                        }
                    }
                    }
                }
                if(shot.getDrawLocationX()>=Invader.getDrawLocationX()-10 && shot.getDrawLocationX()<Invader.getDrawLocationX()+55)
                {
                    if(shot.getDrawLocationY()>=Invader.getDrawLocationY()-5 && shot.getDrawLocationY()<Invader.getDrawLocationY()+35)
                    {
                        con=4;
                        contador2=0;
                        rojo=-1;
                        puntaje+=aleatorio;
                        lblPuntaje.setText(String.valueOf(puntaje));
                        colision=1;
                        shot.setDrawLocationY(30);
                    }
                }
                
                
                for(int i=0;i<12;i++)
                {
                    if(shot.getDrawLocationX()>=escudo[i].getDrawLocationX()-10 && shot.getDrawLocationX()<escudo[i].getDrawLocationX()+30 && vec[i]<4)
                    {
                        if(shot.getDrawLocationY()>=escudo[i].getDrawLocationY()-10 && shot.getDrawLocationY()<escudo[i].getDrawLocationY()+30)
                        {
                            vec[i]++;
                            shot.setDrawLocationY(30);
                        }
                    }
                }
                
                
                if(shot.getDrawLocationY()<10)
                {
                    disparar=0;
                    shot.setDrawLocationY(tank.getDrawLocationY()-15);
                    colision=0;
                }
            }
            
            
            contador++;
            if(habilitar==1)
            {
            even*=-1;
            locationX+=sign*velocidad;
            espacio=0;
            for(int i=0;i<=10;i++)
            {
                espacioy=0;
                tope=1;
                for(y=0;y<=1;y++)
                {
                if(mat[i][y+3]==1)
                {
                invader1[i][y].setDrawLocationX(locationX+espacio);
                invader1[i][y].setDrawLocationY(locationY+150+espacioy);
                }
                if(mat[i][y+1]==1)
                {
                invader2[i][y].setDrawLocationX(locationX+espacio);
                invader2[i][y].setDrawLocationY(locationY+50+espacioy);
                }
                if(mat[i][0]==1)
                {
                invader3[i].setDrawLocationX(locationX+espacio);
                invader3[i].setDrawLocationY(locationY);
                }
                if(tope==1 && (invader3[i].getDrawLocationX()< 20 || invader3[i].getDrawLocationX()> 750 || invader2[i][y].getDrawLocationX()< 20 || invader2[i][y].getDrawLocationX()> 750 || invader1[i][y].getDrawLocationX()< 20 || invader1[i][y].getDrawLocationX()> 750))
                {
                    sign*=-1;
                    tope=0;
                    locationY+=30;
                    if(velocidad<=7)
                    {
                    velocidad+=1;
                    }
                }
                if(invader1[i][y].getDrawLocationY()>=560 && invader1[i][y].getDrawLocationY()<610)
                {
                    muerte=1;
                    con2=3;
                    temporizador.stop();
                }
                else if(invader2[i][y].getDrawLocationY()>=560 && invader2[i][y].getDrawLocationY()<610)
                {
                    muerte=1;
                    con2=3;
                    temporizador.stop();
                }
                else if(invader3[i].getDrawLocationY()>=560 && invader3[i].getDrawLocationY()<610)
                {
                    muerte=1;
                    con2=3;
                    temporizador.stop();
                }
                espacioy+=50;
                }
                espacio+=50;
            }
            }
            
            
            if(contador==30)
            {
                muerte=0;
                habilitar=1;
                contador=0;
                contador2++;
                invaderdisparar=1;
                aux=0;
                while(aux<1)
                {
                    i=(int)(Math.random()*11-1);
                    aux=0;
                    for(y=0;y<5;y++)
                    {
                        if(mat[i][y]==1)
                        {
                            aux++;
                        }
                    }
                }  
                if(mat[i][4]==1)
                {
                    invadershot.setDrawLocationX(invader1[i][1].getDrawLocationX()+10);
                    invadershot.setDrawLocationY(locationY+200);
                }
                else if(mat[i][3]==1)
                {
                    invadershot.setDrawLocationX(invader1[i][0].getDrawLocationX()+10);
                    invadershot.setDrawLocationY(locationY+150);
                }
                else if(mat[i][2]==1)
                {
                    invadershot.setDrawLocationX(invader2[i][1].getDrawLocationX()+10);
                    invadershot.setDrawLocationY(locationY+100);
                }
                else if(mat[i][1]==1)
                {
                    invadershot.setDrawLocationX(invader2[i][0].getDrawLocationX()+10);
                    invadershot.setDrawLocationY(locationY+50);
                }
                else
                {
                    invadershot.setDrawLocationX(invader3[i].getDrawLocationX()+10);
                    invadershot.setDrawLocationY(locationY);
                }
            }
            if(contador2>3 && habilitar==1)
            {
                aleatorio=(int)(Math.random()*250+1);
                Invader.setDrawLocationX(Invader.getDrawLocationX()+(8*rojo));
                if(Invader.getDrawLocationX()>810 || Invader.getDrawLocationX()<-50)
                {
                    contador2=0; 
                    rojo*=-1;
                }
            }
            if(invaderdisparar==1)
            {
                invadershot.setDrawLocationY(invadershot.getDrawLocationY()+30);
                if(invadershot.getDrawLocationY()>590)
                {
                    if(invadershot.getDrawLocationX()>=tank.getDrawLocationX()-15 && invadershot.getDrawLocationX()<tank.getDrawLocationX()+50)
                    {
                        muerte=1;
                        con2++;
                        lblVidas.setText(String.valueOf(3-con2));
                        invadershot.setDrawLocationX(900);
                        invadershot.setDrawLocationY(100);
                    }
                    else if(invadershot.getDrawLocationY()>630)
                    {
                        invadershot.setDrawLocationY(invadershot.getDrawLocationY()+30);
                        invaderdisparar=0;
                        invadershot.setDrawLocationX(900);
                        invadershot.setDrawLocationY(100);
                    }
                }
                else if(invadershot.getDrawLocationY()>510)
                {
                    for(int i=0;i<12;i++)
                    {
                        if(invadershot.getDrawLocationX()>=escudo[i].getDrawLocationX()-10 && invadershot.getDrawLocationX()<escudo[i].getDrawLocationX()+25 && vec[i]<4)
                        {
                                vec[i]++;
                                invadershot.setDrawLocationX(900);
                                invadershot.setDrawLocationY(100);
                        }
                    }
                }
            }
           
            repaint();
        }
    });
    
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(imgInvader, Invader.getDrawLocationX(),Invader.getDrawLocationY(),50,30,this);
        if(muerte==1)
        {
            if(con2==1)
            {
                g.drawImage(imgExplosion,tank.getDrawLocationX()-5,tank.getDrawLocationY()-5,65,55,this);
                g.drawImage(imgTank,950,tank.getDrawLocationY(),50,50,this);
                habilitar=0;
            }
            else if(con2==2)
            {
                g.drawImage(imgExplosion,tank.getDrawLocationX()-5,tank.getDrawLocationY()-5,65,55,this);
                g.drawImage(imgTank,950,tank.getDrawLocationY(),50,50,this);
                habilitar=0;
            }
            else
            {
                g.drawImage(imgExplosion,tank.getDrawLocationX()-5,tank.getDrawLocationY()-5,65,55,this);
                temporizador.stop();
                JOptionPane.showMessageDialog(null,"YOU DIED");  
            }
        }
        else
        {
            g.drawImage(imgTank,tank.getDrawLocationX(),tank.getDrawLocationY(),50,50,this);
        }
        
        if(even == 1)
        {
            for(int i=0;i<=10;i++)
            {
                for(y=0;y<=1;y++)
                {
                    g.drawImage(imgInvader1a, invader1[i][y].getDrawLocationX(),invader1[i][y].getDrawLocationY(),40,30,this);
                    g.drawImage(imgInvader2a, invader2[i][y].getDrawLocationX(),invader2[i][y].getDrawLocationY(),40,30,this);
                    g.drawImage(imgInvader3a, invader3[i].getDrawLocationX(),invader3[i].getDrawLocationY(),40,30,this);
                }
            }
        }
        else
        {
            for(int i=0;i<=10;i++)
            {
                for(y=0;y<=1;y++)
                {
                    g.drawImage(imgInvader1b, invader1[i][y].getDrawLocationX(),invader1[i][y].getDrawLocationY(),40,30,this);
                    g.drawImage(imgInvader2b, invader2[i][y].getDrawLocationX(),invader2[i][y].getDrawLocationY(),40,30,this);
                    g.drawImage(imgInvader3b, invader3[i].getDrawLocationX(),invader3[i].getDrawLocationY(),40,30,this);
                }
            }
        }
        
        for(int i=0;i<12;i++)
        {
            if(vec[i]==1)
            {
                g.drawImage(imgUno, escudo[i].getDrawLocationX(),escudo[i].getDrawLocationY(),30,30,this);
            }
            else if(vec[i]==2)
            {
                g.drawImage(imgDos, escudo[i].getDrawLocationX(),escudo[i].getDrawLocationY(),30,30,this);
            }
            else if(vec[i]==3)
            {
                g.drawImage(imgTres, escudo[i].getDrawLocationX(),escudo[i].getDrawLocationY(),30,30,this);
            }
        }
        
        if(colision==1)
        {
            if(con==1)
            {
                g.drawImage(imgExplosion, invader1[auxiliarx][auxiliary].getDrawLocationX(),invader1[auxiliarx][auxiliary].getDrawLocationY(),40,30,this);
                invader1[auxiliarx][auxiliary].setDrawLocationX(50);
                invader1[auxiliarx][auxiliary].setDrawLocationY(800);
            }
            else if(con==2)
            {
                g.drawImage(imgExplosion, invader2[auxiliarx][auxiliary].getDrawLocationX(),invader2[auxiliarx][auxiliary].getDrawLocationY(),40,30,this);
                invader2[auxiliarx][auxiliary].setDrawLocationX(100);
                invader2[auxiliarx][auxiliary].setDrawLocationY(800);
            }
            else if(con==3)
            {
                g.drawImage(imgExplosion, invader3[auxiliarx].getDrawLocationX(),invader3[auxiliarx].getDrawLocationY(),40,30,this);
                invader3[auxiliarx].setDrawLocationX(200);
                invader3[auxiliarx].setDrawLocationY(800);
            }
            else if(con==4)
            {
                g.drawImage(imgExplosion, Invader.getDrawLocationX(),Invader.getDrawLocationY(),55,35,this);
                Invader.setDrawLocationX(817);
            }
        }
        
        if(disparar==1)
        {
            g.drawImage(imgShot,shot.getDrawLocationX(),shot.getDrawLocationY(),20,20,this);
        }
        if(invaderdisparar==1)
        {
            g.drawImage(imgInvadershot,invadershot.getDrawLocationX(),invadershot.getDrawLocationY(),20,20,this);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblPuntaje = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblVidas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("SCORE");

        lblPuntaje.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPuntaje.setText("0");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("LIVES");

        lblVidas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblVidas.setText("3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lblPuntaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 499, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lblVidas)
                .addGap(89, 89, 89))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblPuntaje)
                    .addComponent(jLabel2)
                    .addComponent(lblVidas))
                .addGap(0, 588, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        temporizador.start(); 
    }//GEN-LAST:event_formWindowOpened

    
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
            java.util.logging.Logger.getLogger(FrmSpace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSpace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSpace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSpace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSpace().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblPuntaje;
    private javax.swing.JLabel lblVidas;
    // End of variables declaration//GEN-END:variables
}
