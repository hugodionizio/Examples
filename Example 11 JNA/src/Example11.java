// Projeto : JNA Example #11
// Classe: Example11.java
// Função : : Retrieve a Struct Containing an Array of Doubles from C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Seg Mai 11 15:29:20 BRT 2015

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;
import com.sun.jna.Structure.ByValue;

public class Example11 {
	public interface CLibrary extends Library {
		public static class Example11Struct extends Structure {
			public static class ByValue extends Example11Struct implements Structure.ByValue {}
		
			public int numVals;
			public Pointer vals; // double*
		}

		public Example11Struct.ByValue example11_getStruct();
		public void example11_cleanup(Pointer pVals);
	}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example11Struct.ByValue ex11val;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample11.so", CLibrary.class);
			// call the C function to retrieve an Example11Struct (by value)
			// note that although the struct is copied, it still contains a pointer
			// (that is not deep-copied)
			ex11val = clib.example11_getStruct();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample11.so", CLibrary.class);
			// call the C function to retrieve an Example11Struct (by value)
			// note that although the struct is copied, it still contains a pointer
			// (that is not deep-copied)
			ex11val = clib.example11_getStruct();
		}

		System.out.println("example 11: retrieved " + ex11val.numVals + " values:");
		double ex11total = 0.0;
		if (ex11val.numVals > 0) {
			for (int ex11loop=0; ex11loop<ex11val.numVals; ex11loop++) {
				// extract each double value from the buffer held by the struct
				double ex11d = ex11val.vals.getDouble(ex11loop * Native.getNativeSize(Double.TYPE));
				ex11total += ex11d;
			}
			System.out.println("\t(example 11: cleanup)");
			clib.example11_cleanup(ex11val.vals);
		}
		System.out.println("\ttotal: " + ex11total);
	}
}
// Fim da classe
