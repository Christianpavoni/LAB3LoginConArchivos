package com.example.lab3loginconarchivos.registro;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lab3loginconarchivos.model.Usuario;
import com.example.lab3loginconarchivos.request.ApiClient;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> message;
    private MutableLiveData<Usuario> usuario;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context=getApplication().getApplicationContext();
    }

    public LiveData<String> getMessage() {

        if (message == null) {
            message = new MutableLiveData<>();
        }
        return message;
    }

    public LiveData<Usuario> getusuario() {

        if (usuario == null) {
            usuario = new MutableLiveData<>();
        }
        return usuario;
}


    public void guardar(String dni,String nombre,String apellido,String email,String password){

        Usuario usuario=new Usuario(dni,nombre,apellido,email,password);
        boolean res=false;

        if(!email.equals("") && !password.equals("")){
            res= ApiClient.guardar(context,usuario);
            if(res){
                message.setValue("Guardado con exito");

            }else{
                message.setValue("Error al guardar");
            }
        }
        else
            message.setValue("Ingrese Mail y Password.");



    }

    public void leer(){

        Usuario us=ApiClient.leer(context);
        if(us!=null)
            usuario.setValue(us);
    }
}
