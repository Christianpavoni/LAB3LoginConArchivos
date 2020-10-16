package com.example.lab3loginconarchivos.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lab3loginconarchivos.model.Usuario;
import com.example.lab3loginconarchivos.registro.RegistroActivity;
import com.example.lab3loginconarchivos.request.ApiClient;

public class LoginActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> error;



    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context=getApplication().getApplicationContext();
    }

    public LiveData<String> getError() {

        if (error == null) {
            error = new MutableLiveData<>();
        }
        return error;
    }



    public void login(String email, String password){

        Usuario usuario=ApiClient.login(context,email,password);
        if(usuario!=null){

            Intent i=new Intent(context, RegistroActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("perfil",true);
            context.startActivity(i);

        }else{
            error.setValue("El email y password ingresados son incorrectos.");
        }

    }


    public void accesoRegistro(){

            Usuario usuario=ApiClient.leer(context);

            if(usuario==null){
                Intent i=new Intent(context, RegistroActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }else{
                error.setValue("Ya se registró un usuario");
            }


    }

}
