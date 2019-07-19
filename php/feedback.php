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
$useridowner=$_POST["ownerid"];
 $card =$_POST["feedback"];
$date=$_POST["date"];

$stmt="update buyitems set feedback='".$card."',datefeedback='".$date."' where itemid='".$itemid."' and ownerid='".$useridowner."'";
$result=mysqli_query($conn,$stmt);
if($result){
echo("Add");
}
else echo("Not AdD");
?>