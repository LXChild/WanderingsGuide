package com.cnsoft.lxchild.search;

/**
 * Created by LXChild on 2015/5/4.
 */
public class PlaceEntity {
    private int placeTypeCode;
    private int placeNum;
    private int placeIcon;
    private String placeName;
    private int[] placePos;

    public int getPlaceTypeCode() {
        return placeTypeCode;
    }

    public void setPlaceTypeCode(int placeTypeCode) {
        this.placeTypeCode = placeTypeCode;
    }

    public int getPlaceNum() {
        return placeNum;
    }

    public void setPlaceNum(int placeNum) {
        this.placeNum = placeNum;
    }

    public int getPlaceIcon() {
        return placeIcon;
    }

    public void setPlaceIcon(int placeIcon) {
        this.placeIcon = placeIcon;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int[] getPlacePos() {
        return placePos;
    }

    public void setPlacePos(int[] placePos) {
        this.placePos = placePos;
    }
}
