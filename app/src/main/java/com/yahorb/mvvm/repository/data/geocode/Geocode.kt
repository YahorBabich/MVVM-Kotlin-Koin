package com.yahorb.mvvm.repository.data.geocode

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Geocode {

    /**
     * @return The results
     */
    /**
     * @param results The results
     */
    @SerializedName("results")
    @Expose
    var results: List<Result> = ArrayList()
    /**
     * @return The status
     */
    /**
     * @param status The status
     */
    @SerializedName("status")
    @Expose
    var status: String? = null

}
