package model;

import java.io.Serializable;
import java.util.Arrays;

public class Library implements Serializable {

    private static final int INITIAL_CAPACITY = 1;
    private int publicationsNumber = 0;
    private Publication[] publications = new Publication[INITIAL_CAPACITY];

    public Publication[] getPublications() {
        Publication[] result = new Publication[publicationsNumber];
        for (int i = 0; i < result.length; i++) {
            result[i] = publications[i];
        }
        return result;
    }

    public void addPublication(Publication publication) {
        if (publicationsNumber >= publications.length) {
            publications = Arrays.copyOf(this.publications, publications.length * 2);
        }
        publications[publicationsNumber] = publication;
        publicationsNumber++;
    }

    public boolean removePublication(Publication publication) {
        final int notFound = -1;
        int found = notFound;

        for (int i = 0; i < publications.length && found == notFound; i++) {
            if (publication.equals(publications[i])) {
                found = i;
            } else {
                i++;
            }
        }
        if (found != notFound) {
            System.arraycopy(publications, found + 1, publications, found, publications.length - found - 1);
            publicationsNumber--;
            publications[publicationsNumber] = null;
        }
        return found != notFound;
    }
}



