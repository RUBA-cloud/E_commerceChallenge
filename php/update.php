<?php
require_once('wordsearch.php');
$userid=$_POST["id"];
$old=$_POST["old"];
$new=$_POST["new"];
$colum=$_POST["colum"];
$stmt =("update signup set name='ruba' where userid='24' and name='you'");
$result=mysqli_query($conn,$stmt);
if($result){
echo("Update");
}
else
echo("Not Update");
?>