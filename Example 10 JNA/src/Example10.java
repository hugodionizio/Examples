// Projeto : JNA Example #10
// Classe: Example10.java
// Função : : Send a Struct Containing an Array of Doubles to C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Seg Mai 11 11:46:18 BRT 2015

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;
import com.sun.jna.Memory;

public class Example10 {
	public interface CLibrary extends Library {
		public static class Example10Struct extends Structure {
			public static class ByReference extends Example10Struct implements Structure.ByReference {}
		
			public int numVals;
			public Pointer vals; // double*
		}

		public double example10_sendStruct(Example10Struct.ByReference val);
	}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example10Struct.ByReference ex10val;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample10.so", CLibrary.class);

			// set up an Example10Struct with 100 values
			ex10val = new CLibrary.Example10Struct.ByReference();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample10.so", CLibrary.class);

			// set up an Example10Struct with 100 values
			ex10val = new CLibrary.Example10Struct.ByReference();
		}

		ex10val.numVals = 100;

		// allocate memory for the 100 double values
		ex10val.vals = new Memory(100 * Native.getNativeSize(Double.TYPE));

		// note: Memory instance (and its associated memory allocation) will be freed when ex10val goes out of scope
		// fill in 100 double values into the block of allocated memory
		for (int dloop=0; dloop<100; dloop++) {
			// fill in junk values just for the sake of this example
			ex10val.vals.setDouble(dloop * Native.getNativeSize(Double.TYPE), ((double)dloop + 100) / 100);
		}

		// call the C function
		double ex10total = clib.example10_sendStruct(ex10val);
		System.out.println("example 10: " + ex10total);
	}
}

// Fim da classe
