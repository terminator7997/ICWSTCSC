package in.bvm.college.icwstcsc;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QuestionsSpreadsheetWebService {
    @POST("1FAIpQLSf1guJIumOVb8wGp20eMFsG6Fl38xqmMZfk19ICFdedN0uITw/formResponse")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(
            @Field("entry.52831270") String pid,
            @Field("entry.648143560") String name,
            @Field("entry.404738240") String feedback
    );
}
