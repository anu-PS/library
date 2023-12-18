package ps.anu;

public enum Genre {
    Fiction("Fiction", 1),
    SciFiction("SciFiction", 2),
    Philosophy("Philosophy", 3),
    Mystery("Mystery", 4),
    Thriller("Thriller", 5),
    Romance("Romance", 6),
    Westerns("Westerns", 7),
    Dystopian("Dystopian", 8),
    Other("Other", 9);

    private String genreName;
    private int genre;

    Genre(String genreName, int genre) {
        this.genreName = genreName;
        this.genre = genre;
    }

    public String getGenreName() {
        return genreName;
    }

    public int getGenre() {
        return genre;
    }
}


