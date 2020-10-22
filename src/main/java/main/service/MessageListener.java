package main.service;

import main.model.Message;

// Опциональный интерфейс для лисенеров.
public interface MessageListener<T>
{
    // @param <T> тип сообщений, которые будем слушать.
    void handleMessage(Message<T> incomingMessage);
}