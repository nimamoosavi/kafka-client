package app.ladderproject.kafka.service;

import com.webold.framework.domain.dto.BaseDTO;
import com.webold.framework.packages.kafka.KafkaConsumer;

import java.util.List;
/**
 * @author nima
 * @version 1.0.1
 * @apiNote you must know that all data consume in list of Object in memory .. and when you dont empty this list ... application may be crashed
 * @since 1.0.1
 */
public interface KafkaConsumerService extends KafkaConsumer {

    /**
     * @return list of Objects that consume list of Data
     */
    List<Object> kafkaMessages();

    /**
     * @return the result of remove the list of consume objects
     */
    BaseDTO<Boolean> removeAll();

    /**
     * @return the last of object that consumed in list
     */
    Object getLastMessage();

    /**
     * @param tClass the object of class you want cast to it
     * @param <R>    the type of class you want return
     * @return the last of object that consumed in list
     */
    <R> R getLastMessage(Class<R> tClass);

    /**
     * @return the first of object that consumed in list
     */
    Object getFirstMessage();

    /**
     * @param tClass the object of class you want cast to it
     * @param <R>    the type of class you want return
     * @return the first of object that consumed in list
     */
    <R> R getFirstMessage(Class<R> tClass);

    /**
     * @return the first of object that consumed in list
     * @apiNote remove the object from list after return
     */
    Object getAndRemoveFirstMessage();

    /**
     * @param tClass the object of class you want cast to it
     * @param <R>    the type of class you want return
     * @return the first of object that consumed in list
     * @apiNote remove the object from list after return
     */
    <R> R getAndRemoveFirstMessage(Class<R> tClass);

    /**
     * @return the last of object that consumed in list
     * @apiNote remove the object from list after return
     */
    Object getAndRemoveLastMessage();

    /**
     * @param tClass the object of class you want cast to it
     * @param <R>    the type of class you want return
     * @return the last of object that consumed in list
     * @apiNote remove the object from list after return
     */
    <R> R getAndRemoveLastMessage(Class<R> tClass);

    /**
     * @return the size of consumed Data
     */
    int messagesList();

    /**
     * @param index of list you want to get
     * @return the object that consumed in list
     */
    Object getMessage(int index);

    /**
     * @param index  of list you want to get
     * @param tClass the object of class you want cast to it
     * @param <R>    the type of class you want return
     * @return the object that consumed in list
     */
    <R> R getMessage(int index, Class<R> tClass);

    /**
     * @param index  of list you want to get
     * @param tClass the object of class you want cast to it
     * @param <R>    the type of class you want return
     * @return the object that consumed in list
     * @apiNote remove the object from list after return
     */
    <R> R getAndRemoveMessage(int index, Class<R> tClass);
}
