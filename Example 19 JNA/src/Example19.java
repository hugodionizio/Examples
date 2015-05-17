// Projeto : JNA Example #19
// Classe: Example19.java
// Função : : Get a union from C
// Autor(es) : Hugo Dionizio Santos
// Data : Sáb Mai 16 16:27:45 BRT 2015

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;
import com.sun.jna.Union;

public class Example19 {
	public interface CLibrary extends Library {
	    public static interface Example19UnionType {
		public static final int UNKNOWN = 0;
		public static final int INTEGER = 1;
		public static final int FLOAT = 2;
		public static final int STRING = 3;
	    }    
		public static class Example19Union extends Union {
			public static class ByValue extends Example19Union implements Union.ByValue {};
		
			public int intnumber;
			public float floatnumber;
			public String stringval;
		}
	
		public static class Example19UnionHolder extends Structure {
			public static class ByValue extends Example19UnionHolder implements Structure.ByValue {}
		
			public int uniontype;
			public Example19Union unionval;
		}

		public Example19UnionHolder.ByValue example19_getUnion();
	}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example19UnionHolder ex19;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample19.so", CLibrary.class);
			// 
			ex19 = clib.example19_getUnion();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample19.so", CLibrary.class);
			// 
			ex19 = clib.example19_getUnion();
		}
		switch (ex19.uniontype)
		{
			case CLibrary.Example19UnionType.INTEGER:
			{
				ex19.unionval.setType(Integer.TYPE);
				ex19.unionval.read();
				System.out.println("example 19: received integer value " + ex19.unionval.intnumber);
				break;
			}
			case CLibrary.Example19UnionType.FLOAT:
			{
				ex19.unionval.setType(Float.TYPE);
				ex19.unionval.read();
				System.out.println("example 19: received float value " + ex19.unionval.floatnumber);
				break;
			}
			case CLibrary.Example19UnionType.STRING:
			{
				ex19.unionval.setType(String.class);
				ex19.unionval.read();
				System.out.println("example 19: received string value " + ex19.unionval.stringval);
				break;
			}
			default:
			{
				System.err.println("example 19: error: unrecognized union type received: " + ex19.uniontype);
				break;
			}
		}		
	}
}

// Fim da classe
