package com.hunybei.util;

import java.util.NoSuchElementException;

/**
 * 队列的数据结构顶级接口 , 先进先出
 *
 * @param <E>
 */
public interface Queue<E> extends Collection<E> {
    /**
     * 插入到队列中， 如果队列已经满了的话抛出 IllegalStateException 异常
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws IllegalStateException    if the element cannot be added at this
     *                                  time due to capacity restrictions
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this queue
     * @throws NullPointerException     if the specified element is null and
     *                                  this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *                                  prevents it from being added to this queue
     */
    boolean add(E e);

    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element only
     * by throwing an exception.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this queue, else
     * {@code false}
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this queue
     * @throws NullPointerException     if the specified element is null and
     *                                  this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *                                  prevents it from being added to this queue
     */
    boolean offer(E e);

    /**
     * 检索并删除此队列的头。队列为空会抛出异常
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    E remove();

    /**
     * 检索并删除此队列的头，如果队列为空返回null
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    E poll();

    /**
     * 检索但不删除此队列的头 , 如果队列为空 抛出异常
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    E element();

    /**
     * 检索但不删除此队列的头 , 如果队列为空 返回null
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    E peek();
}
