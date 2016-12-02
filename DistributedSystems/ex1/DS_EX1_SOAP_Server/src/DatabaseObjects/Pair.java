package DatabaseObjects;

import java.io.Serializable;

public class Pair<L,R> implements Serializable {
	private static final long serialVersionUID = 1L;
	
  private L left;
  private R right;

  public Pair(L left, R right) {
    this.left = left;
    this.right = right;
  }

  public L getLeft() { return left; }
  public void setLeft(L l) { left = l; }
  public R getRight() { return right; }
  public void setRight(R r) { right = r; }

  @Override
  public int hashCode() { return left.hashCode() ^ right.hashCode(); }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Pair)) return false;
    Pair<?, ?> pairo = (Pair<?, ?>) o;
    return this.left.equals(pairo.getLeft()) &&
           this.right.equals(pairo.getRight());
  }
  
  @Override
	public String toString() {
		return left.toString() + " = " + right.toString(); 
	}

}