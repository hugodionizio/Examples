// Projeto : JNA Example #4
// Classe : Example4.java
// Função : Get a Struct from C (By Value)
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Dom, Mai 10 2015 03:28:33 BRT

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;
import com.sun.jna.Structure.ByValue;

public class Example4 {
	public interface CLibrary extends Library {
		public static class Example4Struct extends Structure {
			public static class ByValue extends Example4Struct implements Structure.ByValue {}
		
			public int val;
		}

		public Example4Struct.ByValue example4_getStruct();
	}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example4Struct.ByValue e4val;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample4.so", CLibrary.class);
			// get a Struct from C (By Value)
			e4val = clib.example4_getStruct();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample4.so", CLibrary.class);
			// get a Struct from C (By Value)
			e4val = clib.example4_getStruct();
		}

		System.out.println("example 4: " + e4val.val);
	}
}
