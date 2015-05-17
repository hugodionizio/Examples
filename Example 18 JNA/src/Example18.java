// Projeto : JNA Example #18
// Classe: Example18.java
// Função : : Send a union to C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Sáb Mai 16 16:27:45 BRT 2015

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;		
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;
import com.sun.jna.Union;

public class Example18 {
	public interface CLibrary extends Library {
    public static interface Example18UnionType {
        public static final int UNKNOWN = 0;
        public static final int INTEGER = 1;
        public static final int FLOAT = 2;
        public static final int STRING = 3;
    }    
	public static class Example18Union extends Union {
		public static class ByValue extends Example18Union implements Union.ByValue {};
		
		public int intnumber;
		public float floatnumber;
		public String stringval;
	}
	
	public static class Example18UnionHolder extends Structure {
		public static class ByReference extends Example18UnionHolder implements Structure.ByReference {}
		
		public int uniontype;
		public Example18Union unionval;
	}

	public void example18_sendUnion(Example18UnionHolder.ByReference pHolder);
	}

	static public void main(String argv[]) {
		CLibrary clib;
		CLibrary.Example18UnionHolder.ByReference ex18ref1;

		try {
			clib = (CLibrary)Native.loadLibrary("./libexample18.so", CLibrary.class);
			// 
			ex18ref1 = new CLibrary.Example18UnionHolder.ByReference();
		}
		catch (UnsatisfiedLinkError e) {
			clib = (CLibrary)Native.loadLibrary("./bin/libexample18.so", CLibrary.class);
			// 
			ex18ref1 = new CLibrary.Example18UnionHolder.ByReference();		
		}
		ex18ref1.uniontype = CLibrary.Example18UnionType.INTEGER;
		ex18ref1.unionval.setType(Integer.TYPE);
		ex18ref1.unionval.intnumber = 23;
		clib.example18_sendUnion(ex18ref1);

		CLibrary.Example18UnionHolder.ByReference ex18ref2 = new CLibrary.Example18UnionHolder.ByReference();
		ex18ref2.uniontype = CLibrary.Example18UnionType.STRING;
		ex18ref2.unionval.setType(String.class);
		ex18ref2.unionval.stringval = "hello";
		clib.example18_sendUnion(ex18ref2);

		CLibrary.Example18UnionHolder.ByReference ex18ref3 = new CLibrary.Example18UnionHolder.ByReference();
		ex18ref3.uniontype = CLibrary.Example18UnionType.FLOAT;
		ex18ref3.unionval.setType(Float.TYPE);
		ex18ref3.unionval.floatnumber = 2.3f;
		clib.example18_sendUnion(ex18ref3);
	}
}

// Fim da classe
