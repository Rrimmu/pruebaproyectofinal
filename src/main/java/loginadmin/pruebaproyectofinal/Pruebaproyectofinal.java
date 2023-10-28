/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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
import static loginadmin.pruebaproyectofinal.Elecciones.elecciones;
import static loginadmin.pruebaproyectofinal.Votante.RegistroV;


/**
 *
 * @author ngl
 */
public class Pruebaproyectofinal {

    public static void main(String[] args) throws IOException {
  Scanner scan = new Scanner(System.in);
        System.out.println("-----Bienvenido administrador-----\n");

        File ArchivoAdmin = new File("Admin.txt");
        File ArchivoUs = new File("Usuarios.txt");

        if (!ArchivoAdmin.exists()) {
            DatosAdmin(scan, ArchivoAdmin);
        }

        boolean sI = false;
        while (!sI) {
            if (IniciarS(scan, ArchivoAdmin)) {
                System.out.println("Se inició sesión con éxito");
                sI = true;
                RegUs(ArchivoUs);
            } else {
                System.out.println("El usuario o contraseña son incorrectos...");
                System.out.println("---Vuelva a intentar---");
            }
        }

        scan.close();
    }

    // Metodo para crear al admin
    private static void DatosAdmin(Scanner scan, File ArchivoAdmin) throws IOException {
        System.out.println("Cree un Usuario:  ");
        String us = scan.nextLine();
        System.out.println("Cree una contraseña:   ");
        String co = scan.nextLine();

        try (PrintWriter writer = new PrintWriter(ArchivoAdmin)) {
            writer.println(us);
            writer.println(co);
        }
    }

    // Metodo para poder iniciar sesion y verificar si los datos son correctos o incorrectos
    private static boolean IniciarS(Scanner scan, File ArchivoAdmin) throws IOException {
        System.out.println("Ingrese el usuario: ");
        String us = scan.nextLine();
        System.out.println("Ingrese la contraseña:  ");
        String co = scan.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(ArchivoAdmin))) {
            String usuarioReg = reader.readLine();
            String contraReg = reader.readLine();

            return us.equals(usuarioReg) && co.equals(contraReg);
        }
    }
    //Registro de usuarios
    public static void RegUs(File ArchivoUs) throws IOException {
        Scanner scanner = new Scanner(System.in);

        boolean salir = false;
        while (!salir) {
            System.out.print("Elija una opcion: \n");

            System.out.println("1. Registrar usuario ");
            System.out.println("2. Modificar usuario ");
            System.out.println("3. Reiniciar contraseña ");
            System.out.println("4. Interfaz votante ");
            System.out.println("5. Administracion de elecciones: ");


            int casos = scanner.nextInt();
            scanner.nextLine();
            
     
            switch (casos) {
        
        case 1: 
            System.out.println("Nombres:  ");
            String name = scanner.nextLine();
            System.out.println("Apellidos :  ");
            String ap = scanner.nextLine();
            System.out.println("correo:  ");
            String correo = scanner.nextLine();
            System.out.println("Rol 'Administrador, Registrador, Auditor':  ");
            String rol = scanner.nextLine();
            System.out.println("Contraseña:  ");
            String contra = scanner.nextLine();
                           
               //Crear una nueva carpeta para el registro de cada usuario de la plataforma.
            // Se divide con slash "|"
                String RegUs = name + ", " + ap + ", " + correo + ", " + rol + ", " + contra;
            BufferedWriter writer = new BufferedWriter(new FileWriter("ArchivoUs.txt", true));
            writer.write("Nombre |  "+"  | Apellidos |  "+"  | Correo |  "+"  | Rol |  " + "  | Contraseña | \n  ");
            writer.write(RegUs);
            writer.newLine();
            writer.close();
            System.out.println("~~Usuario agregado con exito~~");
                break;
                    //Modificar usuario...
        case 2:
                ModUs();
                break;
                
        case 3:
                 ContraUs();
                 break;
                        
        case 4:
            RegistroV();
            
            
        case 5:
            elecciones();
                    }   
            
            
                }

            }
    //Codigo para modificar a los usuarios mediante una variable string linea que verifica si el correo ingresado es del usuario previamente registrado y si es correcto
    //Se dirige hacia el archivo donde estan los usuarios y cambia lo modificado
    public static void ModUs() throws FileNotFoundException, IOException{
        Scanner scan3 = new Scanner(System.in);
         System.out.print("Ingrese el correo del usuario que desea modificar: ");
            String correob = scan3.nextLine();
            System.out.println("Ingrese Nuevo nombre: ");
            String NovoNom = scan3.nextLine();
            System.out.println("Ingrese Nuevos apellidos: ");
            String NovoAp = scan3.nextLine();
            System.out.println("Ingrese Nuevo rol: ");
            String NovoRol = scan3.nextLine();
            
            File archivo = new File ("ArchivoUs.txt");
            File archivoTemp = new File ("ArchTemp");
            
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemp, true));
            
            String linea;
            while((linea = reader.readLine())!=null){
                String[] split = linea.split(", ");
                    if(split.length >=3  && split [2].equals(correob)){
                        split[0] = NovoNom;
                        split[1] = NovoAp;
                        split[3] = NovoRol;
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
    public static void ContraUs() throws FileNotFoundException, IOException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el correo para el cambio de contrase;a");
            String correob = scan.nextLine();
        System.out.println("Ingrese la nueva contrasenia");
            String nuevacontra = scan.nextLine();
            
            File archivo = new File ("ArchivoUs.txt");
            File archivoTemp = new File ("ArchTemp");
            
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemp, true));
            
            String linea;
            while ((linea = reader.readLine())!=null){
            String[] split = linea.split(", ");
                if(split.length >=3 && split [2].equals(correob)){
                    split[3] = nuevacontra;
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

                        
    
                
    



        

    

