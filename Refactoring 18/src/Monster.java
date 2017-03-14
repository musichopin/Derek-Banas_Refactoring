/*The Basics of the Abstract Design Pattern
1. There is a Monster abstract class
2. The difference between the Monsters is the Objects 
	stored in Attack and Range
3. An interface is used to represent both options
	available for Attack and Range
4. 2 classes implement that Attack & Range interfaces
5. There is a MonsterFactory interface. It represents 
	monsters with different Attack & Range types
6. If I want a Vampire with a MediumAttack & MediumRange
	a Vampire Factory is made that assigns those classes
	to the Vampire objects Attack & Range attributes
7. The only thing left is the builder that receives a string
	If the String is a Vampire we pass the Vampire Factory
	into the Vampire class Object.
8. The Vampire class object assigns the Attack & Range Objects
	as the attributes for Attack & Range for every Vampire
	Object that is created.*/

// This class defines attributes and capabilities 
// for each monster

abstract class Monster {

	private String name;
	
	MonsterAttackPower attackPower;
	MonsterAttackRange attackRange;
	
	abstract void makeMonster();
	
	public void checkIfVictimIsInRange(){
		
		System.out.println(getName() + " checks if victim is " + attackRange);
		
	}
	
	public void attackTheVictim(){
		
		System.out.println(getName() + " attacks the victim for " + attackPower);
		
	}
	
//	***called by main method***
	public String toString(){
		
		String infoOnMonster = getName() + " attacks anything " + attackRange + 
				" for " + attackPower;
		
		return infoOnMonster;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

//*A factory is sent into this class that will assign
//the right objects for attack and range to the Zombie*

class Zombie extends Monster{
	
	// The type of attack & range to assign are past
	// into the constructor
	
	MonsterFactory monsterFactory;
	
	public Zombie(MonsterFactory monsterFactory) {
		this.monsterFactory = monsterFactory;
	}

	void makeMonster() {
		
		System.out.println("Making a Zombie");
		
		// **These properties are stored in the Monster abstract class
//		attackPower is of type MonsterAttackPower
//		attackRange is of type MonsterAttackRange**
		attackPower = monsterFactory.assignAttackPower(); // *returns "10 in damage" with toString method*
		attackRange = monsterFactory.assignAttackRange(); // *returns "5 away" with toString method*
		
	}
	
}

//The same is done for the vampire

class Vampire extends Monster{
	
	// The type of attack & range to assign are past
	// into the constructor
	
	MonsterFactory monsterFactory;
	
	public Vampire(MonsterFactory monsterFactory) {
		this.monsterFactory = monsterFactory;
	}

	void makeMonster() {
		
		System.out.println("Making a Vampire");
		
		// These are stored in the Monster class
//		**attackPower is of type MonsterAttackPower
//		attackRange is of type MonsterAttackRange**
		attackPower = monsterFactory.assignAttackPower(); // *returns "20 in damage" with toString method*
		attackRange = monsterFactory.assignAttackRange(); // *returns "10 away" with toString method*
		
	}
	
}

// *Here I define the attributes for each monster
// and the methods that will define them*

interface MonsterFactory{
	
	public MonsterAttackPower assignAttackPower();
	public MonsterAttackRange assignAttackRange();
	
}

// This defines the attack and range to assign to
// each Zombie created

class ZombieFactory implements MonsterFactory{

	public MonsterAttackPower assignAttackPower() {
		// ***alt: public BasicAttack assignAttackPower since 
		// "new MediumAttack" is never returned from this method***
		
		return new BasicAttack(); // **calls and returns toString method**
		
	}

	public MonsterAttackRange assignAttackRange() {
		// **alt: public BasicRange assignAttackRange**
		
		return new BasicRange(); // **calls and returns toString method**
		
	}
	
}

//This defines the attack and range to assign to
//each Vampire created

class VampireFactory implements MonsterFactory{

	public MonsterAttackPower assignAttackPower() {
		
		return new MediumAttack();
		
	}

	public MonsterAttackRange assignAttackRange() {
		
		return new MediumRange();
		
	}
	
}

//Now since I defined these interfaces, anything
//that implements this interface can replace toString

interface MonsterAttackPower{
	
	public String toString();
	
}

//These replace toString for MonsterAttackPower

class BasicAttack implements MonsterAttackPower{
	
	public String toString(){
		
		return "10 in damage";
		
	}
	
}

class MediumAttack implements MonsterAttackPower{
	
	public String toString(){
		
		return "20 in damage";
		
	}
	
}

//Now I do the same for MonsterAttackRange

interface MonsterAttackRange{
	
	public String toString();
	
}

class BasicRange implements MonsterAttackRange{
	
	public String toString(){
		
		return "5 away";
		
	}
	
}

class MediumRange implements MonsterAttackRange{
	
	public String toString(){
		
		return "10 away";
		
	}
	
}

// Now that I have Monsters defined, their individual attacks
// & ranges and I have a factory for making them I have to
// create a way to order them.

abstract class MonsterBuilder {
	
	protected abstract Monster makeMonster(String typeOfMonster);
	
	public Monster orderAMonster(String typeOfMonster){
//		***not an alt: public Vampire orderAMonster
//		since Monster is placeholder for both Vampire and Zombie***
		
		Monster theMonster = makeMonster(typeOfMonster);
		
		// Test out the methods for the Monster
//		***only makeMonster method is overridden by subclasses 
//		of Monster abstract class.
//		Besides printing, makeMoster method also sets variables which 
//		are used by attackTheVictim, checkIfVictimIsInRange and toString methods***
		theMonster.makeMonster();
		theMonster.checkIfVictimIsInRange();
		theMonster.attackTheVictim();
		
		return theMonster;
		
	}
	
}

class OrderAMonster extends MonsterBuilder{

	protected Monster makeMonster(String typeOfMonster) {
		
		Monster theMonster = null;
		
		if(typeOfMonster.equals("Zombie")){
			
			// *Create the factory that assigns the right attributes to each Zombie*
			MonsterFactory monsterFactory = new ZombieFactory();
			
			// Create a Zombie Monster that stores the Objects
			// specific for each zombie so they can be assigned
			// to this monster
			
			theMonster = new Zombie(monsterFactory);
			
			theMonster.setName("Zombie Bob");
			
		} else if(typeOfMonster.equals("Vampire")){
				
				// Create the factory that assigns the right attributes
				// to each Vampire
				
				MonsterFactory monsterFactory = new VampireFactory();
				
				// Create a Vampire Monster that stores the Objects
				// specific for each Vampire so they can be assigned
				// to this monster
				
				theMonster = new Vampire(monsterFactory);
				
				theMonster.setName("Vampire Paul");
				
		}
		
		return theMonster;
		
	}
	
}

class MonsterMakerTest{
	
	public static void main(String[] args){
		
		// Create a way to order new monsters
		
		MonsterBuilder monsterBuilder = new OrderAMonster();
		// **alt: "OrderAMonster monsterBuilder = new OrderAMonster();"**
		
		Monster zombie = monsterBuilder.orderAMonster("Zombie");
		// ***alt: Zombie zombie = (Zombie) monsterBuilder.orderAMonster("Zombie");***
		
		// Makes a call to the toString method
		
		System.out.println(zombie + "\n");
		
		Monster vampire = monsterBuilder.orderAMonster("Vampire");
		
		System.out.println(vampire + "\n");
		
	}
	
}
/*
Making a Zombie
Zombie Bob checks if victim is 5 away
Zombie Bob attacks the victim for 10 in damage
Zombie Bob attacks anything 5 away for 10 in damage

Making a Vampire
Vampire Paul checks if victim is 10 away
Vampire Paul attacks the victim for 20 in damage
Vampire Paul attacks anything 10 away for 20 in damage
*/