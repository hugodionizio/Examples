// JNA Example #5: Modify Struct in C

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;

public class Example5 {
	public interface CLibrary extends Library {
		public static class Example5Struct extends Structure {
			public static class ByReference extends Example5Struct implements Structure.ByReference {}
		
			public int val;
		}

		public void example5_fillStruct(Example5Struct.ByReference pval);
	}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example5Struct.ByReference e5ref;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample5.so", CLibrary.class);
			// 
			e5ref = new CLibrary.Example5Struct.ByReference();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample5.so", CLibrary.class);
			//
			e5ref = new CLibrary.Example5Struct.ByReference(); 
		}

		clib.example5_fillStruct(e5ref);
		System.out.println("example 5: " + e5ref.val);

	}
}




