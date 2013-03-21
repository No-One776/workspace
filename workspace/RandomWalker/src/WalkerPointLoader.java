import java.util.*;
import java.io.*;

/*************************************************************************
*
* WalkerPointLoader.java
*
* This class creates an array of WalkerPoint objects based on
* descriptions contained in a file.
*
*************************************************************************/
class WalkerPointLoader
{
    // Initial size of the array of WalkerPoint objects
    public static final int INITIAL_SIZE = 1;

    // ///////////////////////////////////////////////////////////////
    //
    // Scanner openFile(String)
    //
    // Returns a Scanner object that reads from the specified file, or
    // null if there's an error.
    //
    public static Scanner openFile(String fileName)
    {
        File file = new File(fileName);
        Scanner answer = null;
        try {
            answer = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Can't open file " + fileName + " because "
                    + e.getMessage());
        }
        return answer;
    }

    // ///////////////////////////////////////////////////////////////
    //
    // static WalkerPoint makeWalkerPointFromString(String)
    //
    // Creates a WalkerPoint object based on the description of the
    // desired point contained in String s.
    //
    // The String should have the format
    //
    // class:parm,parm,parm, ...
    //
    // where "class" is the class of the object to be created.
    // "class" must be "WalkerPoint" or a subclass of WalkerPoint.
    //
    // After the colon comes a comma-separated list of parameters to
    // the initialize() method. For a WalkerPoint, the values are the
    // red component, green component, and blue component of its color
    // (in that order). Subclasses of WalkerPoint may define
    // different input strings.
    //
    public static WalkerPoint makeWalkerPointFromString(String s)
    {
        // Split s into two parts: The part before the colon, and the
        // part after.
        String[] parts = s.split(":");

        // If s doesn't have two parts, the input String isn't valid.
        // Give up and return null.
        if (parts.length < 2) {
            System.err.printf("Input string \"%s\" is not "
                    + "formatted correctly", s);
            return null;
        }

        // Now, try to obtain a Class object describing the particular
        // java class we want to instantiate (WalkerPoint,
        // WalkerPointMod, WalkerPointReset, etc.). The forName
        // method will throw a "ClassNotFound" exception if the
        // specified class doesn't exist (e.g., if the user requests a
        // "WalkerPointBob"); therefore, it must be contained in a
        // "try-catch" block.
        Class<?> desiredClass = null;
        try {
            desiredClass = Class.forName(parts[0]);
        } catch (ClassNotFoundException e) {

            // If the user requests a class that doesn't exist, print
            // an error message to stderr and return null.
            System.err.println("ERROR.  Couldn't load class " + parts[0]
                    + " because " + e.getMessage());
            return null;
        }

        WalkerPoint p = null;

        // Now that we have a Class object describing the class we
        // want to instantiate, we use the newInstance method to
        // create an instance of the desired class. newInstance calls
        // the constructor that takes no parameters. If the desired
        // class doesn't implement a constructor that takes no
        // parameters, newInstance throws an InstantiationException.
        // Therefore, the call to newInstance is contained in a
        // try-catch block.
        try {
            p = (WalkerPoint) desiredClass.newInstance();
        } catch (InstantiationException e) {
            // If there's a problem, print an error message to stderr
            // and return null.
            System.err.println("ERROR.  Couldn't instantiate class " + parts[0]
                    + " because " + e.getMessage());
            return null;
        } catch (IllegalAccessException e) {
            System.err.println("ERROR.  Couldn't instantiate class " + parts[0]
                    + " because " + e.getMessage());
            return null;
        }

        // Finally, use the part of s after the colon to initialize
        // the object. The call to trim removes any whitespace at the
        // beginning or end of the string.
        if (!p.initialize(parts[1].trim())) {
            System.err.println("\tInitialize failed: " + parts[1]);
            return null;
        }

        return p;
    }

    public static WalkerPoint[] loadWalkerPointsFromFile(String fileName)
    {
        Scanner input = openFile(fileName);

        if (input == null) {
            return null;
        }
        return loadWalkerPointsFromScanner(input);
    }

    // ////////////////////////////////////////////////////////////////
    //
    // static WalkerPoint[] loadWalkerPointsFromScanner(Scanner)
    //
    // This method creates one WalkerPoint for each line obtained from
    // the Scanner object passed as input, puts those WalkerPoints in
    // an array, and returns the array.
    //
    public static WalkerPoint[] loadWalkerPointsFromScanner(Scanner input)
    {
        WalkerPoint[] answer = new WalkerPoint[INITIAL_SIZE];

        ArrayGrower<WalkerPoint> ag = new ArrayGrower<WalkerPoint>(
                new WalkerPoint[1]);

        int x = 0;
        while (input.hasNextLine()) {

            // trim removes any extra whitespace.
            String line = input.nextLine().trim();

            // This allows us to ignore any blank lines, or lines that
            // begin with a # (i.e., comments.)
            if (line.equals("") || line.charAt(0) == '#') {
                continue;
            }

            // grow the array, if necessary
            if (x == answer.length) {
                // answer = growWalkerPointArray(answer);
                answer = ag.growArray(answer);
            }

            // Make a new WalkerPoint based on the next line in the file
            answer[x] = makeWalkerPointFromString(line);

            // Return null if we find an invalid line in the input file
            if (answer[x] == null) {
                return null;
            }
            x++;
        } // end for

        // Return an array that contains only WalkerPoint objects (no
        // null slots).
        // return shrinkWalkerPointArray(answer, x);
        return ag.shrinkArray(answer, x);
    } // end loadWalkerPointsFromFile

    public static WalkerPoint[] growWalkerPointArray(WalkerPoint[] smallArray)
    {

        // Create a new, larger array
        WalkerPoint[] new_array = new WalkerPoint[smallArray.length * 2];

        // Copy the data from the "small" array into the bigger array
        for (int loop = 0; loop < smallArray.length; loop++) {
            new_array[loop] = smallArray[loop];
        }
        return new_array;
    } // end growArray

    public static WalkerPoint[] shrinkWalkerPointArray(
            WalkerPoint[] largeArray, int desiredSize)
    {

        // The method that calls shrinkArray is responsible for
        // checking that desiredSize is at most largeArray.length. If
        // the calling method fails to ensure this condition, we
        // simply quit the program.
        if (desiredSize > largeArray.length) {
            System.err.println("ERROR!  The size parameter to shrinkArray "
                    + "is larger than the array itself!");
            System.exit(0);
        }

        // Create an array of the desired size.
        WalkerPoint[] answer = new WalkerPoint[desiredSize];

        // Copy the data from the large array into the small array.
        for (int loop = 0; loop < desiredSize; loop++) {
            answer[loop] = largeArray[loop];
        }
        return answer;
    }

} // end WalkerPointLoader