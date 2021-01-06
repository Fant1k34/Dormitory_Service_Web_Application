package com.dormitory.app.helpful;

public class PictureMarket {
    private int idMark;
    private int idPicture;
    private byte[] bytes;
    private String path;
    private String name;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIdMark() {
        return idMark;
    }

    public void setIdMark(int idMark) {
        this.idMark = idMark;
    }

    public int getIdPicture() {
        return idPicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdPicture(int idPicture) {
        this.name = String.valueOf(idPicture);
        this.idPicture = idPicture;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
