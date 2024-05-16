
<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'conexion.php';
    $correo=$_POST["correo"];
    $producto=$_POST["producto"];
   
    $query="INSERT INTO compras
              (correo,producto) 
    VALUES('".$correo."','".$producto."')";

    
    $resultado=$mysql->query($query);
    if($resultado==true){
        echo "La compra se agrego";
    }else{
        echo "Error al insertar la compra";
    }
}