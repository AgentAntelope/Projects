package Try;



import java.io.Serializable;

/**Student
*@author Kristy Caster & Sean Myers
*/
public class Student implements Serializable{
	private String chapter,first, middle, last, address, city, state, email, termCode, degree, program, school,  phone;
	private int zip, peopleSoft, year, spring, summer12, summerI, summerII, fall, returning;
	private boolean undergrad, dependent;
	private Notebook notes;

	/**default constructor */
	public Student(){
		notes = new Notebook();
	}

	/**
	*@param f first name
	*@param m middle initial
	*@param l last name
	*@param a address
	*@param c city
	*@param s state
	*@param z ZIP code
	*@param e e-mail
	*@param ps PeopleSoft
	*@param p phone
	*@param u undergrad (false if grad student)
	*@param d degree type (BS, BA, Masters, etc.)
	*@param pr program (Computer Science, Nursing, etc.)
	*@param sc school (College of Arts & Sciences, etc.)
	*@param ch GI bill chapter
	*@param y school year
	*@param dp dependent (false if veteran)
	*@param r 1 for returning students, 2 for new students, 3 for new students with transfer credit;
	*/
	public Student(String f, String m, String l, String a, String c, String s, int z, String e, int ps, String p, boolean u, String d, String pr, String sc, String ch, int y, boolean dp, int r){
		first = f;
		middle = m;
		last = l;
		address = a;
		city = c;
		state = s;
		zip = z;
		email = e;
		peopleSoft = ps;
		phone = p;
		undergrad = u;
		degree = d;
		program = pr;
		school = sc;
		chapter = ch;
		year = y;
		dependent = dp;
		returning = r;
		notes = new Notebook();
	}
	
	public Student(String first){
		this.first = first;
	}
	//setters
	
	/**set first name
	*@param f first name
	*/
	public void setFirst(String f){
		first = f;
	}

	/**set middle initial
	*@param m middle initial
	*/
	public void setMiddle(String m){
		middle = m;
	}

	/**set last name
	*@param l last name
	*/
	public void setLast(String l){
		last = l;
	}

	/**set address
	*@param a address
	*/
	public void setAddress(String a){
		address = a;
	}

	/**set city
	*@param c city
	*/
	public void setCity(String c){
		city = c;
	}

	/**set state
	*@param s state
	*/
	public void setState(String s){
		state = s;
	}

	/**set ZIP code
	*@param z ZIP code
	*/
	public void setZip(int z){
		zip = z;
	}

	/**set e-mail
	*@param e e-mail
	*/
	public void setEmail(String e){
		email = e;
	}

	/**set PeopleSoft
	*@param p PeopleSoft
	*/
	public void setPeopleSoft(int p){
		peopleSoft = p;
	}

	/**set phone
	*@param p phone
	*/
	public void setPhone(String p){
		phone = p;
	}

	/**set undergrad status
	*@param u undergrad status (true if undergraduate, false if graduate)
	*/
	public void setUndergrad(boolean u){
		undergrad = u;
	}

	/**set degree type
	*@param d degree type
	*/
	public void setDegree(String d){
		degree = d;
	}

	/**set program
	*@param p program
	*/
	public void setProgram(String p){
		program = p;
	}

	/**set school
	*@param s school
	*/
	public void setSchool(String s){
		school = s;
	}

	/**set GI bill chapter
	*@param c GI bill chapter
	*/
	public void setChapter(String c){
		chapter = c;
	}

	/**set dependent status
	*@param d dependent (true if dependent, false if veteran)
	*/
	public void setDependent(boolean d){
		dependent = d;
	}
	
	/**set school year
	*@param y school year
	*/
	public void setYear(int y){
		year = y;
	}
	
	/**
	 * updates which semester & # of credits
	 * student has mostly recently been certified for
	 * @param spr
	 * @param sum12
	 * @param sumI
	 * @param sumII
	 * @param f
	 */
	public void setSemester(int spr, int sum12, int sumI, int sumII, int f){
		spring = spr;
		summer12 = sum12;
		summerI = sumI;
		summerII = sumII;
		fall = f;
	}
	
	/**
	 * 
	 * @param r 1 for returning, 2 for new, 3 for transfer
	 */
	public void setReturning(int r){
		returning = r;
	}
	
	//getters

	/**get first name
	*@return first first name
	*/
	public String getFirst(){
		return first;
	}

