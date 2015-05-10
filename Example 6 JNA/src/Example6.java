// JNA Example #6: Send an Array of Structs to C

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;

public class Example6 {
	public interface CLibrary extends Library {
		public static class Example6Struct extends Structure {
			public static class ByReference extends Example6Struct implements Structure.ByReference {}
		
			public int val;
		}

		public int example6_sendStructArray(Example6Struct.ByReference vals, int numVals);
	}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example6Struct.ByReference e6ref;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample6.so", CLibrary.class);
			// send an Array of Structs to C
			e6ref = new CLibrary.Example6Struct.ByReference();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample6.so", CLibrary.class);
			// send an Array of Structs to C
			e6ref = new CLibrary.Example6Struct.ByReference();
		}

		// Structure.toArray allocates a contiguous block of memory internally
		// note that for large arrays, this can be extremely slow
		CLibrary.Example6Struct[] vals = (CLibrary.Example6Struct[])e6ref.toArray(3);

		// Note: do NOT allocate a new object for each struct in the array -
		//			toArray() has already allocated each array item within a contiguous block
		//			of memory
		vals[0].val = 1;
		vals[1].val = 2;
		vals[2].val = 3;
		int total = clib.example6_sendStructArray(e6ref, 3);
		System.out.println("example 6: " + total);
	}
}
