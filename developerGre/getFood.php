<?php
if($_SERVER["REQUEST_METHOD"]=="GET"){
    require_once 'conexion.php';
    $title = $_GET["title"];
    $query = "SELECT * FROM comidas WHERE title ='$title'";
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