package eu.cartograph.androidapi;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedList;
import java.util.List;

public class MapListResponse implements Parcelable {
    final static int CURRENT_VERSION = 1;

    public static class MapEntry {
        public MapEntry(String key, String name, String description) throws NullPointerException {

            if (key == null || name == null || description == null) {
                throw new NullPointerException();
            }

            this.key = key;
            this.name = name;
            this.description = description;
        }
        public String key; // a key identifying the map within the service
        public String name;
        public String description;

        // Bounding box of the map region
        public double boundingBoxTop = 90,
                boundingBoxLeft = -180,
                boundingBoxBottom = -90,
                boundingBoxRight = 180; //lat,lon,lat,lon
    }

    private List<MapEntry> mEntries = new LinkedList<>();

    public List<MapEntry> getEntries() {
        return mEntries;
    }

    public void addEntry(MapEntry entry) {
        mEntries.add(entry);
    }

    public MapListResponse() {

    }

    public MapListResponse(Parcel in) {
        int currentVersion = in.readInt();
        int cnt = in.readInt();
        for (int i=0; i<cnt; i++) {
            MapEntry entry = new MapEntry(in.readString(), in.readString(), in.readString());
            entry.boundingBoxTop = in.readDouble();
            entry.boundingBoxLeft = in.readDouble();
            entry.boundingBoxBottom = in.readDouble();
            entry.boundingBoxRight = in.readDouble();
            mEntries.add(entry);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(CURRENT_VERSION);
        dest.writeInt(mEntries.size());
        for (MapEntry e: mEntries) {
            dest.writeString(e.key);
            dest.writeString(e.name);
            dest.writeString(e.description);
            dest.writeDouble(e.boundingBoxTop);
            dest.writeDouble(e.boundingBoxLeft);
            dest.writeDouble(e.boundingBoxBottom);
            dest.writeDouble(e.boundingBoxRight);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MapListResponse> CREATOR = new Creator<MapListResponse>() {
        @Override
        public MapListResponse createFromParcel(Parcel in) {
            return new MapListResponse(in);
        }

        @Override
        public MapListResponse[] newArray(int size) {
            return new MapListResponse[size];
        }
    };
}
