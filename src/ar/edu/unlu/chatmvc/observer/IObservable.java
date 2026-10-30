package ar.edu.unlu.chatmvc.observer;

public interface IObservable {
    void agregarObservador(IObservador o);
    void removerObservador(IObservador o);
    void notificarObservadores(Object evento);
}
