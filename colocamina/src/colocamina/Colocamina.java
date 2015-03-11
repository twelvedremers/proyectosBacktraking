/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colocamina;
import java.io.*;
import java.util.*;
/**
 *
 * @author jhonger
 */
public class Colocamina {

    static Solucion soluciones;
    
   
    public static void mostrar(int matriz[][]){
        int aux[][]=new int[matriz.length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(matriz[i][j]>0)
                    System.out.printf(" %d ", matriz[i][j]);
                else
                    System.out.printf("%d ", matriz[i][j]);
                aux[i][j]=matriz[i][j];
            }
        System.out.print("\n");
        }
         System.out.print("\n");
          System.out.print("\n");
          
          
    }

      public static int[][] Cargado(int[][] matrizO) {
          
          int a[][]=new int[matrizO.length][matrizO[0].length];
          for (int i = 0; i < matrizO.length; i++) {
            System.arraycopy(matrizO[i], 0, a[i], 0, matrizO[i].length);
          }
          return a;
      }
      
 
      
       public static void inicial(int[][] matrizO) {
           int Solucion[][]=Cargado(matrizO);
          Fsolucion2(matrizO,Solucion,0,0);
      }
     
    
     public static void Fsolucion2(int[][] matrizO, int[][] Solucion, int i, int j) {
       
             if(matrizO[i][j]==-1){
                 
                 
                     Solucion[i][j]=9;    // se coloca la bomba para probar si se puede 
                        
                     if(i== (matrizO.length-1) && j==(matrizO[0].length-1))  // si se llega al final se revisa todo desde el principio
                                ValidacionFinal(matrizO,Solucion,0,0);
                                            
                     if(i<(matrizO.length-1) && j==(matrizO[0].length-1))    // si se termina la fila se va con el siguiente
                                Fsolucion2(matrizO, Solucion, i+1, 0);
                                        
                     if(i<=(matrizO.length-1) && j<(matrizO[0].length-1))   // si se termina la columna se va con el siguiente
                                Fsolucion2(matrizO, Solucion, i, j+1);
                                    
                    Solucion[i][j]=-1;      // si no es posible colocar se resetea la casilla
                    
                    if(i==(matrizO.length-1) && j==(matrizO[0].length-1))  // si se llega al final se revisa todo desde el principio
                                ValidacionFinal(matrizO,Solucion,0,0);
               
                    if(i<(matrizO.length-1) && j==(matrizO[0].length-1))    // si se termina la fila se va con el siguiente
                                 Fsolucion2(matrizO, Solucion, i+1, 0);
                            
                    if(i<=(matrizO.length-1) && j<(matrizO[0].length-1))   // si se termina la columna se va con el siguiente
                                Fsolucion2(matrizO, Solucion, i, j+1);
                             
                 
     
             }else{
                 
                 if(SigueValido(matrizO,Solucion,i,j)){
                 
                      
                    if(i== (matrizO.length-1) && j==(matrizO[0].length-1)){
                  
                         ValidacionFinal(matrizO,Solucion,0,0);
                    }
                    
                    if(i<(matrizO.length-1) && j==(matrizO[0].length-1))
                       Fsolucion2(matrizO, Solucion, i+1, 0);
                            
                    if(i<=(matrizO.length-1)&& j<(matrizO[0].length-1))
                       Fsolucion2(matrizO, Solucion, i, j+1);
                 
                 }
             
             }
    }
     
   
    public static void main(String[] args) {
        
       // int matrizO[][]={{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};
       int matrizO[][]=leerArchivo();
       soluciones=new Solucion(matrizO);
       inicial(soluciones.getOriginal());
        System.out.println(soluciones.toString());
        guardar(soluciones);
        
    }


    public static boolean SigueValido(int[][] matrizO, int[][] Solucion, int i, int j) {
        if(Solucion[i][j]!=9){
        if(n_bombas(Solucion, i, j)>Solucion[i][j])
            return false;
         if((n_bombas(Solucion, i, j)+casillasLibres(Solucion, i, j))<Solucion[i][j])
            return false;
        }
        
        return true;
        
    }

    public static boolean ValidacionFinal(int[][] matrizO, int[][] Solucion,int i,int j) {
      
        
       if(Solucion[i][j]!=9 && Solucion[i][j]!=-1){
        
            if(i== (matrizO.length-1) && j==(matrizO[0].length-1))
            {   if(Solucion[i][j]==n_bombas(Solucion,i,j)) {
                  
                    soluciones.addMatriz(Solucion);
                    
            }
                 return (Solucion[i][j]==n_bombas(Solucion,i,j));
            }     
                    
            if(i<(matrizO.length-1) && j==(matrizO[0].length-1))
                return (Solucion[i][j]==n_bombas(Solucion,i,j)) && ValidacionFinal(matrizO,Solucion,i+1,0);
                            
            if(i<=(matrizO.length-1)&& j<(matrizO[0].length-1))
                return (Solucion[i][j]==n_bombas(Solucion,i,j)) && ValidacionFinal(matrizO,Solucion,i,j+1);
           
       
       } else {
        
            if(i== (matrizO.length-1) && j==(matrizO[0].length-1))
            { 
              
                 soluciones.addMatriz(Solucion);
                return true;
            }     
                    
            if(i<(matrizO.length-1) && j==(matrizO[0].length-1))
                return true && ValidacionFinal(matrizO,Solucion,i+1,0);
                            
            if(i<=(matrizO.length-1)&& j<(matrizO[0].length-1))
                return true && ValidacionFinal(matrizO,Solucion,i,j+1);
       
          
       }
        
       return true;
        
    }

    public static int n_bombas(int[][] Solucion, int i, int j) {
        
        int n=0;
       
            for (int k = i-1; k <= i+1; k++) {
                for (int l = j-1; l <= j+1; l++) {
                           if(k>=0 && l>=0 && k<= (Solucion.length-1) && l<=(Solucion[0].length-1))
                                if(Solucion[k][l]==9)
                                    n++;
                               
                           
                   }
                }
        return n;
    }

    public static int casillasLibres(int[][] Solucion, int i, int j) {
         
        int n=0;
       
            for (int k = i-1; k <= i+1; k++) {
                for (int l = j-1; l <= j+1; l++) {
                           if(k>=0 && l>=0 && k<= (Solucion.length-1) && l<=(Solucion[0].length-1))
                                if(Solucion[k][l]==-1)
                                    n++;
                               
                           
                   }
                }
        return n;
    }

    
    public static void guardar(Solucion soluciones) {
       FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("soluciones.txt");
            pw = new PrintWriter(fichero);
            pw.print(soluciones.toString());
 
        } catch (Exception e) {
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
           }
        }
    }
   
    private static int[][] leerArchivo() {
        int minas[][]=null;
        int n=1,m=1;
        
         File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("minas.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;int i=0;
         
         n=Integer.parseInt(br.readLine());
         m=Integer.parseInt(br.readLine());
         
         minas=new int[n][m];
         while((linea=br.readLine())!=null){
              int j=0;
          
             for (StringTokenizer stringTokenizer = new StringTokenizer(linea," \n,."); stringTokenizer.hasMoreTokens();) {
                 String token = stringTokenizer.nextToken();
                 
                if(token.equalsIgnoreCase("X"))
                    minas[i][j]=-1;
                else if(token.equalsIgnoreCase("*"))
                    minas[i][j]=9;
                else 
                    minas[i][j]=Integer.parseInt(token);
                 j++;
             }
             i++;
         }
      }
      catch(Exception e){
         System.out.print("error al cargar");
      }finally{
         try{                   
            if( null != fr ){  
               fr.close();    
            }                 
         }catch (Exception e2){
         }
      }
        return minas;
    }

      
   

  
}
