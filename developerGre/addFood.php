
<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'conexion.php';
    $title=$_POST["title"];
    $subtitle=$_POST["subtitle"];
    $costo=$_POST["costo"];
    $img=$_POST["img"];
    $query="INSERT INTO comidas
              (title,subtitle,costo,img) 
    VALUES('".$title."','".$subtitle."','".$costo."','".$img."')";

    
    $resultado=$mysql->query($query);
    if($resultado==true){
        echo "La comida se agrego";
    }else{
        echo "Error al insertar el alimento";
    }
}