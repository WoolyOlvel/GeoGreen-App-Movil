
<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'conexion.php';
    $usuario=$_POST["usuario"];
    $correo=$_POST["correo"];
    $contra1=$_POST["contra1"];
    $contra2=$_POST["contra2"];
    $query="INSERT INTO users
              (usuario,correo,contra1,contra2) 
    VALUES('".$usuario."','".$correo."','".$contra1."','".$contra2."')";

    
    $resultado=$mysql->query($query);
    if($resultado==true){
        echo "El usuario se inserto de forma exitosa";
    }else{
        echo "Error al insertar el usuario";
    }
}