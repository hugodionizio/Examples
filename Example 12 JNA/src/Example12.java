// Projeto : JNA Example #12
// Classe: Example12.java
// Função : : Retrieve a Struct Containing an String from C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Sáb Mai 16 09:34:01 BRT 2015

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;
import com.sun.jna.Structure.ByValue;

public class Example12 {
	public interface CLibrary extends Library {
		public static class Example12Struct extends Structure {
			public static class ByValue extends Example12Struct implements Structure.ByValue {}
		
			public String pszVal;
		}

		public Example12Struct.ByValue example12_getString();
		public void example12_cleanup(Example12Struct.ByValue sVal);
}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example12Struct.ByValue ex12val;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample12.so", CLibrary.class);
			// call the C function to retrieve the struct containing a string
			ex12val = clib.example12_getString();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample12.so", CLibrary.class);
			// call the C function to retrieve the struct containing a string
			ex12val = clib.example12_getString();
		}
		System.out.println("example 12: " + ex12val.pszVal);

		// note that although we received the struct by value, it still contains a pointer
		// to a memory buffer (which was not deep-copied) that was allocated on the heap in C -
		// so we need to call a C function to clean up that memory when we're done with it.
		System.out.println("\t(example 12 cleanup)");
		clib.example12_cleanup(ex12val);
	}
}
// Fim da classe
