// Projeto : JNA Example #16
// Classe: Example16.java
// Função : : Receive a Struct Containing an Array of Structs from C
// Autor(es) : Hugo Dionizio Santos
// Data : Sáb Mai 16 16:27:45 BRT 2015

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;

public class Example16 {
	public interface CLibrary extends Library {
		public static class Example16StructA extends Structure {
			public static class ByReference extends Example16StructA implements Structure.ByReference {}
		
			public int val;
		}
	
		public static class Example16StructB extends Structure {
			public static class ByValue extends Example16StructB implements Structure.ByValue {}
		
			public int numAs;
			public Example16StructA.ByReference as;
		}

		public Example16StructB.ByValue example16_get();
		public void example16_cleanup(Example16StructB.ByValue sVal);
}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example16StructB.ByValue ex16val;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample16.so", CLibrary.class);
			// 
			ex16val = clib.example16_get();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample16.so", CLibrary.class);
			// 
			ex16val = clib.example16_get();
		}		

		System.out.println("example 16: retrieved " + ex16val.numAs + " values:");

		// Structure.toArray copies the data from the C-allocated block of memory
		// into a Java array of Example16Struct objects
		// Note that for large arrays, this can be extremely slow
		CLibrary.Example16StructA[] ex16as = (CLibrary.Example16StructA[])ex16val.as.toArray(ex16val.numAs);
		for (int ex16loop=0; ex16loop<ex16val.numAs; ex16loop++) {
			System.out.println("\t" + ex16as[ex16loop].val);
		}

		// although we received a copy of the struct (by value), it contains a pointer
		// to a buffer that was not deep-copied and was allocated by C code - that
		// buffer needs to be freed in C code
		System.out.println("(example 16 cleanup)");
		clib.example16_cleanup(ex16val);
	}
}

// Fim da classe
