package ccuiot.iotc.aspect;

public class CurrencyUtils {
    public static Integer getYuan2Fen(String yuan) {
        Double v = Double.parseDouble(yuan);
        v *= 100;
        return v.intValue();
    }

    public static String getFen2YuanWithPoint(Integer fen) {
        Double v = new Double(fen);
        v /= 100;
        return v.toString();
    }

    public static void main(String[] args) {
        System.out.println(getYuan2Fen("1.23"));
        System.out.println(getFen2YuanWithPoint(1234));
    }
}
