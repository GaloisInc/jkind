package jkind;

import java.io.File;

import jkind.analysis.Level;
import jkind.analysis.StaticAnalyzer;
import jkind.lustre.Node;
import jkind.lustre.Program;
import jkind.translation.FlattenCompoundTypes;
import jkind.translation.InlineConstants;
import jkind.translation.InlineNodeCalls;
import jkind.translation.InlineUserTypes;
import jkind.translation.Node2Excel;
import jkind.translation.RemoveCondacts;
import jkind.translation.RemoveNonConstantArrayIndices;

public class JLustre2Excel {
	public static void main(String args[]) {
		try {
			if (args.length != 1) {
				System.out.println("usage: jlustre2excel <input>");
				System.exit(-1);
			}
			String filename = args[0];

			if (!new File(filename).exists()) {
				System.out.println("Error: cannot find file " + filename);
				System.exit(-1);
			}

			Program program = Main.parseLustre(filename);
			if (program.getMainNode() == null) {
				System.out.println("Error: no main node");
				System.exit(-1);
			}

			if (!StaticAnalyzer.check(program, Level.WARNING)) {
				System.exit(-1);
			}

			program = InlineUserTypes.program(program);
			program = InlineConstants.program(program);
			program = RemoveCondacts.program(program);
			Node main = InlineNodeCalls.program(program);
			main = RemoveNonConstantArrayIndices.node(main);
			main = FlattenCompoundTypes.node(main);

			String outFilename = filename + ".xls";
			Node2Excel.convert(main, outFilename);
			System.out.println("Wrote " + outFilename);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
