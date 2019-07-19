<?php
$server="localhost";
$password="123abcaa";
$username="root";
$Db="android2";
$conn=mysqli_connect($server,$username,$password,$Db);
if($conn){//Check for connection
print("connect");}

 //Make connection to my Table and DataBase.
      
            $itemid = $_POST["itemid"]; 
$useridowner=$_POST["id"];
           $productname = $_POST["itemname"]; 
            $card =$_POST["card"];
     
$useridlogin=$_POST["userid"];
$username=$_POST["username1"];
$username2=$_POST["username2"];

$stmt="INSERT INTO Buyitems(`ownerid`,`ownername`, `userid`, `username`,`itemname`,`card`,`itemid`) values('$useridowner','$username','$useridlogin','$username2','$productname','$card','$itemid')";
 if ($conn->query($stmt) === TRUE) {
            echo "Buy";
          
        } else {
           echo("Failed");}
?>
