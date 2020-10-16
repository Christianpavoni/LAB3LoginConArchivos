package com.example.lab3loginconarchivos.model;

public class Usuario {

    private String Dni;
    private String Nombre;
    private String Apellido;
    private String Email;
    private String Password;

    public Usuario() {
    }

    public Usuario(String dni, String nombre, String apellido, String email, String password) {
        Dni = dni;
        Nombre = nombre;
        Apellido = apellido;
        Email = email;
        Password = password;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
