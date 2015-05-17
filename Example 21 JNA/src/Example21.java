// Projeto : JNA Example #21
// Classe: Example21.java
// Função : : Receive an array of unions from C
// Autor(es) : Hugo Dionizio Santos
// Data : Sáb Mai 16 16:27:45 BRT 2015

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;
import com.sun.jna.Union;

public class Example21 {
	public interface CLibrary extends Library {
		public static interface Example21UnionType {
		    public static final int UNKNOWN = 0;
		    public static final int INTEGER = 1;
		    public static final int FLOAT = 2;
		    public static final int STRING = 3;
		}    
		public static class Example21Union extends Union {
			public static class ByValue extends Example21Union implements Union.ByValue {};
		
			public int intnumber;
			public float floatnumber;
			public String stringval;
		}
	
		public static class Example21UnionHolder extends Structure {
			public static class ByReference extends Example21UnionHolder implements Structure.ByReference {}
		
			public int uniontype;
			public Example21Union unionval;
		}
	
		public static class Example21UnionList extends Structure {
			public static class ByValue extends Example21UnionList implements Structure.ByValue {}
		
			public int numVals;
			public Example21UnionHolder.ByReference vals;
		}

		public Example21UnionList.ByValue example21_get();
		public void example21_cleanup(Example21UnionList.ByValue list);
	}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example21UnionList.ByValue ex21list;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample21.so", CLibrary.class);
			// 
			ex21list = clib.example21_get();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample21.so", CLibrary.class);
			// 
			ex21list = clib.example21_get();
		}

		ex21list.read();
		System.out.println("Example 21: received " + ex21list.numVals + " values:");
		CLibrary.Example21UnionHolder[] ex21holders = (CLibrary.Example21UnionHolder[])ex21list.vals.toArray(ex21list.numVals);
		for (int i=0; i<ex21list.numVals; i++)
		{
			switch (ex21holders[i].uniontype)
			{
				case CLibrary.Example21UnionType.INTEGER:
				{
					ex21holders[i].unionval.setType(Integer.TYPE);
					ex21holders[i].read();
					System.out.println("example 21: received integer value " + ex21holders[i].unionval.intnumber);
					break;
				}
				case CLibrary.Example21UnionType.FLOAT:
				{
					ex21holders[i].unionval.setType(Float.TYPE);
					ex21holders[i].read();
					System.out.println("example 21: received float value " + ex21holders[i].unionval.floatnumber);
					break;
				}
				case CLibrary.Example21UnionType.STRING:
				{
					ex21holders[i].unionval.setType(String.class);
					ex21holders[i].read();
					System.out.println("example 21: received string value " + ex21holders[i].unionval.stringval);
					break;
				}
				default:
				{
					System.err.println("example 21: error: unrecognized union type received: " + ex21holders[i].uniontype);
					break;
				}
			}
		}

		// cleanup
		System.out.println("Example 21 cleanup");
		ex21list.write();
		clib.example21_cleanup(ex21list);
	}
}

// Fim da classe
