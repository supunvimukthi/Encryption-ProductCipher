
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Supun
 */
public class ProductCipher {
    public static StringBuffer SubEncrypt(String subInput){
        StringBuffer subOutput = new StringBuffer();
        for(int i=0 ; i<subInput.length() ; i++) {
            char c = subInput.charAt(i);
            subOutput.append((char) (c+5));}
        return subOutput; 
        
    }
    public static void TransEncrypt(StringBuffer subOutput,int n){
        String transInput= subOutput.toString();
        int modulus;
        if((modulus=transInput.length()%n)!=0){
            modulus=n-modulus;
            for(int i=modulus;i!=0;i--){
                transInput+="@"; //Add padding to the remaining spaces 
            }
        }
        StringBuffer transOutput = new StringBuffer();
        System.out.println("\n Transposition Matrix:");
        for(int i=0 ; i<n ; i++) {
            for(int j=0 ; j<transInput.length()/n ; j++) {
                char c = transInput.charAt(i+(j*n));
                System.out.print(c);
                transOutput.append(c);
            }
            System.out.println();
        }
       
        JOptionPane.showMessageDialog(null, transOutput,"Final Encrypted Text",JOptionPane.PLAIN_MESSAGE);
    
    }
    public static void SubDecrypt(StringBuffer transPlaintext ){
        StringBuffer plaintext = new StringBuffer();
        for(int i=0 ; i<transPlaintext.length() ; i++) {
            char c = transPlaintext.charAt(i);
            plaintext.append((char) (c-5));
        }

        System.out.println("\nPlaintext:");
        System.out.println(plaintext);
    }
    
    public static void TransDecrypt(StringBuffer transOutput,int n){
        n = transOutput.length()/n;
        StringBuffer transPlaintext = new StringBuffer();
        for(int i=0 ; i<n ; i++) {
            for(int j=0 ; j<transOutput.length()/n ; j++) {
                char c = transOutput.charAt(i+(j*n));
                transPlaintext.append(c);
            }
        }
    }
    
    public static void main(String args[]) {
       
        File file = new File("TextToEncrypt.txt"); 
        String subInput=""; 
        try {
            subInput = new Scanner(file).nextLine();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductCipher.class.getName()).log(Level.SEVERE, null, ex);
        }
        int n =  Integer.parseInt(JOptionPane.showInputDialog("Enter a Key :" ));
        TransEncrypt(SubEncrypt(subInput),n); 
        
    }
    
}
