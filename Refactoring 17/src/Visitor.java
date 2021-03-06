// Each concrete visitor type will implement
// the Visitor interface. Here we define every
// instance of method overloading needed

interface Visitor {
	
	public double visit(SalesTrainee trainee);
	public double visit(Salesman salesman);
	public double visit(Boss boss);
//	***yukarıdaki metodların argumentinde type olarak Visitable kullanmak isteseydik 
//	1. metod overloading yapamayacak ve her metoda farklı isim vermek zorunda kalacaktık
//	2. Visitable classında (boss, salesman ve salestrainee classlarında kullanılan) 
//	yeni metodlar tanımlamak zorunda olacaktık***

}