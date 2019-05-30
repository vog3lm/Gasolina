package app;

public interface Decorateable<R,T> {

	public R decorate(T decoration);
	
}
