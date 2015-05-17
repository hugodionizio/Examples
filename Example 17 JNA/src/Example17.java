// Projeto : JNA Example #17
// Classe: Example17.java
// Função : : Send a ragged multidimensional array of strings to C
// Autor(es) : Hugo Dionizio Santos
// Data : Sáb Mai 16 16:27:45 BRT 2015

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;
import com.sun.jna.StringArray;

public class Example17 {
	public interface CLibrary extends Library {
	public static class Example17StructA extends Structure {
		public static class ByReference extends Example17StructA implements Structure.ByReference {}
		
		public int numVals;
		public Pointer vals; // char**
	}
	
	public static class Example17StructB extends Structure {
		public static class ByReference extends Example17StructB implements Structure.ByReference {}
		
		public int numVals;
		public Example17StructA.ByReference vals;
	}

	public void example17_sendMultidimensionalArray(Example17StructB.ByReference pVal);
}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example17StructB.ByReference ex17bref;

		// prepare sample data
		String[][] ex17vals = new String[3][];
		ex17vals[0] = new String[2];
		ex17vals[0][0] = "oneone";
		ex17vals[0][1] = "onetwo";
		ex17vals[1] = new String[3];
		ex17vals[1][0] = "twoone";
		ex17vals[1][1] = "twotwo";
		ex17vals[1][2] = "twothree";
		ex17vals[2] = new String[1];
		ex17vals[2][0] = "threeone";

		// set up JNA structure
		try {
			clib = (CLibrary)Native.loadLibrary("./libexample17.so", CLibrary.class);
			ex17bref = new CLibrary.Example17StructB.ByReference();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample17.so", CLibrary.class);
			ex17bref = new CLibrary.Example17StructB.ByReference();
		}

		ex17bref.numVals = ex17vals.length;
		ex17bref.vals = new CLibrary.Example17StructA.ByReference();
		CLibrary.Example17StructA[] ex17aval = (CLibrary.Example17StructA[])ex17bref.vals.toArray(ex17bref.numVals);
		for (int aloop=0; aloop<ex17bref.numVals; aloop++) {
			ex17aval[aloop].numVals = ex17vals[aloop].length;
			ex17aval[aloop].vals = new StringArray(ex17vals[aloop]);
		}

		// pass data to C
		clib.example17_sendMultidimensionalArray(ex17bref);
	}
}

// Fim da classe
