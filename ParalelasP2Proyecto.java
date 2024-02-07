package paralelasp2proyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.*;

public class ParalelasP2Proyecto implements Runnable{
public static Interfaz grafica;
public static Runnable mS;
private static String s1;
private static String s2;
private static int[] x;

 
    //Inicializar hilos en el executor service 
    public ParalelasP2Proyecto(int[] x,String str1,String str2){
    this.s1=str1;
    this.s2=str2;
    this.x=x; 
    }

    //Es lo que harán los hilos
    @Override
    public void run() {
        excountSubstring(s1,s2);
    }

    
    //Algoritmo para el conteo de las substring executor service    
    static void excountSubstring(String str1,String str2)
    {
    int n1 = str1.length();
    int n2 = str2.length();
    //System.out.println(n1);
    if (n1 == 0 || n1 < n2){
    return;
    } else if (str1.substring(0, n2).equals(str2)){
        x[0]+= 1;
    }
        excountSubstring(str1.substring(1),str2);
    }
    
    //Se inicializa el ExecutorService
    public static int exConteo(int x,String s1,String s2){
        int[] m={x};
        ExecutorService executor = Executors.newFixedThreadPool(8); 
            for(int i=0;i<8;i++){
                mS = new ParalelasP2Proyecto(m,s1,s2);
            }
                executor.execute(mS);
                executor.shutdown();
                while(!executor.isTerminated()); 
        return m[0];
    }

    //Algoritmo para el conteo de las substring secuencial
    static int countSubstring(String str1,String str2)
    {
        int n1 = str1.length();
        int n2 = str2.length();
 
        // Caso Base
        if (n1 == 0 || n1 < n2)
        return 0;
 
        // Caso recursivo Verificando si la primera substring corresponde al texto de String1
        if (str1.substring(0, n2).equals(str2))
        return countSubstring(str1.substring(1),str2) + 1;
 
        // De otra manera, retorna el conteo
        return countSubstring(str1.substring(1),str2);
    }
 
    // Secuencial
    public static void main(String args[])
    {
    grafica=new Interfaz();
    }
    
    //Secuencial
    public static int Conteo(String str1,String str2){
        int p;
        p=countSubstring(str1,str2);
        return p;
    }
    
    //Inicializar Forkjoin
    public static int forkJoinConteo(int x,String s1,String s2){
        int[] m={x};
        ForkJoinConteo task = new ForkJoinConteo(m, s1,s2);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);
        return m[0];
    }
    
    
    public static class ForkJoinConteo extends RecursiveAction{
        private final String s1;
        private final String s2;
        private static int[] x;
  
    //Iniciar hilos en ForkJoin    
    public ForkJoinConteo(int[] x,String str1,String str2) {
        this.s1=str1;
        this.s2=str2;
        this.x=x;
    }

    //Algoritmo para el conteo de las substring en forkjoin
    @Override
    protected void compute() {
    int n1 = s1.length();
    int n2 = s2.length();
    int y=1;
    
    if (n1 == 0 || n1 < n2){
        return;
 
    }else if (s1.substring(0, n2).equals(s2)){
        x[0]+= 1;
    }
       
    invokeAll(new ForkJoinConteo(x, s1.substring(1),s2)); 
    }
    }
}





  

 
