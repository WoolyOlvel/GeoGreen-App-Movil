<?php
if($_SERVER["REQUEST_METHOD"]=="GET"){
    require_once 'conexion.php';
    $correo = $_GET["correo"];
    $query = "SELECT * FROM users WHERE correo ='$correo'";
    $resul = $mysql->query($query);
    if($mysql->affected_rows >0){
        while($row = $resul->fetch_assoc()){
            $array = $row;
        }
        echo json_encode($array);
    }else{
        echo "No existee";
    }
}