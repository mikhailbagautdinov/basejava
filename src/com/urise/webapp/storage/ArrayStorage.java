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
            if (present(uuid) == -1) {
                storage[size++] = resume;
            } else {
                System.out.println("ERROR: Resume " + uuid + " already exists!");
            }
        } else {
            System.out.println("ERROR: ArrayStorage overflow!");
        }
    }

    public Resume get(String uuid) {
        int check = present(uuid);
        if (check == -1) {
            System.out.println("ERROR: Resume " + uuid + " doesn't exist!");
            return null;
        }
            return storage[check];
    }

    public void delete(String uuid) {
        int check = present(uuid);
        if (check != -1) {
            storage[check] = storage[size - 1];
            storage[--size] = null;
        } else {
            System.out.println("ERROR: Resume " + uuid + " doesn't exist!");
        }
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int check = present(uuid);
        if (check != -1) {
            storage[check] = resume;//update resume in storage
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
