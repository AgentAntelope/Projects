<?php
class User{
	private $user_id, $screen_name, $f_name, $l_name, $email, $password, $curr_chat_id, $fb_id, $guid, $db, $loginState;
	private function __construct($screen_name, $guid)
		{
			$this->screen_name = $screen_name;
			$this->guid = $guid;
			$this->db = new mysqli('pseudocodingnet.fatcowmysql.com', 'kittenfire', '128411', 'chatengine');
			if(!$this->db){
				echo "Cannot connect to db"; 
				die();	
			}
			$this->setupCredentials();
		}	
		
	/**
	If the username and password are correct, returns a User object, otherwise it returns NULL.
	**/
	public static function login($screenname, $pw)
	{
		$hashPW = md5($pw);
		$db = new mysqli('pseudocodingnet.fatcowmysql.com', 'kittenfire', '128411', 'chatengine');
		if(!$db){
			echo "Could not connect";
			die();
		}
		//Creates a new GUID every attempt to login to prevent hackers from hacking the place!
		$guid = uniqid('', true);
		$db->query("update Members set guid='$guid' where screen_name='$screenname'"); 
		
		//Check to see if the user exists with the username and pw..
		$stmt = $db->prepare("select screen_name from Members where screen_name = ? and password = ? ");
		$stmt->bind_param('ss', $screenname, $hashPW);
		$stmt->execute();
		$stmt->bind_result($screen_name);
		if($stmt->fetch())
		{
			return new User($screenname, $guid);
		}
		else
		{
			//echo "FUCK YOU HACKER";//lol.
			return NULL;
		}
	}
	
	/***********************************************************************
	 If me_id is correct, will return a User object.
	 Otherwise, will return null (and call you a fucking hacker).
	************************************************************************/
	public static function fb_login($me_id){
		$db = new mysqli('pseudocodingnet.fatcowmysql.com', 'kittenfire', '128411', 'chatengine');
		if(!$db){
			echo "Could not connect";
			die();
		}
		//Creates a new GUID every attempt to login to prevent hackers from hacking the place!
		$guid = uniqid('', true);
		$db->query("update Members set guid='$guid' where fb_id='$me_id'"); 
		
		//Check to see if the user exists with the username and pw..
		$stmt = $db->prepare("select screen_name from Members where fb_id=? ");
		$stmt->bind_param('i', $me_id);
		$stmt->execute();
		$stmt->bind_result($screen_name);
		if($stmt->fetch())
		{
			return new User($screen_name, $guid);
		}
		else
		{
			//echo "FUCK YOU HACKER";//lol.
			return NULL;
		}
	}
	
	/***************************************************************************************************************
		Sets up all the private fields of the user. This way we can edit them without having to query from the db every
		2 seconds.
	****************************************************************************************************************/
	private function setupCredentials(){
		$stmt = $this->db->prepare("select * from Members where screen_name = ? AND guid=?");
		$stmt->bind_param('ss',$this->screen_name, $this->guid);
		$stmt->execute();
		$stmt->bind_result($this->user_id, $this->screen_name, $this->f_name, $this->l_name, $this->email, $this->password, $this->curr_chat_id, $this->fb_id, $this->guid);
		if($stmt->fetch()){
			$this->loginState = true;
		}
		else{
			$this->loginState = false;
		}
	}
	/***********************************************************************
	 *Createes a new member.
	 **********************************************************************/
	public static function createUser($screen_name, $password, $first_name, $last_name, $email){
		if(User::screenNameInDB($screen_name)){
			return json_encode(array("created"=>-1)); //User already in the database
		}		
		$db = new mysqli('pseudocodingnet.fatcowmysql.com', 'kittenfire', '128411', 'chatengine');
		$passwordhash = md5($password);
		if(!$db){
			echo "Could not connect";
			die();
		}
		$query ="insert into Members(screen_name, f_name, l_name, email, password, curr_chat_id) values(?,?,?,?,?,0)";
		$stmt = $db->prepare($query);
		$stmt->bind_param('sssss',$screen_name, $first_name, $last_name, $email, $passwordhash);
		$stmt->execute();
		if($stmt->affected_rows == 1){//Successful insert
			$created = array("created"=>1);  //JSON object means created.
			
			//Login to set Session and Cookies :P
			$user = User::login($screen_name, $password);
			$arMerge= $user->sendLogin();
			$result = array_merge($created, $arMerge);
			$user->createOnline();
			//Encode those cookies to the client.
			return json_encode($result);
		}
		else{
			return json_encode(array("created"=>-1)); //Unexplained failure, put it with screen_name, lol.
		}
	}
	
