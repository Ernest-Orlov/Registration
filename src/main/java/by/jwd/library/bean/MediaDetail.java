package by.jwd.library.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MediaDetail implements Serializable {
    private static final long serialVersionUID = 7759631852833962220L;

    private int mediaID;

    private int totalCopies;
    private int availableCopies;
    private int reservedCopies;
    private int loanedCopies;

    private double price;

    private String title;
    private String summary;
    private String iSBN;
    private String picture;
    private String publisher;
    private String format;
    private String language;
    private String restriction;

    private List<Author> authors;
    private List<Genre> genres;

    public MediaDetail() {
        mediaID = 1;
        totalCopies = 0;
        availableCopies = 0;
        reservedCopies = 0;
        loanedCopies = 0;
        price = 0;

        title = "NoTitle";
        summary = "NoSummary";
        iSBN = "NoISBN";
        picture = "NoPicture";
        publisher = "NoPublisher";
        format = "NoMaterialType";
        language = "NoLanguage";
        restriction = "NoRestriction";

        authors = new ArrayList<>();
        genres = new ArrayList<>();
    }

    public MediaDetail(int mediaID,
                       int totalCopies, int availableCopies, int reservedCopies, int loanedCopies,
                       double price,
                       String title, String summary, String iSBN, String picture,
                       String publisher, String format, String language, String restriction,
                       List<Author> authors, List<Genre> genres) {

        this.mediaID = mediaID;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.reservedCopies = reservedCopies;
        this.loanedCopies = loanedCopies;
        this.price = price;

        this.title = title;
        this.summary = summary;
        this.iSBN = iSBN;
        this.picture = picture;
        this.publisher = publisher;
        this.format = format;
        this.language = language;
        this.restriction = restriction;

        this.authors = authors;
        this.genres = genres;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public int getReservedCopies() {
        return reservedCopies;
    }

    public void setReservedCopies(int reservedCopies) {
        this.reservedCopies = reservedCopies;
    }

    public int getLoanedCopies() {
        return loanedCopies;
    }

    public void setLoanedCopies(int loanedCopies) {
        this.loanedCopies = loanedCopies;
    }

    public String getiSBN() {
        return iSBN;
    }

    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }

    public int getMediaID() {
        return mediaID;
    }

    public void setMediaID(int mediaID) {
        this.mediaID = mediaID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if ((obj.getClass() != this.getClass())) return false;
        MediaDetail that = (MediaDetail) obj;
        return mediaID == that.mediaID &&
                Double.compare(that.price, price) == 0 &&
                title.equals(that.title) &&
                summary.equals(that.summary) &&
                Objects.equals(iSBN, that.iSBN) &&
                Objects.equals(picture, that.picture) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(format, that.format) &&
                Objects.equals(language, that.language) &&
                Objects.equals(restriction, that.restriction) &&
                Objects.equals(authors, that.authors) &&
                Objects.equals(genres, that.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediaID, price, title, summary, iSBN, picture, publisher, format, language, restriction, authors, genres);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode()) +
                "MediaDetail{" +
                "mediaID=" + mediaID +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                ", reservedCopies=" + reservedCopies +
                ", loanedCopies=" + loanedCopies +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", iSBN='" + iSBN + '\'' +
                ", picture='" + picture + '\'' +
                ", publisher='" + publisher + '\'' +
                ", materialType='" + format + '\'' +
                ", language='" + language + '\'' +
                ", restriction='" + restriction + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                '}';
    }
}
