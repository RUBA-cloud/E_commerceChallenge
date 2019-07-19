<?php
$server="localhost";
$password="123abcaa";
$username="root";
$Db="android2";
$conn=mysqli_connect($server,$username,$password,$Db);
if($conn){//Check for connection
}

 //Make connection to my Table and DataBase.
      
  $itemid = $_POST["itemid"]; 
$useridowner=$_POST["userid"];
 $card =$_POST["finishdate"];
$date=$_POST["startdate"];
    

$stmt="update buyitems set startdate='".$date."',finishdate='".$card."' where itemid='".$itemid."' and userid='".$useridowner."'";
$result=mysqli_query($conn,$stmt);
if($result){
echo("Add");}