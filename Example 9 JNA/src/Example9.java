// Projeto : JNA Example #9
// Classe: Example9.java
// Função : Receive an Array of Doubles from C
// Autor(es) : Hugo Dionizio Santos
// Data : Seg Mai 11 11:05:54 BRT 2015

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;
import com.sun.jna.ptr.IntByReference;

public class Example9 {
	public interface CLibrary extends Library {
		public double example9_getDoubleArray(PointerByReference valsRef, IntByReference numValsRef);
		public void example9_cleanup(Pointer p);
}

	static public void main(String argv[]) {
		CLibrary clib;
		PointerByReference ex9ValsRefPtr;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample9.so", CLibrary.class);
			// Receive an Array of Doubles from C
			ex9ValsRefPtr = new PointerByReference();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample9.so", CLibrary.class);
			// Receive an Array of Doubles from C
			ex9ValsRefPtr = new PointerByReference();
		}

		IntByReference ex9NumValsRef = new IntByReference();

		// call the C function
		clib.example9_getDoubleArray(ex9ValsRefPtr, ex9NumValsRef);

		// extract the number of values returned
		int ex9NumVals = ex9NumValsRef.getValue();
		System.out.println("example 9: retreived " + ex9NumVals + " values:");

		// make sure the C function returned some values
		if (0 < ex9NumVals) {
			// extract the pointed-to pointer
			Pointer ex9pVals = ex9ValsRefPtr.getValue();
			// look at each value
			double ex9total = 0.0;
			for (int ex9Loop=0; ex9Loop<ex9NumVals; ex9Loop++) {
				double ex9val = ex9pVals.getDouble(ex9Loop * Native.getNativeSize(Double.TYPE));
				ex9total += ex9val;
			}
			System.out.println("\ttotal: " + ex9total);
	
			// call C code to clean up memory allocated in C
			System.out.println("\t(example 9: cleanup)");
			clib.example9_cleanup(ex9pVals);
		}		
	}
}

// Fim da classe
