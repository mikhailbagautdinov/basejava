package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        if (size < 10000) {
            if (get(r.getUuid()) == null) {
                storage[size++] = r;
            }
            else {
                System.out.println("ERROR: Resume already exists!");
            }
        } else {
            System.out.println("ERROR: ArrayStorage overflow!");
        }
    }

    public Resume get(String uuid) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String methodName=stackTrace[2].getMethodName();
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                return storage[i];
            }
        }
        if(methodName=="main")
            System.out.println("ERROR: Resume doesn't exist!");
        return null;
    }

    public void delete(String uuid) {
        if (get(uuid)!=null) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                storage[i]=storage[size-1];
                storage[--size]=null;
                break;
            }
        }
    }
        else
            System.out.println("ERROR: Resume doesn't exist!");
    }

    public void update(Resume resume) {
        Resume r;
        if (Arrays.asList(storage).contains(resume)) {
            r= get(resume.getUuid());
            r.setUuid("updated1");
        } else {
            System.out.println("ERROR: Resume doesn't exist!");
        }
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
