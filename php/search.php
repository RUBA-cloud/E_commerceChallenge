<?php
require_once('wordsearch.php');
$search=$_POST["search"];
$stmt = $conn->prepare("SELECT * FROM items where name='".$search."'");
	
	
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
		array_push($products, $temp);}
                  echo json_encode($products);
?>