	/**get middle initial
	*@return middle middle initial
	*/
	public String getMiddle(){
		return middle;
	}

	/**get last name
	*@return last last name
	*/
	public String getLast(){
			return last;
	}

	/**get address
	*@return address address
	*/
	public String getAddress(){
		return address;
	}

	/** get Notebook
	 * @return Notebook Notebook
	 */
	public Notebook getNotebook(){
		return notes;
	}
	/**get city
	*@return city city
	*/
	public String getCity(){
		return city;
	}

	/**get state
	*@return state state
	*/
	public String getState(){
		return state;
	}

	/**get ZIP code
	*@return zip ZIP code
	*/
	public int getZip(){
		return zip;
	}

	/**get e-mail
	*@return email e-mail
	*/
	public String getEmail(){
		return email;
	}

	/**get PeopleSoft
	*@return peopleSoft PeopleSoft
	*/
	public int getPeopleSoft(){
		return peopleSoft;
	}

	/**get phone
	*@return phone phone
	*/
	public String getPhone(){
		return phone;
	}

	/**get undergrad status
	*@return true if undergraduate, false if graduate
	*/
	public boolean getUndergrad(){
		if(undergrad) return true;
		else return false;
	}

	/**get degree type
	*@return degree degree type
	*/
	public String getDegree(){
		return degree;
	}

	/**get program
	*@return program program
	*/
	public String getProgram(){
		return program;
	}

	/**get school
	*@return school school
	*/
	public String getSchool(){
		return school;
	}

	/**get GI bill chapter
	*@return chapter GI bill chapter
	*/
	public String getChapter(){
		return chapter;
	}

	/**get dependent status
	*@return dependent true if student is a dependent, false if veteran
	*/
	public boolean getDependent(){
		return dependent;
	}
	
	/**get school year
	*@return year most recent school year student has verified enrollment for
	*/
	public int getYear(){
		return year;
	}
	
	/**These getters return number of credits student is claiming.
	 * Each semester is automatically set to zero.  When a student verifies enrollment,
	 * credits for that particular semester must be set using the appropriate setter.
	 */
	
	/**get Spring credits
	 * 
	 * @return number of spring credits
	 */
	public int getSpring(){
		return spring;
	}
	
	/**get Summer (12 weeks)  credits
	 * 
	 * @return number of Summer (12 weeks) credits
	 */
	public int getSummer12(){
		return summer12;
	}
	
	/**get Summer I credits
	 * 
	 * @return number of spring credits
	 */
	public int getSummerI(){
		return summerI;
	}
	
	/**get Summer II credits
	 * 
	 * @return number of Summer II credits
	 */
	public int getSummerII(){
		return summerII;
	}
	
	/**get Fall credits
	 * 
	 * @return number of Fall credits
	 */
	public int getFall(){
		return fall;
	}
	
	/**
	 * returns status of student:
	 * returning, new, or transfer
	 */
	public String getReturning(){
		switch(returning){
		case 1:
			return "Returning Student";
		case 2:
			return "New Student";
		case 3:
			return "New Transfer Student";
		default:
			return "unk";
		}
	}

	/**student's name
	 * 
	 * @return Last, First MI
	 */
	@Override
	public String toString(){
		StringBuilder a = new StringBuilder();
		a.append(getLast() + ", " + getFirst() + " " + getMiddle()+ "\n");
		a.append("Address: "+address+ ", City:" + city + ", State: " + state + "\n" );
		a.append("Zip"+ zip + ", Email:" + email + ", Chapter:"+ chapter +"\n");
		return a.toString();
	}
	
	/**calculates term code
	 * 
	 * @return term code
	 */
	public String getTermCode(){
		StringBuilder calYear = new StringBuilder();
		calYear.append(getYear());
		StringBuilder termCode = new StringBuilder();
		termCode.append(calYear.charAt(0));
		if(getSpring() > 0){
			calYear.delete(0, 2);
			return termCode.append(calYear.toString() + "4").toString();
		}
		else if(getSummer12() > 0 || getSummerI() > 0 || getSummerII() > 0){
			calYear.delete(0, 2);
			return termCode.append(calYear.toString() + "7").toString();
		}
		else if(getFall() > 0){
			calYear.delete(0, 2);
			int yr = Integer.parseInt(calYear.toString());
			yr++;
			return termCode.append(yr + "1").toString();
		}
		else{
			return "" + year;
		}
	}
}

