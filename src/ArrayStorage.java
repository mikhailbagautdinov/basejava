import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
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
        for (i = 0; i < length; i++) {
            if (storage[i].uuid.equals(uuid))
                break;
        }
        for (int j = i; j < length; j++) {
            storage[j] = storage[j + 1];
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int count = 0;
        for (Resume r : storage
                ) {
            if (r == null)
                count++;
        }
        Resume[] output = new Resume[storage.length - count];
        int j = 0;
        for (Resume resume : storage
                ) {
            if (resume != null)
                output[j++] = resume;
        }

        return output;
    }

    int size() {
        return getAll().length;
    }
}
