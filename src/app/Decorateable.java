package app;

public interface Decorateable<R,T> {
	
	public R onDecorate(T decoration);
	
}