	//Get comments from createUser, it's the same but with facebook instead.
	public static function createUserFB($screen_name, $fb_id, $first_name, $last_name, $email){
		if(User::screenNameInDB($screen_name)){
			return json_encode(array("created"=>-1));
		}
		if(User::fbInDB($fb_id)){
			return json_encode(array("created"=>0));
		}
		$db = new mysqli('pseudocodingnet.fatcowmysql.com', 'kittenfire', '128411', 'chatengine');
		$password = md5(uniqid('',true));
		$guid = uniqid('', true);
		if(!$db){
			echo "Could not connect";
			die();
		}
		$query ="insert into Members(screen_name, f_name, l_name, email, password, fb_id, curr_chat_id) values(?,?,?,?,?,?, 0)";
		$stmt = $db->prepare($query);
		$stmt->bind_param('sssssi',$screen_name, $first_name, $last_name, $email, $password, $fb_id);
		$stmt->execute();
		if($stmt->affected_rows == 1){
			$created = array("created"=>1);
			$user = User::fb_login($fb_id);
			$arMerge= $user->sendLogin();
			$user->createOnline();
			$result = array_merge($created, $arMerge);
			return json_encode($result);
		}
		else{
			return json_encode(array("created"=>-1));
		}
	}
	

	/**
	 *Checks to see if the facebook id is already in the database
	 **/
	public static function fbInDB($fb_id){
		
		//connect to database
		$db = new mysqli('pseudocodingnet.fatcowmysql.com', 'kittenfire', '128411', 'chatengine');
		if(!$db){
			echo "Could not connect";
			die();
		}
		
		//Get all users with the fb_id of the parameter
		$stmt = $db->prepare("select screen_name from Members where fb_id=$fb_id");
		$stmt->execute();
		$stmt->bind_result($screen);
		if($stmt->fetch()){//If any return true.
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 *@param: $screen_name: the name to check
	 *@return: Will return true if it is in the db, otherwise false.
	 **/
	public static function screenNameInDB($screen_name){
		$db = new mysqli('pseudocodingnet.fatcowmysql.com', 'kittenfire', '128411', 'chatengine');
		if(!$db){
			echo "Could not connect";
			die();
		}
		$stmt = $db->prepare("select screen_name from Members where screen_name = \"$screen_name\"");
		$stmt->execute();
		$stmt->bind_result($screen);
		if($stmt->fetch()){
			return true;
		}
		else{
			return false;
		}
	}
	public function __toString(){
			return "HELLO";
	}
	public function setUserInterestRating($id, $rating){
		$stmt = $this->db->prepare("insert into Ratings(user_id,int_id,rating) values(?,?,?)");
		$stmt->bind_param('iii', $this->user_id, $id, $rating);
		$stmt->execute();
		$stmt->close();
	}
	
	public function createOnline(){
		$stmt = $this->db->prepare("insert into Online_users(user_id,cat_id, timestamp) values(?,0, NOW())");
		$stmt->bind_param('i', $this->user_id);
		$stmt->execute();
		$stmt->close();
	}
	public function updateUserInterestRating($id, $rating){
		$uid = $this->user_id;
		$this->db->query("update Ratings set rating = $rating where user_id = $uid AND int_id= $id");
	}
	
	public function setSession(){
		$_SESSION['screen_name'] = $this->screen_name;
		$_SESSION['guid'] = $this->guid;
		$_SESSION['user_id'] = $this->user_id;
	}
	
	public function sendLoginEncoded(){
		return json_encode(sendLogin());
		
	}
	public function sendLogin(){
		if($this->loginState){
			$_SESSION['screen_name'] = $this->screen_name;
			$_SESSION['guid'] = $this->guid;
			$_SESSION['user_id'] = $this->user_id;
			return array("screen_name"=>$this->screen_name, "guid"=>$this->guid, "user_id"=> $this->user_id);
		}
		else{
			return NULL;
		}
	}
	public function setLogin(){
		if($this->loginState){
			$_SESSION['screen_name'] = $this->screen_name;
			$_SESSION['guid'] = $this->guid;
			$_SESSION['user_id'] = $this->user_id;
			setcookie('screen_name', $this->screen_name, time()+30000000);
			setcookie('guid', $this->guid, time()+30000000);
			setcookie('user_id', $this->user_id, time()+30000000);
			return true;
		}
		return false;
	}
	public static function userObject(){
		if(isset($_SESSION['screen_name']) && isset($_SESSION['guid'])){
			return new User($_SESSION['screen_name'], $_SESSION['guid']);
		}
		if(isset($_COOKIE['screen_name']) && isset($_COOKIE['guid'])){
			$a = new User($_COOKIE['screen_name'], $_COOKIE['guid']);
			if($a->loginState){
				$a->setSession();
				return $a;
			}
			else{
				return NULL;
			}			
		}
	}
	public function logoff(){
		setcookie('screen_name', $this->screen_name, time()-30000000);
		setcookie('guid', $this->guid, time()-30000000);
	}
	
	public function getChatLines(){
		$stmt = $this->db->prepare("select Chats.chat_id, master.line_id, master.sender_id, master.text_line, Members.screen_name from Master_log as master, Chats, Members where Chats.chat_id = master.chat_box_id AND (Chats.user_a_id = ? OR Chats.user_b_id=?) AND master.line_id > ? AND master.sender_id != ? AND Members.user_id = master.sender_id");
		$stmt->bind_param('iiii', $this->user_id, $this->user_id, $this->curr_chat_id, $this->user_id);
		$stmt->execute();
		$stmt->bind_result($chatBox,$lineID, $senderID, $text, $senderName);
		$totalResults = array();
		while($stmt->fetch()){
			
			$totalResults[] = array('line_id'=>$lineID,'chatbox_id'=>$chatBox, 'sender_id'=>$senderID, 'text'=>$text, 'sender_name'=>$senderName);
		}
		if($lineID){
			$this->updateLog($lineID);			
		}
		$this->updateTimeActive();
		return json_encode($totalResults);
	}
	
	private function updateLog($lineID){
		$stmt = $this->db->prepare("update Members set curr_chat_id = ? where user_id = ?");
		$stmt->bind_param('ii', $lineID, $this->user_id);
		$stmt->execute();
	}
	/******************************************************
	 *@param: $otherUserID-- is the id of the other person you are trying to chat with.
	 *@return: Will return a chat_id (Could be one previously used)
	 ***************************************************/
	public function createChat($otherUserID){
		if($this->user_id < $otherUserID){
			$user1 = $this->user_id;
			$user2 = $otherUserID;
		}
		else{
			$user2 = $this->user_id;
			$user1 = $otherUserID;
		}
		$chat_id = $this->getChatID($user1, $user2);
		if($chat_id != -1){
			return json_encode(array('chat_id'=>$chat_id));
		}
		else{
			$stmt = $this->db->prepare("insert into Chats(user_a_id, user_b_id) values(?,?)");
			$stmt->bind_param('ii', $user1, $user2);
			$stmt->execute();
			$stmt->close();
			return json_encode(array('chat_id'=>$this->getChatID($user1, $user2)));
		}
	}
	private function updateTimeActive(){
		$stmt = $this->db->prepare("update Online_users set timestamp = NOW() where user_id=?");
		$stmt->bind_param('i', $this->user_id);
		$stmt->execute();	
	}
	private function getChatID($user1, $user2){
		$stmt = $this->db->prepare("select chat_id from Chats where user_a_id = ? AND user_b_id = ?");
		$stmt->bind_param('ii', $user1, $user2);
		$stmt->execute();
		$stmt->bind_result($chat_id);
		if($stmt->fetch()){
			return $chat_id;
		}
		else{
			return -1;
		}
	}
	public function sendChatLine($text, $chatbox_id){
		$text = strip_tags($text);
		$stmt = $this->db->prepare("insert into Master_log(sender_id, text_line, chat_box_id, timestamp) values(?,?,?, NOW())");
		$stmt->bind_param('isi', $this->user_id, $text, $chatbox_id);
		$stmt->execute();
	}
	public function changeRoom($cat_id){
		$stmt = $this->db->prepare("update Online_users set cat_id=? where user_id=?");
		$stmt->bind_param('ii', $cat_id, $this->user_id);
		$stmt->execute();
	}
	public function getOnlineUsers(){
		//$stmt = $this->db->prepare("select Members.user_id, Online_users.cat_id, screen_name from Online_users, Members where Members.user_id = Online_users.user_id AND Members.user_id != ?");
		$stmt = $this->db->prepare("select Members.user_id, Online_users.cat_id, screen_name from Online_users, Members where Members.user_id = Online_users.user_id AND TIMESTAMPDIFF(SECOND, timestamp, NOW()) < 10 AND Members.user_id != ?");
		$stmt->bind_param('i',$this->user_id);
		$stmt->execute();
		$stmt->bind_result($userID, $catID, $screenName);
		$totalResults = array();
		while($stmt->fetch()){
			$totalResults[] = array('user_id'=>$userID, 'cat_id'=>$catID, 'screen_name'=>$screenName);
		}
		return json_encode($this->parseData($totalResults));
	}
	private function parseData($usersOnline){
		
	}
	public static function getAllCategories(){
		$db = new mysqli('pseudocodingnet.fatcowmysql.com', 'kittenfire', '128411', 'chatengine');
		if(!$db){
			echo "Could not connect";
			die();
		}
		$stmt =$db->prepare("select * from Categories");
		$stmt->execute();
		$stmt->bind_result($catID, $catName);
		$totalResults = array();
		while($stmt->fetch()){
			$totalResults[] = array('cat_id'=>$catID, 'cat_name'=>$catName);
		}
		return json_encode($totalResults);
	}
	public static function getRatingsForCat($cat_id){
		$db = new mysqli('pseudocodingnet.fatcowmysql.com', 'kittenfire', '128411', 'chatengine');
		if(!$db){
			echo "Could not connect";
			die();
		}
		$stmt =$db->prepare("select * from interests where cat_id=?");
		$stmt->bind_param('i', $cat_id);
		$stmt->execute();
		$stmt->bind_result($intID, $intName, $catID);
		$totalResults = array();
		while($stmt->fetch()){
			$totalResults[] = array('cat_id'=>$catID, 'int_name'=>$intName, 'int_id'=>$intID);
		}
		return json_encode($totalResults);
		
	}
}

//$a = User::fb_login(111); //I manlly edited my username to have this fb_login_id
//echo "Object:" . $a;
/*
if(User::screenNameInDB('PEW')){
	echo "IT IS IN THE DATABASE";
}
else{
	echo "Not in db";
}*/
?>