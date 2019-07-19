<?php
 $response = array();
 $server ="localhost";
$password="123abcaa";
$username="root";
$Db="android2";
$conn=mysqli_connect($server,$username,$password,$Db);
if($conn){//Check for connection
print("connect");}

 //Make connection to my Table and DataBase.
      
            $id = $_POST["id"]; 

       $productname = $_POST["name"]; 
        $desc =$_POST["desc"];
    $salary = $_POST["salary"]; 
   $salary2 = $_POST["salary2"]; 
   $thum =($_POST["thumb"]);
$departmentid=$_POST['Did'];
$image1=$_POST["image1"];
$image2=$_POST["image2"];
$username=$_POST["username"];
$userid=$_POST["userid"];
$discount=$_POST["discount"];
$departmentname=$_POST["dname"];
$stmt="INSERT INTO items(`id`,`name`, `descriptian`, `salary`,`salaryprice`,`discount`,`thumbnial`,`image1`,`image2`,`departmentname`,`departmentid`,`username`,`userid`) VALUES ('$id', '$productname', '$desc','$salary','$salary2','$discount','$thum','$image1','$image2','$departmentname','$departmentid','$username','$userid')";

        if ($conn->query($stmt) === TRUE) {
            echo "successfully";
          
        } else {
           echo("Failed");
        }?>