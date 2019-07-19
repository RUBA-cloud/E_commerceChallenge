<?php
$server ="localhost";
$password="123abcaa";
$username="root";
$Db="android2";

$conn=mysqli_connect($server,$username,$password,$Db);
$Email= $_POST["email"]; 
$password=$_POST["password"];
$stmt = $conn->prepare("SELECT id,name from signup where email='".$Email."' and password='".$password."'");
	
	
	$stmt->execute();
$products=array();
$stmt->bind_result($id, $name);
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		
$temp["id"]=$id;
$temp["name"]=$name;
array_push($products, $temp);}
  echo json_encode($products);

                 
?>