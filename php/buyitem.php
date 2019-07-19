<?php
$server ="localhost";
$password="123abcaa";
$username="root";

$Db="android2";
$id=$_POST["userid"];


$conn=mysqli_connect($server,$username,$password,$Db);//Make connection to my Table and DataBase.
$stmt = $conn->prepare("SELECT `ownerid`,`ownername`,`userid`, `username`,`itemname`,`card`,`itemid`,`startdate`,`finishdate`  FROM Buyitems where userid='".$id."';");
	
	//executing the query 
	$stmt->execute();
$products=array();
$stmt->bind_result($ownerid,$ownername,$userid, $username,$itemname,$card,$itemid,$startdate,$finishdate);
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		
$temp["ownerid"]=$ownerid;
$temp["ownername"]=$ownername;
$temp["userid"]=$userid;                       
$temp["username"]=$username;                         
 $temp["itemname"]=$itemname;                         
 $temp["card"]=$card;                           
 $temp["itemid"]=$itemid; 
$temp["finishdate"]=$finishdate;
$temp["startdate"]=$startdate;
array_push($products, $temp);
}

                  echo json_encode($products); ?>