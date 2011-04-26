 <?php
class User{
	private $user_id, $screen_name, $f_name, $l_name, $email, $password, $curr_chat_id, $fb_id, $guid, $db;
	public function __construct($screen_name, $guid)
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
		if(!$db)
		{
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
			echo "FUCK YOU HACKER";//lol.
			return NULL;
		}
	}
	
	/***************************************************************************************************************
		Sets up all the private fields of the user. This way we can edit them without having to query from the db every
		2 seconds.
	****************************************************************************************************************/
	private function setupCredentials()
	{
		$stmt = $this->db->prepare("select * from Members where screen_name = ? AND guid=?");
		$stmt->bind_param('ss',$this->screen_name, $this->guid);
		$stmt->execute();
		$stmt->bind_result($this->user_id, $this->screen_name, $this->f_name, $this->l_name, $this->email, $this->password, $this->curr_chat_id, $this->fb_id, $this->guid);
		if($stmt->fetch())
		{
			echo "Successfully logged in" . "<br />";
		}
		else
		{
			echo "Nope";	
		}
	}
	/**************************************************************
	 *Couldn't think of anything creative to put here yet...
	 ****************************************************************/
	public function __toString()
	{
			return "HELLO";
	}
	/*****************************************************
	 *Creates a user rating, using the rating id and the number($rating)
	 ***************************************************/
	public function setUserInterestRating($id, $rating)
	{
		$stmt = $this->db->prepare("insert into Ratings(user_id,int_id,rating) values(?,?,?)");
		$stmt->bind_param('iii', $this->user_id, $id, $rating);
		$stmt->execute();
		$stmt->close();
	}
	/****************************************************************
	 *takes the rating id, and the new rating and updates it. 
	 *************************************************************/
	public function updateUserInterestRating($id, $rating)
	{
		$uid = $this->user_id;
		$this->db->query("update Ratings set rating = $rating where user_id = $uid AND int_id= $id");
	}
}



$rawr = 'sean';
$pew = 'rawr';
$a = User::login($rawr, $pew);
echo "Object:" . $a; //Should say HELLO
?>