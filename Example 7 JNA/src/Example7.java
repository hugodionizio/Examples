// Projeto : JNA Example #7
// Classe : Example7.java
// Função : Retrieve an Array of Structs from C
// Autor(es) : Hugo Dionizio Santos
// Data : Dom 10 Mai 2015 20:45:07 BRT 

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;
import com.sun.jna.ptr.IntByReference;

public class Example7 {
	public interface CLibrary extends Library {
		public static class Example7Struct extends Structure {
			public static class ByReference extends Example7Struct implements Structure.ByReference {}
		
			public int val;
		
			public Example7Struct() {}
			public Example7Struct(Pointer p) {
				super(p);
			}
		}

		public void example7_getStructArray(PointerByReference valsRef, IntByReference numValsRef);
		public void example7_cleanupStructArray(Pointer p);
}

	static public void main(String argv[]) {
		CLibrary clib;
		PointerByReference valsRefPtr;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample7.so", CLibrary.class);
			// 
			valsRefPtr = new PointerByReference();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample7.so", CLibrary.class);
			// 
			valsRefPtr = new PointerByReference();
		}

		IntByReference numValsRef = new IntByReference();
		clib.example7_getStructArray(valsRefPtr, numValsRef);
		int numVals = numValsRef.getValue();

		// extract the pointed-to pointer
		Pointer pVals = valsRefPtr.getValue();

		// read the pointed-to memory into an Example7Struct
		CLibrary.Example7Struct valsRef = new CLibrary.Example7Struct(pVals);
		valsRef.read();

		// Structure.toArray copies the data from the C-allocated block of memory
		// into a Java array of Example7Struct objects
		// Note that for large arrays, this can be extremely slow
		CLibrary.Example7Struct[] e7vals = (CLibrary.Example7Struct[])valsRef.toArray(numVals);
		System.out.println("example 7: retrieved " + numVals + " values:");
		for (CLibrary.Example7Struct e7val : e7vals) {
			System.out.println("\t" + e7val.val);
		}

		// call C code to clean up memory allocated in C
		System.out.println("\t(example 7: cleanup)");
		clib.example7_cleanupStructArray(pVals);
	}
}




