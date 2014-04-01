package jkind.lustre;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jkind.lustre.visitors.AstVisitor;

public class Program extends Ast {
	final public List<TypeDef> types;
	final public List<Constant> constants;
	final public List<Node> nodes;
	final public String main;

	public Program(Location location, List<TypeDef> types, List<Constant> constants,
			List<Node> nodes, String main) {
		super(location);
		this.types = Collections.unmodifiableList(types);
		this.constants = Collections.unmodifiableList(constants);
		this.nodes = Collections.unmodifiableList(nodes);
		if (main == null && nodes.size() > 0) {
			this.main = nodes.get(nodes.size() - 1).id;
		} else {
			this.main = main;
		}
	}

	public Program(List<TypeDef> types, List<Constant> constants, List<Node> nodes) {
		this(Location.NULL, types, constants, nodes, null);
	}

	public Program(List<Node> nodes) {
		this(Location.NULL, Collections.<TypeDef>emptyList(), Collections.<Constant>emptyList(), nodes, null);
	}

	public Program(Node... nodes) {
		this(Location.NULL, Collections.<TypeDef>emptyList(), Collections.<Constant>emptyList(), Arrays
				.asList(nodes), null);
	}
	
	public Node getMainNode() {
		for (Node node : nodes) {
			if (node.id.equals(main)) {
				return node;
			}
		}
		return null;
	}

	@Override
	public <T, S extends T> T accept(AstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}
}