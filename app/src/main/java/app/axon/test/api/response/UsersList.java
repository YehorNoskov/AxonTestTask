
package app.axon.test.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersList {

    @SerializedName("results")
    @Expose
    private List<UserResp> results = null;
    @SerializedName("info")
    @Expose
    private Info info;

    public List<UserResp> getResults() {
        return results;
    }

    public void setResults(List<UserResp> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
