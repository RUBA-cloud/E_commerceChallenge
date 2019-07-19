<?php
require_once('wordsearch.php');
$userid=$_POST["id"];
$itemid=$_POST["itemid"];
$stmt =("DELETE FROM items where userid='".$userid."' and id='".$itemid."'");
$result=mysqli_query($conn,$stmt);
if($result){
echo("Delet");
}
else
echo("Not Delet");
?>
