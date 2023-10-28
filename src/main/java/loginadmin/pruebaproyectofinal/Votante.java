/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginadmin.pruebaproyectofinal;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import static loginadmin.pruebaproyectofinal.Votante.contravotante;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author angel
 */
public class Votante {
   
        public static void RegistroV() throws IOException{
            Scanner scan = new Scanner(System.in);
            File ArchivoV = new File ("ArchivoV.txt");
            
           
            System.out.println("~~~Bienvendio al Registro de Vontantes~~~ \n");
            System.out.println("Eliga una de las siguientes opciones: ");
            
            System.out.println("1. Registrar votante");
            System.out.println("2. Modificar Votante");
            System.out.println("3. Cambiar Contrasenia");
            System.out.println("4. Dar de baja por motivo de fallecimiento");
            
            
            int casos = scan.nextInt();
            scan.nextLine();
            
        switch (casos) {
            
            case 1:
                votante();
                break;
                
              
            case 2:
                ModV();
                 break;
            case 3:
                contraV();
                 break;
             }
    }
       //Codigo de creacion de registro de votantes
       public static void votante() throws IOException{
                  
           Scanner scan= new Scanner(System.in);
                System.out.println("Escribe los Nombres: ");
                String nomv = scan.nextLine();
                System.out.println("Escriba los apellidos: ");
                String apv = scan.nextLine();
                System.out.println("Escriba el CUI: ");
                String cui = scan.nextLine();
                System.out.println("Ingrese dia y mes de nacimiento: ");
                String dmn = scan.nextLine();
                System.out.println("Ingrese anio de nacimiento: ");
                String an = scan.nextLine();
                System.out.println("Ingrese direccion de residencia: ");
                String dr = scan.nextLine();
                System.out.println("Ingrese departamento de residencia: ");
                String dp = scan.nextLine();
                System.out.println("Ingrese municipio de residencia: ");
                String muni = scan.nextLine();
                System.out.println("Ingrese pais de residencia: ");
                String pais = scan.nextLine();
                System.out.println("Ingrese Correo: ");
                String correov = scan.nextLine();
                
                //usar un if para ver si es mayor de 18 anios para que el votante pueda ser asignado
                String contrav = contravotante();
                
                if (Integer.parseInt(an) <= 2005){
                String a[] = new String[11];
                a[0] = nomv;
                a[1] = apv;
                a[2] = cui;
                a[3] = dmn;
                a[4] = an;
                a[5] = dr;
                a[6] = dp;
                a[7] = muni;
                a[8] = pais;
                a[9] = correov;
                a[10] = contrav;
                

                
                String[][] RegistroV = {
                    {"|correo|", " |Nombres| ", " |Apellidos| ", "|CUI|", " |Fecha de nacimiento| ", " |Anio de nacimiento| ", " |Direccion de residencia| ", " |Departamento de residencia| ", " |Municipio de seridencia| ", " |Pais| ", " |Contrasenia| "}, 
                    { a[9]+ " , "+ a[0]+" , ", a[1]+" , ", a[2]+" , ", a[3]+" , ", a[4]+" , ", a[5]+" , ", a[6]+" , ", a[7]+" , ", a[8]+" , ", a[10]} 
                };
                
            try(PrintWriter writer = new PrintWriter(new FileWriter("ArchivoV", true))) {
                for (String[] string : RegistroV){
                for (String datos : string) {
                    writer.println(datos);
                }
                writer.println();
                }
                System.out.println("Se agrego exitosamente el usuario");
            }
       }
                else {
                   System.out.println("No es mayor de edad: ");
                }    
           
       
       }         
       //Codigo donde genera la contrasenia aleatoria del votante para despues usar ese metodo en el registro de usuarios en donde se almacenara con los demas datos
      
          public static String contravotante (){
                String contr = RandomStringUtils.random(16, true, true);
              System.out.println("La contrasenia asignada es: "+ contr);
              return contr;
          }
          
          
          
          
          //Codigo para modificar el votante, cambiar la dirreccion de residencia, pais, etc
          public static void ModV() throws FileNotFoundException, IOException {
              Scanner scan3 = new Scanner(System.in);
         System.out.print("Ingrese el correo del usuario que desea modificar: ");
            String correob = scan3.nextLine();
            System.out.println("Ingrese Nuevo pais: ");
            String Novopais = scan3.nextLine();
            System.out.println("Ingrese Nuevo departamento: ");
            String Novod = scan3.nextLine();
            System.out.println("Ingrese Nuevo Municipio: ");
            String NovoM = scan3.nextLine();
            System.out.println("Ingrese Nueva direccion: ");
            String Novodd = scan3.nextLine();
            
            File archivo = new File ("ArchivoV.txt");
            File archivoTemp = new File ("ArchTemp");
            
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemp, true));
            
            String linea;
            while((linea = reader.readLine())!=null){
                String[] split = linea.split(", ");
                    if(split.length >=3  && split [9].equals(correob)){
                        split[8] = Novopais;
                        split[6] = Novod;
                        split[7] = NovoM;
                        split[5] = Novodd;
                        linea = String.join(", ", split);        
                    }
                    writer.write(linea);
                    writer.newLine();
                    
                    reader.close();
                    writer.close();
                
                if (archivo.delete() && archivoTemp.renameTo(archivo)) {
                    System.out.println(" ~~~Cambios realizados con exito~~~ ");
                }else {
                       System.err.println("No se pudo realizar el cambio");
                            }
                
                                          }

                                                                                }
          public static void contraV() throws FileNotFoundException, IOException{
              Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el correo para el cambio de contrase;a");
            String correob = scan.nextLine();
            String nuevacontra = contravotante();
            
            File archivo = new File ("ArchivoV.txt");
            File archivoTemp = new File ("ArchTemp");
            
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemp, true));
            
            String linea;
            while ((linea = reader.readLine())!=null){
            String[] split = linea.split(", ");
                if(split.length >=3 && split [9].equals(correob)){
                    split[10] = nuevacontra;
                    linea = String.join(linea, split);
                    }
                writer.write(linea);
                writer.newLine();
                
                reader.close();
                writer.close();
                
                if(archivo.delete() && archivoTemp.renameTo(archivo)){
                    System.out.println("~~~El cambio de contrasenia ha sido exitoso~~~");
                } else {
                    System.err.println("Hubo un erro al cambiar la contrasenia");
                }
                        }
          }
                 
}
