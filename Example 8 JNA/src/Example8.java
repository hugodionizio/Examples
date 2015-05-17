// Projeto : JNA Example #8
// Classe: Example8.java
// Função : Send an Array of Doubles to C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Seg Mai 11 03:49:32 BRT 2015

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;
import com.sun.jna.Memory;

public class Example8 {
	public interface CLibrary extends Library {
		public double example8_sendDoubleArray(Pointer p, int numVals);
	}

	static public void main(String argv[]) {
		CLibrary clib;
		Pointer ex8p;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample8.so", CLibrary.class);
			// Memory allocates a contiguous block of memory suitable for sharing with C directly - no copying is necessary
			ex8p = new Memory(100 * Native.getNativeSize(Double.TYPE));
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample8.so", CLibrary.class);
			// Memory allocates a contiguous block of memory suitable for sharing with C directly - no copying is necessary
			ex8p = new Memory(100 * Native.getNativeSize(Double.TYPE));
		}

		// note: Memory instance (and its associated memory allocation) will be freed when ex8p goes out of scope
		for (int dloop=0; dloop<100; dloop++) {
			// populate the array with junk data (just for the sake of the example)
			ex8p.setDouble(dloop * Native.getNativeSize(Double.TYPE), ((double)dloop + 100) / 100);
		}

		// call the C function
		double ex8total = clib.example8_sendDoubleArray(ex8p, 100);
		System.out.println("example 8: " + ex8total);	
	}
}
// Fim da classe
