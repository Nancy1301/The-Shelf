package com.example.android.theshelf;

public class Book
{
    private String Title;
    private int BookId;
    private String Author_Name;

    public String getTitle() {
        return Title;
    }
    public void setTitle(String name) {Title = name;}
    public int getBookId() {
        return BookId;
    }
    public void setBookId(int id) {
        BookId = id;
    }
    public String getAuthor_Name() {
        return Author_Name;
    }
    public void setAuthor_Name(String name) {Author_Name = name;}
}
