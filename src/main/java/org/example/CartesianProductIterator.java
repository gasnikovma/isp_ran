package org.example;

import java.util.*;

public class CartesianProductIterator<E> implements Iterator<List<E>> {
    private final List<List<E>> collections;
    private final int[] indices;
    boolean hasNext;

    public CartesianProductIterator(List<? extends Collection<E>> collections) {
        this.collections = new ArrayList<>(collections.size());
        for (Collection<E> collection : collections) {
            this.collections.add(new ArrayList<>(collection));
        }
        this.indices = new int[this.collections.size()];
        this.hasNext = !this.collections.isEmpty();
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public List<E> next() {
        if (!hasNext) {
            throw new NoSuchElementException("collection is empty");
        }
        List<E> result = new ArrayList<>(indices.length);
        for (int i = 0; i < indices.length; i++) {
            result.add(collections.get(i).get(indices[i]));
        }
        for (int i = indices.length - 1; i >= 0; i--) {
            if (indices[i] < collections.get(i).size() - 1) {
                indices[i]++;
                break;
            } else {
                indices[i] = 0;
                if (i == 0) {
                    hasNext = false;
                }
            }
        }
        return result;
    }
}
