package ar.edu.unlu.chatmvc.observer;

import ar.edu.unlu.chatmvc.modelo.Eventos;

public interface IObservador {
    public void actualizar(IObservable observable, Object e);
}
