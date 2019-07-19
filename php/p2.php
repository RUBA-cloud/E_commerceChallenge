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
      if($_SERVER["METHOD"]=="POST"){
            $name = $_POST["username"]; 
           $email = $_POST["email"]; 
            $password =$_POST["password"];
    $address = $_POST["address1"]; 
           $address2 = $_POST["address2"]; 
            $country =($_POST["country"]);
    $city= $_POST["city"]; 
           $region = $_POST["region"];
     $mob = $_POST["mobile"]; 
           $day = $_POST["daymobile"];  
 
           $eve= $_POST["evemobile"]; 
            $shop =($_POST["ship_cart"]);
               $stmt=("INSERT INTO signup(`name`, `email`, `password`,`mobilenumber`,`daynumber`,`evenumber`,`city`,`country`,`region`,`Address1`,`Address2`,`Cart`) VALUES ('$name', '$email', '$password','$mob','$eve','$day','$city','$country','$region','$address','$address2','$shop')");



        if ($conn->query($stmt) === TRUE) {
            echo "successfully";
          
        } else {
           echo("Failed");
        }
}           
?>