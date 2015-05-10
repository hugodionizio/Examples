// Example #4: Get a Struct from C (By Value)

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
