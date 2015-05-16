// Projeto : JNA Example #14
// Classe: Example14.java
// Função : : Receive a Struct Containing an Array of Strings from C
// Autor(es) : Hugo Dionizio Santos
// Data : Sáb Mai 16 16:27:44 BRT 2015

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;

public class Example14 {
	public interface CLibrary extends Library {
		public static class Example14Struct extends Structure {
			public static class ByValue extends Example14Struct implements Structure.ByValue {}
		
			public int numVals;
			public Pointer vals; // char**
		}

		public Example14Struct.ByValue example14_getStrings();
		public void example14_cleanup(Example14Struct.ByValue sVal);
}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example14Struct.ByValue ex14val;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample14.so", CLibrary.class);
			// Receive a Struct Containing an Array of Strings from C
			ex14val = clib.example14_getStrings();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample14.so", CLibrary.class);
			// Receive a Struct Containing an Array of Strings from C
			ex14val = clib.example14_getStrings();
		}

		System.out.println("example 14: retrieved " + ex14val.numVals + " values:");

		// getStringArray copies the contents of the C-allocated memory buffer into a Java-managed String[]
		String[] ex14vals = ex14val.vals.getStringArray(0, ex14val.numVals);
		for (int ex14loop=0; ex14loop<ex14val.numVals; ex14loop++) {
			System.out.println("\t" + ex14vals[ex14loop]);
		}
		System.out.println("\t(example 14 cleanup)");
		clib.example14_cleanup(ex14val);
	}
}

// Fim da classe
