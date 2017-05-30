package mobile.android.mentawaitour.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by angga on 03/11/16.
 */

public class Content extends RealmObject {

    @PrimaryKey
    private int id;

    private String name, key, description;
    private RealmList<Photo> photos;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RealmList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(RealmList<Photo> photos) {
        this.photos = photos;
    }

    public static RealmList<Content> fromJSONArray(JSONArray response) throws JSONException {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmList<Content> contents = new RealmList<>();
        for (int i = 0; i < response.length(); i++)
            contents.add(fromJSON(response.getJSONObject(i), realm));

        realm.commitTransaction();
        return contents;
    }

    public static Content fromJSON(JSONObject response, Realm realm) throws JSONException {
        Content content = new Content();

        content.setId(response.getInt("id"));
        content.setName(response.getString("name"));
        content.setKey(response.getString("key"));
        content.setDescription(response.getString("description"));
        content.setPhotos(Photo.fromJSONArray(response.getJSONArray("photos"), realm));

        realm.copyToRealmOrUpdate(content);
        return content;
    }

    public static Content getByKey(String key) {
        return Realm.getDefaultInstance().where(Content.class).equalTo("key", key).findFirst();
    }

    public static boolean hasData() {
        long count = Realm.getDefaultInstance().where(Content.class).count();
        if (count == 0) return false;
        return true;
    }
}
