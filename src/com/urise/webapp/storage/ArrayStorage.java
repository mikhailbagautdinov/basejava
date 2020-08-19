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

    public void save(Resume resume) {
        if (size < storage.length) {
            String uuid = resume.getUuid();
            int index = present(uuid);
            if (index != -1) {
                System.out.println("ERROR: Resume " + uuid + " already exists!");
            } else {
                storage[size++] = resume;
            }
        } else {
            System.out.println("ERROR: ArrayStorage overflow!");
        }
    }

    public Resume get(String uuid) {
        int index = present(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("ERROR: Resume " + uuid + " doesn't exist!");
        return null;
    }

    public void delete(String uuid) {
        int index = present(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[--size] = null;
        } else {
            System.out.println("ERROR: Resume " + uuid + " doesn't exist!");
        }
    }

    //update resume in storage
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = present(uuid);
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("ERROR: Resume " + uuid + " doesn't exist!");
        }
    }

    private int present(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
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
