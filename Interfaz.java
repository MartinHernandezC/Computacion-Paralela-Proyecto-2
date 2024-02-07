/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paralelasp2proyecto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Interfaz extends JFrame{
    
    JLabel lblTiempo, lblTexto, lblTextoMostr, lblConteo, lblPalabracont; JLabel numero1;
    JButton btnSecuencial, btnExecutor, btnFork, btnIngresar, btnOrdenar;
    JTextField txtTexto, txtTexto1;
    JTextArea txtTextoMostr, txtConteo, txtTiempo, txtOriginal1;
    JScrollPane scroll1,scroll2;
    
    int x=0;
    public static String a;
    
    public Interfaz(){
        setTitle("Proyecto 2do Parcial Computación Paralela");
        setSize(450, 450);
        setLayout(null);
        
        lblTextoMostr = new JLabel("Texto mostrado");
        lblTextoMostr.setBounds(15,110,150,30);
        add(lblTextoMostr);
        
        txtTextoMostr = new JTextArea();
        txtTextoMostr.setEnabled(false);
        
        scroll1 = new JScrollPane(txtTextoMostr);
        scroll1.setBounds(15,150,180,70);
        scroll1.setEnabled(false);
        add(scroll1);
        
        lblConteo = new JLabel("Conteo");
        lblConteo.setBounds(215,110,150,30);
        add(lblConteo);
        
        txtConteo = new JTextArea();
        txtConteo.setEnabled(false);
        
        scroll2 = new JScrollPane(txtConteo);
        scroll2.setBounds(215,150,180,70);
        scroll2.setEnabled(false);
        add(scroll2);
        
        lblTiempo = new JLabel("Duración:");
        lblTiempo.setBounds(15,350,150,30);
        add(lblTiempo);
        
        txtTiempo = new JTextArea("");
        txtTiempo.setBounds(75,350,80,25);
        txtTiempo.disable();
        add(txtTiempo);
        
        //Etiqueta de texto
        lblTexto = new JLabel("Escriba el texto:");
        lblTexto.setBounds(15,15,150,30);
        add(lblTexto);
        
        txtTiempo = new JTextArea("");
        txtTiempo.setBounds(75,350,80,25);
        txtTiempo.disable();
        add(txtTiempo);
        
        //Etiqueta de texto
        lblPalabracont = new JLabel("Palabra a contar:");
        lblPalabracont.setBounds(15,60,150,30);
        add(lblPalabracont);
        
        //Ingreso de texto
        txtTexto1 = new JTextField();
        txtTexto1.setBounds(135,60,110,30);
        add(txtTexto1);

        
        //Botones
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(260,37,110,30);
        add(btnIngresar);
        btnIngresar.addActionListener(new ActionListener(){@Override public void actionPerformed(ActionEvent e) { selectionButtonPressed(e);}} );
        
        //Ingreso de texto
        txtTexto = new JTextField();
        txtTexto.setBounds(135,15,110,30);
        add(txtTexto);
        
        btnSecuencial = new JButton("Secuencial");
        btnSecuencial.setBounds(15,240,100,30);
        add(btnSecuencial);
        btnSecuencial.addActionListener(new ActionListener(){@Override public void actionPerformed(ActionEvent e) { selectionButtonPressed(e);}} );
        
        btnExecutor = new JButton("ExecutorService");
        btnExecutor.setBounds(150,240,130,30);
        add(btnExecutor);
        btnExecutor.addActionListener(new ActionListener(){@Override public void actionPerformed(ActionEvent e) { selectionButtonPressed(e);}} );
        
        btnFork = new JButton("Fork/Join");
        btnFork.setBounds(315,240,100,30);
        add(btnFork);
        btnFork.addActionListener(new ActionListener(){@Override public void actionPerformed(ActionEvent e) { selectionButtonPressed(e);}} );
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void  selectionButtonPressed(ActionEvent ae) {
        String tam = txtTexto.getText();
        String palabracont = txtTexto1.getText();
        int b=0;
        double ti,tf;
        if(tam.equals("")){
            System.out.println("Ingresa texto");
        }else{
            if(ae.getSource()==btnIngresar){     
            txtTextoMostr.setText(tam);
            } 
            ti=System.nanoTime();       
            if(ae.getSource()==btnSecuencial){
                b=ParalelasP2Proyecto.Conteo(tam,palabracont);
                txtConteo.setText(Integer.toString(b));
                tf=(System.nanoTime()-ti)/100000000;
                txtTiempo.setText(String.valueOf(tf));          
            }
            else if(ae.getSource()==btnExecutor){
                b=ParalelasP2Proyecto.exConteo(b,tam,palabracont);
                txtConteo.setText(Integer.toString(b));
                tf=(System.nanoTime()-ti)/100000000;
                txtTiempo.setText(String.valueOf(tf)); 
                
            }else if(ae.getSource()==btnFork){
                b=ParalelasP2Proyecto.forkJoinConteo(b,tam,palabracont);
                txtConteo.setText(Integer.toString(b));
                tf=(System.nanoTime()-ti)/100000000;
                txtTiempo.setText(String.valueOf(tf));     
            }        
        }
    }    
    
}
