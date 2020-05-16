package net.mediator.interfaces;

public interface Handler<T extends Request, R extends Response> {
    R handle(T request);
}
