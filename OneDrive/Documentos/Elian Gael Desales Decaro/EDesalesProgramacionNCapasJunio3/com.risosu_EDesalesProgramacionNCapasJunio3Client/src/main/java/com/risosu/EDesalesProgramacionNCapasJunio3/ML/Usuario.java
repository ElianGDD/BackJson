package com.risosu.EDesalesProgramacionNCapasJunio3.ML;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Valid
public class Usuario {

    private int idUsuario;
    @Size(min = 3, max = 10, message = "Nombre entre 3 y 5")
    @NotEmpty(message = "Ingresa dato :@")
    private String nombre;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date FechaNacimiento;
    private String userName;
    private String apellidoPaterno;
    private String apellidoMaterno;
    //@Email(message = "Correo invalido")
    @Pattern(regexp = "[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}", message = "correo invalido regex")
    private String email;
    private String password;
    private char sexo;  // CHAR(1) en Oracle
    private String telefono;
    private String celular;
    private String curp;
    public Roll Roll;
    private String ImagenPerfil;
    private int Status;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public Roll getRoll() {
        return Roll;
    }

    public void setRoll(Roll Roll) {
        this.Roll = Roll;
    }

    public String getImagenPerfil() {
        return ImagenPerfil;
    }

    public void setImagenPerfil(String ImagenPerfil) {
        this.ImagenPerfil = ImagenPerfil;
    }

    public void setStatus(int Status){
        this.Status = Status;
    }
    public int getStatus(){
        return Status;
    }
    
}
