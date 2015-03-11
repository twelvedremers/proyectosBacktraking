/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colocamina;
import java.util.*;
/**
 *
 * @author jhonger
 */
class Solucion {
    
    private int [][] original;
    private List<int [][]> matriz=new ArrayList<int [][]>();;

    public Solucion(int [][] n) {
        
        this.original=n;
    }

    public List<int[][]> getMatriz() {
        return matriz;
    }

    public void addMatriz(int[][] matriz) {
        int[][]aux=new int[matriz.length][matriz[0].length];
        
        for (int i = 0; i <matriz.length; i++) {
            System.arraycopy(matriz[i], 0, aux[i], 0, matriz[0].length);
            
        }
        this.matriz.add(aux);
    }

    public int[][] getOriginal() {
        return original;
    }

    @Override
     public String toString() {
       String palabra="";int n=1;
       
       palabra+="\n mapa original \n \n";
       
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[i].length; j++) 
            {
                switch(original[i][j]){
                
                    case -1:{   palabra += " X";
                                break;
                                    }
                    case 9:{   palabra += " *";
                                break;
                                    }
                    default:    palabra+=" "+original[i][j];
                
                }
               

            }
             palabra+="\n";
        }
        
        palabra+=((matriz.size()==0)?"\n la matriz no es valida":"\n la matriz es valida estas son las soluciones:")+"\n\n";

       
        
        for ( int [][] a: matriz) {
            
        palabra+="\n solucion "+n+" \n \n";
        
        for ( int i = 0; i < a.length; i++) {
            for ( int j = 0; j < a[i].length; j++) 
            {
                switch(a[i][j]){
                
                    case -1:{   palabra += " X";
                                break;
                                    }
                    case 9:{   palabra += " *";
                                break;
                                    }
                    default:    palabra+=" "+a[i][j];
                
                }
               

            }
            palabra+="\n";
            
        }
        n++;
        palabra+="\n";
        
        }
        return palabra; 
    }
   
    
    
    
    
    
    
    public int cantidad(){
    
    return matriz.size();
    }
    
    
    
}
