package com.example.lab3loginconarchivos.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lab3loginconarchivos.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ApiClient {
    public static File archivo;

    public static File recuperarArchivo(Context context){

        if(archivo==null) {
            File carpeta=context.getFilesDir();
            archivo = new File(carpeta, "usuario.dat");
        }
        return archivo;
    }

    public static boolean guardar (Context context, Usuario usuario){

        File ar=recuperarArchivo(context);
        boolean res=false;
        try {
            FileOutputStream fos=new FileOutputStream(ar);
            BufferedOutputStream bos=new BufferedOutputStream(fos);
            DataOutputStream dos=new DataOutputStream(bos);

            dos.writeUTF(usuario.getDni());
            dos.writeUTF(usuario.getNombre());
            dos.writeUTF(usuario.getApellido());
            dos.writeUTF(usuario.getEmail());
            dos.writeUTF(usuario.getPassword());
            dos.close();
            res=true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return res;

    }

    public static Usuario leer (Context context){

        Usuario usuario=null;
        File ar=recuperarArchivo(context);

        try {
            FileInputStream fis=new FileInputStream(ar);
            BufferedInputStream bis=new BufferedInputStream(fis);
            DataInputStream dis=new DataInputStream(bis);

            String dni=dis.readUTF();
            String nombre=dis.readUTF();
            String apellido=dis.readUTF();
            String email=dis.readUTF();
            String password=dis.readUTF();

            usuario=new Usuario(dni,nombre,apellido,email,password);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return usuario;
    }

    public static Usuario login(Context context,String email,String password){

        Usuario usuario=leer(context);

        if(usuario!=null)
            if(email.equals(usuario.getEmail()) && password.equals(usuario.getPassword()) && !email.equals("") &&  !password.equals(""))
                return usuario;



        return null;

    }
}
