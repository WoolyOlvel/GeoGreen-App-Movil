
<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'conexion.php';
    $title=$_POST["title"];
    $subtitle=$_POST["subtitle"];
    $costo=$_POST["costo"];
    $img=$_POST["img"];
    $cali=$_POST["cali"];
    $descuento=$_POST["descuento"];
    $limite=$_POST["limite"];
    $query="INSERT INTO paquetes
              (title,subtitle,costo,cali,descuento,limite,img) 
    VALUES('".$title."','".$subtitle."','".$costo."','".$cali."','".$descuento."','".$limite."','".$img."')";

    
    $resultado=$mysql->query($query);
    if($resultado==true){
        echo "El paquete se agrego";
    }else{
        echo "Error al insertar el paquete";
    }
}