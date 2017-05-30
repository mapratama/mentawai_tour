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

public class Photo extends RealmObject {

    @PrimaryKey
    private int id;

    private String name, photoUrl;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RealmList<Photo> fromJSONArray(JSONArray response, Realm realm) throws JSONException {
        RealmList<Photo> banners = new RealmList<>();
        for (int i = 0; i < response.length(); i++)
            banners.add(fromJSON(response.getJSONObject(i), realm));

        return banners;
    }

    public static Photo fromJSON(JSONObject response, Realm realm) throws JSONException {
        Photo photo = new Photo();
        photo.setId(response.getInt("id"));
        photo.setName(response.getString("name"));
        photo.setPhotoUrl(response.getString("photo_url"));

        realm.copyToRealmOrUpdate(photo);
        return photo;
    }

    public static Photo get(int id) {
        return Realm.getDefaultInstance().where(Photo.class).equalTo("id", id).findFirst();
    }
}
