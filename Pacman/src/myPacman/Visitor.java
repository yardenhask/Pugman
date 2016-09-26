package myPacman;

public interface Visitor {
public void visit(WeakGhost ghost);
public void visit(MightyGhost ghost);
}
