<?php
$interests = array('tennis', 'baseball','archery','basketball');
sort($interests);
?>
<html>
<body>
<form method = "post">
Name: <input = "text" name = "name" /> <br/>
Interests: <select name = "interests">

<?php 
foreach ($interests as $v){
print "<option value = \" $v \"></option>";
}
?>
</select>
<input type = "submit" value = "Submit" />
</form>
</body> 
</html>
