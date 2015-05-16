// Projeto : JNA Example #13
// Classe: Example13.java
// Função : : Send a Struct Containing an Array of Strings to C
// Autor(es) : Hugo Dionizio Santos
// Data : Sáb Mai 16 12:40:00 BRT 2015

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;
import com.sun.jna.StringArray;

public class Example13 {
	public interface CLibrary extends Library {
		public static class Example13Struct extends Structure {
			public static class ByReference extends Example13Struct implements Structure.ByReference {}
		
			public int numVals;
			public Pointer vals; // char**
		}

		public void example13_sendStrings(Example13Struct.ByReference pVal);
	}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example13Struct.ByReference ex13ref;

		// generate a basic String array
		String[] ex13vals = new String[5];
		ex13vals[0] = "one";
		ex13vals[1] = "two";
		ex13vals[2] = "three";
		ex13vals[3] = "four";
		ex13vals[4] = "five";

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample13.so", CLibrary.class);
			ex13ref = new CLibrary.Example13Struct.ByReference();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample13.so", CLibrary.class);
			ex13ref = new CLibrary.Example13Struct.ByReference();
		}
		ex13ref.numVals = 5;

		// we mapped the char** to a Pointer; StringArray is a Pointer subclass that manages memory
		// specifically for an array of strings
		// the StringArray constructor copies the contents of the Java String[] into the Pointer's
		// contiguous memory
		// (note that because we are copying each String value, this may not be as efficient for large
		// string arrays as something closer to example 8)
		ex13ref.vals = new StringArray(ex13vals);

		// note: like Memory, StringArray will free the contiguous block of memory it copied the Strings into when the instance goes out of scope
		clib.example13_sendStrings(ex13ref);	
	}
}
// Fim da classe
