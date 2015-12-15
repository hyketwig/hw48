//Team <team name> -- Jannie Li, Bayle Smith-Salzburg, Denis Duman
//APCS1 pd10
//HW48 -- Half ing Time Trials
//2015-12-14


/*============================================
  class OrderedArrayList
  Wrapper class for ArrayList.
  Imposes the restriction that stored items 
  must remain sorted in ascending order
  ============================================*/

//ArrayList's implementation is in the java.util package
import java.util.ArrayList;


public class OrderedArrayList {

    // instance of class ArrayList, holding objects of type Comparable 
    // (ie, instances of a class that implements interface Comparable)
    private ArrayList<Comparable> _data;


    // default constructor initializes instance variable _data
    public OrderedArrayList() {
	_data = new ArrayList<Comparable>();
    }


    public String toString() { 
	return _data.toString(); 
    }


    public Comparable remove( int index ) { 
	return _data.remove(index); 
    }


    public int size() { 
	return _data.size();
    }

    
    public Comparable get( int index ) { 
	return _data.get(index); 
    }


    // addLinear takes as input any comparable object 
    // (i.e., any object of a class implementing interface Comparable)
    // inserts newVal at the appropriate index
    // maintains ascending order of elements
    // uses a linear search to find appropriate index
    public void addLinear( Comparable newVal ) 
    { 
	for( int p = 0; p < _data.size(); p++ ) {
	    if ( newVal.compareTo( _data.get(p) ) < 0 ) { //newVal < oal[p]
		_data.add( p, newVal );
		return; //Q:why not break?
	    }
	}
	_data.add( newVal ); //newVal > every item in oal, so add to end
    }


    // addBinary takes as input any comparable object 
    // (i.e., any object of a class implementing interface Comparable)
    // inserts newVal at the appropriate index
    // maintains ascending order of elements
    // uses a binary search to find appropriate index
    public void addBinary( Comparable newVal ) { 
	//initialize upperbound, lowerbound and median
	int lo = 0;
	int med = 0;
	int hi = _data.size()-1;

	while ( lo <= hi ) { //running until target is found or bounds cross

	    med = (lo + hi) / 2;
	    int x = _data.get(med).compareTo( newVal );
	        
	    if ( x == 0 ) { //equal value found, insert here
		_data.add( med, newVal );
		return;
	    }
	    else if ( x > 0 ) //newVal < med, so look at lower half of data
		hi = med - 1;
	    else //newVal > med, so look at upper half of data
		lo = med + 1;
	}
	// If you make it this far, newVal was not in the ArrayList.
	// So insert at lo. Q: How do you know lo is correct insertion index?
	_data.add( lo, newVal );
    }	


    // determine whether element present in data structure using linear search
    // return index of occurrence or -1 if not found
    public int findLin( Comparable target ) 
    { 
	for( int p = 0; p < _data.size(); p++ ) {//go through each item
	    if ( target.compareTo( _data.get(p) ) == 0 ) {
	        return p;//index of item found
	    }
	}
        return -1; //not found
    }


    // determine whether element present in data structure using binary search
    // return index of occurrence or -1 if not found
    public int findBin( Comparable target ) 
    { 
	//initialize upperbound, lowerbound and median
	int lo = 0;
	int med = 0;
	int hi = _data.size()-1;

	while ( lo <= hi ) { //running until target is found or bounds cross

	    med = (lo + hi) / 2;
	    int x = _data.get(med).compareTo( target );
	        
	    if ( x == 0 ) { //equal value found, return index
	        return med;
	    }
	    else if ( x > 0 ) //target < med, so look at lower half of data
		hi = med - 1;
	    else //target > med, so look at upper half of data
		lo = med + 1;
	}
        return -1; //not found
    }


    // regular add method for testing
    // bc we're adding them in order so no point
    // wasting time using linear or binary
    public void add( Comparable newVal )
    {
	_data.add(newVal);
    }

    
    // main method solely for testing purposes
    public static void main( String[] args ) 
    {

	OrderedArrayList Franz = new OrderedArrayList();

	/*
	System.out.println("\nValues to add via addLinear() calls:");

	// testing linear search
	for( int i = 0; i < 15; i++ ) {
	    int valToAdd = (int)( 50 * Math.random() );
	    System.out.println( valToAdd );
	    Franz.addLinear( valToAdd );
	}

	System.out.println("\nafter population via addLinear() calls:");
	System.out.println( Franz );
	System.out.println();


	Franz = new OrderedArrayList();

	System.out.println("\nValues to add via addBinary() calls:");

				
	// testing binary search
	for( int i = 0; i < 15; i++ ) {
	    int valToAdd = (int)( 50 * Math.random() );
	    System.out.println( valToAdd );
	    Franz.addBinary( valToAdd );
	}

	System.out.println("\nafter population via addBinary() calls:");
	System.out.println( Franz );
	System.out.println();
	*/


	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   INSERT WELL-COMMENT TIMING APPARATUS HERE
	   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/



	// BASIC PRE-TIMING TESTS
	/*
	for( int i = 0; i < 1000000000; i++ ) {
	    Franz.addLinear( i );
	}
	
	// testing findLin()
	System.out.println("\ntesting findLin");
	System.out.println( Franz );
	System.out.println( Franz.findLin(1) );
	System.out.println( Franz.findLin(5) );
	System.out.println( Franz.findLin(0) );
	System.out.println( Franz.findLin(22) );


	// testing findBin()
	System.out.println("\ntesting findBin");
	System.out.println( Franz );
	System.out.println( Franz.findBin(1) );
	System.out.println( Franz.findBin(5) );
	System.out.println( Franz.findBin(0) );
	System.out.println( Franz.findBin(22) );
	*/


	// TIMED TESTS
	//list of changeable size (depending on how long you want to wait)
	//search for each item in the list using each search
	//forces search of all items from beginning to end
	//returns time from start of search to end

	Franz = new OrderedArrayList();

	long size = 100000;// currentTimeMillis is a long
	long time; //will be time lapsed
	long time1;//will be time at start of run
	long time2;//will be time after run
	
	// populate Franz
	for( int i = 0; i < size; i++ ) {
	    Franz.add( i );
	}

	
	// testing findLin()	    
	System.out.println("\ntesting linear search");

	time1 = System.currentTimeMillis();//right before findlin
	
	for (int i = 0; i < size; i++ ) {//running
	    Franz.findLin( i );
	}

	time2 = System.currentTimeMillis();//after run
	time = time2 - time1;//time taken to do the work
	System.out.println("\nLinear Search Time: " + time);


	
	// testing findBin()
	System.out.println("\ntesting binary search");

	time1 = System.currentTimeMillis();//before run

	for (int i = 0; i < size; i++ ) {//running
	    Franz.findBin( i );
	}

	time2 = System.currentTimeMillis();//after run
	time = time2 - time1;//time elapsed
	System.out.println("\nBinary Search Time: " + time);
    }

}//end class OrderedArrayList
 
