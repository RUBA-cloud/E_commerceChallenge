<?php

$server ="localhost";
$password="123abcaa";
$username="root";

$Db="android2";



$conn=mysqli_connect($server,$username,$password,$Db);//Make connection to my Table and DataBase.
$stmt = $conn->prepare("SELECT * FROM items;");
	
	//executing the query 
	$stmt->execute();
$products=array();
$stmt->bind_result($id, $name,  $decriptian, $salary, $salaryprice,$discount,$thumbnial,$image1,$image2,$depname,$depid,$username,$userid);
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		
$temp["id"]=$id;
$temp["name"]=$name;
$temp["decriptian"]=$decriptian;                       
$temp["salary"]=$salary;                         
 $temp["salaryprice"]=$salaryprice;                         
      $temp["discount"]=$discount;                           
                  $temp["thumbnial"]=$thumbnial;      
         $temp["username"]=$username;                           
                  $temp["userid"]=$userid;      
        
   $temp["image1"]=$image1;                         
 $temp["image2"]=$image2 ;
  $temp["departmantname"]=$depname;                         
 $temp["departmantid"]=$depid;
$temp["username"]=$username;
$temp["userid"]=$userid;
		array_push($products, $temp);
}

                  echo json_encode($products);
?>