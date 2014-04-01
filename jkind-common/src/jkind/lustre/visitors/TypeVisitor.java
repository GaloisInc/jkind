package jkind.lustre.visitors;

import jkind.lustre.ArrayType;
import jkind.lustre.NamedType;
import jkind.lustre.RecordType;
import jkind.lustre.SubrangeIntType;

public interface TypeVisitor<T> {
	public T visit(ArrayType e);
	public T visit(NamedType e);
	public T visit(RecordType e);
	public T visit(SubrangeIntType e);
}
