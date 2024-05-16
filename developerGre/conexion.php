<?php
$mysql=new mysqli("localhost","root","","geogreen");
if($mysql->connect_error){
    die("Error de conexion");
}
else{
    //echo "Conexion exitosa";
}