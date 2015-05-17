// Example #2: Send a String to C, Receive a String from C

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;

public class Example2 {
	public interface CLibrary extends Library {
		public void example2_sendString(String val);
		public void example2_getString(PointerByReference val);
		public void example2_cleanup(Pointer p);
	}

	static public void main(String argv[]) {
		CLibrary clib;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample2.so", CLibrary.class);
			// send a string to C
			clib.example2_sendString("fooey");
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample2.so", CLibrary.class);
			// send a string to C
			clib.example2_sendString("fooey");
		}
		
		// get string from C
		// allocate a void**
		PointerByReference ptrRef = new PointerByReference();
		// call the C function
		clib.example2_getString(ptrRef);
		// extract the void* that was allocated in C
		Pointer p = ptrRef.getValue();
		// extract the null-terminated string from the Pointer
		String val = p.getString(0);
		System.out.println("example 2b: " + val);

		// clean up memory allocated in C
		clib.example2_cleanup(p);
	}
}




