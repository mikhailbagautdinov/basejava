package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        String uuid;
        if (size < storage.length) {
            uuid = r.getUuid();
            if (get(uuid) == null) {
                storage[size++] = r;
            } else {
                System.out.println("ERROR: Resume "+uuid+" already exists!");
            }
        } else {
            System.out.println("ERROR: ArrayStorage overflow!");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("ERROR: Resume "+uuid+" doesn't exist!");
        return null;
    }

    public void delete(String uuid) {
        if (get(uuid) != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[--size] = null;
                    break;
                }
            }
        } else {
            System.out.println("ERROR: Resume "+uuid+" doesn't exist!");
        }
    }

    public void update(Resume resume) {
        Resume r;
        String uuid = resume.getUuid();
        if ((r = get(uuid)) != null) {
            r.setUuid("updated1");
        }
        else {
            System.out.println("ERROR: Resume "+uuid+" doesn't exist!");}
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
