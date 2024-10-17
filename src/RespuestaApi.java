import com.google.gson.annotations.SerializedName;
import java.util.Map;


public class RespuestaApi {
    @SerializedName("conversion_rates")
    private Map<String, Double> tasasDeConversion;

    public Map<String, Double> getTasasDeConversion() {
        return tasasDeConversion;
    }

    public void setTasasDeConversion(Map<String, Double> tasasDeConversion) {
        this.tasasDeConversion = tasasDeConversion;
    }
}
