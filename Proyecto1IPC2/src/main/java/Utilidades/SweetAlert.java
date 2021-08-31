/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.io.PrintWriter;

/**
 *
 * @author OrdSon
 */
public class SweetAlert {
    public void showError(PrintWriter writer ,String mensaje){
        
        writer.println("Swal.fire({");
        writer.println("  icon: 'error',");
        writer.println("title: 'Cielos',");
        writer.print("text: '");
        writer.print(mensaje);
        writer.print("',");
        writer.println("})");
        
    }
}
