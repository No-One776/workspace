import java.util.ArrayList;
class ArrayGrower<T>
{

    public ArrayGrower(T[] _dummy) {dummy = _dummy;}

    /////////////////////////////////////////////////////////////////
    //
    // growArray
    //
    // This method takes as input a full array, and returns a new,
    // larger array that contains the same data.
    //
    // Note: In java, you can't create a new array of a generic type
    // (there are type-safety issues).  Thus, I wrote the method
    // makeNewArray to get around this limitation.
    //
    public  T[] growArray(T[] smallArray) {
	
	T[] new_array = makeNewArray(smallArray, smallArray.length * 2); 

	// Copy the data from the "small" array into the bigger array
	for (int loop = 0; loop < smallArray.length; loop++) {
	    new_array[loop] = smallArray[loop];
	}
	return new_array;
    } // end growArray

    public T[] shrinkArray(T[] largeArray, int desiredSize) 
    {
	// The method that calls shrinkArray is responsible for
	// checking that desiredSize is at most largeArray.length.  If
	// the calling method fails to ensure this condition, we
	// simply quit the program.
	if (desiredSize > largeArray.length) {
	    System.err.println("ERROR!  The size parameter to shrinkArray "
			       + "is larger than the array itself!");
	    System.exit(0);
	}

	T[] answer = makeNewArray(largeArray, desiredSize);

	// Copy the data from the large array into the small array.
	for (int loop = 0; loop < desiredSize; loop++) {
	    answer[loop] = largeArray[loop];
	}
	return answer;
    }
    

    /////////////////////////////////////////////////////////////////
    //
    // growArray
    //
    // This method takes as input a full array, and returns a new,
    // larger array that contains the same data.
    //
    // Note: In java, you can't create a new array of a generic type
    // (there are type-safety issues).  This method is a back-door to
    // getting a generic array of a different size.  I wrote it only
    // to be able to introduce generics in Java.  In practice, you
    // would use the ArrayList class directly; you wouldn't ever
    // resize arrays by hand.
    //
    public T[] makeNewArray(T[] oldArray, int desired_size)
    {

	ArrayList<T> al = new ArrayList<T>(desired_size);
	for (int x = 0; x < desired_size; x++) {
	    al.add(oldArray[x % oldArray.length]);
	}
	T[] answer = al.toArray(dummy);
	for (int x = oldArray.length; x < answer.length; x++) {
	    answer[x] = null;
	}
	return answer;

    }


    protected T[] dummy;


} // end class ArrayGrower