<?php
$server="localhost";
$password="123abcaa";
$username="root";
$Db="android2";
$conn=mysqli_connect($server,$username,$password,$Db);
if($conn){//Check for connection
}

 //Make connection to my Table and DataBase.
      
 
$userid=$_POST["userid"];
 $card =$_POST["shopcard"];


$stmt="update signup set Cart='".$card."' where id='".$userid."'";
$result=mysqli_query($conn,$stmt);
if($result){
echo("done");}