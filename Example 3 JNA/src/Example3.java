// Example #3: Send a Struct to C (By Reference)

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;

public class Example3 {
	public interface CLibrary extends Library {
		public static class Example3Struct extends Structure {
			public static class ByReference extends Example3Struct implements Structure.ByReference {}
		
			public int val;
		}

		// unless otherwise specified, ByReference is assumed - but it can't hurt to be explicit
		public void example3_sendStruct(Example3Struct.ByReference sval);
	}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example3Struct.ByReference e3ref;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample3.so", CLibrary.class);
			// send a Struct to C (By Reference)
			e3ref = new CLibrary.Example3Struct.ByReference();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample3.so", CLibrary.class);
			// send a Struct to C (By Reference)
			e3ref = new CLibrary.Example3Struct.ByReference();
		}
		
		e3ref.val = 7;
		clib.example3_sendStruct(e3ref);
	}
}




