import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size++] = r;
    }

    Resume get(String uuid) {
        if (size == 0)
            return null;
        for (int i = 0; i < size; i++) {
            Resume resume = storage[i];
            if (resume.uuid.equals(uuid))
                return resume;
        }
        return null;
    }

    void delete(String uuid) {
        int i;
        boolean flag = false;
        for (i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                flag = true;
                break;
            }
        }
        for (int j = i; j < size - 1; j++) {
            storage[j] = storage[j + 1];
        }
        if (flag) {
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
    }
}
