// Projeto : JNA Example #20
// Classe: Example20.java
// Função : : Send an array of unions to C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Sáb Mai 16 16:27:45 BRT 2015

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;
import com.sun.jna.Union;
import com.sun.jna.StringArray;

public class Example20 {
	public interface CLibrary extends Library {
	    public static interface Example20UnionType {
		public static final int UNKNOWN = 0;
		public static final int INTEGER = 1;
		public static final int FLOAT = 2;
		public static final int STRING = 3;
	    }    
		public static class Example20Union extends Union {
			public static class ByValue extends Example20Union implements Union.ByValue {};
		
			public int intnumber;
			public float floatnumber;
			public String stringval;
		}
	
		public static class Example20UnionHolder extends Structure {
			public static class ByReference extends Example20UnionHolder implements Structure.ByReference {}
		
			public int uniontype;
			public Example20Union unionval;
		}
	
		public static class Example20UnionList extends Structure {
			public static class ByReference extends Example20UnionList implements Structure.ByReference {}
		
			public int numVals;
			public Example20UnionHolder.ByReference vals;
		}

		public void example20_send(Example20UnionList.ByReference pList);
	}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example20UnionList.ByReference ex20listref;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample20.so", CLibrary.class);
			// 
			ex20listref = new CLibrary.Example20UnionList.ByReference();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample20.so", CLibrary.class);
			// 
			ex20listref = new CLibrary.Example20UnionList.ByReference();
		}

		ex20listref.numVals = 3;
		ex20listref.vals = new CLibrary.Example20UnionHolder.ByReference();
		CLibrary.Example20UnionHolder[] ex20holders = (CLibrary.Example20UnionHolder[])ex20listref.vals.toArray(3);
		ex20holders[0].uniontype = CLibrary.Example20UnionType.INTEGER;
		ex20holders[0].unionval.setType(Integer.TYPE);
		ex20holders[0].unionval.intnumber = 31;
		ex20holders[1].uniontype = CLibrary.Example20UnionType.FLOAT;
		ex20holders[1].unionval.setType(Float.TYPE);
		ex20holders[1].unionval.floatnumber = 32.3f;
		ex20holders[2].uniontype = CLibrary.Example20UnionType.STRING;
		ex20holders[2].unionval.setType(String.class);
		ex20holders[2].unionval.stringval = "hello";
//		ex20holders.write();
		clib.example20_send(ex20listref);
	}
}

// Fim da classe
