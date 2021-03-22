
import java.util.List;

public class MyMath {

	public static float Sum(List<Float> values){
    float sumResult = 0;
    for(float value : values)
    sumResult += value;

    return sumResult;
   }
}