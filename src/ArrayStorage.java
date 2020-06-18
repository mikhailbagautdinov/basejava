/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int count = size();
        for (int i = 0; i < count; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        int length = size();
        if (length == 0)
            return null;
        for (int i = 0; i < length; i++) {
            Resume resume = storage[i];
            if (resume.uuid.equals(uuid))
                return resume;
        }
        return null;
    }

    void delete(String uuid) {
        int length = size();
        int i;
        boolean flag = false;
        for (i = 0; i < length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                flag = true;
                break;
            }
        }
        for (int j = i; j < length - 1; j++) {
            storage[j] = storage[j + 1];
        }
        if (flag)
            storage[length - 1] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int count = 0;
        for (Resume r : storage) {
            if (r == null)
                count++;
        }
        Resume[] output = new Resume[storage.length - count];
        int j = 0;
        for (Resume resume : storage) {
            if (resume != null)
                output[j++] = resume;
        }

        return output;
    }

    int size() {
        int count = 0;
        for (Resume r : storage) {
            if (r != null)
                count++;
        }
        return count;
    }
}
