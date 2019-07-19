
<?php
$server ="localhost";
$password="123abcaa";
$username="root";
$Db="android";
$conn=mysqli_connect($server,$username,$password,$Db);//Make connection to my Table and DataBase.
if($conn){//Check for connection
print("connect");} 
 

?>
